const canvas = document.getElementById('canvas')
const c = canvas.getContext('2d')
const colorsCombo2 = ['#e5243f', '#f65c51', '#f7b15c', '#54c6be', '#2f5755']; // muted colors
const spatulaImage = document.getElementById("spatula")
const flyImage = document.getElementById("fly")
let animation
canvas.width = innerWidth
canvas.height = innerHeight
let mouse = {
    x: undefined,
    y: undefined
}

// Event Listeners
addEventListener('resize', () => {
    location.reload()
})

addEventListener('mousemove', (event) => {
    mouse.x = event.clientX
    mouse.y = event.clientY
})

addEventListener("click", () => {
    console.log("Click");
    flies.forEach((fly, i) => {
        // if() {
        //     flies.splice(i,1);
        //     console.log("Got it");
        // }
    })
})

// Functions
function randomNumRange(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}

function distance(x1, y1, x2, y2) {
    // Distance Formula from Coordinate Geometry
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))
}


// Classes
class Spatula {
    static radius = 0.039 * canvas.height
    constructor(position) {
        this.position = {
            x: position.x,
            y: position.y
        }
        this.radius = Spatula.radius
        this.mass = 1
        this.color = colorsCombo2[Math.floor(Math.random() * colorsCombo2.length)]
        this.image = spatulaImage
    }
    draw() {
        // c.beginPath()
        // c.arc(this.position.x, this.position.y, this.radius, 0, Math.PI * 2, true)
        // c.fillStyle = this.color
        // c.fill()
        // c.closePath()
        c.save()
        c.drawImage(this.image, this.position.x, this.position.y)
        c.restore()
    }
    update() {
        this.position.x = mouse.x
        this.position.y = mouse.y
        this.draw()
    }
}

class Fly {
    constructor() {
        this.position = {
            x: Math.random() * canvas.width * 0.5 - 100,
            y: Math.random() * canvas.height * 0.5 - 100
        }
        this.velocity = {
            x: Math.random() * 5,
            y: Math.random() * 5
        }
        this.radius = 10
        this.mass = 1
        this.color = 'black'
        this.image = flyImage
    }
    draw() {
        // c.beginPath()
        // c.arc(this.position.x, this.position.y, this.radius, 0, Math.PI * 2, true)
        // c.fillStyle = this.color
        // c.fill()
        // c.closePath()
        c.save()
        c.drawImage(this.image, this.position.x, this.position.y)
        c.restore()
    }
    update() {
        if (this.position.x + this.velocity.x + 50 >= canvas.width || this.position.x - 50 <= 0) {
            this.velocity.x = -this.velocity.x
        }
        if (this.position.y + this.velocity.y + 50 >= canvas.height || this.position.y - 50 <= 0) {
            this.velocity.y = -this.velocity.y
        }
        this.position.x += this.velocity.x
        this.position.y += this.velocity.y
        this.draw()
    }
}


// Implementations 
let stick = new Spatula({ x: mouse.x, y: mouse.y })
let flies = [new Fly()]
for (let index = 0; index < 30; index++) {
    flies[index] = new Fly();
}
let frame = 0
function animate() {
    animation = requestAnimationFrame(animate)
    c.rect(0, 0, canvas.width, canvas.height)
    c.fillStyle = 'white' // Background Color
    c.fill()

    flies.forEach(fly => {
        fly.update()

        // if(frame % 10000 == 0) {
        //     fly.velocity.x = Math.random()*10 - 5
        //     fly.velocity.y = Math.random()*10 - 5
        // }
    })
    stick.update();
    if (frame > 100000) {
        frame = 0
    }
}
animate()