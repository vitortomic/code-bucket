var fs = require('fs');

const napisiTekstUFajl = function(imeFajla, tekst){
    fs.writeFile(imeFajla, tekst, "UTF-8", ()=>console.log("fajl sacuvan"));
}

napisiTekstUFajl("test.txt", "ovo je test");
/*
const odlozi = function(tekst){
    return new Promise(resolve=>{
        setTimeout(() => resolve(tekst + " " + new Date() + " "), 1000);
    });
}


const piramida = function(){
    odlozi("start")
        .then(x=> odlozi(x)
            .then(y=> odlozi(y)
                .then(z=> odlozi(z)
                    .then(a=> console.log(a))
                )
            )
        )
}

const async = async function test(){
    let x = await odlozi("start")
    let y = await odlozi(x);
    let z = await odlozi(y);
    let a = await odlozi(z);
    console.log(a);
}

async();
*/