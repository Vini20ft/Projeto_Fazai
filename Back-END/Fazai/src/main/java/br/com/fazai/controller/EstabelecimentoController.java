package br.com.fazai.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fazai.dominion.Endereco;
import br.com.fazai.dominion.EstabelecimentoMobile;
import br.com.fazai.dominion.Localizacao;
import br.com.fazai.model.Estabelecimento;
import br.com.fazai.service.EstabelecimentoService;

/**
 * 
 * @author vinicius.santana
 *
 *         Essa a classe que o Spring vai gerenciar (Controller para o
 *         estabelecimento)
 *
 * @Controller => informa que a classe � um controller a ser gerenciado pelo
 *             Spring
 *
 * @RequestMapping => caminho para acessar o controller
 */

@Controller
@RequestMapping("/estabelecimento")
public class EstabelecimentoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    public void setEstabelecimentoService(EstabelecimentoService estabelecimentoService) {
	this.estabelecimentoService = estabelecimentoService;
    }

    /**
     * 
     * @RequestMapping => value => Defini o caminho para a chamada da view.
     * @RequestMapping => method => Defini o o metodo http que o m�todo vai
     *                 responder.
     */
    @RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody String Salvar(@RequestBody Estabelecimento estabelecimento) {

	try {

	    if (this.estabelecimentoService != null) {
		this.estabelecimentoService.salvarEstabelecimento(estabelecimento);
		return HttpStatus.CREATED.name().toString();
	    } else {
		return HttpStatus.CONFLICT.name().toString();
	    }

	} catch (Exception e) {
	    return HttpStatus.HTTP_VERSION_NOT_SUPPORTED.name().toString();
	}

    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public @ResponseBody void Alterar(@RequestBody Estabelecimento estabelecimento) {

	try {

	    this.estabelecimentoService.alterarEstabelecimento(estabelecimento);

	} catch (Exception e) {

	}

    }

    @RequestMapping(value = "/consultarTodos", method = RequestMethod.GET)
    public @ResponseBody List<Estabelecimento> ConsultarTodos() {

	return this.estabelecimentoService.todosEstabelecimentos();

    }

    @RequestMapping(value = "/excluirRegistro/{codigo}", method = RequestMethod.DELETE)
    public @ResponseBody void ExcluirRegistro(@PathVariable int codigo) {

	this.estabelecimentoService.Excluir(codigo);

    }

    @RequestMapping(value = "/estabelecimentosList/cidade={cidade}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public @ResponseBody String estabelecimentoList(@PathVariable String cidade) {
	// instancia um novo JSONObject
	JSONObject my_obj = new JSONObject();

	List<Estabelecimento> estabelecimentoList = this.estabelecimentoService.todosEstabelecimentosPorCidade(cidade);
	List<EstabelecimentoMobile> estabelecimentoMobiles = new ArrayList<EstabelecimentoMobile>();

	if (estabelecimentoList.size() > 0) {
	    for (Estabelecimento estabelecimento : estabelecimentoList) {
		EstabelecimentoMobile estabelecimentoMobile = new EstabelecimentoMobile();
		Endereco endereco = new Endereco();
		Localizacao localizacao = new Localizacao();

		estabelecimentoMobile.setNome(estabelecimento.getNome_estabelecimento());
		estabelecimentoMobile.setCnpj(estabelecimento.getCnpj_estabelecimento());
		estabelecimentoMobile.setFoto(estabelecimento.getUrl_image_Estabelecimento());
		localizacao.setLatitude(estabelecimento.getLatitude_estabelecimento());
		localizacao.setLogitude(estabelecimento.getLongitude_estabelecimento());
		endereco.setLocalizacao(localizacao);
		estabelecimentoMobile.setEndereco(endereco);

		estabelecimentoMobiles.add(estabelecimentoMobile);
	    }
	}

	my_obj.put("estabelecimentoList", estabelecimentoMobiles);

	return my_obj.toString();

    }

    @RequestMapping(value = "/estabelecimentoList/codigo={codigo}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public @ResponseBody String estabelecimentoListDetail(@PathVariable int codigo) {
	// instancia um novo JSONObject
	JSONObject my_obj = new JSONObject();

	Estabelecimento estabelecimentoListDetalhe = this.estabelecimentoService
		.consultarEstabelecimentoPorCodigo(codigo);

	List<EstabelecimentoMobile> estabelecimentoMobiles = new ArrayList<EstabelecimentoMobile>();

	if (estabelecimentoListDetalhe.getId_estabelecimento() == codigo) {

	    try {
		EstabelecimentoMobile estabelecimentoMobile = new EstabelecimentoMobile();
		Endereco endereco = new Endereco();
		Localizacao localizacao = new Localizacao();

		estabelecimentoMobile.setNome(estabelecimentoListDetalhe.getNome_estabelecimento());
		estabelecimentoMobile.setCnpj(estabelecimentoListDetalhe.getCnpj_estabelecimento());
		estabelecimentoMobile.setFoto(estabelecimentoListDetalhe.getUrl_image_Estabelecimento());
		estabelecimentoMobile.setRating(estabelecimentoListDetalhe.getRating_estabelecimento());
		localizacao.setLatitude(estabelecimentoListDetalhe.getLatitude_estabelecimento());
		localizacao.setLogitude(estabelecimentoListDetalhe.getLongitude_estabelecimento());
		endereco.setEstado(estabelecimentoListDetalhe.getEstado_estabelecimento());
		endereco.setCidade(estabelecimentoListDetalhe.getCidade_estabelecimento());
		endereco.setLocalizacao(localizacao);
		estabelecimentoMobile.setEndereco(endereco);

		estabelecimentoMobiles.add(estabelecimentoMobile);
	    } catch (Exception e) {
		return e.toString();
	    }

	}

	my_obj.put("estabelecimentoList", estabelecimentoMobiles);

	return my_obj.toString();

    }
}