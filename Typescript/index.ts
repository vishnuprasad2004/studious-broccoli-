
type A = {
    "name" : string,
    "class": number,
    "email": string,
    "hobbies": string[],
    "section":"A"|"B"
}

let b: A =  {
    "name":"Tony Stark",
    "hobbies": ["coding"],
    "email":"something@gmail.com",
    "class":12,
    "section":"A"
}

let a:any = 10
a = "233"

function findmax(a:number, b:number):string {
    return Math.max(a,b).toString();
}


class Observe<T> {
    constructor( value: T) {

    }
}

