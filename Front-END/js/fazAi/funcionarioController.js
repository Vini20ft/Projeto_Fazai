function funcionarioController($scope, ServiceFazAi, toaster, $interval, $state, $stateParams, $timeout) {

    $scope.Funcionario = {};
    $scope.Funcionario.FoodTruck = {};
    $scope.FuncionarioDados = {};
    $scope.foodTrucks = [];
    $scope.Pesquisa = false;
    $scope.bloquearTela = false;
    $scope.liberarAcesso = false;

    $scope.selection = [];

    $scope.toggleSelection = function toggleSelection(item) {
        var ind = undefined;
        $scope.selection.forEach(function (itemList, index) {
            if (itemList.Id === item.Id) {
                ind = index;
                return;
            }
        });

        if (ind != undefined) $scope.selection.splice(ind, 1);
        else $scope.selection.push(item);
    };

    $scope.Entrar = function () {
        var item = $scope.Funcionario;
        $scope.bloquearTela = true;
        $scope.Pesquisa = true;
        var url = "/Funcionario/Entrar";

        if ($scope.liberarAcesso) {
            setCookie("emailUsuario", item.Email);
            $state.go('index.main');
        } else {
            ServiceFazAi.filtroService(url, item).then(function (response) {
                $scope.bloquearTela = false;
                $scope.Pesquisa = false;
                setCookie("emailUsuario", item.Email);
                var listaIdFoodTruckFuncionario = '';
                response.data.FoodTruckFuncionario.forEach(function (item, index) {
                    if (index != 0)
                        listaIdFoodTruckFuncionario += '|' + (item.IdFoodTruck);
                    else
                        listaIdFoodTruckFuncionario += (item.IdFoodTruck);
                });
                setCookie("listaIdFoodTruckFuncionario", listaIdFoodTruckFuncionario);
                $state.go('index.main');
            }, function errorCallback(response) {
                $scope.MsgErro(response, loginErro);
                $scope.bloquearTela = false;
                $scope.Pesquisa = false;
                deleteAllCookies();
            });
        }
    }

    $scope.Pesquisar = function () {
        $scope.verificarOuAtualizarCookies();
        $scope.Pesquisa = true;

        ServiceFazAi.getServiceFoodTruck('/Funcionario/GetAll', getCookie("listaIdFoodTruckFuncionario")).then(function (response) {
            $scope.FuncionarioDados = response.data;
            $scope.Pesquisa = false;
        }, function errorCallback(response) {
            $scope.MsgErro(response, pesquisaErro);
            $scope.Pesquisa = false;
        });
    }

    $scope.CadastroEdicaoInit = function (edicao) {
        $scope.verificarOuAtualizarCookies();

        ServiceFazAi.getServiceFoodTruck('/FoodTruck/GetAllDropDown', getCookie("listaIdFoodTruckFuncionario")).then(function (response) {
            $scope.foodTrucks = response.data;
            if (edicao === true)
                $scope.GetForId();
        }, function errorCallback(response) {
            $state.go('index.funcionario');
        });

        $scope.Pesquisa = false;
    }

    $scope.Salvar = function (edicao, registrar) {
        $scope.verificarOuAtualizarCookies();

        var itemSalvar = $scope.Funcionario;

        if (registrar === true)
            itemSalvar.IdPerfil = 1;
        else {

            if ($scope.Funcionario.Perfis.selected != undefined)
                itemSalvar.IdPerfil = $scope.Funcionario.Perfis.selected.Id;

            if (itemSalvar.IdPerfil == 1 && ($scope.selection == undefined || $scope.selection.length == undefined || $scope.selection.length <= 0)) {
                $scope.ToasterMsg(camposObrigatorioFoodTruckFuncionario, 'warning');
                return;
            }

            itemSalvar.FoodTruckFuncionario = [];

            if (itemSalvar.IdPerfil == 1)
                $scope.selection.forEach(function (item, index) { itemSalvar.FoodTruckFuncionario.push({ IdFoodTruck: item.Id }); });
            else
                itemSalvar.FoodTruckFuncionario.push({ IdFoodTruck: $scope.Funcionario.foodTruck.selected.Id });

            if ($scope.Funcionario.Estados.selected != undefined)
                itemSalvar.Estado = $scope.Funcionario.Estados.selected.nome;
        }

        $scope.Pesquisa = true;

        var urlSalvar = '/Funcionario/Insert';
        if (edicao === true) urlSalvar = '/Funcionario/Update';

        ServiceFazAi.saveService(urlSalvar, itemSalvar).then(function (response) {
            $scope.ToasterMsg(salvoSucesso);
            $scope.Pesquisa = false;
            if (registrar === true) {

                setCookie("emailUsuario", response.data.Email);
                var listaIdFoodTruckFuncionario = '';
                response.data.FoodTruckFuncionario.forEach(function (item, index) {
                    if (index != 0)
                        listaIdFoodTruckFuncionario += '|' + (item.IdFoodTruck);
                    else
                        listaIdFoodTruckFuncionario += (item.IdFoodTruck);
                });
                setCookie("listaIdFoodTruckFuncionario", listaIdFoodTruckFuncionario);

                $state.go('index.primeiroAcesso');
            }
            else
                $state.go('index.funcionario');

            itemSalvar = undefined;
        }, function errorCallback(response) {
            $scope.MsgErro(response, salvoErro);
            $scope.Pesquisa = false;
            itemSalvar = undefined;
        });
    }

    $scope.EditarItem = function (itemLista) {
        $scope.verificarOuAtualizarCookies();
        $state.go('index.funcionarioEdicao/:funcionarioItem', { funcionarioItem: itemLista.Id });
    }

    $scope.ExcluirItem = function (itemLista) {
        $scope.verificarOuAtualizarCookies();

        ServiceFazAi.removeService('/Funcionario/Delete', itemLista.Id).then(function (response) {
            $scope.ToasterMsg(excluidoSucesso);
            $scope.Pesquisar();
        }, function errorCallback(response) {
            $scope.MsgErro(response, excluidoErro);
        });
    }

    $scope.GetForId = function () {
        $scope.Pesquisa = true;
        ServiceFazAi.getForIdService('/Funcionario/GetForId', $stateParams.funcionarioItem).then(function (response) {
            $scope.Funcionario = response.data;

            var estadoSelecionado = $scope.Estados.filter(function (elemento) {
                return elemento.nome === $scope.Funcionario.Estado;
            });
            $scope.Funcionario.Estados = {};
            $scope.Funcionario.Estados.selected = estadoSelecionado[0];

            var perfilSelecionado = $scope.Perfis.filter(function (elemento) {
                return elemento.Id === $scope.Funcionario.IdPerfil;
            });
            $scope.Funcionario.Perfis = {};
            $scope.Funcionario.Perfis.selected = perfilSelecionado[0];

            if ($scope.Funcionario.Perfis.selected.Id == 1) {


                $timeout(function () {
                    $scope.Funcionario.FoodTruckFuncionario.forEach(function (item, index) {
                        //$scope.selection.push({ Id: item.FoodTruck.Id, Nome: item.FoodTruck.Nome });

                        $('#' + item.FoodTruck.Id).prop('checked', true);
                        $scope.toggleSelection({ Id: item.FoodTruck.Id, Nome: item.FoodTruck.Nome });
                    });
                }, 300);

            }
            else {
                $scope.Funcionario.foodTruck = {};
                $scope.Funcionario.foodTruck.selected = $scope.foodTrucks.filter(function (elemento) {
                    return elemento.Id === $scope.Funcionario.FoodTruckFuncionario[0].IdFoodTruck;
                })[0];
            }

            $scope.Pesquisa = false;

        }, function errorCallback(response) {
            $state.go('index.funcionario');
        });
    }

    $scope.Estados = [{ nome: "AC" }, { nome: "AL" }, { nome: "AP" }, { nome: "AM" }, { nome: "BA" }, { nome: "CE" },
          { nome: "DF" }, { nome: "ES" }, { nome: "GO" }, { nome: "MA" }, { nome: "MT" }, { nome: "MS" }, { nome: "MG" },
          { nome: "PA" }, { nome: "PB" }, { nome: "PR" }, { nome: "PE" }, { nome: "PI" }, { nome: "RJ" }, { nome: "RN" },
          { nome: "RS" }, { nome: "RO" }, { nome: "RR" }, { nome: "SC" }, { nome: "SP" }, { nome: "SE" }, { nome: "TO" }
    ];

    $scope.Perfis = [{ Id: 1, Nome: "Gerente" }, { Id: 2, Nome: "Funcionário" }];

    $scope.carregarFunTela = function () {
        $http.get("https://dl.dropbox.com/s/78keffdnvw0yeoy/funcionarios.json").success(function (response) {
            $scope.FuncionarioDados = response;
            $scope.response = response;
            alert("pegou")

        }).error(function (err) {
            console.log(err);
        });
    };

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

};

angular
    .module('inspinia')
    .controller("funcionarioController", funcionarioController);