package fr.orsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.orsys.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, String> {
	@Query("select c from Compte c where c.codeCompte=:x ")
	public Compte chercherUn(@Param("x")String codeCpte);
}
