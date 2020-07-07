package fr.orsys.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor  @Data
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	private Long code;
	private String nom;
	private String email;
	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}
	@OneToMany(mappedBy = "client", fetch=FetchType.LAZY)
	private Collection<Compte> comptes;
}
