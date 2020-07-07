package fr.orsys.metier;



import java.util.Date;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.orsys.dao.CompteRepository;
import fr.orsys.dao.OperationRepository;
import fr.orsys.entities.Compte;
import fr.orsys.entities.CompteCourant;
import fr.orsys.entities.Operation;
import fr.orsys.entities.Retrait;
import fr.orsys.entities.Versements;




@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {
     @Autowired
	private CompteRepository compteRepository;
     @Autowired
	private OperationRepository operationRepository;
	@Override
	public Compte consulterCompte(String codeCpte) {
		// TODO Auto-generated method stub
		
		
		//Compte cp = compteRepository.getOne(codeCpte);
		Compte cp = compteRepository.chercherUn(codeCpte);
		//Compte cp = compteRepository.findById(codeCpte).orElse(null);
		
		if(cp==null)throw new RuntimeException("compte introuvable");
		return cp;
		
	}

	@Override
	public void verser(String code, double montant) {
		// TODO Auto-generated method stub
		Compte cp=consulterCompte(code);
		Versements v= new Versements(new Date(), montant, cp);
		operationRepository.save(v);
		cp.setSolde(cp.getSolde()+montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void retirer(String code, double montant) {
		// TODO Auto-generated method stub
		Compte cp=consulterCompte(code);
		double faciliteCaisse=0;
		if(cp instanceof CompteCourant)
			faciliteCaisse=((CompteCourant) cp).getDecouvert();
		if((cp.getSolde()+faciliteCaisse)< montant) throw new RuntimeException("solde insuffisant");
		
		Retrait r = new Retrait(new Date(), montant, cp);
		operationRepository.save(r);
		cp.setSolde(cp.getSolde()-montant);
		compteRepository.save(cp);
		
	}

	@Override
	public void virement(String codeCpt1, String codeCpt2, double montant) {
		// TODO Auto-generated method stub
		if(codeCpt1.equals(codeCpt2))throw new RuntimeException("impossible virement sur le meme compte!!!");
		retirer(codeCpt1, montant);
		verser(codeCpt2, montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCpt, int page, int size) {
		// TODO Auto-generated method stub
		 PageRequest pageable = PageRequest.of(page, size);
		return operationRepository.listOperation(codeCpt,pageable);
	}

}
