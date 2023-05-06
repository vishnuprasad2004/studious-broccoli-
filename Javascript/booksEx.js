
async function putBooks(data) {

    let res = await fetch(`http://localhost:3000/books`,{
        method:'POST',
        body:JSON.stringify(data),
        headers:{
            'Content-type': 'application/json; charset=UTF-8'
        },
    })
    let msg = await res.json();
    console.log(msg);
}

putBooks({
    id:3,
    title:'until i found you.',
    author:'Lord sgarsgar'
}).then(() => {
    console.log('Done!!');
}).catch(e => {
    console.log(e);
});