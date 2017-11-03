var express = require('express')
var request = require('request')
var app = express()

app.get('/', function(req,res){
    getText().then(text => res.send(text));
})

function getText(){
    return new Promise(resolve => {
        request('http://app.pluralsight.com/data/search?searchTerm=asdasd', (error,response,body) =>{
            console.log(body);
            resolve(body);
        })
    })
}

app.listen(9000);