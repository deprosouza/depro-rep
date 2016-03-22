app.controller('mediaConsultaController', function($http, $templateCache, $scope, $timeout) {

	var executando = false;
	$scope.gLetra = null,
	$scope.alfabeto = ['#','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','X','Y','W','Z'],
	
	/**
	 * 
	 */
	$scope.executaBusca = function(params, offset) {
		$http({
			method : 'POST',
			url : '../media/',
			data : $scope.gerarCriteria(false, null, params, offset),
			headers : {
				'Content-Type' : 'application/json'
			},
			cache : $templateCache
		}).success(function(data) {
			$scope.medias = data.response.list;
			$scope.paginator(data.response.size, 100, offset);
		});
	},
	
	/**
	 * 
	 */
	$scope.executaBuscaPorLetra = function(letra, params, offset) {
		$http({
			method : 'POST',
			url : '../media/',
			data : $scope.gerarCriteria(true, letra, params, offset),
			headers : {
				'Content-Type' : 'application/json'
			},
			cache : $templateCache
		}).success(function(data) {
			$scope.medias = data.response.list;
			$scope.paginator(data.response.size, 100, offset);
		});
	},
	
	/**
	 * 
	 */
	$scope.paginator = function(size, limit, offset) {
		$scope.size = size;
		var paginas = Math.ceil(size / limit);
		var paginaAtual = offset ? offset + 1 : 1;
		
		executando = false;
		$('#mediaPaginator_top,#mediaPaginator_bottom').bootpag({
			   total: paginas,
			   page: paginaAtual,
			   maxVisible: 10,
			   leaps: false,
			   firstLastUse: true,
			   first: '←',
			   last: '→',
		}).on('page', function(event, num){
			if (!executando) {
				executando = true;
				
				if ($scope.gLetra) {
					$scope.executaBuscaPorLetra($scope.gLetra, $scope.params, num -1);
				} else {
					$scope.executaBusca($scope.params, num -1);
				}
				
			}
		});
	},
	
	/**
	 * 
	 */
	$scope.gerarCriteria = function(isPorLetra, letra, params, offset) {
		var criteria = {'limit' : 100, 'offset' : 0, 'paged' : true, 'sort': 'titulo.nome'};
		var criterios = {};
		
		if (offset) {
			criteria['offset'] = offset;
		}
		
		if (params) {
			if (params.nome && !isPorLetra) {
				criterios['titulo.nome'] = params.nome;
				$scope.letra = null;
			}
			
			criterios['id'] = params.chave;
			if (params.pathImagem) {
				criterios['pathImagem'] = params.pathImagem;
			}
		}
		
		if (isPorLetra) {
			$scope.gLetra = letra;
			if (letra === '#') {
				criterios['letras'] = ['0','1','2','3','4','5','6','7','8','9','(',')','.','/','\\','+','-','*','!','?','"','\'','@'];
			} else {
				criterios['letras'] = [letra.toLowerCase(), letra.toUpperCase()];
			}
		} else {
			$scope.gLetra = null; 
		}
		
		criterios['genero.id'] = [];
		$('.generos').each(function() {
			if(this.checked) {
				criterios['genero.id'].push($(this).val());
			}
		});
		
		criteria['criterios'] = criterios;
		return criteria;
	}
	
});
