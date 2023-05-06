
// let array = [1,2,3,4,5,6,7,8,9,10];
// console.log(...array);
// array = array.map(el => el*el);


// console.log(...array);
let arr = [3,7,5,1,9,0,4];

function selectionSort(array) {
    for(let i=0; i<array.length-1; i++) {
        for(let j=i+1; j<array.length; j++) {
            if(array[i]>array[j]) {
                [array[i],array[j]] = [array[j],array[i]];
            }
        }
    }

}
// console.log(...arr);
// selectionSort(arr);
// setTimeout(() => {console.log(...arr);},5000);

let student = [{
    name: "John Smith",
    semester: 2,
    age:19,
    greet:function() {
        console.log(`my name is ${this.name} and i am ${this.age} years old.`);
    }
},
{
    name: "Anne Hatheway",
    semester: 2,
    age:27,
    greet:function() {
        console.log(`my name is ${this.name} and i am ${this.age} years old.`);
    }
    
}];

student[1].greet();
