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
    var x = await odlozi("start")
    var y = await odlozi(x);
    var z = await odlozi(y);
    var a = await odlozi(z);
    console.log(a);
}

async();
