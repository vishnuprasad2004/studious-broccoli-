// Declarations
const canvas = document.getElementById('canvas')
const c = canvas.getContext('2d')
const colorsCombo2 = ['#e5243f','#f65c51','#f7b15c','#54c6be','#2f5755']; // muted colors
let animation
canvas.width = innerWidth
canvas.height = innerHeight
let mouse = {
    x: canvas.width/2,
    y: canvas.height/2
}
let mouseDown = false
let loss = 0.88
// Event Listeners
addEventListener('resize',() => {
    location.reload()
})

addEventListener('mousemove',(event) => {
    mouse.x = event.clientX
    mouse.y = event.clientY
})

addEventListener('mousedown',() => {
    mouseDown = true
})
addEventListener('mouseup',() => {
    mouseDown = false
})


// Classes
class Particle {
    static radius = 0.002 * canvas.height
    constructor(position={x: Math.random()*canvas.width, y: Math.random()*canvas.height},mass,radius) {
        this.position = {
            x: position.x,
            y: position.y
        }
        this.velocity = {
            x:0,
            y:0
        }
        this.acceleration = {
            x:0,
            y:0
        }
        this.fx = 0
        this.fy = 0
        this.mass = mass
        this.radius = radius
        this.color = /*colorsCombo2[Math.floor(Math.random()*colorsCombo2.length)]*/ 'black'
    }
    draw() {
        c.beginPath()
        c.arc(this.position.x, this.position.y, this.radius, 0, Math.PI * 2, true)
        c.fillStyle = this.color
        c.fill()
        c.closePath()
    }
    update() {
        // Head-on Collision
        for(let i=0; i<particles.length; i++) {
            if(particles[i] === this) {
                continue
            }
            if(distance(this.position.x, this.position.y, particles[i].position.x, particles[i].position.y) <= this.radius + particles[i].radius ) {
                resolveCollision2(this, particles[i])
            }
            resolveGravity(this, particles[i])
        }
        // Horizontal Collision
        if(this.position.x + this.radius >= canvas.width - 0 || this.position.x - this.radius <= 0) {
            this.velocity.x = - Math.floor(this.velocity.x * loss)
            this.acceleration.x = - this.acceleration.x
        }else {
            this.velocity.x += this.acceleration.x
        }
        // Vertical Collision
        if(this.position.y + this.radius >= canvas.height - 0 || this.position.y - this.radius <= 0) {
            this.velocity.y = - Math.floor(this.velocity.y * loss)
            this.acceleration.x = -this.acceleration.x
        }else {
            this.velocity.y += this.acceleration.y
        }

        this.position.x += Math.floor(this.velocity.x)
        this.position.y += Math.floor(this.velocity.y)
        this.draw()
        
    }
}

// Functions
function randomNumRange(min, max) {
    return Math.floor(Math.random()*(max - min + 1) + min);
}

function distance(x1, y1, x2, y2) {
    // Distance Formula from Coordinate Geometry
    return Math.sqrt(Math.pow(x2-x1,2)+ Math.pow(y2-y1,2))
}

function rotate(velocity, angle) {
    const rotatedVelocities = {
        x: velocity.x * Math.cos(angle) - velocity.y * Math.sin(angle),
        y: velocity.x * Math.sin(angle) + velocity.y * Math.cos(angle)
    };
    return rotatedVelocities;
}

/**
 * Swaps out two colliding particles' x and y velocities after running through
 * an elastic collision reaction equation
 */
function resolveCollision(particle, otherParticle) {
    const xVelocityDiff = particle.velocity.x - otherParticle.velocity.x;
    const yVelocityDiff = particle.velocity.y - otherParticle.velocity.y;

    const xDist = otherParticle.position.x - particle.position.x;
    const yDist = otherParticle.position.y - particle.position.y;

    // Prevent accidental overlap of particles
    if (xVelocityDiff * xDist + yVelocityDiff * yDist >= 0) {

        // Grab angle between the two colliding particles
        const angle = -Math.atan2(otherParticle.position.y - particle.position.y, otherParticle.position.x - particle.position.x);

        // Store mass in var for better readability in collision equation
        const m1 = particle.mass;
        const m2 = otherParticle.mass;

        // Velocity before equation
        const u1 = rotate(particle.velocity, angle);
        const u2 = rotate(otherParticle.velocity, angle);

        // Velocity after 1d collision equation
        const v1 = { x: u1.x * (m1 - m2) / (m1 + m2) + u2.x * 2 * m2 / (m1 + m2), y: u1.y };
        const v2 = { x: u2.x * (m1 - m2) / (m1 + m2) + u1.x * 2 * m2 / (m1 + m2), y: u2.y };

        // Final velocity after rotating axis back to original location
        const vFinal1 = rotate(v1, -angle);
        const vFinal2 = rotate(v2, -angle);

        // Swap particle velocities for realistic bounce effect
        particle.velocity.x = Math.floor(vFinal1.x * loss * 0.2);
        particle.velocity.y = Math.floor(vFinal1.y * loss * 0.2);

        otherParticle.velocity.x = Math.floor(vFinal2.x * loss );
        otherParticle.velocity.y = Math.floor(vFinal2.y * loss );
    }
}

function resolveCollision2(particle, otherParticle) {
    // function resolveCollision(info) {  // "info" is a Collision object from above
    //     let nx = info.dx /info.d;  // Compute iegen vectors
    //     let ny = info.dy /info.d;
    //     let s = info.o1.r + info.o2.r - info.d;
    //     info.o1.x -= nx * s/2;  // Move first object by half of collision size
    //     info.o1.y -= ny * s/2;
    //     info.o2.x += nx * s/2;  // Move other object by half of collision size in opposite direction
    //     info.o2.y += ny * s/2;
    // }
    let dx = (otherParticle.position.x - particle.position.x)
    let dy = (otherParticle.position.y - particle.position.y)

    let d = distance(particle.position.x, particle.position.y, otherParticle.position.x, otherParticle.position.y)

    let nx = dx / d
    let ny = dy / d

    let s = particle.radius + otherParticle.radius - d
    particle.position.x -= nx * s/2
    particle.position.y -= ny * s/2
    otherParticle.position.x += nx * s/2
    otherParticle.position.y += ny * s/2


}

/**
 * Calculates gravitational forces between 2 particles
 */
function resolveGravity(particle, otherParticle) {
    particle.fx = 0
    particle.fy = 0
    otherParticle.fx = 0
    otherParticle.fy = 0
    // getting the masses of both particles
    const m1 = particle.mass
    const m2 = otherParticle.mass
    // calculating the x and y distances 
    let dx = (otherParticle.position.x - particle.position.x)
    let dy = (otherParticle.position.y - particle.position.y)
    // calculating distance between the 2 particles
    let d = distance(particle.position.x, particle.position.y, otherParticle.position.x, otherParticle.position.y)
    if(d < 0) {
        d = 1
    }
    // Newton's Law of Gravitation
    let f = ( 1 * m1 * m2 ) / Math.pow(d, 2)
    // calculating x and y components of the force
    let fx = f * dx * 0.1
    let fy = f * dy * 0.1
    // calculating acceleration for both particles
    // f = m * a => a = f / m 
    particle.acceleration.x += fx / m1 
    particle.acceleration.y += fy / m1

    otherParticle.acceleration.x += fx / m2
    otherParticle.acceleration.y += fy / m2

}

async function addParticle(x, y) {
    let radius = 1
    particles.push(new Particle({x:mouse.x,y:mouse.y},radius/(1.5*Particle.radius),radius))
    while(mouseDown) {
        particles[particles.length - 1].radius = radius
        radius++
    }
}


// Implementations 
let particles = []
particles.push(new Particle({x:canvas.width/2, y:canvas.height/2}, 35, Particle.radius * 30 * 1.5))
for(let i=0; i<10; i++) {
    // let mass = Math.random() * 9 + 1
    let mass = 5
    let radius = Particle.radius * mass * 1.5
    let x = randomNumRange(radius*2, canvas.width-radius*2)
    let y = randomNumRange(radius*2, canvas.height-radius*2)
    if(i != 0) {
        for(let j=0; j< particles.length; j++) {
            if(distance(x, y, particles[j].position.x, particles[j].position.y) - radius*2 < 0) {
                x = randomNumRange(radius*2, canvas.width-radius*2);
                y = randomNumRange(radius*2, canvas.width-radius*2);
                j = -1
            }
        }
    }
    particles.push(new Particle({
        x: x,
        y: y
    }, mass, radius))
}

function animate() {
    animation = requestAnimationFrame(animate)
    c.rect(0, 0, canvas.width, canvas.height)
    c.fillStyle = '#ffffff22' // Background Color
    c.fill()
    
    if(mouseDown) {
        addParticle(mouse.x, mouse.y)
    }
    particles[0].draw()
    particles[0].position.x = canvas.width/2
    particles[0].position.y = canvas.height/2
    for(let i=1;i<particles.length; i++) {
        particles[i].update()
    }
    // particles.forEach(particle => {
    //     particle.update()
    // })

}
animate()