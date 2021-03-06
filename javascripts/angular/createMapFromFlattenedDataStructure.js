 /**
     * returns promise for categories map
     */
    var getCategoriesData = function(categoriesData){
	    return $http.get("/api/prod_categ/selectable")
	    .then(function successCallback (response) {
	    	return getCategoriesMap(response.data)
	    }, function errorCallback (response) {
	    	errorHandlingService.handleError(response);
	    	return {};
	    });
	}
    
    /**
     * maps categories data
     */
    var getCategoriesMap = function(categories){
    	var sectorsMap = new Map();
    	categories.forEach(function(element){
    		if(element.heirarchyLevel == 0){
    			element.categoriesMap = new Map();
        		sectorsMap.set(element.erpId, element);
    		}
    		if(element.heirarchyLevel == 1){
    			element.subCategoriesMap = new Map();
    			sectorsMap.get(element.parentErpId).categoriesMap.set(element.erpId, element);
    		}
    		if(element.heirarchyLevel == 2){
    			for(var key of sectorsMap.keys()){
    				if(sectorsMap.get(key).categoriesMap.get(element.parentErpId)){
    					sectorsMap.get(key).categoriesMap.get(element.parentErpId).subCategoriesMap.set(element.erpId,element);
    					break;
    				}
    			}
    		}
    	});
    	return sectorsMap;
    }
     
    /**
     * converts maps to arrays, to be used in ng-repeat
     */
    var convertCategoriesMapsToArrays = function(categoriesMap){
    	categoriesMap.forEach(function(sector){
    		sector.categoriesMap.forEach(function(category){
    			category.subCategories = convertMapToValueArray(category.subCategoriesMap);
    		});
    		sector.categories = convertMapToValueArray(sector.categoriesMap);
    	});
    	var sectors = convertMapToValueArray(categoriesMap);
    	return sectors;
    }
    
    /**
     * creates a value array from map -> Map(key,value) => value[]
     */
    var convertMapToValueArray = function(map){
    	return Array.from(map, x => x[1]);
    }
    
    /**
     * fetches categories and populates them in the scope
     */
    var populateCategoriesData = function(){
    	var categoriesPromise = getCategoriesData();
    	categoriesPromise.then(function(sectors){
    		$scope.sectors = convertCategoriesMapsToArrays(sectors);
    		console.log($scope.sectors);
    	})
    }
    
    
   //sample category
   
var category = {
  "id": 1336,
  "createdAt": 1486393810632,
  "lastModifiedDate": 1490796603028,
  "version": 4,
  "erpId": 2500047,
  "dUpd": 1490790681330,
  "categoryName": "SVEŽE MESO RNF - MR",
  "code": "MR",
  "parentErpId": 3,
  "heirarchyLevel": 1,
  "deleted": false,
  "description": null,
  "user": null,
  "businessPartnerOffer": null
}
/**
	sectors categories and subcategories are all stored in the same table in the database, The relations are defined by heirarchyLevel and parentErpId fields.
	Sectors have heriarchy level 0, categories 1 and subcategories 2. The parent of subcategory is category, the parent of category is a sector.
	This code constructs a map to properly organize all the category elements and then converts them to arrays so they can be used in combination with ng-repeat to display them in a view.
*/
