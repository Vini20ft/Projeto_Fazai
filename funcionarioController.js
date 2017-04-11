function funcionarioController($scope, ServiceFazAi, toaster, $interval, $state, $stateParams) {

    
    $scope.funcionario = {};
    $scope.FuncionarioDados = {};
    $scope.Pesquisa = false;


    $scope.ToasterMsg = function (mensagem, tipo) {
        toaster.pop({
            type: tipo,
            body: mensagem,
            showCloseButton: true
        });
    };


   
   
    $scope.CadastroEdicaoInit = function (edicao) {
        $scope.verificarOuAtualizarCookies();
        $scope.FuncionarioDados = funcionario;

        if (edicao === true) {
            $scope.FuncionarioDados = $stateParams.funcionario;
            if ($scope.FuncionarioDados.nome != undefined && $scope.FuncionarioDados.nome != "") {
                var funcionarioSelecionado = $scope.FuncionarioDados.filter(function (elemento) {
                    return elemento.ID === $scope.FuncionarioDados.id;
                });
                $scope.FuncionarioDados.selected = funcionarioSelecionado[0];
            }
            $scope.ToasterMsg('Carregar os dados para editar', 'error');
        }
        else $scope.ToasterMsg('Carregar os dados para cadastrar', 'error');
        $scope.Pesquisa = false;
    }

    $scope.AdicionarFuncionario = function () {
        $scope.verificarOuAtualizarCookies();
        $scope.FuncionarioDados.push($scope.funcionario);
        $scope.funcionario = {};
        $scope.ToasterMsg(adicionadoSucesso, 'success');
    }




    $scope.fazerAlteracao = function (funcionarioTela) {
        resource.get({ id: funcionarioTela.ID  }), function(funcionarioTela) {
            funcionarioTela.$save();
        
        };
    };

    $scope.DeletarFuncionario = function (funcionario) {
        $scope.verificarOuAtualizarCookies();
        ServiceFazAi.removeService('/Funcionario/Delete', funcionario.id).then(function (response) {
            $scope.ToasterMsg(excluidoSucesso);
            $scope.Pesquisar();
        }, function errorCallback(response) {
            $scope.MsgErro(response, excluidoErro);
        });

        /* var index = $scope.listaCardapio.indexOf(itemLista);
         if (index != -1) $scope.listaCardapio.splice(index, 1);
         $scope.ToasterMsg(excluidoSucesso, 'success');*/
    };
        
        

    



             $scope.Estados = [ {nome: "AC"},{nome: "AL"},{nome: "AP"},{nome: "AM"},{nome: "BA"},{nome: "CE"},
                   {nome: "DF"},{nome: "ES"},{nome: "GO"},{nome: "MA"},{nome: "MT"},{nome: "MS"},{nome: "MG"},
                   {nome: "PA"},{nome: "PB"},{nome: "PR"},{nome: "PE"},{nome: "PI"},{nome: "RJ"}, {nome: "RN"},
                   {nome: "RS"},{nome: "RO"},{nome: "RR"},{nome: "SC"},{nome: "SP"},{nome: "SE"},{nome: "TO"}                

               ];

              $scope.Perfis = [ {nome: "Gerente"}, {nome: "Colaborador"}]; 

            
               $scope.carregarFunTela = function (){
                $http.get("https://dl.dropbox.com/s/78keffdnvw0yeoy/funcionarios.json").success(function(response) {
                    $scope.FuncionarioDados = response;
                    $scope.response = response;
                  alert ("pegou")
                  
                  }).error(function(err) {
                  console.log(err);
                   });

               };

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

};


angular
    .module('inspinia')
    .controller("funcionarioController", funcionarioController);