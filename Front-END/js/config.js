/**
 * INSPINIA - Responsive Admin Theme
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
    $urlRouterProvider.otherwise("/index/main");

    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });

    $stateProvider
        .state('index', {
            abstract: true,
            url: "/index",
            templateUrl: "views/common/content.html",
            data: { pageTitle: 'Login' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.main', {
            url: "/main",
            templateUrl: "views/main.html",
            data: { pageTitle: 'Faz Ai' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                        },
                        {
                            name: 'ui.footable',
                            files: ['js/plugins/footable/angular-footable.js']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('login', {
            url: "/login",
            templateUrl: "views/login.html",
            data: { pageTitle: 'Login' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('register', {
            url: "/register",
            templateUrl: "views/register.html",
            data: { pageTitle: 'Registrar' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.primeiroAcesso', {
            url: "/primeiroAcesso",
            templateUrl: "views/primeiroAcesso.html",
            data: { pageTitle: 'Primeiro Acesso' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.foodtruck', {
            url: "/foodtruck",
            templateUrl: "views/foodtruck.html",
            data: { pageTitle: 'Food truck' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.funcionario', {
            url: "/funcionario",
            templateUrl: "views/Funcionario/funcionario.html",
            data: { pageTitle: 'Funcionário' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                        },
                        {
                            name: 'ui.footable',
                            files: ['js/plugins/footable/angular-footable.js']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.funcionarioCadastro', {
            url: "/funcionarioCadastro",
            templateUrl: "views/Funcionario/funcionarioCadastro.html",
            data: { pageTitle: 'Cadastro de Funcionário' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'ui.select',
                            files: ['js/plugins/ui-select/select.min.js', 'css/plugins/ui-select/select.min.css']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.funcionarioEdicao/:funcionarioItem', {
            url: "/funcionarioEdicao/:funcionarioItem",
            templateUrl: "views/Funcionario/funcionarioEdicao.html",
            data: { pageTitle: 'Cadastro de Funcionário' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            name: 'ui.select',
                            files: ['js/plugins/ui-select/select.min.js', 'css/plugins/ui-select/select.min.css']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.cardapio', {
            url: "/cardapio",
            templateUrl: "views/Cardapio/cardapio.html",
            data: { pageTitle: 'Cardápio' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                        },
                        {
                            name: 'ui.footable',
                            files: ['js/plugins/footable/angular-footable.js']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.cardapioCadastro', {
            url: "/cardapioCadastro",
            templateUrl: "views/Cardapio/cardapioCadastro.html",
            data: { pageTitle: 'Cadastro de Card�pio' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                        },
                        {
                            name: 'ui.footable',
                            files: ['js/plugins/footable/angular-footable.js']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.cardapioEdicao/:cardapioItem', {
            url: "/cardapioEdicao/:cardapioItem",
            templateUrl: "views/Cardapio/cardapioEdicao.html",
            data: { pageTitle: 'Cadastro de Card�pio' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/footable/footable.all.min.js', 'css/plugins/footable/footable.core.css']
                        },
                        {
                            name: 'ui.footable',
                            files: ['js/plugins/footable/angular-footable.js']
                        },
                        {
                            insertBefore: '#loadBefore',
                            name: 'toaster',
                            files: ['js/plugins/toastr/toastr.min.js', 'css/plugins/toastr/toastr.min.css']
                        },
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
        .state('index.relatorio', {
            url: "/relatorio",
            templateUrl: "views/relatorio.html",
            data: { pageTitle: 'Relat�rios' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
         .state('EsqueceuSenha', {
            url: "/EsqueceuSenha",
            templateUrl: "views/EsqueceuSenha.html",
            data: { pageTitle: 'Esqueceu Senha' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            files: ['js/plugins/date.js']
                        }
                    ]);
                }
            }
        })
}
angular
    .module('inspinia')
    .config(config)
    .run(function ($rootScope, $state) {
        $rootScope.$state = $state;
    });
