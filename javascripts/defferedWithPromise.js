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
