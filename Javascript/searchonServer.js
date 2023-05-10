const express = require('express');
const app = express();
const db = require("./marvel-db");

let formHtmlCode = `
<html>
<head>
  <title>Marvel Movie Ratings</title>
  <style>
    @import url('https://fonts.googleapis.com/css?family=Poppins');
    body {
        text-align: center;
        font-family: 'Poppins',sans-serif;
    }
  </style>
</head>
<body>
  <h1>Marvel Movie Ratings</h1>
  <form action="/search" method="GET">
    <label for="searchInput">Search Query:</label>
    <input type="search" id="searchInput" name="query" />
    <button type="submit">Search</button>
  </form>
  
</body>
</html>
`;

app.get('/', (req, res) => {
    res.send(formHtmlCode);
});

app.get('/search', async(req, res) => {
    const query = req.query.query;
//   const matchingNames = names.filter(name => name.toLowerCase().includes(query.toLowerCase()));
    const matchingNames = [];
    for(let i=0; i<db.length; i++) {
        if(db[i].name.toLowerCase().match(query.toLowerCase())) {
            matchingNames.push(db[i]);
        }
    }
    res.status(200).json({
    query: query,
    results: matchingNames
    });
});

app.listen(3000, () => {
    console.log('Server is running on http://localhost:3000');
});