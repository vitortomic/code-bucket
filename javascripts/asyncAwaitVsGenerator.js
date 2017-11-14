const fs = require('fs');

/**
 * using async await
 */
const fileReadPromise = ()=>{
    return new Promise((resolve,reject)=>{
        fs.readFile('test.txt','utf8', (error, data)=>{
            if(error) reject(error);
            resolve(data);
        })
    })
}

const readFileAsync = async ()=>{
    try{
        let file = await fileReadPromise();
        console.log(file);
    }
    catch(error){
        console.log(error);
    }
}

readFileAsync();

/**
 * using generators
 */

function thunkify (nodefn) { // [1]
    return function () { // [2]
      var args = Array.prototype.slice.call(arguments)
      return function (cb) { // [3]
        args.push(cb)
        nodefn.apply(this, args)
      }
    }
}

/*
    1. Take an existing Node callback style function as input.
    2. Return a function that converts Node-style into a thunk-style.
    3. Enable the asynchronous function to be execute independently from its initial setup by delaying the execution until its returned function is called.
*/

function run (genFn) {
    var gen = genFn() // [1]
    next() // [2]
  
    function next (er, value) { // [3]
      if (er) return gen.throw(er)
      var continuable = gen.next(value)
  
      if (continuable.done) return // [4]
      var cbFn = continuable.value // [5]
      cbFn(next)
    }
}

/*
    1. Immediately invoke the generator function. This returns a generator in a suspended state.
    2. Then, invoke the next function. We call it right away to tell the generator to resume execution (since next triggers gen.next()).
    3. Notice how next looks just like the Node callback signature (er, value). Every time a thunk completes its asynchronous operation we will call this function.
    4. If there was an error from the asynchronous operation, throw the error back into the generator to be handled there.
    5. If successful, send the value back to the generator. This value gets returned from the yield call.
    6. If we have no more left to do in our generator, then stop by returning early.
    7. If we have more to do, take the value of the next yield and execute it using our next as the callback.
*/


var readFile = thunkify(fs.readFile)

run(function* () {
  try {
    var file = yield readFile('test.txt', 'utf-8')
    console.log(file)
  }
  catch (er) {
    console.error(er)
  }
})
