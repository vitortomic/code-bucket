 eventLoop(listOfNames[0]);
function eventLoop(name){
          currentPlayer = play(name);
          currentPlayer.on('playing',function(){
          listOfDurations[name] = currentPlayer.duration();
          console.log(listOfDurations);
          eventLoop(listOfNames[++i]);
    });
}