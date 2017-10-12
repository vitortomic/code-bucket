function imageCachingService(fileService){
	var cachedImages = new Map();
	var promiseMap = new Map();
	
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
		//otherwise get the image
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
				return Promise.reject("No image url provided");
			}
			var imageUri = getImageById(getImageUrlWithoutExtenstion(imgUrl));
			if(imageUri){
				return Promise.resolve(imageUri);
			}
			return cacheImage(getImageUrlWithoutExtenstion(imgUrl));
		}
	};
}
