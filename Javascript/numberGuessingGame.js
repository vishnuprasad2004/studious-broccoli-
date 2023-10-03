const readline = require("readline");
const read = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

read.question("Whats your name darling?",(name)=> {
    console.log(`Your Name is ${name}`);
});