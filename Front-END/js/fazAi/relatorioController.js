function relatorioController($scope, ServiceFazAi, toaster, $interval, $state, $stateParams) {

    this.demo1Data = [34, 43, 43, 35, 44, 32, 44, 52];
    this.demo1Options = {
        type: 'line',
        width: '100%',
        height: '60',
        lineColor: '#1ab394',
        fillColor: "#ffffff"
    };

    //google.charts.load('current', { 'packages': ['bar'] });
    //google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Mês', 'Total vendas'],
          ['Jan', 10],
          ['Fev', 8],
          ['Mar', 25],
          ['Abr', 12],
          ['Mai', 10],
          ['Jun', 17],
          ['Jul', 2],
          ['Ago', 1],
          ['Set', 3],
          ['Out', 8],
          ['Nov', 31],
          ['Dez', 12]
        ]);

        var options = { chart: { title: 'Total de pedidos por mês' } };
        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));
        chart.draw(data, google.charts.Bar.convertOptions(options));
    }

    $scope.pedidoDados = {
        listaIdFoodTruckFuncionario: getCookie("listaIdFoodTruckFuncionario"),
        DataInicial: '',
        DataFinal: '',
        IdFoodTruck: 0
    };
    $scope.listaPedidos = [];
    $scope.Pesquisa = false;
    var callAtTimeout = function () { };

    $scope.Pesquisar = function () {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;
        var itemPesquisa = $scope.pedidoDados;
        itemPesquisa.listaIdFoodTruckFuncionario = getCookie("listaIdFoodTruckFuncionario");
        if ($scope.pedidoDados.foodTruck.selected != undefined) itemPesquisa.IdFoodTruck = $scope.pedidoDados.foodTruck.selected.Id;
        ServiceFazAi.filtroService('/Pedido/GetAllFiltro', itemPesquisa).then(function (response) {
            $scope.listaPedidos = response.data;
            $scope.Pesquisa = false;
        }, function errorCallback(response) {
            $scope.MsgErro(response, pesquisaErro);
            $scope.Pesquisa = false;
        });
    }

    $scope.RelatorioInit = function () {
        $scope.verificarOuAtualizarCookies();

        ServiceFazAi.getServiceFoodTruck('/FoodTruck/GetAllDropDown', getCookie("listaIdFoodTruckFuncionario")).then(function (response) {
            $scope.foodTrucks = response.data;
        }, function errorCallback(response) {
            $state.go('index.main');
        });
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
    .controller("relatorioController", relatorioController);