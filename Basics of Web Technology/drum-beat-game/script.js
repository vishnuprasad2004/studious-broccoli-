const yellow = document.getElementById("yellow");
const red = document.getElementById("red");
const blue = document.getElementById("blue");
const green = document.getElementById("green");

// let yellowAudio = new Audio('./cymbals.mp3');
// let redAudio = new Audio('./drum.mp3');
// let blueAudio = new Audio('./drum-2.mp3');
// let greenAudio = new Audio('./drum3.mp3');
// yellow.onclick = async() => {
//     redAudio.pause()
//     greenAudio.pause()
//     blueAudio.pause()
//     yellowAudio.play() 
// }
// red.onclick = async() => {
//     yellowAudio.pause()
//     greenAudio.pause()
//     blueAudio.pause()
//     redAudio.play()
// }
// blue.onclick = async() => {
//     yellowAudio.pause()
//     greenAudio.pause()
//     redAudio.pause()
//     blueAudio.play()
// }
// green.onclick = async() => {
//     yellowAudio.pause()
//     blueAudio.pause()
//     redAudio.pause()
//     greenAudio.play()
// }

function generate() {
    red.classList.toggle('.bright')
}

generate()
