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

import br.com.fazai.dominion.CardapioMobile;
import br.com.fazai.dominion.ItemMobile;
import br.com.fazai.model.Cardapio;
import br.com.fazai.model.ItemCardapio;
import br.com.fazai.service.CardapioService;

@Controller
@RequestMapping("/cardapio")
public class CardapioController {

    /**
     * Injetando o objeto cardapioDAO
     */
    @Autowired
    private CardapioService cardapioServ;

    public void setCardapioService(CardapioService cardapioServ) {
	this.cardapioServ = cardapioServ;
    }

    /**
     * 
     * @RequestMapping => value => Defini o caminho para a chamada da view.
     * @RequestMapping => method => Defini o o m�todo http que o m�todo vai
     *                 responder.
     */
     @RequestMapping(value = "/salvar", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public @ResponseBody String Salvar(@RequestBody Cardapio cardapio) {

	try {

		 if (this.cardapioServ != null) {
			 this.cardapioServ.salvarCardapio(cardapio);
				return HttpStatus.CREATED.name().toString();
			    } else {
				return HttpStatus.CONFLICT.name().toString();
			    }
		
		
	   

	} catch (Exception e) {
		 return HttpStatus.HTTP_VERSION_NOT_SUPPORTED.name().toString();
			}

		    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public @ResponseBody void Alterar(@RequestBody Cardapio cardapio) {

	try {

	    this.cardapioServ.alterarCardapio(cardapio);

	} catch (Exception e) {

	}

    }

    @RequestMapping(value = "/excluirRegistro/{codigo}", method = RequestMethod.DELETE)
    public @ResponseBody void ExcluirRegistro(@PathVariable int codigo) {

	this.cardapioServ.Excluir(codigo);

    }

    @RequestMapping(value = "/cardapioList/codigo={codigo_estabelecimento}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public @ResponseBody String cardapioList(@PathVariable int codigo_estabelecimento) {
	// instancia um novo JSONObject
	JSONObject my_obj = new JSONObject();

	List<Cardapio> cardapioList = this.cardapioServ.todosCardapios(codigo_estabelecimento);
	List<CardapioMobile> cardapiosMobile = new ArrayList<CardapioMobile>();

	if (cardapioList.size() > 0) {
	    for (Cardapio cardapio : cardapioList) {
		CardapioMobile cardapioMobile = new CardapioMobile();
		cardapioMobile.setId(cardapio.getId_cardapio());
		cardapioMobile.setDescricao(cardapio.getDescricao_cardapio());
		cardapioMobile.setFoto(cardapio.getImagem_cardapio_cardapio());

		List<ItemCardapio> itemList = this.cardapioServ.todosItemsPorCardapio(cardapio.getId_cardapio());
		List<ItemMobile> itemMobileList = new ArrayList<ItemMobile>();
		if (itemList.size() > 0) {
		    for (ItemCardapio itemCardapio : itemList) {
			ItemMobile item_mobile = new ItemMobile();
			item_mobile.setCodigo(itemCardapio.getId_item());
			item_mobile.setNome(itemCardapio.getNome());
			item_mobile.setDescricao(itemCardapio.getDescricao());
			item_mobile.setTempo_estimado(itemCardapio.getTempo_estimado());
			item_mobile.setValor(itemCardapio.getValor());
			itemMobileList.add(item_mobile);
		    }
		    cardapioMobile.setItens_cardapio(itemMobileList);
		}else{
		    cardapioMobile.setItens_cardapio(itemMobileList);
		}
		cardapiosMobile.add(cardapioMobile);
	    }   
	}else{
	   return "Cardapio não encontrato"; 
	}
	my_obj.put("cardapioList", cardapiosMobile);
	return my_obj.toString();
    }

}