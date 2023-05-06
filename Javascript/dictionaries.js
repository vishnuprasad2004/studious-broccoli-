import chalk from "chalk";
import inquirer from "inquirer";
// NOTICE : inquirer only works in module js form so note that...



// Source - MDN References (not for the code...)
// First, we have the variable name — superHeroes.
// Inside that, we want to access the members property, so we use ["members"].
// members contains an array populated by objects. We want to access the second object inside the array, so we use [1].
// Inside this object, we want to access the powers property, so we use ["powers"].
// Inside the powers property is an array containing the selected hero's superpowers. We want the third one, so we use [2].

async function getWord(word) {
    
    try {
        // asking for response from API using fetch() method
        let response = await fetch(`https://api.dictionaryapi.dev/api/v2/entries/en/${word}`); 
        let data = await response.json();
        if(!response.ok)    throw "Word not Found";
        return data;

    } catch (error) {

        console.log(chalk.cyan(error));

    }
    
}

const getMeaning = async(word) => {

    console.log(chalk.cyan(`Fetching ${word} Meaning....`));
    const wordData =  await getWord(word);
    const temp = wordData[0]["meanings"][0]["definitions"][0]["definition"];
    
    console.log(chalk.cyan(`Fetched ${word} meaning :${temp} `));

    // creating a object to display as a table in the terminal 
    let details = {
        "Word":wordData[0]["word"],
        "phonetics":wordData[0]["phonetic"],
        "Meaning_1":wordData[0]["meanings"][0]["definitions"][0]["definition"],
        // "Meaning_2":wordData[0]["meanings"][1]["definitions"][0]["definition"],
        "part_of_speech":wordData[0]["meanings"][0]["partOfSpeech"],
    }
    
   return details;
}


async function main() {

    // prompting input from terminal
    const answer = await inquirer.prompt({
        name: 'word',
        type:'input',
        message:'Enter a Word:',
        default() {
            return 'Hello';
        }
    });


    let word = answer.word;
    let a = getMeaning(word);
    // if promise is resolved then do some work...
    a.then((value) => {

        console.table(value);
    });
    
}


main();