var promis = new Promise(function(resolve, reject){
	setTimeout(function(){
		resolve("uspeh");
    },10000);
});

promis.then(function(response){
    console.log(response)
});


var promise = new Promise((resolve,reject)=>{
    setTimeout(function(){
        resolve("uspeh");
    },5000);
});

promise.then((response) => { 
    console.log(response);
});

