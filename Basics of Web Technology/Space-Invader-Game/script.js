//Default Declarations
const canvas = document.querySelector('#canvas')
const c = canvas.getContext('2d')
const intro = document.querySelector('#intro');
canvas.width = innerWidth
canvas.height = innerHeight
const mouse = {
    x: innerWidth / 2,
    y: innerHeight / 2
}
let animation;
// Intro
// document.body.onload = () => {
//     intro.showModal()
// }
// document.getElementById("press-to-continue").onclick = () => {
//     intro.close()
// }

//Constant / Declarations
const bgColor = '#000011'
const scale = 0.15
const playerSpeed = 4
const gap = 20
let keys = {
    ArrowLeft: {
        pressed : false
    },
    ArrowRight: {
        pressed : false
    },
    Space: {
        pressed : false
    }
}


// Event Listeners
addEventListener('mousemove', (event) => {
    mouse.x = event.clientX
    mouse.y = event.clientY
})

addEventListener("keydown",({ key }) => {
    switch(key) {
        // Right
        case "ArrowRight":
            keys.ArrowRight.pressed = true
            break
        // Left
        case "ArrowLeft":
            keys.ArrowLeft.pressed = true
            break
        // Throw Projectiles
        case " ":
            keys.Space.pressed = true
            projectiles.push(new Projectile({
                x:player.position.x + player.width/2,
                y:player.position.y
            }))
            console.log(projectiles.length)
            break
    }

})

addEventListener("keyup",({ key }) => {
    switch(key) {
        // Right
        case "ArrowRight":
            keys.ArrowRight.pressed = false
            break
        // Left
        case "ArrowLeft":
            keys.ArrowLeft.pressed = false
            break
        // Throw Projectiles
        case " ":
            keys.Space.pressed = false
            break
    }

})


// Assets
const playerImage = new Image()
playerImage.src = './spaceship-player.png'
const invaderImage = new Image()
invaderImage.src = './space-invader.png'


// Classes
class Player {
    constructor() {
        this.velocity = {
            x:0,
            y:0
        }
        if(playerImage) {
            this.height = playerImage.height * scale
            this.width = playerImage.width * scale
            this.image = playerImage
            this.position = {
                x: canvas.width/2 - this.width/2,
                y: canvas.height - this.height - 30
            }
        }
        this.rotation = 0
    }

    draw() {
        c.save()
        c.translate(this.position.x + this.width/2, this.position.y + this.height/2)
        c.rotate(this.rotation)
        c.translate(-this.position.x - this.width/2, -this.position.y - this.height/2)
        c.drawImage(this.image,this.position.x,this.position.y,this.width,this.height);
        c.restore()
    }

    update() {
        if(playerImage) {
            this.position.x += this.velocity.x   
            this.draw()
        }
    }
}

class Invader {
    constructor(position) {
        if(invaderImage) {
            this.position = {
                x: position.x,
                y: position.y
            }
            this.velocity = {
                x:2,
                y:0
            }
            this.image = invaderImage
            this.width = invaderImage.width * scale * 0.5
            this.height = invaderImage.height * scale * 0.5
        }
    }

    draw() {
        c.drawImage(this.image, this.position.x, this.position.y, this.width, this.height)
    }

    update() {
        this.position.x += this.velocity.x
        this.position.y += this.velocity.y
        this.draw()
    }
}

class Grid {
    constructor(rows, columns) {
        this.position = {
            x:100,
            y:100
        }
        this.velocity = {
            x:1
        }
        console.log(this.velocity.x)
        this.rows = rows
        this.cols = columns
        this.invaders = []
        let x = this.position.x
        let y = this.position.y
        for(let i=0; i< this.rows; i++) {
            for(let j=0; j< this.cols; j++) {
                this.invaders.push(new Invader({x:x,y:y}))
                x += (invaderImage.width*scale*0.5 + gap)
            }
            x = this.position.x
            y += (invaderImage.height*scale*0.5 + gap)
        }
    }

    // draw() {
    //     this.invaders.forEach(invader => {
    //         invader.draw()
    //     })
    // }
    update() {
        if(this.invaders[this.rows].position.x + invaderImage.width < canvas.width) {
            this.invaders.forEach(invader => {
                invader.position.x += this.velocity.x
                invader.draw()
            })
        }else if(this.invaders[0].position.x <= 0) {
            this.invaders.forEach(invader => {
                invader.position.x += -this.velocity.x
                invader.draw()
            })
        }else {
            this.invaders.forEach(invader => {
                invader.position.x += -this.velocity.x
                invader.draw()
            })
            
            this.velocity.x = -this.velocity.x
        }
        // this.position.x += this.velocity.x
        // this.draw()
    }
}

class Projectile {
    constructor(position) {
        this.position = {
            x: position.x,
            y: position.y
        }
        this.velocity = {
            x:0,
            y:-5
        }
        this.radius = 2
    }

    draw() {
        c.beginPath()
        c.arc(this.position.x, this.position.y, this.radius, 0, Math.PI*2, true)
        c.fillStyle = 'red'
        c.fill()
        c.closePath()
    }

    update() {
        this.position.x += this.velocity.x
        this.position.y += this.velocity.y
        this.draw()
    }
}

// Implementation
let player = new Player()
let projectiles = []
let grid = new Grid(2,10);

// Animation Loop
function animate() {
    animation = requestAnimationFrame(animate)
    // c.clearRect(0, 0, canvas.width, canvas.height)
    c.rect(0, 0, canvas.width, canvas.height)
    c.fillStyle = bgColor;
    c.fill()
    player.update()
    projectiles.forEach((projectile,i) => {
        projectile.update()
        if(projectile.position.y < 0) {
            setTimeout(()=>{
                projectiles.splice(i,1)
            },0)
        }
    })
    grid.update()
    // moving of player to the left
    if(keys.ArrowLeft.pressed && player.position.x >= 0) {
        player.velocity.x = -playerSpeed
        player.rotation = -0.15
    // moving the player to the right
    }else if(keys.ArrowRight.pressed && player.position.x + player.width <= canvas.width) {
        player.velocity.x = playerSpeed
        player.rotation = 0.15
    }else {
        player.rotation = 0
        player.velocity.x = 0
    }

    
}

animate()


