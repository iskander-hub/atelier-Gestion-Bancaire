package fr.orsys.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CE")
@NoArgsConstructor  @Data
public class CompteEpargne extends Compte{
	public CompteEpargne(String codeCompte, Date dateCreaction, double solde, Client client,
		double taux) {
		super(codeCompte, dateCreaction, solde, client);
		this.taux=taux;
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
private double taux;
}
