(function(window){
	function authService(){
		
		var secretPrefix = "totp_secret_key_prefix_careupdate";
		
		var getUuid = function() {
			  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
			    var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
			    return v.toString(16);
			  });
		}
	
		var dec2hex = function(s) { return (s < 15.5 ? '0' : '') + Math.round(s).toString(16); }
		
		var hex2dec = function(s) { return parseInt(s, 16); }
	
		var base32tohex = function(base32) {
		    var base32chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
		    var bits = "";
		    var hex = "";
	
		    for (var i = 0; i < base32.length; i++) {
		        var val = base32chars.indexOf(base32.charAt(i).toUpperCase());
		        bits += leftpad(val.toString(2), 5, '0');
		    }
	
		    for (var i = 0; i+4 <= bits.length; i+=4) {
		        var chunk = bits.substr(i, 4);
		        hex = hex + parseInt(chunk, 2).toString(16) ;
		    }
		    return hex;
	
		}
	
		var leftpad = function(str, len, pad) {
		    if (len + 1 >= str.length) {
		        str = Array(len + 1 - str.length).join(pad) + str;
		    }
		    return str;
		}
	
		var generateOtpForKey = function(key){
			key = base32tohex(key);
			var epoch = Math.round(new Date().getTime() / 1000.0);
		    var time = leftpad(dec2hex(Math.floor(epoch / 30)), 16, '0');
	
		    // updated for jsSHA v2.0.0 - http://caligatio.github.io/jsSHA/
		    var shaObj = new jsSHA("SHA-1", "HEX");
		    shaObj.setHMACKey(key, "HEX");
		    shaObj.update(time);
		    var hmac = shaObj.getHMAC("HEX");
	
		    
		    var offset = hex2dec(hmac.substring(hmac.length - 1));
		        
	
		    var otp = (hex2dec(hmac.substr(offset * 2, 8)) & hex2dec('7fffffff')) + '';
		    otp = (otp).substr(otp.length - 6, 6);
	
		   	console.log(otp);
			return otp;
		}
	
		var generateOtp = function(secretPrefix, userName, deviceId, smsOtp){
			return generateOtpForKey(encodeToBase32(secretPrefix + userName + deviceId + smsOtp));
		}
		
		var encodeToBase32 = function(string){
			 /* encodes a string s to base32 and returns the encoded string */
		    var alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

		    var parts = [];
		    var quanta= Math.floor((s.length / 5));
		    var leftover = s.length % 5;

		    if (leftover != 0) {
		       for (var i = 0; i < (5-leftover); i++) { s += '\x00'; }
		       quanta += 1;
		    }

		    for (i = 0; i < quanta; i++) {
		       parts.push(alphabet.charAt(s.charCodeAt(i*5) >> 3));
		       parts.push(alphabet.charAt( ((s.charCodeAt(i*5) & 0x07) << 2)
		           | (s.charCodeAt(i*5+1) >> 6)));
		       parts.push(alphabet.charAt( ((s.charCodeAt(i*5+1) & 0x3F) >> 1) ));
		       parts.push(alphabet.charAt( ((s.charCodeAt(i*5+1) & 0x01) << 4)
		           | (s.charCodeAt(i*5+2) >> 4)));
		       parts.push(alphabet.charAt( ((s.charCodeAt(i*5+2) & 0x0F) << 1)
		           | (s.charCodeAt(i*5+3) >> 7)));
		       parts.push(alphabet.charAt( ((s.charCodeAt(i*5+3) & 0x7F) >> 2)));
		       parts.push(alphabet.charAt( ((s.charCodeAt(i*5+3) & 0x03) << 3)
		           | (s.charCodeAt(i*5+4) >> 5)));
		       parts.push(alphabet.charAt( ((s.charCodeAt(i*5+4) & 0x1F) )));
		    }

		    var replace = 0;
		    if (leftover == 1) replace = 6;
		    else if (leftover == 2) replace = 4;
		    else if (leftover == 3) replace = 3;
		    else if (leftover == 4) replace = 1;

		    for (i = 0; i < replace; i++) parts.pop();
		    for (i = 0; i < replace; i++) parts.push("=");

		    return parts.join("");
		}
		
		var getDeviceId = function(){
			//if device id is not saved, generate and save a new one
			var deviceId = localStorage.getItem("deviceId");
			if(!deviceId){
				deviceId = getUuid();
				localStorage.setItem("deviceId", deviceId);
			}
			return deviceId;
		}
		
		var service = {
				generateUUID : function(){
					return getUuid();
				},
				getSecondFactorToken : function(username){
					var smsOtp = localStorage.getItem("smsOtp");
					if(!smsOtp) return null;
					
					var deviceId = getDeviceId();
					
					return generateOtp(secretPrefix, username, deviceId, smsOtp);
				},
				testOtp : function(key){
					generateOtpForKey(key);
				},
				getDeviceId : function(){
					return getDeviceId();
				}
		}
		
		return service;
	}

	window.authService = authService();
})(window);
