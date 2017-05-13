function foodtruckController($scope, ServiceFazAi, toaster, $interval, $state, $stateParams) {

    $scope.FoodTruckDados = {};
    $scope.listaFoodTruck = {};
    $scope.foodTrucks = [];
    $scope.listaItemFoodTruck = {};
    $scope.Pesquisa = false;
    $scope.disabledUpload = false;

    $scope.Pesquisar = function () {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;
        ServiceFazAi.getServiceFoodTruck('/FoodTruck/GetAll', getCookie("listaIdFoodTruckFuncionario")).then(function (response) {
            $scope.listaFoodTruck = response.data;
            $scope.Pesquisa = false;
        }, function errorCallback(response) {
            $scope.MsgErro(response, pesquisaErro);
            $scope.Pesquisa = false;
        });
    }

    $scope.CadastroEdicaoInit = function (edicao) {
        $scope.verificarOuAtualizarCookies();
        if (edicao === true)
            $scope.GetForId();
        $scope.Pesquisa = false;
    }

    $scope.Salvar = function (edicao) {
        $scope.verificarOuAtualizarCookies();
        var itemSalvar = angular.copy($scope.FoodTruckDados);

        if (itemSalvar.EmailFuncionario == undefined)
            itemSalvar.EmailFuncionario = getCookie("emailUsuario");

        if ($scope.FoodTruckDados.Estados.selected != undefined)
            itemSalvar.Estado = $scope.FoodTruckDados.Estados.selected.nome;

        $scope.Pesquisa = true;

        var urlSalvar = '/FoodTruck/Insert';
        if (edicao === true) urlSalvar = '/FoodTruck/Update';

        ServiceFazAi.saveService(urlSalvar, itemSalvar).then(function (response) {


            setCookie("emailUsuario", response.data.Email);
            var listaIdFoodTruckFuncionario = '';
            response.data.FoodTruckFuncionario.forEach(function (item, index) {
                if (index != 0)
                    listaIdFoodTruckFuncionario += '|' + (item.IdFoodTruck);
                else
                    listaIdFoodTruckFuncionario += (item.IdFoodTruck);
            });
            setCookie("listaIdFoodTruckFuncionario", listaIdFoodTruckFuncionario);


            $scope.ToasterMsg(salvoSucesso);
            $scope.Pesquisa = false;
            $state.go('index.foodtruck');
        }, function errorCallback(response) {
            $scope.MsgErro(response, salvoErro);
            $scope.Pesquisa = false;
        });
    }

    $scope.EditarItem = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $state.go('index.foodtruckEdicao/:foodtruckItem', { foodtruckItem: itemLista.Id });
    }

    $scope.AdicionarFoto = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $state.go('index.foodtruckAdicionarFoto/:foodtruckItem', { foodtruckItem: itemLista.Id });
    }

    $scope.SalvarFoto = function () {

        var formData = new FormData();
        formData.append('file', $('#foto')[0].files[0]);
        $scope.disabledUpload = true;

        ServiceFazAi.uploadService('/FoodTruck/Upload?id=' + $stateParams.foodtruckItem + '&email=' + getCookie("emailUsuario"), formData).then(function (response) {
            $scope.disabledUpload = false;
            $scope.ToasterMsg(salvoSucesso);
            $state.go('index.foodtruck');
        }, function errorCallback(response) {
            $scope.MsgErro(response, salvoErro);
            $scope.disabledUpload = false;
        });

    }

    $scope.ExcluirItem = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;
        ServiceFazAi.removeService('/FoodTruck/Delete', itemLista.Id + '&email=' + getCookie("emailUsuario")).then(function (response) {
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
        ServiceFazAi.getForIdService('/FoodTruck/GetForId', $stateParams.foodtruckItem).then(function (response) {
            $scope.FoodTruckDados = response.data;

            var estadoSelecionado = $scope.Estados.filter(function (elemento) {
                return elemento.nome === $scope.FoodTruckDados.Estado;
            });
            $scope.FoodTruckDados.Estados = {};
            $scope.FoodTruckDados.Estados.selected = estadoSelecionado[0];

            $scope.Pesquisa = false;
        }, function errorCallback(response) {
            $state.go('index.foodtruck');
        });
    }

    $scope.Estados = [{ nome: "AC" }, { nome: "AL" }, { nome: "AP" }, { nome: "AM" }, { nome: "BA" }, { nome: "CE" },
          { nome: "DF" }, { nome: "ES" }, { nome: "GO" }, { nome: "MA" }, { nome: "MT" }, { nome: "MS" }, { nome: "MG" },
          { nome: "PA" }, { nome: "PB" }, { nome: "PR" }, { nome: "PE" }, { nome: "PI" }, { nome: "RJ" }, { nome: "RN" },
          { nome: "RS" }, { nome: "RO" }, { nome: "RR" }, { nome: "SC" }, { nome: "SP" }, { nome: "SE" }, { nome: "TO" }
    ];

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
    .controller("foodtruckController", foodtruckController);