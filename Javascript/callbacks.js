

let txt = "good";

function timedCall(callback,text) {
    if(text === "good"){
        console.log("Good Boy");
        callback("Vishnu");
    }
}

function print(name) {
    console.log(`Hello ${name}`);
}

setTimeout(()=>{
    timedCall(print,txt);
},3000);

