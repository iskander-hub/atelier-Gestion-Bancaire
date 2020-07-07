package fr.orsys;

import java.util.Date;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import fr.orsys.dao.ClientRepository;
import fr.orsys.dao.CompteRepository;
import fr.orsys.dao.OperationRepository;
import fr.orsys.entities.Client;
import fr.orsys.entities.Compte;
import fr.orsys.entities.CompteCourant;
import fr.orsys.entities.CompteEpargne;
import fr.orsys.entities.Retrait;
import fr.orsys.entities.Versements;

@SpringBootApplication
public class AteliersBanqueApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AteliersBanqueApplication.class, args);
		 ClientRepository clients= ctx.getBean(ClientRepository.class);
		System.out.println("--------Ajout des Clients---------");
		Client client1=new Client();
		client1.setNom("claude");
		client1.setEmail("claude@gmail.com");
		clients.save(client1);
		Client client2=new Client();
		client2.setNom("Fabian");
		client2.setEmail("fabien@gmail.com");
		clients.save(client2);
		
		clients.findAll().forEach(c->{
			System.out.println(c.getNom());
		});
		System.out.println("------Recherche par mots Clés----------");
		List<Client> clts=clients.clientsParMC("%F%");
		clts.forEach(c->{
			System.out.println(c.getNom());
		});
		System.out.println("--------Ajout des Comptes---------");
		 CompteRepository comptes= ctx.getBean(CompteRepository.class);
		 OperationRepository operations=ctx.getBean(OperationRepository.class);
		 Compte cp1 = comptes.save(new CompteCourant("c1", new Date(), 90000, client1, 6000));
			Compte cp2 = comptes.save(new CompteEpargne("c2", new Date(), 6000, client2, 5.5));
			System.out.println("--------Ajout des opérations---------");
			operations.save(new Versements(new Date(), 9000, cp1));
			operations.save(new Versements(new Date(), 6000, cp1));
			operations.save(new Versements(new Date(), 2300, cp1));
			operations.save(new Retrait(new Date(), 9000, cp1));
			
			operations.save(new Versements(new Date(), 2300, cp2));
			operations.save(new Versements(new Date(), 6000, cp2));
			operations.save(new Versements(new Date(), 2000, cp2));
			operations.save(new Retrait(new Date(), 3000, cp2));
	}

}


