package br.com.fazai.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.fazai.dominion.ItemMobile;
import br.com.fazai.model.ItemCardapio;
import br.com.fazai.service.ItemCardapioService;

@Controller
@RequestMapping("/itemcardapio")
public class ItemCardapioController {

    @Autowired
    private ItemCardapioService itemcardapioServ;

    public void setItemCardapioService(ItemCardapioService itemcardapioServ) {
	this.itemcardapioServ = itemcardapioServ;
    }

    /**
     * 
     * @RequestMapping => value => Defini o caminho para a chamada da view.
     * @RequestMapping => method => Defini o o m�todo http que o m�todo vai
     *                 responder.
     */
   @RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody String Salvar(@RequestBody ItemCardapio itemcardapio) {

	try {


		if (this.itemcardapioServ != null) {
			 this.itemcardapioServ.salvarItemCardapio(itemcardapio);
			return HttpStatus.CREATED.name().toString();
		    } else {
			return HttpStatus.CONFLICT.name().toString();
		    }
		
	   

	} catch (Exception e) {

	    return HttpStatus.HTTP_VERSION_NOT_SUPPORTED.name().toString();
			}

		    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public @ResponseBody void Alterar(@RequestBody ItemCardapio itemcardapio) {

	try {

	    this.itemcardapioServ.alterarItemCardapio(itemcardapio);

	} catch (Exception e) {

	}

    }

    @RequestMapping(value = "/consultarTodos", method = RequestMethod.GET)
    public @ResponseBody List<ItemCardapio> ConsultarTodos() {

	return this.itemcardapioServ.TodosItens();

    }

    @RequestMapping(value = "/itemCardapio/{codigo}", method = RequestMethod.DELETE)
    public @ResponseBody void ExcluirRegistro(@PathVariable int codigo) {

	this.itemcardapioServ.Excluir(codigo);

    }

    @RequestMapping(value = "/itemCardapioList/codigo={codigo}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public @ResponseBody String estabelecimentoListDetail(@PathVariable int codigo) {
	// instancia um novo JSONObject
	JSONObject my_obj = new JSONObject();

	List<ItemCardapio> itensdetalhe = this.itemcardapioServ.todosItemsporCardapio(codigo);
	List<ItemMobile> itensMobile = new ArrayList<ItemMobile>();
	if (itensdetalhe.size() > 0) {
	    try {
		for (ItemCardapio itemCardapio : itensdetalhe) {
			ItemMobile item_mobile = new ItemMobile();
			item_mobile.setCodigo(itemCardapio.getId_item());
			item_mobile.setNome(itemCardapio.getNome());
			item_mobile.setDescricao(itemCardapio.getDescricao());
			item_mobile.setTempo_estimado(itemCardapio.getTempo_estimado());
			item_mobile.setValor(itemCardapio.getValor()); 
			itensMobile.add(item_mobile);
		}
	    } catch (Exception e) {
		return e.toString();
	    }

	}

	my_obj.put("itemCardapio", itensMobile);

	return my_obj.toString();

    }

}
