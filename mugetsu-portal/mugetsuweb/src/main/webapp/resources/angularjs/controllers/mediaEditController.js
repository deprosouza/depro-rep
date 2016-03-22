app.controller('mediaEditController', function($http, $templateCache, $scope, $timeout) {
	
	$scope.initVisualizaMedia = function(id) {
		$http({
			method : 'POST',
			url : '../media/' + id,
			headers : {
				'Content-Type' : 'application/json'
			},
			cache : $templateCache
		}).success(function(data) {
			$scope.media = data.response.media;
			$scope.imagem64 = data.response.imagem64;
		});
	},
	
	$scope.updateFromOrign = function(id) {
		$http({
			method : 'POST',
			url : '../media/updateorigem/' + id,
			headers : {
				'Content-Type' : 'application/json'
			},
			cache : $templateCache
		}).success(function(data) {
			$scope.media = data.response.media;
			$scope.imagem64 = data.response.imagem64;
		});
	}
	
});
