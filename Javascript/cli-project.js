import inquirer from "inquirer";

const answer = await inquirer.prompt({
    name: 'word',
    type:'password',
    choices: ["A","B","C","D","E"],
    message:'Enter a Word:',
});

console.log(answer.word);