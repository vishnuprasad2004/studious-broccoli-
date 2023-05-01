import chalk from "chalk";
import inquirer from "inquirer";


/*
async function getW() {
    let delhiWeather = new Promise((resolve, reject) => {
            setTimeout(() => {
                    resolve("27 Deg")
            }, 2000)
    })

    let bangaloreWeather = new Promise((resolve, reject) => {
            setTimeout(() => {
                    resolve("21 Deg")
            }, 5000)
    })

    console.log("Fetching Delhi Weather Please wait ...")
    let delhiW = await delhiWeather
    console.log("Fetched Delhi Weather: " + delhiW)
    console.log("Fetching Bangalore Weather Please wait ...")
    let bangaloreW = await bangaloreWeather
    console.log("Fetched Bangalore Weather: " + bangaloreW)
    return [delhiW, bangaloreW];
}
*/


async function getWeather(city) {
    
    try {
        let response = await fetch(`https://goweather.herokuapp.com/weather/%7B${city}%7D`);    
        let data = await response.json();
        if(!response.ok)    throw "Weather not Found";
        return data;
    } catch (error) {
        console.log(chalk.cyan(error));
    }
    
}

const getTemp = async(city) => {
    console.log(chalk.cyan(`Fetching ${city} Weather....`));
    const weatherData =  await getWeather(city);
    const temp = weatherData.temperature;
    
    console.log(chalk.cyan(`Fetched ${city} Weather :${temp} `));
    let details = {
        "temperature":temp,
        "wind":weatherData.wind,
        "type":weatherData.description
    }
    
    /* 
    console.log(`Fetching Chennai Weather....`);
    const chennai = await getWeather("chennai");
    const chennaiTemp = chennai["temperature"];
    console.log(`Fetched Chennai Weather : ${chennaiTemp}`);
    */
   return details;
}



// let a = getTemp("chennai");
// a.then((value) => {
//     console.table(value);
// });

async function main() {
    const answer = await inquirer.prompt({
        name: 'city_name',
        type:'input',
        message:'Enter a City Name:',
        default() {
            return 'Gunupur';
        }
    });
    let city = answer.city_name;
    let a = getTemp(city);
    a.then((value) => {
        console.table(value);
    });
}

main();