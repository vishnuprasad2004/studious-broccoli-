

import express from 'express';
// Creates an Express application using the top-level function
const app = express();

// Define port number as 5000
const port = 5000;

let html = `<b>Hello World</b><br>
            <button>Click Me</button>
            <ol>
                <li>Mango</li>
                <li>Watermelon</li>
                <li>Banana</li>
                <li>Apple</li>
            </ol>
            <table border="2px" cellspacing="2px" cellpadding="2px">
                <tr>
                    <td>Javascript</td>
                    <td>CSS</td>
                    <td>HTML</td>
                </tr>
                <tr>
                    <td>Python</td>
                    <td>Java</td>
                    <td>Kotlin</td>
                </tr>
                <tr>
                    <td>Ruby</td>
                    <td>Solidity</td>
                    <td>Flutter</td>
                </tr>
            </table>`;

// Routes HTTP GET requests to the specified path "/" with the specified callback function
app.get('/', function(request, response) {
 response.send(html);
});
// Make the app listen on port 3000
app.listen(port, function() {
 console.log('Server listening on http://localhost:' + port);
});
