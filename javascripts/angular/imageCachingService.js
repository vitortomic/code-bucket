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
			return response;
		});
	}
	
	return {
		getImageUri : function(imgUrl){
			var imageUri = getImageById(getImageUrlWithoutExtenstion(imgUrl));
			if(imageUri){
				return $q.resolve(imageUri);
			}
			return cacheImage(getImageUrlWithoutExtenstion(imgUrl));
		}
	};
}]);
