package fr.orsys.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, length = 1, name = "TYPE_OP")
@NoArgsConstructor  @Data 
public abstract class Operation implements Serializable {
	public Operation(Date dateOperation, double montant, Compte compte) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long numeriOperation;
	private Date dateOperation;
	private double montant;
	@ManyToOne
	@JoinColumn(name = "CODE_CPTE")
	private Compte compte;
}
