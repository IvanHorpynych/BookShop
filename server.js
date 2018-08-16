
let express = require('express');
let cookieParser = require('cookie-parser');
let app = express();
app.use(cookieParser());

app.get('/registration.html', function(req, res, next) {
    let token = req.cookies['Auth-Token'];
    if (req.cookies['Auth-Token']) {
        res.redirect('/shopfront.html')
    }
    next();
});

app.get('/shopfront.html', function(req, res, next) {
    if (!req.cookies['Auth-Token']) {
        res.redirect('/registration.html')
    }
    next();
});

app.use(express.static('public'));

app.listen(666);
console.log("StaticServer started...");