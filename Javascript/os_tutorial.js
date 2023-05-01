import os from "os";

const user = os.userInfo();

console.log(user);

console.log(os.uptime()/360);

const currentOS = {
    name:os.type(),
    release:os.release(),
    totalMem:os.totalmem(),
    freeMem:os.freemem()
}

console.log(currentOS);