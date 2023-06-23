if(process.env.NODE_ENV !== 'production') {
    require("dotenv").config();
}

const express = require("express");
const passport = require("passport");
const bcrypt = require("bcrypt");
const flash = require("express-flash");
const session = require("express-session");
const methodOverride = require("method-override");

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
    saveUninitialized: false,
}));
app.use(passport.initialize());
app.use(passport.session());
app.use(methodOverride('_method'));


// routes
app.get('/',checkAuthenticated, (req,res) => {
    res.render("index.ejs", {name: req.user.name});
});

app.get('/register',checkNotAuthenticated,(req,res) => {
    res.render('register.ejs');
});

app.post('/register',checkNotAuthenticated, async(req,res) => {
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

app.get('/login',checkNotAuthenticated, (req,res) => {
    res.render('login.ejs');
});

app.post('/login',checkNotAuthenticated, passport.authenticate('local', {
    successRedirect: '/',
    failureRedirect:'/login', 
    failureFlash: true,
}));

app.delete('/logout',(req,res) => {
    req.logOut();
    res.redirect('/login');
});

function checkAuthenticated(req, res, next) {
    if(req.isAuthenticated()) {
        return next();
    }
    res.redirect('/login');
}

function checkNotAuthenticated(req, res, next) {
    if(req.isAuthenticated()) {
        return res.redirect('/');
    }
    next();
}

app.listen(PORT,()=> console.log(`listening on http://localhost:${PORT}`));
