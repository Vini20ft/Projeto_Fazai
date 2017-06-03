function cardapioController($scope, ServiceFazAi, toaster, $interval, $state, $stateParams) {

    $scope.CardapioDados = {};
    $scope.CardapioDados.ItemCardapio = [];
    $scope.CardapioDados.foodTruck = {};
    $scope.listaCardapio = {};
    $scope.foodTrucks = [];
    $scope.listaItemCardapio = {};
    $scope.Pesquisa = false;
    $scope.disabledUpload = false;
    var callAtTimeout = function () { };

    $scope.Pesquisar = function () {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;
        ServiceFazAi.getServiceFoodTruck('/Cardapio/GetAll', getCookie("listaIdFoodTruckFuncionario")).then(function (response) {
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
        ServiceFazAi.getServiceFoodTruck('/FoodTruck/GetAllDropDown', getCookie("listaIdFoodTruckFuncionario")).then(function (response) {
            $scope.foodTrucks = response.data;
            if (edicao === true)
                $scope.GetForId();
        }, function errorCallback(response) {
            $state.go('index.cardapio');
        });
        $scope.Pesquisa = false;
    }

    $scope.Salvar = function (edicao) {
        $scope.verificarOuAtualizarCookies();
        var itemSalvar = $scope.CardapioDados;
        if ($scope.CardapioDados.ItemCardapio == undefined || $scope.CardapioDados.ItemCardapio.length == undefined || $scope.CardapioDados.ItemCardapio.length <= 0) {
            $scope.ToasterMsg(camposObrigatorioItemCardapio, 'warning');
            return;
        }
        if (itemSalvar.IdFoodTruck == undefined) {
            if ($scope.CardapioDados.foodTruck.selected != undefined)
                itemSalvar.IdFoodTruck = $scope.CardapioDados.foodTruck.selected.Id;
        }

        $scope.Pesquisa = true;

        var urlSalvar = '/Cardapio/Insert';
        if (edicao === true) urlSalvar = '/Cardapio/Update';

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
        $state.go('index.cardapioEdicao/:cardapioItem', { cardapioItem: itemLista.Id });
    }

    $scope.ItensCardapioItem = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $state.go('index.cardapioItens/:cardapioItem', { cardapioItem: itemLista.Id });
    }

    $scope.AdicionarFoto = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $state.go('index.cardapioItemAdicionarFoto/:cardapioItem', { cardapioItem: itemLista.Id });
    }

    $scope.ExcluirItem = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;
        ServiceFazAi.removeService('/Cardapio/Delete', itemLista.Id).then(function (response) {
            $scope.ToasterMsg(excluidoSucesso);
            $scope.Pesquisar();
            $scope.Pesquisa = false;
        }, function errorCallback(response) {
            $scope.MsgErro(response, excluidoErro);
            $scope.Pesquisa = false;
        });
    }

    $scope.GetForId = function () {
        $scope.Pesquisa = true;
        ServiceFazAi.getForIdService('/Cardapio/GetForId', $stateParams.cardapioItem).then(function (response) {
            $scope.CardapioDados = response.data;

            $scope.CardapioDados.foodTruck = {};
            $scope.CardapioDados.foodTruck.selected = $scope.foodTrucks.filter(function (elemento) {
                return elemento.Id === $scope.CardapioDados.IdFoodTruck;
            })[0];

            $scope.Pesquisa = false;

        }, function errorCallback(response) {
            $state.go('index.cardapio');
        });
    }

    $scope.AdicionarItemCardapio = function () {
        $scope.verificarOuAtualizarCookies();
        if ($scope.listaItemCardapio.Nome == undefined || $scope.listaItemCardapio.Nome == "" ||
            $scope.listaItemCardapio.Preco == undefined || $scope.listaItemCardapio.Preco == "") {
            $scope.ToasterMsg(camposObrigatorios, 'warning');
            return;
        }
        $scope.CardapioDados.ItemCardapio.push($scope.listaItemCardapio);
        $scope.listaItemCardapio = {};
        $scope.ToasterMsg(adicionadoSucesso, 'success');
    }

    $scope.ExcluirItemCardapio = function (itemLista, editar) {
        $scope.verificarOuAtualizarCookies();

        if (editar === true) {
            ServiceFazAi.removeService('/Cardapio/DeleteItemCardapio', itemLista.Id).then(function (response) {
            }, function errorCallback(response) {
                $scope.MsgErro(response, excluidoErro);
            });
        }

        var index = $scope.CardapioDados.ItemCardapio.indexOf(itemLista);
        if (index != -1) $scope.CardapioDados.ItemCardapio.splice(index, 1);
        $scope.ToasterMsg(excluidoSucesso, 'success');
    }

    $scope.PesquisarInitItemCardapio = function () {
        $scope.GetForId();
    }

    $scope.SalvarFoto = function () {

        var formData = new FormData();
        formData.append('file', $('#foto')[0].files[0]);
        $scope.disabledUpload = true;

        ServiceFazAi.uploadService('/Cardapio/Upload?id=' + $stateParams.cardapioItem, formData).then(function (response) {
            $scope.disabledUpload = false;
            $scope.ToasterMsg(salvoSucesso);
            $state.go('index.cardapioItens/:cardapioItem', { cardapioItem: response.data });
            //$state.go('index.cardapio');
        }, function errorCallback(response) {
            $scope.MsgErro(response, salvoErro);
            $scope.disabledUpload = false;
        });

    }

    $scope.MascaraMoeda = function (objTextBox, SeparadorMilesimo, SeparadorDecimal, e) {
        var sep = 0;
        var key = '';
        var i = j = 0;
        var len = len2 = 0;
        var strCheck = '0123456789';
        var aux = aux2 = '';
        var whichCode = (window.Event) ? e.which : e.keyCode;
        if (whichCode == 13) return true;
        key = String.fromCharCode(whichCode); // Valor para o código da Chave
        if (strCheck.indexOf(key) == -1) return false; // Chave inválida
        len = objTextBox.value.length;
        for (i = 0; i < len; i++)
            if ((objTextBox.value.charAt(i) != '0') && (objTextBox.value.charAt(i) != SeparadorDecimal)) break;
        aux = '';
        for (; i < len; i++)
            if (strCheck.indexOf(objTextBox.value.charAt(i)) != -1) aux += objTextBox.value.charAt(i);
        aux += key;
        len = aux.length;
        if (len == 0) objTextBox.value = '';
        if (len == 1) objTextBox.value = '0' + SeparadorDecimal + '0' + aux;
        if (len == 2) objTextBox.value = '0' + SeparadorDecimal + aux;
        if (len > 2) {
            aux2 = '';
            for (j = 0, i = len - 3; i >= 0; i--) {
                if (j == 3) {
                    aux2 += SeparadorMilesimo;
                    j = 0;
                }
                aux2 += aux.charAt(i);
                j++;
            }
            objTextBox.value = '';
            len2 = aux2.length;
            for (i = len2 - 1; i >= 0; i--)
                objTextBox.value += aux2.charAt(i);
            objTextBox.value += SeparadorDecimal + aux.substr(len - 2, len);
        }
        return false;
    }

    ////////////////////////////////////////////////////
    //Itens comum que deve ter em todos os controllers.
    //Observar os parametros do controller também.
    ////////////////////////////////////////////////////
    $scope.verificarOuAtualizarCookies = function () {
        if (callAtTimeout)
            callAtTimeout = function () { };

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
    .controller("cardapioController", cardapioController);