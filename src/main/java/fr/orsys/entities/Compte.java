package fr.orsys.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CPTE", discriminatorType = DiscriminatorType.STRING, length = 2)
@NoArgsConstructor   @ToString
public abstract class Compte implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String codeCompte;
	private Date dateCreaction;
	private double solde;
	@ManyToOne
	@JoinColumn(name = "CODE_CLI")
	private Client client;
	@OneToMany(mappedBy = "compte")
	private Collection<Operation> operations;
	public String getCodeCompte() {
		return codeCompte;
	}
	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}
	public Date getDateCreaction() {
		return dateCreaction;
	}
	public void setDateCreaction(Date dateCreaction) {
		this.dateCreaction = dateCreaction;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Collection<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}
	public Compte(String codeCompte, Date dateCreaction, double solde, Client client
			) {
		super();
		this.codeCompte = codeCompte;
		this.dateCreaction = dateCreaction;
		this.solde = solde;
		this.client = client;
		
	}

	
}
