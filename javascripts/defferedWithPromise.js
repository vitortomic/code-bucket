//pure es6 promise
function promisTest(){
	return new Promise((resolve)=>{
        setTimeout(()=> resolve("hello world"),10000)
    });
}

var promis = promisTest();
promis.then((result)=>console.log(result));


//deffered version
Promise.deffered = function(){
    var result = {};
    result.promise = new Promise((resolve,reject)=>{
        result.resolve = resolve;
        result.reject = reject;
    });
    return result;
};

var deffered = new Promise.deffered();
deffered.promise.then((result)=> console.log(result));

setTimeout(()=>{deffered.resolve("hello world")},10000);

//refactored
//deffered version
Promise.deffered = function(){
    var result = {};
    result.promise = new Promise((resolve,reject)=>{
        result.resolve = resolve;
        result.reject = reject;
    });
    return result;
};

function getPromise(){
    var deffered = new Promise.deffered();
    setTimeout(()=>deffered.resolve("hello world"),1000);
    return deffered.promise;
}

var promis = getPromise();
promis.then((result)=>console.log(result));
