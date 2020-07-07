package fr.orsys.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("V")
@Data @AllArgsConstructor 
public class Versements extends Operation {
	public Versements( Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

}
