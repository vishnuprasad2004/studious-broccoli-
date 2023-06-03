const canvas = document.getElementById("canvas");
const c = canvas.getContext('2d');
const gameContainer = document.getElementById('game-container')
const gameover =  document.getElementById('game-over');

canvas.width = 400
canvas.height = 270
let animation;
let keys = {
    w: {
        pressed: false
    },
    s: {
        pressed: false
    },
    ArrowUp: {
        pressed: false
    },
    ArrowDown: {
        pressed: false
    },
    space: {
        pressed: false
    }
}
// Event Listeners
document.getElementById('restart').addEventListener('click',() => {
    location.reload()
})

addEventListener('resize',() => {
    location.reload();
});

addEventListener('keydown',({ key }) => {
    console.log(key)
    switch(key) {
        case 'w':
            keys.w.pressed = true;
            break;
        case 's':
            keys.s.pressed = true;
            break;
        case 'ArrowUp':
            keys.ArrowUp.pressed = true;
            break;
        case 'ArrowDown':
            keys.ArrowDown.pressed = true;
            break;
        case ' ':
            // keys.space.pressed = true
            animate()
    }
});
addEventListener('keyup',({ key }) => {
    switch(key) {
        case 'w':
            keys.w.pressed = false;
            break;
        case 's':
            keys.s.pressed = false;
            break;
        case 'ArrowUp':
            keys.ArrowUp.pressed = false;
            break;
        case 'ArrowDown':
            keys.ArrowDown.pressed = false;
            break;
        case ' ':
            keys.space.pressed = false
    }
});

class Player {
    constructor(x,y) {
        this.x = x
        this.y = y
        this.height = 90
        this.width = 12
        this.vy = 0
    }
    draw() {
        c.beginPath()
        c.rect(this.x, this.y, this.width, this.height)
        c.fillStyle = '#444444'
        c.fill()
        c.closePath()
    }
    update() {
        if(this.height + this.y + this.vy > canvas.height - 2 || this.y + this.vy < 2) {
            // this.vy = - this.vy
        }else{
            this.y += this.vy
        }
        this.draw()
    }
}

class Ball {
    constructor() {
        this.x = canvas.width/2
        this.y = canvas.height/2
        this.vx = 2 
        this.vy = 2 
        this.radius = 12
    }
    draw() {
        c.beginPath()
        c.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false)
        c.fillStyle = '#666666'
        c.fill()
        c.closePath()
    }
    update() {
        if(this.x + this.radius + this.vx > canvas.width || this.x - this.radius < 0) {
            this.vx = 0
            this.vy = 0
            gameOver()
        }
        if(this.y + this.radius + this.vy > canvas.height || this.y - this.radius < 0) {
            this.vy = - this.vy
        }
        this.x += this.vx
        this.y += this.vy
        this.draw()
    }
}




// Functions
function gameOver() {
    cancelAnimationFrame(animation)
    gameover.style.display = 'block';
    // c.rect(0, 0, canvas.width, canvas.height)
    // c.fillStyle = '#202122'
    // c.fill()
}



function distance(x1, y1, x2, y2) {
    // Distance Formula from Coordinate Geometry
    return Math.sqrt(Math.pow(x2-x1,2)+ Math.pow(y2-y1,2))
}


// Classes

// Implementations 
let player1 = new Player(2,2)
let player2 = new Player(canvas.width-14,2)
let ball = new Ball()
// c.beginPath()
// c.moveTo(canvas.width/2,0)
// c.lineTo(canvas.width/2,canvas.height)
// c.closePath()
// for(let i=0;i<10;i++) {
//     if(i*7) {
//         c.moveTo(canvas.width/2, i*7)
//         c.lineTo(canvas.width/2,i*7)
//         c.strokeStyle ='white'
//         c.stroke()
//     }
// }


function animate() {
    animation = requestAnimationFrame(animate)
    c.rect(0, 0, canvas.width, canvas.height)
    c.fillStyle = '#111111' // Background Color
    c.fill()
    c.beginPath()
    c.moveTo(canvas.width/2,0)
    c.lineTo(canvas.width/2,canvas.height)
    c.setLineDash([5,5])
    c.strokeStyle = '#dddddd'
    c.stroke()
    c.closePath()
    player1.update()
    player2.update()
    ball.update()
    if(keys.w.pressed) {
        player1.vy = -2
    }else if(keys.s.pressed) {
        player1.vy = 2
    }else{
        player1.vy = 0
    }
    if(keys.ArrowUp.pressed) {
        player2.vy = -2
    }else if(keys.ArrowDown.pressed) {
        player2.vy = 2
    }else{
        player2.vy = 0
    }
    
    // Player 1 Collision
    if(player1.x + ball.x + ball.vx <=  player1.width + ball.radius && (player1.y <= ball.y && player1.y + player1.height >= ball.y)) {
        ball.vx = - ball.vx
    }
    // Player 2 Collision
    if(player2.x - ball.x - ball.vx <=  ball.radius && (player2.y <= ball.y && player2.y + player2.height >= ball.y)) {
        ball.vx = - ball.vx
    }

   

}
// animate()