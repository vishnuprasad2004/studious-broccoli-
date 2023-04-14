import fs from "fs";

// Synchronously reads the entire contents of a file.
const readme = fs.readFileSync("writeMe.txt","utf-8");


console.log("\n"+readme+"\n");

// The mode option only affects the newly created file. See open for more details.
fs.writeFile('writeMe.txt', "hello",);

// Synchronously append data to a file, creating the file if it does not yet exist. data can be a string or a Buffer.
fs.appendFileSync("writeMe.txt",readme);

// Returns true if the path exists, false otherwise.

console.log(fs.existsSync("WriteMe.txt"));

fs.copyFileSync("WEB_DEV_Roadmap.txt","text.txt");

