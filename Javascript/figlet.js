
import figlet from 'figlet';
import chalk from "chalk";


console.log(chalk.green("Hello World")+" I am Vishnu Prasad Korada"+chalk.redBright("!"));



figlet("Welcome Vishnu",function (err, data) {
    if (err) {
      console.log("Something went wrong...");
      console.log(err);
      return;
    }
    console.log(chalk.red.redBright(data));
});

// figlet.fonts(function (err, fonts) {
//   if (err) {
//     console.log("something went wrong...");
//     console.dir(err);
//     return;
//   }
//   console.dir(fonts);
// });

// figlet("dwd",{
//   font:"Hieroglyphs"
// })