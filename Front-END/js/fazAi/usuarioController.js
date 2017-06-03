function usuarioController($scope, ServiceFazAi, toaster, $interval, $state) {

    $scope.dadosUsuario = {};
    $scope.bloquearTela = false;
    $scope.liberarAcesso = true;
    var callAtTimeout = function () { };

    $scope.entrar = function () {
        var item = $scope.dadosUsuario;
        $scope.bloquearTela = true;
        var url = "/login/entrar";

        if ($scope.liberarAcesso) {
            setCookie("emailUsuario", item.emailUsuario);
            $state.go('index.main');
        } else {
            ServiceFazAi.filtroService(url, item).then(function (response) {
                $scope.bloquearTela = false;
                setCookie("emailUsuario", item.emailUsuario);
                $state.go('index.main');
            }, function errorCallback(response) {
                $scope.MsgErro(response, loginErro);
                $scope.bloquearTela = false;
                deleteAllCookies();
            });
        }
    }

    ////////////////////////////////////////////////////
    //Itens comum que deve ter em todos os controllers.
    //Observar os parametros do controller também.
    ////////////////////////////////////////////////////
    $scope.verificarOuAtualizarCookies = function () {
        var emailUsuario = getCookie("emailUsuario");
        var dadosFuncionario = getCookie("listaIdFoodTruckFuncionario");
        if (emailUsuario == "") $state.go('login');
        else {
            setCookie("emailUsuario", emailUsuario);
            setCookie("listaIdFoodTruckFuncionario", dadosFuncionario);
        }
    }

    setCookie = function (cname, valor) {
        var d = new Date();
        //console.log("Set cookie - Data Atual: " + d.toUTCString());
        d.add(10).minutes(); // 10 minutos
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + valor + ";" + expires + ";";
        console.log("Set cookie - " + cname + "=" + valor + ";" + expires + ";");
    }

    getCookie = function (cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                console.log("Get cookie - " + c.substring(name.length, c.length));
                return c.substring(name.length, c.length);
            }
        }
        console.log("Get cookie - ");
        return "";
    }

    deleteAllCookies = function () {
        var cookies = document.cookie.split(";");
        for (var i = 0; i < cookies.length; i++) {
            var cookie = cookies[i];
            var eqPos = cookie.indexOf("=");
            var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
            document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
        }
        console.log("Deletar todos os Cookies");
    }

    $scope.ToasterMsg = function (mensagem, tipo) {
        toaster.pop({
            type: tipo,
            body: mensagem,
            showCloseButton: true
        });
    }

    $scope.MsgErro = function (response, mensagem) {
        console.log(response.data);
        if (response.status == 406)
            $scope.ToasterMsg(response.data, 'error');
        else
            $scope.ToasterMsg(mensagem, 'error');
    }
    ////////////////////////////////////////////////////
    //Itens comum que deve ter em todos os controllers.
    //Observar os parametros do controller também.
    ////////////////////////////////////////////////////
}

angular
    .module('inspinia')
    .controller("usuarioController", usuarioController);