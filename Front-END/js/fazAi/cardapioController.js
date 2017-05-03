function cardapioController($scope, ServiceFazAi, toaster, $interval, $state, $stateParams) {

    $scope.CardapioDados = {};
    $scope.CardapioDados.ItemCardapio = [];
    $scope.listaCardapio = {};
    $scope.foodTrucks = {};
    $scope.listaItemCardapio = [];
    $scope.Pesquisa = false;

    $scope.Pesquisar = function () {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;
        ServiceFazAi.getService('/Cardapio/Pesquisar').then(function (response) {
            $scope.listaCardapio = response.data;
            $scope.Pesquisa = false;
        }, function errorCallback(response) {
            $scope.MsgErro(response, pesquisaErro);
            $scope.Pesquisa = false;
        });
        //$scope.listaCardapio = listaCardapio;
    }

    $scope.CadastroEdicaoInit = function (edicao) {
        $scope.verificarOuAtualizarCookies();
        $scope.foodTrucks = listaFoodTrucks;
        if (edicao === true) {
            $scope.CardapioDados = $stateParams.cardapioItem;
            if ($scope.CardapioDados.foodTruck != undefined && $scope.CardapioDados.foodTruck != "") {
                var foodTruckSelecionado = $scope.foodTrucks.filter(function (elemento) {
                    return elemento.id === $scope.CardapioDados.foodTruck.id;
                });
                $scope.CardapioDados.foodTruck.selected = foodTruckSelecionado[0];
            }
            $scope.ToasterMsg('Carregar os dados para editar', 'error');
        }
        else $scope.ToasterMsg('Carregar os dados para cadastrar', 'error');
        $scope.Pesquisa = false;
    }

    $scope.Salvar = function (edicao) {
        $scope.verificarOuAtualizarCookies();
        var itemSalvar = $scope.CardapioDados;
        if ($scope.CardapioDados.ItemCardapio == undefined || $scope.CardapioDados.ItemCardapio.length == undefined || $scope.CardapioDados.ItemCardapio.length <= 0) {
            $scope.ToasterMsg(camposObrigatorioItemCardapio, 'warning');
            return;
        }

        $scope.Pesquisa = true;

        var urlSalvar = '/Cardapio/Insert';
        if (edicao === true) urlSalvar = '/Cardapio/Edit';

        ServiceFazAi.saveService(urlSalvar, itemSalvar).then(function (response) {
            $scope.ToasterMsg(salvoSucesso);
            $scope.Pesquisa = false;
            $state.go('index.cardapio');
        }, function errorCallback(response) {
            $scope.MsgErro(response, salvoErro);
            $scope.Pesquisa = false;
        });
    }

    $scope.EditarItem = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $state.go('index.cardapioEdicao/:cardapioItem', { cardapioItem: itemLista });
    }

    $scope.ExcluirItem = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;
        ServiceFazAi.removeService('/Cardapio/Delete', itemLista.id).then(function (response) {
            $scope.ToasterMsg(excluidoSucesso);
            $scope.Pesquisar();
            $scope.Pesquisa = false;
        }, function errorCallback(response) {
            $scope.MsgErro(response, excluidoErro);
            $scope.Pesquisa = false;
        });

        //var index = $scope.listaCardapio.indexOf(itemLista);
        //if (index != -1) $scope.listaCardapio.splice(index, 1);
        //$scope.ToasterMsg(excluidoSucesso, 'success');
    }

    $scope.AdicionarItemCardapio = function () {
        $scope.verificarOuAtualizarCookies();
        if ($scope.listaItemCardapio.nome == undefined || $scope.listaItemCardapio.nome == "" ||
            $scope.listaItemCardapio.preco == undefined || $scope.listaItemCardapio.preco == "") {
            $scope.ToasterMsg(camposObrigatorios, 'warning');
            return;
        }
        $scope.CardapioDados.ItemCardapio.push($scope.listaItemCardapio);
        $scope.listaItemCardapio = {};
        $scope.ToasterMsg(adicionadoSucesso, 'success');
    }

    $scope.ExcluirItemCardapio = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        var index = $scope.CardapioDados.ItemCardapio.indexOf(itemLista);
        if (index != -1) $scope.CardapioDados.ItemCardapio.splice(index, 1);
        $scope.ToasterMsg(excluidoSucesso, 'success');
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
    .controller("cardapioController", cardapioController);