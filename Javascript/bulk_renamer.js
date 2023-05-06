// import fs from "fs";
const fs = require("fs");

async function main() {
    try{
        fs.readdir(`./Bulk_renamer`,(err,data) => {
            console.log(data);
            for(let i=0; i< data.length; i++) {
                let item = data[i];
                let newFile = './Bulk_renamer/'+ item.replaceAll("nothing","everything");
                fs.rename(`./Bulk_renamer/${item}`,newFile, () => {
                    console.log(item + " rename Successful");
                });
            }
        })
    }catch(err) {
        console.log(err);
    }
}

main();
