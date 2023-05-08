const readline = require("readline");
let input = readline.createInterface(process.stdin,process.stdout);


function reverse(num) {
    let sum = 0;
    while (num != 0) {
        let remainder = num % 10;
        sum = sum * 10 + remainder;
        num = Math.floor(num / 10);
    }
    return sum;
}
// NOTE: since in C and Java are typed languages there is no use of Math.floor 
//as it can be declared as an integar and would be fine


input.question('Enter a Number:',(num) => {
    let rev = reverse(num);
    if(rev == num) {
        console.log(`Palindrome🥳🥳`);
    }else {
        console.log(`too bad...🤷‍♀️`);
    }
    input.close();
});
