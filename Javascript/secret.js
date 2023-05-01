import inquirer from "inquirer";
import chalk from "chalk";
import figlet from "figlet";

let username = await inquirer.prompt({
    name:'username',
    type:'input',
    message:'Enter your Username:'
});

let password = await inquirer.prompt({
    name:'pwd',
    type:'password',
    message:'Enter your Password:',
    mask:true,
});

console.log(password);
if(password.pwd === 'welcome@123') {
    figlet("Welcome",function (err, data) {
        if (err) {
          console.log("Something went wrong...");
          console.log(err);
          return;
        }
        console.log(chalk.red.redBright(data));
    });
    console.log(chalk.bold.green("secret is : 'nothing 😂😂😂'"));

}else{

    console.log("Haha password is wrong dufuss!!");

}