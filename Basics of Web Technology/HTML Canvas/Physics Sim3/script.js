// Declarations
const canvas = document.getElementById('canvas')
const c = canvas.getContext('2d')
const colorsCombo2 = ['#e5243f','#f65c51','#f7b15c','#54c6be','#2f5755']; // muted colors
let animation
canvas.width = innerWidth
canvas.height = innerHeight
let mouse = {
    x: undefined,
    y: undefined
}

// Event Listeners
addEventListener('resize',() => {
    location.reload()
})

addEventListener('mousemove',(event) => {
    mouse.x = event.clientX
    mouse.y = event.clientY
})

// Functions
function randomNumRange(min, max) {
    return Math.floor(Math.random()*(max - min + 1) + min);
}

function distance(x1, y1, x2, y2) {
    // Distance Formula from Coordinate Geometry
    return Math.sqrt(Math.pow(x2-x1,2)+ Math.pow(y2-y1,2))
}

let gravity = 1
// Classes
class Pendulum {
    static radius = 0.019 * canvas.height
    static X;
    static Y;
    constructor(x, y) {
        this.x = x
        this.y = y
        Pendulum.X = x
        Pendulum.Y = y
        this.radians = Math.PI/2
        this.angularV = 0.01
        this.angularA = 0.001
        this.radius = Pendulum.radius
        this.color = colorsCombo2[Math.floor(Math.random()*colorsCombo2.length)]
        this.length = Pendulum.Y
        this.velocity
    }
    draw() {
        c.beginPath()
        c.moveTo(canvas.width/2, 0)
        c.lineTo(this.x, this.y)
        c.strokeStyle = '#000000'
        c.stroke()
        c.beginPath()
        c.arc(this.x, this.y, this.radius, 0, Math.PI * 2)
        c.fillStyle = this.color
        c.fill()
        c.closePath()
    }
    update() {
        let force = -gravity * Math.sin(this.radians)
        this.angularA = force / this.length
        this.angularV += this.angularA
        // this.angularV *= 0.99
        this.radians += this.angularV
        this.x = Pendulum.X + Math.sin(this.radians) * this.length
        this.y =  Math.cos(this.radians) * this.length
        
        this.draw()
        
    }
}


// Implementations 
let pendulum = new Pendulum(canvas.width/2, 500)
function animate() {
    animation = requestAnimationFrame(animate)
    c.rect(0, 0, canvas.width, canvas.height)
    c.fillStyle = 'white' // Background Color
    c.fill()
    
    pendulum.update()

}
animate()