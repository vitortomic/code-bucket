app.factory('imageCachingService', ['fileService','$q', function(fileService, $q){
	
	var cachedImages = new Map();
	var promise = $q.defer();
	
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
imageCachingService.getImageUri(message.sender.imageUrl).then(function(img){
	message.avatarImgUri = img;
})
<img data-ng-src="{{message.avatarImgUri}}" class="md-avatar"/>
