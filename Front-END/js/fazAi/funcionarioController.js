function funcionarioController($scope, ServiceFazAi, toaster) {

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
        if (edicao === true) $scope.ToasterMsg('Carregar os dados para editar', 'error');
        else $scope.ToasterMsg('Carregar os dados para cadastrar', 'error');
    }

    $scope.SalvarFuncionario = function (edicao) {
        if (edicao === true) alert('Criar lógica para editar', 'error');
        else alert('Criar lógica para cadastrar', 'error');
    }

}

angular
    .module('inspinia')
    .controller("funcionarioController", funcionarioController);