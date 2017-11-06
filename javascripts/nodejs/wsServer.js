const WebSocket = require('ws');
const wss = new WebSocket.Server({port: 9000});

wss.on('connection', (connection) => {
    connection.on('message', (message)=>{
        console.log("Received message: " + message);
    })
    var interval = setInterval(()=>{
        if(connection.OPEN) connection.send("hahahha");
    },2000);
    connection.on('close', (connection) =>{
        console.log("Client disconnected")
        clearInterval(interval);
    })
})