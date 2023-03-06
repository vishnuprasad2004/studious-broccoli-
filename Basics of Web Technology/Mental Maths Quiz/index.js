
const submitBtn = document.getElementById("submitBtn");
const questionText = document.querySelector("#question");
const resultText = document.querySelector("#result");
const nextBtn = document.querySelector("#nextBtn");

nextBtn.addEventListener("click",next);

const operations = ["+","-","x"];
let score = 0;
let round = 0;

let running  = false;
let x1,x2,opIdx;

initialize();


function initialize() {
    running  = true;
    //opIdx is random index produced for operations array
    opIdx = Math.floor(Math.random()* 3);
   
    x1 = Math.floor(Math.random()*15)+1;
    x2 = Math.floor(Math.random()*10)+1;

    generateQuestion(x1,x2,opIdx);

    submitBtn.onclick = function() {
        check(x1,x2,opIdx);
    }

    round += 1;
    
}

function generateQuestion(x1,x2,opIdx) {
    let question = x1 + " " + operations[opIdx] + " " +  x2;
    //printing the question
    console.log(question);
    questionText.textContent = question;
}

function generateAnswer(x1,x2,opIdx) {
    if(operations[opIdx] == "+")    return x1+x2;
    else if(operations[opIdx] == "-")   return x1-x2;
    else    return x1*x2;
}

function check(x1,x2,opIdx) {

    let answer = generateAnswer(x1,x2,opIdx);
    let input = document.getElementById("answer").value;
    input = Number(input);
    
    if(answer != input) {
        resultText.textContent = "Wrong Answer.."; 
    }else {
        resultText.textContent = "Good Job !!";
        score+=1;
    }
    running  = false;
    console.log("score = " + score);
}

function next() {
    running = true;

    if(round == 3) {
        resultText.textContent = "Your Score is " + score + "/" + round + ".";
        questionText.textContent = "Thank You for playing";
        running = false;
    }

    let x1 = Math.floor(Math.random()*15)+1;
    let x2 = Math.floor(Math.random()*10)+1;
    let opIdx = Math.floor(Math.random()* 3);

    generateQuestion(x1,x2,opIdx);

    submitBtn.onclick = function() {
        check(x1,x2,opIdx);
    }
    round += 1;
}


/*
I understood the problem which was declaring the input variable in the \
beginning which took the value which was already in the text box (which is 0).
Now the input is only taken after the answer is written and check button is clicked. 
*/ 






