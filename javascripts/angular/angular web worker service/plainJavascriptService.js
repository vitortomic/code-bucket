function scoringService() {
  
  var scoreItems = function (items, weights) {
    return new Promise((resolve)=>{
        var worker = new Worker('/worker-demo/scoring.worker.js');
        var orders = {
            items: items,
            weights: weights
        };
        worker.postMessage(orders);
        worker.onmessage = function (e) {
            if (e.data && e.data.ready) {
                resolve(e.data.items);
            }
        };
    });
    
  };
  var hostObject = {
    scoreItems: function (items, weights) {
      return scoreItems(items, weights);
    }
  };

  return hostObject;

}