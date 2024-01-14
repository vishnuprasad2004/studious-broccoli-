import inquirer from "inquirer";


function bubbleSort(array) {
    for (let i = 0; i <= array.length - 1; i++) {
        for (let j = 0; j < (array.length - i - 1); j++) {
            if (array[j] > array[j + 1]) {
                let temp = array[j]
                array[j] = array[j + 1]
                array[j + 1] = temp
            }
        }
    }
}

function merge(arr, l, m, r) {
    let n1 = m - l + 1;
    let n2 = r - m;

    let L = new Array(n1);
    let R = new Array(n2);

    for (let i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (let j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];

    let i = 0;
    let j = 0;
    let k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        }
        else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

function mergeSort(arr, l, r) {
    if (l >= r) {
        return;
    }
    let m = l + parseInt((r - l) / 2);
    mergeSort(arr, l, m);
    mergeSort(arr, m + 1, r);
    merge(arr, l, m, r);
}


function selectionSort(arr) {
    let i, j, min_idx;
    for (i = 0; i < arr.length - 1; i++) {
        min_idx = i;
        for (j = i + 1; j < arr.length; j++)
            if (arr[j] < arr[min_idx])
                min_idx = j;
        let temp = arr[min_idx];
        arr[min_idx] = arr[i];
        arr[i] = temp;
    }
}


function partition(arr, low, high) { 
    let pivot = arr[high]; 
    let i = low - 1; 
    for (let j = low; j <= high - 1; j++) {  
        if (arr[j] < pivot) { 
            i++; 
            [arr[i], arr[j]] = [arr[j], arr[i]];  
        } 
    } 
    [arr[i + 1], arr[high]] = [arr[high], arr[i + 1]];  
    return i + 1; 
} 

function quickSort(arr, low, high) { 
    if (low >= high) return; 
    let pi = partition(arr, low, high); 
  
    quickSort(arr, low, pi - 1); 
    quickSort(arr, pi + 1, high); 
} 




let arr = []
for (let i = 0; i < 10000; i++) {
    arr[i] = Math.floor(Math.random() * 19) + 10
}

console.time("Bubble Sort Time");
bubbleSort(arr)
console.timeEnd("Bubble Sort Time")

console.time("Selection Sort Time");
selectionSort(arr)
console.timeEnd("Selection Sort Time")

console.time("Merge Sort Time");
mergeSort(arr, 0, arr.length)
console.timeEnd("Merge Sort Time")

// console.time("Quick Sort Time");
// quickSort(arr, 0, arr.length)
// console.timeEnd("Quick Sort Time")