const canvas = document.getElementById('canvas')
const c = canvas.getContext('2d')
const colorsCombo2 = ['#e5243f','#f65c51','#f7b15c','#54c6be','#2f5755']; // muted mine
canvas.width = innerWidth
canvas.height = innerHeight

let mouse = {
    x: undefined,
    y: undefined
}
let animation
let acceleration = 1
let retardation = 0.7 // represents loss of energy
let hold = false
addEventListener('resize',() => {
    location.reload()
})

addEventListener('mousemove',(event) => {
    mouse.x = event.clientX
    mouse.y = event.clientY
})
addEventListener('mousedown',() => {
    hold = true
}) 
addEventListener('mouseup',() => {
    hold = false
}) 

class Particle {
    static radius = 0.039 * canvas.height
    constructor(position) {
        this.position = {
            x: position.x,
            y: position.y
        }
        this.velocity = {
            x:0,
            y:5
        }
        this.radius = Particle.radius
        this.mass = 1
        this.color = colorsCombo2[Math.floor(Math.random()*colorsCombo2.length)]
    }
    draw() {
        c.beginPath()
        c.arc(this.position.x, this.position.y, this.radius, 0, Math.PI*2, true)
        c.fillStyle = this.color
        c.fill()
        c.closePath()
    }
    update() {
        for(let i=0; i< particles.length; i++) {
            if(this === particles[i]) {
                continue;
            }
            if(distance(this.position.x, this.position.y, particles[i].position.x, particles[i].position.y) - this.radius*2 < 0) {
                resolveCollision(this,particles[i]);
            }
        }
        console.log(this.velocity)
        if(this.position.y + this.radius + this.velocity.y >= canvas.height-50 || this.position.y - this.radius < 0) {
            this.velocity.y = - Math.floor(this.velocity.y * retardation) 
        }else {
            this.velocity.y += acceleration
        }
        if(this.position.x + this.radius + this.velocity.x > canvas.width || this.position.x - this.radius < 50) {
            this.velocity.x = -Math.floor(this.velocity.x * retardation)
        }else {
            // this.velocity.x += acceleration*0.6
        }
        this.position.x += this.velocity.x + 1
        this.position.y += this.velocity.y
        this.draw()
        
    }
}

function randomNumRange(min, max) {
    return Math.floor(Math.random()*(max - min + 1) + min);
}

function distance(x1, y1, x2, y2) {
    return Math.sqrt(Math.pow(x2-x1,2)+ Math.pow(y2-y1,2))
}

function rotate(velocity, angle) {
    const rotatedVelocities = {
        x: velocity.x * Math.cos(angle) - velocity.y * Math.sin(angle),
        y: velocity.x * Math.sin(angle) + velocity.y * Math.cos(angle)
    };
    return rotatedVelocities;
}

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
        particle.velocity.x = Math.floor(vFinal1.x * retardation);
        particle.velocity.y = Math.floor(vFinal1.y * retardation);

        otherParticle.velocity.x = Math.floor(vFinal2.x * retardation);
        otherParticle.velocity.y = Math.floor(vFinal2.y * retardation);
    }
}

let particles = [];

for(let i=0; i<60; i++) {
    let radius = Particle.radius
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
    }))
}
function animate() {
    animation = requestAnimationFrame(animate)
    c.rect(0, 0, canvas.width, canvas.height)
    c.fillStyle = 'white'
    c.fill()
    // for(let i=0; i<particles.length; i++) {
    //     let distanceBtwParticles = distance(mouse.x, mouse.y, particles[i].position.x, particles[i].position.y)
    //     if(distanceBtwParticles <= Particle.radius && hold) {
    //         particles[i].position.x = Math.cos(distanceBtwParticles) + mouse.x 
    //         particles[i].position.y = Math.sin(distanceBtwParticles) + mouse.y
    //     }else {

    //     }
    // }
    particles.forEach(particle => {
        let distanceBtwParticles = distance(mouse.x, mouse.y, particle.position.x, particle.position.y)
        let angle = Math.asin((particle.position.y-mouse.y)/distanceBtwParticles)
        if(distanceBtwParticles <= Particle.radius && hold) {
            particle.position.x = Math.cos(distanceBtwParticles) + mouse.x 
            particle.position.y = Math.sin(distanceBtwParticles) + mouse.y
        }else {

        }
        particle.update()
    })

}
animate()