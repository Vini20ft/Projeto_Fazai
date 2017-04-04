function usuarioController($scope, ServiceFazAi, toaster) {

    $scope.dadosUsuario = {};
    $scope.bloquearTela = false;

    $scope.ToasterMsg = function (mensagem, tipo) {
        toaster.pop({
            type: tipo,
            body: mensagem,
            showCloseButton: true
        });
    };

    $scope.MsgErro = function (response, mensagem) {
        console.log(response.data);
        if (response.status == 406)
            $scope.ToasterMsg(response.data, 'error');
        else
            $scope.ToasterMsg(mensagem, 'error');
    }

    $scope.entrar = function () {
        var item = $scope.dadosUsuario;
        $scope.bloquearTela = true;
        var url = "/login/entrar";

        ServiceFazAi.filtroService(url, item).then(function (response) {
            $scope.bloquearTela = false;
            document.cookie = "emailUsuario=" + item.emailUsuario + ";";
            var decodedCookie = decodeURIComponent(document.cookie);
            console.log(decodedCookie);
            $state.go('index.main');
        }, function errorCallback(response) {
            $scope.MsgErro(response, loginErro);
            $scope.bloquearTela = false;
        });
    };

}

angular
    .module('inspinia')
    .controller("usuarioController", usuarioController);