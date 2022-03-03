
var express = require('express');
const path = require('path');
const fs = require('fs');
const app = express();
const { expressCspHeader, NONCE, SELF, INLINE} = require('express-csp-header');

app.use(expressCspHeader({
    directives: {
        'script-src': [SELF, NONCE, INLINE
            ],
        
    }
}));

const distDirectoryPath = path.join(__dirname, './dist/cheHxApp');

app.get('*.*',express.static(distDirectoryPath, {maxAge:'1y'}));

app.all('*', (req,res)=>{
   const nonce1 = req.nonce;
    var html = '';
    const pagePath = path.join('./dist/cheHxApp', 'index.html');
    fs.readFile(pagePath,'utf-8',(err, contents) => {
        html = contents;
        html = html.replace(/<script/g, `<script nonce="${nonce1}"`)
        .replace(/rel="stylesheet"/g, `rel="stylesheet" nonce="${nonce1}"`)
        
        res.send(html);
    });

})


app.listen(8086, '0.0.0.0');
console.log("MyProject Server is Listening on port 8086");
// var express = require('express');
// const path = require('path');
// const fs = require('fs');
// const { expressCspHeader, NONCE, SELF} = require('express-csp-header');
// var app = express(),bodyParser = require("body-parser");;
// app.use(bodyParser.json());
// app.use(expressCspHeader({
//     directives: {
//         'script-src': [NONCE]
//     }
// }));
// app.get('/condenser-listview',  (req, res , next) => {
//     console.log(req.nonce);
//     console.log('reached here');
//     const nonce1 = req.nonce;
//     var html = '';
//     const pagePath = path.join('./dist/cheHxApp', 'index.html');
//     fs.readFile(pagePath,'utf-8',(err, contents) => {
//         html = contents;
//         html = html.replace(/<script/g, `<script nonce="${nonce1}"`)
//         .replace(/rel="stylesheet"/g, `rel="stylesheet" nonce="${nonce1}"`)
        
//         res.send(html);
//     });
    
//     //res.send(HTML);
// });
// app.use(express.static("./dist/cheHxApp")); // myApp will be the same folder name.
// // app.use((req, res) => {
    
    

// app.get('/URl', (req,res) => {
//     console.log('get function')
//     res.send({
//         'heloURl':'https://develop.ctvistaplus.com/hx/api/service-1/hello'
//     });
// });
// app.listen(8080, '0.0.0.0');
// console.log("MyProject Server is Listening on port 8080");