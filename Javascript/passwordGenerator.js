const alphaNumeric = "1234567890qwertyuiopasdfghjklzxcvbnm";
// length of password is 10
//empty string => password
let password = "";
// logic
for(let i=0; i<10; i++) {
    let randomIdx = Math.floor(Math.random()*alphaNumeric.length);
    password = password + alphaNumeric.charAt(randomIdx);
}
// displaying the password
console.log(`Your Password is "\x1b[31m ${password} \x1b[0m" 🙈🙉🙊. Use it safely...`);
