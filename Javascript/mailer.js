import inquirer from "inquirer";
import nodemailer from "nodemailer";
// env has EMAIL and PASSWORD for sending email 
import { env } from "./env.js"


const mailer = async(data) => {

    let transporter = nodemailer.createTransport({
        host: "smtp.gmail.com", // the hostname or IP address to connect to 
        port: 465,
        secure: true,
        auth: {
            user: env.EMAIL , 
            pass: env.PASSWORD, 
        },
    });
    

    let html = `
    <div style="box-shadow:0 0 5px;padding:2rem;border-radius:16px;background:linear-gradient(45deg,#9600ff,#aebaf8);margin:10px;">
        <h1>${data.message}</h1>
    </div>
    <div>
        <h4>Vishnu Prasad Korada</h4>
        <a href="https://github.com/vishnuprasad2004">My GitHub</a>
    </div>`;


    let message = {
        from: `"Howdy Neighbour 👻😎🫡" <${env.EMAIL}>`, 
        to: data.email, 
        subject: "Auto-Message from Vishnu Prasad Korada", 
        html: html, 
    };

    // send the mail with above details
    let info = await transporter.sendMail(message)
        .then(() => {
            console.log("You sent an email successfully...!!!\nMessage sent 👍");    
        }).catch(error => {
            console.log(error);
            console.log("cannot do it..!!!")
            return;
        });
    
}

async function main() {

    const email = await inquirer.prompt({
        name: 'email',
        type:'input',
        message:'Enter the email to send to:',
    });

    const message = await inquirer.prompt({
        name: 'message',
        type:'input',
        message:'Enter the message:',
    });

    await mailer({
        email:email.email,
        message:message.message,
    })

}

main();