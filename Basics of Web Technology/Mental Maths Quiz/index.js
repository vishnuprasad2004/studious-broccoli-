
// document.getElementById("button").onclick = function() {
//     let x1 = Math.floor(Math.random*10)+1;
//     let x2 = Math.floor(Math.random*10)+1;
//     let result = 0;
//     let operations = ["+","-","x"];
//     let idx = Math.floor(Math.random*3);
//     let expression = toString(x1)  + " " + operations[idx] + " " + toString(x2);
//     document.getElementById("question").innerHTML = expression;


// }

// const questions = ["20 x 3",
//                 "12 + 10",
//                 "30 - 15",
//                 "12 x 5",
//                 "17 - 8",
//                 "15 - 8",
//                 "6 x 8"];

// const answers = [60,22,15,60,9,7,48];

const submitBtn = document.getElementById("submitBtn");
const questionText = document.querySelector("#question");


var input = document.getElementById("answer");
let answerInput = Number(input.value);
document.write(answerInput);

const resultText = document.querySelector("#result");
const restartBtn = document.querySelector("#restartBtn");



const operations = ["+","-","x"];
const score = 0;
const rounds = 0;


let running  = false;
let x1,x2,opIdx;
initialize();


function initialize() {
    running  = true;
    opIdx = Math.floor(Math.random()* 3);
    console.log(opIdx);
   
    x1 = Math.floor(Math.random()*15)+1;
    x2 = Math.floor(Math.random()*10)+1;
    console.log(x1,x2);

    generateQuestion(x1,x2,opIdx);

    restartBtn.addEventListener("click",next);

    // submitBtn.onclick = function() {
    //     check(x1,x2,opIdx,answerInput);
    //     alert(answerInput);
    // }
    
}

function generateQuestion(x1,x2,opIdx) {
    // questionText.textContent = questions[idx];
    // console.log(questions[idx]);
    let question = x1 + " " + operations[opIdx] + " " +  x2;
    console.log(question);
    questionText.textContent = question;
}

function generateAnswer(x1,x2,opIdx) {
    if(operations[opIdx] == "+") {
        return x1+x2;
    }
    else if(operations[opIdx] == "-") {
       return x1-x2;
    }
    else {
       return x1*x2;
    }
}

function check(x1,x2,opIdx,answerInput) {

    let answer = generateAnswer(x1,x2,opIdx);
    console.log(answer);
    console.log(answerInput);
    //alert(answerInput);
    
    
    if(answer == answerInput) {
        resultText.textContent = "Good Job !!";
        score+=1;
    }else {
        resultText.textContent = "Wrong Answer.."; 
    }
    running  = false;
}

function next() {
    running = true;

    let x1 = Math.floor(Math.random()*15)+1;
    let x2 = Math.floor(Math.random()*10)+1;
    console.log(x1,x2);

    let opIdx = Math.floor(Math.random()* 3);
    console.log(opIdx);

    generateQuestion(x1,x2,opIdx);

    submitBtn.onclick = function() {
        check(x1,x2,opIdx,answerInput);
    }

}








