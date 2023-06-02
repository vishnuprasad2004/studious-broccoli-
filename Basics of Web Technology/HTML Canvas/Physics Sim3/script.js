// Declarations
const canvas = document.getElementById('canvas')
const c = canvas.getContext('2d')
const massSelector = document.getElementById('mass') 
const dampingSelector = document.getElementById('damping') 
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

let gravity = 0.1
let path = []
// Classes
class Pendulum {
    static radius = 0.029 * canvas.height
    constructor(x, y, mass) {
        this.x = x
        this.y = y
        this.X = x
        this.Y = y
        this.mass = mass
        this.radians = Math.PI/3
        this.angularV = 0.01
        this.angularA = 0.001
        this.radius = Pendulum.radius
        this.color = colorsCombo2[Math.floor(Math.random()*colorsCombo2.length)]
        this.length = this.Y
        this.damping = 0.999
    }
    draw() {
        c.beginPath()
        c.moveTo(this.X, 0)
        c.save()
        c.lineTo(this.x, this.y)
        c.strokeStyle = 'yellow'
        c.lineWidth = 2
        c.stroke()
        c.restore()
        path.forEach((path, i) => {
            c.beginPath()
            c.arc(path.x, path.y, 3, 0, Math.PI * 2)
            c.fillStyle = `rgba(200,150,${i+220})`
            c.fill()
        })
        c.beginPath()
        c.fillStyle = '#000000'
        path.push({x: this.x, y: this.y})
        if(path.length >= 100) {
            path.splice(0,1)
        }
        c.arc(this.x, this.y, this.radius, 0, Math.PI * 2)
        c.fillStyle = this.color
        c.fill()
        c.stroke()
        c.closePath()
    }
    update() {
        let force = - this.mass * gravity * Math.sin(this.radians)
        this.angularA = force / this.length 
        this.angularV += this.angularA
        this.angularV *= this.damping
        this.radians += this.angularV
        this.x = this.X + Math.sin(this.radians) * this.length
        this.y =  Math.cos(this.radians) * this.length
        
        this.draw()
        
    }
}


// Implementations 
// let pendulums = new Pendulum(canvas.width/2, 200)

let pendulums =[new Pendulum(canvas.width/2,500,10)]
function animate() {
    animation = requestAnimationFrame(animate)
    c.rect(0, 0, canvas.width, canvas.height)
    c.fillStyle = '#002366' // Background Color
    c.fill()
    c.beginPath()
    c.moveTo(canvas.width/2,0)
    c.lineTo(canvas.width/2, 600) 
    c.strokeStyle = 'black'
    c.stroke()
    c.closePath()
    pendulums.forEach(pendulum => {
        // let damping = dampingSelector.value / 100;
        // let mass = massSelector.value;
        // pendulum.mass = mass
        // pendulum.damping = damping
        pendulum.update()
    })

}
animate()