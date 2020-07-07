package fr.orsys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.orsys.dao.ClientRepository;
import fr.orsys.entities.Client;


@Controller
public class ClientController {
	@Autowired
	private ClientRepository clientRepository;
	
	@RequestMapping("/clients")
	public String indexClient(){
		
		return "clients";
	}
	@RequestMapping("/chercherClients")
	public String consulter(@RequestParam(name="nom")String nom,Model model,
			@RequestParam(name="page",defaultValue="0" )int page,
			@RequestParam(name="size",defaultValue="3" )int size){
		try {
			model.addAttribute("nom",nom);
			
		
			 PageRequest pageable = PageRequest.of(page, size);
			Page<Client> pagesClients=clientRepository.listClients("%"+nom+"%", pageable);
			if(pagesClients==null)throw new RuntimeException("client n'existe pas");
		
			model.addAttribute("listClients", pagesClients.getContent());
			int[] pages= new int[pagesClients.getTotalPages()];
			model.addAttribute("pages",pages);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception",e);
			System.out.println(e.getMessage());
		}
		
		
		
		return "clients";
	}
	

}
