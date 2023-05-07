if(process.env.NODE_ENV !== 'production') {
    require("dotenv").config();
}

const express = require("express");
const passport = require("passport");
const bcrypt = require("bcrypt");
const flash = require("express-flash");
const session = require("express-session");

const app = express();
const PORT = 3000;


const initializePassport = require('./passport.config.js');
initializePassport(
    passport, 
    email => users.find(user => user.email === email),
    id => users.find(user => user.id === id)
);

const users = [];


// to use the .ejs files in the views folder
app.set('view-engine', 'ejs');
// to access the inputs given in the form inside the req.body.[name attribute]
app.use(express.urlencoded({ extended: false }));



app.use(flash());
app.use(session({
    secret: process.env.SESSION_SECRET,
    resave: false,
    saveUninitialized: false
}));
app.use(passport.initialize());
app.use(passport.session());



// routes
app.get('/',(req,res) => {
    res.render("index.ejs");
});

app.get('/register',(req,res) => {
    res.render('register.ejs');
});

app.post('/register', async(req,res) => {
    try {
        const hashedPwd = await bcrypt.hash(req.body.password,10);
        users.push({
            id: Date.now().toString(),
            name: req.body.name,
            email: req.body.email,
            password: hashedPwd,
        });
        res.redirect('/login');
    } catch {
        res.redirect('/register');

    }  
    console.log(users);
});

app.get('/login',(req,res) => {
    res.render('login.ejs');
});

app.post('/login',passport.authenticate('local', {
    successRedirect: '/',
    failureRedirect:'/login', 
    failureFlash: true
}));

app.listen(PORT,()=> console.log(`listening on http://localhost:${PORT}`));
