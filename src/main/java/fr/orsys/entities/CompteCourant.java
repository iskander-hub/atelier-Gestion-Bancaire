package fr.orsys.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("CC")
@NoArgsConstructor  @Data 
public class CompteCourant extends Compte {
	public CompteCourant(String codeCompte, Date dateCreaction, double solde, Client client,double decouvert) {
		super(codeCompte, dateCreaction, solde, client);
		this.decouvert=decouvert;
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	private double decouvert;

}
