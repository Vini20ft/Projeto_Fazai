var urlBase = "http://localhost:10079/";
//var urlBase = "http://40.114.51.8/servico";

function ServiceFazAi($http) {
    var _getService = function (controllerAction) {
        var urlRequest = urlBase + controllerAction;
        return $http.get(urlRequest);
    }
    var _getServiceFoodTruck = function (controllerAction, item) {
        var urlRequest = urlBase + controllerAction + '?listaIdFoodTruckFuncionario=' + item;
        return $http.get(urlRequest);
    }
    var _getForIdService = function (controllerAction, item) {
        var urlRequest = urlBase + controllerAction + '?id=' + item;
        return $http.get(urlRequest);
    }
    var _filtroService = function (controllerAction, item) {
        var urlRequest = urlBase + controllerAction;
        return $http.post(urlRequest, item);
    }
    var _saveService = function (controllerAction, item) {
        var urlRequest = urlBase + controllerAction;
        return $http.post(urlRequest, item);
    }
    var _editService = function (controllerAction, item) {
        var urlRequest = urlBase + controllerAction;
        return $http.post(urlRequest, item);
    }
    var _removeService = function (controllerAction, item) {
        var urlRequest = urlBase + controllerAction + '?id=' + item;
        return $http.get(urlRequest);
    }
    var _uploadService = function (controllerAction, data) {
        var urlRequest = urlBase + controllerAction;
        return $http({
            url: urlRequest,
            method: "POST",
            data: data,
            headers: { 'Content-Type': undefined }
        });
    }

    return {
        getService: _getService,
        getServiceFoodTruck: _getServiceFoodTruck,
        getForIdService: _getForIdService,
        filtroService: _filtroService,
        saveService: _saveService,
        editService: _editService,
        removeService: _removeService,
        uploadService: _uploadService
    }
}

angular
    .module('inspinia')
    .factory("ServiceFazAi", ServiceFazAi);