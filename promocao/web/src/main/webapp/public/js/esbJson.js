var EsbJson = function(su) {
	var _json = {
		header : {
			service : '',
			requestDate : '',
			companyId : '',
			username : '',
			responseDate : '',
			su : su
		},
		message : {
			criteria : {},
		},
		hash : ''
	};

	this.toString = function() {
		var str = angular.toJson(_json);
		return JSON.stringify(JSON.parse(str));
	};
	
	this.toJson = function() {
		return JSON.parse(angular.toJson(_json));
	};
	
	this.addToSave = function(valor) {
		if (typeof valor === 'string' || typeof valor === 'number'
				|| typeof valor === 'boolean' || typeof valor === 'object') {
			if (typeof _json.message.toSave === 'undefined') {
				_json.message.toSave = {};
			}
			_json.message.toSave = valor;
		} else {
			console.log("Parametro para persistencia invalido.");
		}
	};

	this.addSu = function(su) {
		_json.header.su = su;
	};

	this.addParaemtroToSave = function(chave, valor) {
		if (typeof valor === 'string' || typeof valor === 'number'
				|| typeof valor === 'boolean' || typeof valor === 'object') {
			if (typeof _json.message.toSave === 'undefined') {
				_json.message.toSave = {};
			}
			_json.message.toSave[chave] = valor;
		} else {
			console.log("Parametro para persistencia invalido.");
		}
	};
	
	this.addParametro = function(chave, valor) {
		if (typeof valor === 'string' || typeof valor === 'number'
				|| typeof valor === 'boolean' || typeof valor === 'object') {
			if (typeof _json.message.criteria.criterios === 'undefined') {
				_json.message.criteria.criterios = {};
			}
			_json.message.criteria.criterios[chave] = valor;
		} else {
			console.log("O valor do parametro do criterio informado é do tipo "
					+ typeof valor
					+ ",  só é permitido os tipo: number, string ou boolean");
		}
	};
	
	this.setParametro = function(parametros) {
		if (typeof parametros === 'object') {
			_json.message.criteria.criterios = parametros;
		} else {
			console.log("O valor do parametro deve ser do tipo Object");
		}
	};
	
	this.setCriteria = function(limit, offset, paged, sort, order) {
		if (limit) {
			_json.message.criteria.limit = limit;
		}
		if (typeof offset !== 'undefined') {
			_json.message.criteria.offset = offset;
		}
		if (paged) {
			if (typeof paged === 'boolean') {
				_json.message.criteria.paged = paged;
			} else {
				console.log("O parametro paged deve ser do tipo booleam");
			}
		}
		if (sort) {
			_json.message.criteria.sort = sort;
		}
		if (order) {
			_json.message.criteria.order = order;
		}
	};
	
	this.setLimit = function(limit) {
		_json.message.criteria.limit = limit;
	};
	
	this.setOffset = function(offset) {
		_json.message.criteria.offset = offset;
	};
	
	this.setPaged = function(paged) {
		_json.message.criteria.paged = paged;
	};
	
	this.setSort = function(sort) {
		_json.message.criteria.sort = sort;
	};
	
	this.setOrder = function(order) {
		_json.message.criteria.order = order;
	};
};