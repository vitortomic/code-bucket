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
