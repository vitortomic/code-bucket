const Worker = require('webworker-threads').Worker

let worker = new Worker(()=>{
    this.onmessage = (message)=>{
        if(message.data == 'EXIT'){
            postMessage('Exiting')
            self.close()
            return
        } 
        postMessage(message.data.text)
    }
})

worker.onmessage = (message)=>console.log(message.data)
worker.postMessage({text: "hehe"})
worker.postMessage("EXIT")

