const keys = document.querySelectorAll(".key");
keys.forEach(key => {
    key.onclick = () => {
        key.style.backgroundColor = "white"
        key.style.color = "black"
    }
})