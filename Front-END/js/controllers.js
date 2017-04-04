/**
 * INSPINIA - Responsive Admin Theme
 *
 */

/**
 * MainCtrl - controller
 */
function MainCtrl() {

    this.usuario = {};
    this.usuario.nome = "Teste usuário";
    this.helloText = 'Bem vindo ao Faz aí';
    this.descriptionText = '';

};


angular
    .module('inspinia')
    .controller('MainCtrl', MainCtrl)