function pedidoController($scope, ServiceFazAi, toaster, $interval, $state, $stateParams, $timeout) {

    $scope.PedidoDados = {};
    $scope.PedidoDados.ItemPedido = [];
    $scope.listaPedido = {};
    $scope.foodTrucks = {};
    $scope.listaItemPedido = [];
    $scope.Pesquisa = false;

    $scope.Pesquisar = function () {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;

        //ServiceFazAi.getService('/Pedido/Pesquisar').then(function (response) {
        //    $scope.listaPedido = response.data;
        //    $scope.Pesquisa = false;
        //}, function errorCallback(response) {
        //    $scope.MsgErro(response, pesquisaErro);
        //    $scope.Pesquisa = false;
        //});

        $scope.listaPedido = listaPedido;
        $scope.Pesquisa = false;
    }

    //setInterval(function () { $scope.Pesquisar(); }, 3000);
    var callAtTimeout = function () { $scope.Pesquisar(); $timeout(callAtTimeout, 60000); }
    $timeout(callAtTimeout, 3000);
    //$interval($scope.Pesquisar(), 3000);

    $scope.CancelarPedido = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;

        //ServiceFazAi.removeService('/Pedido/Delete', itemLista.id).then(function (response) {
        //    $scope.ToasterMsg(canceladoSucesso);
        //    $scope.Pesquisar();
        //    $scope.Pesquisa = false;
        //}, function errorCallback(response) {
        //    $scope.MsgErro(response, excluidoErro);
        //    $scope.Pesquisa = false;
        //});

        var index = $scope.listaPedido.indexOf(itemLista);
        if (index != -1) $scope.listaPedido.splice(index, 1);
        $scope.ToasterMsg(canceladoSucesso, 'success');
        $scope.Pesquisa = false;
    }

    $scope.FinalizarPedido = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;

        //ServiceFazAi.removeService('/Pedido/Finalizar', itemLista.id).then(function (response) {
        //    $scope.ToasterMsg(finalizadoSucesso);
        //    $scope.Pesquisar();
        //    $scope.Pesquisa = false;
        //}, function errorCallback(response) {
        //    $scope.MsgErro(response, excluidoErro);
        //    $scope.Pesquisa = false;
        //});

        var index = $scope.listaPedido.indexOf(itemLista);
        if (index != -1) $scope.listaPedido.splice(index, 1);
        $scope.ToasterMsg(finalizadoSucesso, 'success');
        $scope.Pesquisa = false;
    }

    ////////////////////////////////////////////////////
    //Itens comum que deve ter em todos os controllers.
    //Observar os parametros do controller também.
    ////////////////////////////////////////////////////
    $scope.verificarOuAtualizarCookies = function () {
        var emailUsuario = getCookie("emailUsuario");
        if (emailUsuario == "") $state.go('login');
        else setCookie("emailUsuario", emailUsuario);
    }

    setCookie = function (cname, valor) {
        var d = new Date();
        console.log("Set cookie - Data Atual: " + d.toUTCString());
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
    .controller("pedidoController", pedidoController);