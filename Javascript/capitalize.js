const readline = require("readline");
// write a program to accept a string and capitalize the first letter of words

let input = readline.createInterface(process.stdin, process.stdout);

function capitalize(str) {
    let strArr = str.split(' ');
    
    strArr.forEach((element,i) => {
        strArr[i] = element.substring(0,1).toUpperCase() + element.substring(1);
    });
    return strArr.join(' ');
}

input.question('Enter a string:',(string) => {
    console.log(capitalize(string));
    input.close();
});
