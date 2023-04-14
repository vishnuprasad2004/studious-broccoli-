import inquirer from "inquirer";

// stack implementation
const stack = [];
let top = -1;

async function implementation() {

    while(true) {

        let choice = await inquirer.prompt({
            name:'operation',
            type:'list',
            message:'Which operation do u want to perform on stack...',
            choices:[
                'push',
                'pop',
                'peek',
                'display',
                'exit'
            ]
        });
        
        let ch = choice.operation;
        
        if(ch == 'push') {

            let input = await inquirer.prompt({
                name:'element',
                type:'input',
                message:'Enter the Element:'
            });
            
            stack.push(input.element);
            top = top + 1;
            console.log("element pushed");

        }else if(ch == 'pop') {

            let popped = stack.pop();
            top = top-1;
            console.log(`Popped element :${popped}`);

        }else if(ch == 'display') {

            if(top == -1) {
                console.log("Stack is Empty");
                continue;
            }
            console,log(...stack);
            console.log('\n');

        }else if(ch == 'peek'){

            console.log(stack[top]);

        }else{

            break;
        }

    }
}

implementation();
