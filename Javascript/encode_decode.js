

const encoder = new TextEncoder();
const decoder = new TextDecoder();
let e = encoder.encode("Vishnu");
console.log(e);
try {
    let d = decoder.decode(e);
    console.log(d); 
}catch(e) {
    console.log("error can't do that");
}

try {
    cnsole.log();
}catch(e) {
    console.log("error");
}