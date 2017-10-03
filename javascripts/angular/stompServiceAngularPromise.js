app.factory('stompService', ['$q', '$timeout', '$location', '$localStorage', function($q, $timeout, $location, $localStorage){
	var listener = $q.defer();
	var connected = $q.defer();
	var socket = {
			client: null,
			stomp: null
	}
	var service = {
			connect : function(hospitalId, groupId){
				initialize(hospitalId, groupId);
			},
			receive : function(){
				return listener.promise;
			},
			send : function(message){
				var messageForSend = angular.copy(message);
				if(messageForSend.avatarImgUri) messageForSend.avatarImgUri = null;
				connected.promise.then(function(){
					socket.stomp.send("/api/v1/chat/messages", {}, JSON.stringify(messageForSend));
				});				
			},
			isConnected : function(){
				if(!socket || !socket.stomp) return false;
				return socket.stomp.connected;
			},
			disconnect : function(){
				if(socket && socket.stomp){
					socket.stomp.disconnect(function(){
						console.log("Socket disconnected");
					})
				}
			}
	};
	
	service.SOCKET_URL = $location.absUrl().replace($location.path(),"/chat").replace("/home#!","");
	service.RECONNECT_TIMEOUT = 30000;
	
	var subscribeCallback = function(hospitalId, groupId){
		connected.resolve();
		// check user type u localstorage.userdetails and then filter 
		
		var currentUser = JSON.parse(localStorage.getItem("careUpdate.userDetails"));
		
		// in terms of subscription primary contact and receiver are the same user
		var patientPrimary = currentUser.isPatient || currentUser.isPrimaryContact;
		
		var sender = currentUser.role === 'Sender';
		
		// subscribe user only on its topics:
		
		if(patientPrimary || sender){
			socket.stomp.subscribe("/topic/careupdate." + hospitalId
					+ '.' + groupId + ".updates.urgent", function(message){
				listener.notify(getMessage(message));
			});
			if(patientPrimary){
				socket.stomp.subscribe("/topic/careupdate." + hospitalId
						+ '.' + groupId + ".updates.urgent.patient.primary", function(message){
					listener.notify(getMessage(message));
				});
			}
		}
		
		if(!sender){
			socket.stomp.subscribe("/topic/careupdate." + hospitalId
					+ '.' + groupId + ".messages", function(message){
				listener.notify(getMessage(message));
			});
			socket.stomp.subscribe("/topic/careupdate." + hospitalId
					+ '.' + groupId + ".updates.patient.contact", function(message){
				listener.notify(getMessage(message));
			});
		}

		socket.stomp.subscribe("/topic/careupdate." + hospitalId
			    + '.' + groupId + ".updates", function(message){
			listener.notify(getMessage(message));
		});
	};
	
	var getMessage = function(data) {
	      var message = JSON.parse(data.body), out = {};
	      out.message = message;
	      return out;
	};
	
	var reconnect = function(){
		$timeout(function(){
			initialize();
		}, this.RECONNECT_TIMEOUT);
	}

	var initialize = function(hospitalId, groupId){
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.connect({}, subscribeCallback.bind(null, hospitalId, groupId));
		socket.stomp.onclose = reconnect;
	};
	
	return service;
}]);
