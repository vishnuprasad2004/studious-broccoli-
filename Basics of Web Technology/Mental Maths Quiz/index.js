
const submitBtn = document.getElementById("submitBtn");
const questionText = document.querySelector("#question");
const resultText = document.querySelector("#result");
const roundsText = document.getElementById("rounds");

const timeDisplay = document.getElementById("timer");

const operations = ["+","-","x"];
let score = 0;
let round = 0;

//========================
let running  = true;
//==========
let startTime = 0;
let elapsedTime = 0;
let mins = 0;
let secs = 0;
let intervalId;

let x1,x2,opIdx;

initialize();


function initialize() {
    if(running) {
        running  = true;
        startTime = Date.now() - elapsedTime;
        intervalId = setInterval(updateTime,1000);
        //opIdx is random index produced for operations array
        opIdx = Math.floor(Math.random()* 3);
    
        x1 = Math.floor(Math.random()*15)+1;
        x2 = Math.floor(Math.random()*10)+1;
        generateQuestion(x1,x2,opIdx);

        submitBtn.onclick = function() {
            check(x1,x2,opIdx);
        }

        round += 1;
        roundsText.textContent = `${round}/10`;
    }
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
    
    //checking the answer generated and inputed
    if(answer != input) {
        resultText.textContent = "Wrong Answer"; 
        resultText.style.backgroundColor = "#ff0000";
    }else {
        resultText.textContent = "Correct Answer";
        resultText.style.backgroundColor = "#07da63";
        score+=1;
    }

    running  = false;
    console.log("score = " + score);

    document.getElementById("answer").value = " ";
    
    //next question will appear after certain 
    let timeout = setTimeout(next,1200);
}

function next() {
    running = true;

    //ending condition
    if(round == 10) {     
        if(score<=5) {
            //for bad score => bg color is red.
            resultText.style.backgroundColor = "#ff0000";
        }else {
            //for good score => bg color is green.
            resultText.style.backgroundColor = "#07da63";
        }
        resultText.textContent = "Your Score is " + score + "/" + round + ".";
        questionText.textContent = "Thank You for playing";
        document.getElementById("answer").placeholder = " " ;
        document.getElementById("answer").disabled = true;
        
        running = false;
        clearInterval(intervalId);

        return;
    }

    round += 1;
    roundsText.textContent = `${round}/10`;

    //random x1 , x2 , operation Index
    let x1 = Math.floor(Math.random()*15)+1;
    let x2 = Math.floor(Math.random()*10)+1;
    let opIdx = Math.floor(Math.random()* 3);
    generateQuestion(x1,x2,opIdx);

    submitBtn.onclick = function() {
        check(x1,x2,opIdx);
    }

    //resetting the result label to " "
    resultText.style.backgroundColor = "aliceblue";
    resultText.textContent = " ";
}

//utility function
function updateTime() {
    elapsedTime = Date.now() - startTime;

    secs = Math.floor((elapsedTime / 1000) % 60);
    mins = Math.floor((elapsedTime / (1000*60)) % 60);

    function pad(unit) {
        return (("0") + unit).length > 2 ? unit : "0"+unit; 
    }

    secs = pad(secs);
    mins = pad(mins);

    let time = `${mins}:${secs}`;
    timeDisplay.textContent = time;
}


/*
I understood the problem which was declaring the input variable in the \
beginning which took the value which was already in the text box (which is 0).
Now the input is only taken after the answer is written and check button is clicked. 
*/ 






