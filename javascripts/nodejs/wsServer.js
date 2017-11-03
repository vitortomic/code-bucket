const WebSocket = require('ws');
const wss = new WebSocket.Server({port: 9000});

wss.on('connection', (connection) => {
    connection.on('message', (message)=>{
        console.log("Received message: " + message);
    })
    setInterval(()=>{connection.send("hahahha")},2000);
})