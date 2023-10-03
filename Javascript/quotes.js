const { response } = require("express");

// Ipsita ka Tutorial 101 on APIs
const API = 'https://api.quotable.io/quotes/random';

//fetch method  !IMPORTANT
//promises method

// let fetchPromise = fetch(API+"?limit=5");
// fetchPromise.then(response => {
//     return response.json();
// }).then(data => {
//     console.log(data);
// });


// async - await method

async function getQuotes() {
    try {
        let response = await fetch(API);
        let data = await response.json();
        console.log(`\x1b[32m${data[0].content}\x1b[0m`);
        console.log(`\x1b[31m${data[0].author}\x1b[0m`);
    }catch(e) {
        console.log(e.message);
    }
}

getQuotes();