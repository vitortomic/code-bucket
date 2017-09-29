app.factory('imageCachingService', ['fileService','$q', function(fileService, $q){
	
	var cachedImages = new Map();
	var promiseMap = new Map();
	var promise = $q.defer();
	
	var getImageUrlWithoutExtenstion = function(userImageUrl){
		var dotIndex = userImageUrl.indexOf(".");
		return imageUrlWithoutExtension = userImageUrl.substring(0, dotIndex);
	}
	
	var getImageById = function(userImageUrl){
		return cachedImages.get(userImageUrl);
	}
	
	var cacheImage = function(imgUrl){
		//if promise for the provided url was already made return it
		var savedPromise = promiseMap.get(imgUrl);
		if(savedPromise) return savedPromise;
		//othervise get the img
		var cachePromise = fileService.getFilePromiseBase64(imgUrl).then(function(response){
			cachedImages.set(imgUrl, response);
			return response;
		});
		promiseMap.set(imgUrl, cachePromise);
		return cachePromise;
	}
	
	return {
		getImageUri : function(imgUrl){
			if(!imgUrl){
				return $q.reject("No image url provided");
			}
			var imageUri = getImageById(getImageUrlWithoutExtenstion(imgUrl));
			if(imageUri){
				return $q.resolve(imageUri);
			}
			return cacheImage(getImageUrlWithoutExtenstion(imgUrl));
		}
	};
}]);
//usage
imageCachingService.getImageUri(message.sender.imageUrl)
	.then(function(img){
		message.avatarImgUri = img;
	}, function(error){
		message.avatarImgUri = null;
	}
);
<img ng-show="message.avatarImgUri" data-ng-src="{{message.avatarImgUri}}" class="md-avatar"/>
<img ng-show="!message.avatarImgUri" src="/images/icons/no_picture_big.png" class="md-avatar"/>
