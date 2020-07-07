package fr.orsys.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.orsys.entities.Compte;
import fr.orsys.entities.Operation;
import fr.orsys.metier.IBanqueMetier;



@Controller
public class CompteController {

	@Autowired
	private IBanqueMetier banqueMetier;
	@RequestMapping("/")
	public String index(){
		
		return "template";
	}
	@RequestMapping("/comptes")
	public String index2(){
		
		return "comptes";
	}
	@RequestMapping("/consulterCompte")
	public String consulter(@RequestParam(name="codeCompte")String codeCpt,Model model,
			@RequestParam(name="page",defaultValue="0" )int page,
			@RequestParam(name="size",defaultValue="3" )int size){
		try {
			model.addAttribute("codeCompte",codeCpt);
			Compte cp =banqueMetier.consulterCompte(codeCpt);
			Page<Operation> pagesOperations=banqueMetier.listOperation(codeCpt, page, size);
			model.addAttribute("listOperations", pagesOperations.getContent());
			int[] pages= new int[pagesOperations.getTotalPages()];
			model.addAttribute("pages",pages);
			
			
			model.addAttribute("compte",cp);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("exception",e);
			System.out.println(e.getMessage());
		}
		
		
		
		return "comptes";
	}
	@RequestMapping(value="/saveOperation",method=RequestMethod.POST)
	public String saveOperation(Model model,String typeOperation,String codeCompte,double montant,String codeCompte2){
		
		//String codeCompte=model.getAttribute("codeCompte").toString();
		try {
			if(typeOperation.equals("VERS")){
				banqueMetier.verser(codeCompte, montant);
			}
			else if(typeOperation.equals("RETR")){
				banqueMetier.retirer(codeCompte, montant);
			}
			else if(typeOperation.equals("VIR")){
				banqueMetier.virement(codeCompte, codeCompte2, montant);
			}
			model.addAttribute("codeCompte",codeCompte);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error",e);
			System.out.println(e.getMessage());
			return "redirect:/consulterCompte?codeCompte="+codeCompte+"&error="+e.getMessage();
		}
	
		//model.addAttribute("codeCompte",codeCompte);
		
		return "redirect:/consulterCompte?codeCompte="+codeCompte;
	}
}
