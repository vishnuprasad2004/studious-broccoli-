/**
 * 1. create 4 bins at the 4 corners
 * 2. maintain score of 4 players
 * 3. create the player logic
 */

const canvas = document.querySelector('#canvas');
const c = canvas.getContext('2d');
canvas.width = innerHeight;
canvas.height = innerHeight;


const NUMBER_OF_PIECES = 7


const mouse = {
    x: innerWidth / 2,
    y: innerHeight / 2
}

// Event Listeners
addEventListener('mousemove', (event) => {
    mouse.x = event.clientX;
    mouse.y = event.clientY;
})

addEventListener('resize', () => {
    canvas.width = innerHeight;
    canvas.height = innerHeight;
    location.reload()

})

/**
 * Rotates coordinate system for velocities
 *
 * Takes velocities and alters them as if the coordinate system they're on was rotated
 *
 * @param  Object | velocity | The velocity of an individual particle
 * @param  Float  | angle    | The angle of collision between two objects in radians
 * @return Object | The altered x and y velocities after the coordinate system has been rotated
 */

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
 *
 * @param  Object | particle      | A particle object with x and y coordinates, plus velocity
 * @param  Object | otherParticle | A particle object with x and y coordinates, plus velocity
 * @return Null | Does not return a value
 */

function resolveCollision(particle, otherParticle) {
    const xVelocityDiff = particle.velocity.x - otherParticle.velocity.x;
    const yVelocityDiff = particle.velocity.y - otherParticle.velocity.y;

    const xDist = otherParticle.x - particle.x;
    const yDist = otherParticle.y - particle.y;

    // Prevent accidental overlap of particles
    if (xVelocityDiff * xDist + yVelocityDiff * yDist >= 0) {

        // Grab angle between the two colliding particles
        const angle = -Math.atan2(otherParticle.y - particle.y, otherParticle.x - particle.x);

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
        particle.velocity.x = vFinal1.x;
        particle.velocity.y = vFinal1.y;

        otherParticle.velocity.x = vFinal2.x;
        otherParticle.velocity.y = vFinal2.y;
    }
}

function randomNumRange(min, max) {
    return Math.floor(Math.random()*(max - min + 1) + min);
}

/**
 * returns distance between 2 points coordinates (x1,y1) and (x2,y2)
 * @returns number | distance between 2 points 
 */
function distance(x1, y1, x2, y2) {
    return Math.sqrt( Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2) );
}

class Piece {
    constructor(x, y, radius, color) {
        this.x = x;
        this.y = y;
        // this.velocity = {
        //     x: (Math.random() - 0.5)*5,
        //     y: (Math.random() - 0.5) *5
        // }
        
        this.velocity = {
            x: 0,
            y: 0
        }

        this.radius = radius;
        this.color = color;
        this.mass = 1;
        // this.opacity = 0;
    }

    draw() {
        c.beginPath();
        c.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
        c.fillStyle = this.color;
        c.fill();
        c.strokeStyle = "#000";
        c.stroke();
        c.closePath();
    }

    update(particles) {
        this.draw();

        for(let i=0; i< particles.length; i++) {
            if(this === particles[i]) {
                continue;
            }
            if(distance(this.x, this.y, particles[i].x, particles[i].y) - this.radius*2 < 0) {
                // console.log("%cCOLLIDED","color:red;");
                resolveCollision(this,particles[i]);
            }
            // if(distance(this.x, this.y, mouse.x, mouse.y) < 100) {
            //     // console.log("%cCOLLIDED","color:red;");
            //     this.opacity = 1;
            // }else {
            //     this.opacity = 0;
            // }
        }
        if(this.x-this.radius <= 0 || this.radius+this.x >= canvas.width) {
            this.velocity.x = -this.velocity.x;
        }
        
        if(this.y-this.radius <= 0 || this.radius+this.y >= canvas.height) {
            this.velocity.y = -this.velocity.y;
        }
        this.x += this.velocity.x;
        this.y += this.velocity.y;
        
    }

}

class Player {
    constructor(x,y) {
        this.x = x
        this.y = y
        this.color = "#0000ff"
        this.radius = 30
        this.score = 0
        this.velocity = {
            x:0,
            y:0
        }
    }

    draw() {
        c.beginPath();
        c.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
        c.fillStyle = this.color;
        c.fill();
        c.strokeStyle = "#000";
        c.stroke();
        c.closePath();
    }

    update(particles) {
        this.draw();

        for(let i=0; i< particles.length; i++) {
            if(this === particles[i]) {
                continue;
            }
            if(distance(this.x, this.y, particles[i].x, particles[i].y) - this.radius*2 < 0) {
                // console.log("%cCOLLIDED","color:red;");
                resolveCollision(this,particles[i]);
            }
            // if(distance(this.x, this.y, mouse.x, mouse.y) < 100) {
            //     // console.log("%cCOLLIDED","color:red;");
            //     this.opacity = 1;
            // }else {
            //     this.opacity = 0;
            // }
        }
        if(this.x-this.radius <= 0 || this.radius+this.x >= canvas.width) {
            this.velocity.x = -this.velocity.x;
        }
        
        if(this.y-this.radius <= 0 || this.radius+this.y >= canvas.height) {
            this.velocity.y = -this.velocity.y;
        }
        this.x += this.velocity.x;
        this.y += this.velocity.y;
        
    }
}

let particles = []
let score = 0

function init() {
    // the white pieces
    let radius = 20
    for (let i = 0; i < NUMBER_OF_PIECES; i++) {
        
        let x = randomNumRange(radius*2, canvas.width-radius*2);
        let y = randomNumRange(radius*2, canvas.height-radius*2);
        
        // making sure that particles are not over lapping
        if(i != 0) {
            for(let j=0; j<particles.length; j++) {
                if( distance(x, y, particles[j].x, particles[j].y) - radius*2 < 0) {
                    x = randomNumRange(radius*2, canvas.width-radius*2);
                    // y = Math.random()*innerWidth;
                    y = randomNumRange(radius*2, canvas.width-radius*2);
                    j = -1
                }
            }
        }

        particles.push(new Piece(x, y, radius, "#ddd"))
        
    }
    // the black pieces
    for (let i = 0; i < NUMBER_OF_PIECES; i++) {
        let x = randomNumRange(radius*2, canvas.width-radius*2);
        let y = randomNumRange(radius*2, canvas.height-radius*2);

        // making sure that particles are not over lapping
        if(i != 0) {
            for(let j=0; j<particles.length; j++) {
                if( distance(x, y, particles[j].x, particles[j].y) - radius*2 < 0) {
                    x = randomNumRange(radius*2, canvas.width-radius*2);
                    // y = Math.random()*innerWidth;
                    y = randomNumRange(radius*2, canvas.width-radius*2);
                    j = -1
                }
            }
        }

        particles.push(new Piece(x, y, radius, "#111"))
        
    }


    // the red one
    particles.push(new Piece(randomNumRange(radius*2, canvas.width-radius*2),randomNumRange(radius*2, canvas.height-radius*2), radius, "#ff0055"))
    particles.push(new Player(100,100))
}


// Animation Loop
function animate() {
    requestAnimationFrame(animate)
    c.clearRect(0, 0, canvas.width, canvas.height)
    c.drawImage(document.getElementById("bg"),0,0,canvas.width, canvas.height)
    particles.forEach(particle => {
        particle.update(particles);
    });
}

init()
animate()