var promisKocka = new Promise((resolve,reject)=>{
	var broj = Math.random();
	if(broj>=0.5)setTimeout(()=> resolve("uspeh"),2000)
	else setTimeout(()=>reject("neuspeh"),2000)
});
/*
promisKocka.then((uspeshResponse)=>console.log(uspeshResponse),(failResponse)=>console.log(failResponse));
*/
function dajPromis(){
	return promisKocka;
};
async function vratiRez(){
	console.log("poy");
	try{
		var rez = await dajPromis();
		console.log(rez);
	}
	catch(greska){
		console.log(greska);
	}
}
vratiRez();