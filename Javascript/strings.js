// string functions
const str = 'Tony Stank ';
const str2 = 'level';

// split(): Split a string into substrings using the specified separator and return them as an Array.
function splitName() {
    console.log(`First Name:${str.split(' ')[0]}`);
    console.log(`Last Name:${str.split(' ')[1]}`);
}

// replace(): Replaces text in a string, using a regular expression or search string.
function replaceName() {
    let replaced = str.replace('Tony','Shanker');
    console.log(replaced);
}

function isPalindrome(str) {
    let strArr = str.split('');
    strArr = strArr.reverse();
    (strArr.join('') === str)?console.log(`Palindrome🥳🥳`):console.log(`too bad...🤷‍♀️`);
}

isPalindrome(str2);

let d = new Date();

console.log(`Date: ${d.toUTCString().split(' ')[1]}`);
console.log(`Month: ${d.toUTCString().split(' ')[2]}`);
console.log(`Year: ${d.toUTCString().split(' ')[3]}`);
