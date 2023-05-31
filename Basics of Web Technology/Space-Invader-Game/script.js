
/*
  FUTURE IMPROVEMENTS NOTES
    > add explosion animation when game over
    > clean the code
    > difficulty - done
    > immunity when green partilcles are hit by projectiles

 */








//Default Declarations
const canvas = document.querySelector('#canvas')
const c = canvas.getContext('2d')
const intro = document.querySelector('#intro');
const gameOver = document.getElementById("game-over")
const difficultyRadioBtns = document.getElementsByName('difficulty')

canvas.width = innerWidth
canvas.height = innerHeight
const mouse = {
    x: innerWidth / 2,
    y: innerHeight / 2
}

//Constant / Declarations
const bgColor = '#000011'
const scale = 0.15
const playerSpeed = 4
const gap = 20
let animation; 
let score = 0
let difficulty = 500
let storedHighscore  = localStorage.getItem('highScore') || 0
let highscore = storedHighscore
// let exploded = false
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
addEventListener('resize',() => {
    location.reload()
})

// setting eventListeners
document.getElementById("mute-controls").addEventListener("click",() => {
    if(document.getElementById("mute-controls").checked) {
        music.muted = true
    } else {
        music.muted = false
    }  
})

document.getElementById("pause").addEventListener("click",() => {
    if(document.getElementById("pause").checked) {
        cancelAnimationFrame(animation)
        music.muted = true 
    } else {
        music.muted = false 
        animate()
    }  
})


// Assets
// const playerImage = new Image()
// playerImage.src = './img/spaceship-player.png'
const playerImage = document.getElementById('player-image')
const invaderImage = new Image()
invaderImage.src = './img/space-invader.png'
const music = new Audio('./background-music.mp3')
music.autoplay = true


// const explosions = [new Image(), new Image(), new Image(), new Image(), new Image(), new Image()]
// for(let i=1;i<=explosions.length;i++) {
//     explosions[i-1].src = `./img/explosion${i}.png`;
// }


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
                y: canvas.height - this.height - 50
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

    // explosion() {
        
    //     let exTimer = setInterval(() => {
    //         let i = 1 
    //         let explosionImage = new Image()
    //         explosionImage.src = `./img/explosion${i++}.png`
    //         if(explosionImage) {
    //             c.drawImage(explosionImage, this.position.x, this.position.y, this.width, this.height)
    //         }else{
    //             c.fillText('OUT',this.position.x,this.position.y)
    //         }
    //     },0.5)
    // }

    update() {
        if(playerImage) {
            this.position.x += this.velocity.x 
            // if(exploded) {
            //     this.explosion()
            // }else{
            // }  
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

    update(velocity) {
        this.position.x += velocity.x
        this.position.y += velocity.y
        this.draw()
    }

    shoot(invaderProjectiles) {
        invaderProjectiles.push(new InvaderProjectile({
            x: this.position.x + this.width/2,
            y: this.position.y 
        }))
    }
}

class Grid {
    constructor() {
        this.position = {
            x:0,
            y:0
        }
        this.velocity = {
            x:2,
            y:0
        }
        this.rows = Math.floor(Math.random() * 10 + 5)
        this.cols = Math.floor(Math.random() * 4 + 2)
        this.width =  this.rows * 45 
        this.invaders = []
        for(let i=0; i< this.rows; i++) {
            for(let j=0; j< this.cols; j++) {
                this.invaders.push(new Invader({
                    x: i * 45,
                    y: j * 45
                }))
            }
        }
    }

    update() {
        this.position.x += this.velocity.x
        this.position.y += this.velocity.y
        this.velocity.y = 0
        if(this.position.x  + this.width >= canvas.width || this.position.x <= 0) {
            this.velocity.x = -this.velocity.x
            randomInterval = Math.floor(Math.random()*500 + 500)
            this.velocity.y = 30
        }
        if(this.position.y >= 0.6*canvas.width) {
            gameOverFunc()
        }

        // if(this.invaders[this.rows-1].position.x + invaderImage.width < canvas.width) {
        //     this.invaders.forEach(invader => {
        //         invader.position.x += this.velocity.x
        //         invader.position.y += this.velocity.y
        //         invader.draw()
        //     })
        // }else if(this.invaders[0].position.x <= 0) {
        //     this.invaders.forEach(invader => {
        //         invader.position.x += -this.velocity.x
        //         invader.position.y += this.velocity.y
        //         invader.draw()
        //     })
        // }else {
        //     this.invaders.forEach(invader => {
        //         invader.position.x += -this.velocity.x
        //         invader.position.y += this.velocity.y
        //         invader.draw()
        //     })
            
        //     this.velocity.x = -this.velocity.x
        // }

        // console.log(this.position.x)
        // this.invaders.forEach(invader => {
        //     if(invader.position.x  + this.width >= canvas.width) {
        //         this.velocity.x = -this.velocity.x
        //     }
        //     invader.update({
        //         x: this.velocity.x,
        //         y: this.velocity.y
        //     })
        // })
        // this.position.x += this.velocity.x
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

class ImmunityParticles {
    constructor() {
        this.position = {
            x: 20,
            y: Math.random() * canvas.height/2
        }
        this.velocity = {
            x:Math.random()*2 + 1,
            y:Math.random()*2 + 1
        }
        this.radius = 12
    }

    draw() {
        c.beginPath()
        c.arc(this.position.x, this.position.y, this.radius, 0, Math.PI*2, true)
        c.fillStyle = 'lightgreen'
        c.fill()
        c.closePath()
    }

    update() {
        this.position.x += this.velocity.x
        this.position.y += this.velocity.y
        this.draw()
    }
}

class InvaderProjectile {
    constructor(position) {
        this.position = {
            x: position.x,
            y: position.y
        }
        this.velocity = {
            x:0,
            y:5
        }
        this.height = 6
        this.width = 4
    }

    draw() {
        c.beginPath()
        c.rect(this.position.x, this.position.y, this.width, this.height)
        c.fillStyle = 'yellow'
        c.fill()
        c.closePath()
    }

    update() {
        this.position.x += this.velocity.x
        this.position.y += this.velocity.y
        this.draw()
    }

}

class Star {
    static colors = ['#2185C5aa', '#7ECEFDaa', '#FFF6E5aa', '#FF7F66aa'];
    constructor() {
        this.position = {
            x: Math.random() * canvas.width,
            y: Math.random() * canvas.height 
        }
        this.velocity = {
            x:0,
            y:2
        }
        this.radius = Math.random() * 2 
        this.color = Star.colors[Math.floor(Math.random() * Star.colors.length)]
    }

    draw() {
        c.beginPath()
        c.arc(this.position.x, this.position.y, this.radius,0,Math.PI * 2)
        c.fillStyle = this.color
        c.fill()
        c.closePath() 
    }

    update() {
        if(this.position.y > canvas.height) {
            this.position.y = Math.random() * canvas.height - 100
        }
        this.position.x += this.velocity.x
        this.position.y += this.velocity.y
        this.draw()
    }
}

// Implementation
// function init() {
//    player = new Player()
//    projectiles = []
//    grids = []
//    frames = 0
//    invaderProjectiles = []
//    stars = [] 

// }
function gameOverFunc() {
    console.log("%cOUT",'color:red;')
    gameOver.showModal();
    cancelAnimationFrame(animation)
    setTimeout(()=>{
        c.rect(0, 0, canvas.width, canvas.height)
        c.fillStyle = bgColor
        c.fill()
        stars.forEach(star => {
            star.draw()
        })
        
    },0)
    highscore = (highscore > score) ? highscore : score
    localStorage.setItem('highScore',highscore)
    // exploded = true
    // player.image = explosions[explosion]
    score = 0
}

let player = new Player()
let projectiles = []
let grids = []
let invaderProjectiles = []
let immunityParticles = []
let frames = 0
let explosion = 0
let randomInterval = Math.floor(Math.random()*500 + difficulty)
let randomInterval2 = Math.floor(Math.random()*500 + 1000) // for immunity 1000 being likely of occurance
let stars = []
for(let i=0;i<100;i++) {
    stars.push(new Star())
}

c.rect(0, 0,canvas.width, canvas.height)
c.fillStyle = bgColor
c.fill()


// Animation Loop
function animate() {
    animation = requestAnimationFrame(animate)
    c.rect(0, 0, canvas.width, canvas.height)
    c.fillStyle = bgColor;
    c.fill()
    // score text update
    c.fillStyle = 'white'
    c.font = "bold 20px monospace";
    c.fillText(`Score:${score}  High Score:${highscore}`,5,canvas.height-25);

    player.update()
    
    stars.forEach(star => {
        star.update()
    })
    
    projectiles.forEach((projectile,i) => {
        projectile.update()
        // if projectiles are off screen then take them out of the array
        if(projectile.position.y < 0) {
            setTimeout(()=>{
                projectiles.splice(i,1)
            },0)
        }
    })
    immunityParticles.forEach(particle => {
        particle.update()
    })
    
    invaderProjectiles.forEach(invaderProjectile => {
        invaderProjectile.update()
        // collision of invader's projectile and player
        if(invaderProjectile.position.x <= player.position.x + player.width && invaderProjectile.position.x >= player.position.x && invaderProjectile.position.y >= player.position.y && invaderProjectile.position.y <= player.position.y + player.height) {
            // game over
            // console.log("%cOUT",'color:red;')
            // gameOver.showModal();
            // cancelAnimationFrame(animation)
            // setTimeout(()=>{
            //     c.rect(0, 0, canvas.width, canvas.height)
            //     c.fillStyle = bgColor
            //     c.fill()
            //     stars.forEach(star => {
            //         star.draw()
            //     })
                
            // },0)
            // highscore = (highscore > score) ? highscore : score
            // localStorage.setItem('highScore',highscore)
            // // exploded = true
            // // player.image = explosions[explosion]
            // score = 0
            gameOverFunc()
        }
    })
    
    grids.forEach(grid => {

        grid.update()
        // randomly shooting projectiles by invaders
        if(frames % 100 === 0 && grid.invaders.length > 0) {
            grid.invaders[Math.floor(Math.random()*grid.invaders.length)].shoot(invaderProjectiles)
        }
        // updating position of invaders according to grid
        grid.invaders.forEach((invader,i) => {
            invader.update({
                x: grid.velocity.x,
                y: grid.velocity.y
            })
            projectiles.forEach((projectile,j) => {
                // checking for collision between invader and player projectile
                if(projectile.position.y-projectile.radius <= invader.position.y + invader.height && projectile.position.x + projectile.radius >= invader.position.x && projectile.position.x - projectile.radius <= invader.position.x + invader.width && projectile.position.y + projectile.radius >= invader.position.y) {
                    // setTimeout for removing it in the next frame and avoiding flashes
                    setTimeout(() => {
                        // doing this to ensure that the invader and projectile are in the lists
                        let invaderFound = grid.invaders.find(invader2 => invader2 === invader)
                        let projectileFound = projectiles.find(projectile2 => projectile2 === projectile)
                        // if found eliminating both
                        if(invaderFound && projectileFound) {
                            grid.invaders.splice(i,1);
                            projectiles.splice(j,1);
                        }
                    },0)
                    // increasing score
                    score += 100
                }
            })
        })
    })
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
    // randomly adding more grids
    if(frames % randomInterval === 0) {
        grids.push(new Grid());
    }
    if(frames % randomInterval2 === 100) {
        immunityParticles.push(new ImmunityParticles())
    }
    // if(frames % 10 === 0) {
    //     explosion++
    // }

    frames++;
}

// animate()

// intro
document.body.onload = function() {
    intro.showModal()
    for(let i=0;i<difficultyRadioBtns.length;i++) {
        console.log(difficultyRadioBtns[i].value)
        if(difficultyRadioBtns[i].checked) {
            difficulty = difficultyRadioBtns[i].value || 100
            break
        }
    }
    music.play()
}
document.getElementById("press-to-continue").onclick = function() {
    intro.close()
    // console.log(difficulty.value)
    animate()
}
// restart code
document.getElementById("restart").onclick = function() {
    // reinitialising all variables
    gameOver.close()
    player = new Player()
    projectiles = []
    grids = []
    frames = 0
    invaderProjectiles = []
    stars = [] 
    for(let i=0;i<50;i++) {
        stars.push(new Star())
    }
    document.getElementById('pause').checked = false
    animate()
}
 