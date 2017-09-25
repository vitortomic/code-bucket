app.factory('imageCachingService', ['fileService','$q', function(fileService, $q){
	
	var cachedImages = new Map();
	var promise = $q.defer();
	
	//userImageUrl contains file type extension while the GET route only requires the ID string
	var getImageUrlWithoutExtenstion = function(userImageUrl){
		var dotIndex = userImageUrl.indexOf(".");
		return imageUrlWithoutExtension = userImageUrl.substring(0, dotIndex);
	}
	
	var getImageById = function(userImageUrl){
		return cachedImages.get(userImageUrl);
	}
	
	var cacheImage = function(imgUrl){
		return fileService.getFilePromiseBase64(imgUrl).then(function(response){
			cachedImages.set(imgUrl, response);
			return response;
		});
	}
	
	return {
		getImageUri : function(imgUrl){
			//reject promise if no img url is provided
			if(!imgUrl){
				return $q.reject("No image url provided");
			}
			var imageUri = getImageById(getImageUrlWithoutExtenstion(imgUrl));
			//if image is cached in the map wrap it into a promise and return
			if(imageUri){
				return $q.resolve(imageUri);
			}
			//if the image is not cached, return promise for fetching and caching the image
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
