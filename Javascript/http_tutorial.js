// const http = require("http");
import http from "http";
const PORT = 5000;

const server =  http.createServer((req,res) => {
    if(req.url === '/about') {
        res.end("Here is our shit all together");
    }
    if(req.url === '/') {
        res.end("Which section do you want, go and come when selectd...");
    }
    if(req.url === '/random') {
        res.end();
    }
    res.end(`<h1>Oopss!!!</h1>
            <p>Can,t you see we are shut down, now shut tfu and sleep!!</p>
             
            `);
    // console.log(req);
    // res.write(`<button>Click Me</button>`);
    // res.end();
});

server.listen(PORT);
