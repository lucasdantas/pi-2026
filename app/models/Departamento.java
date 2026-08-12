package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Departamento extends Model {
	
	public String nome;
	public Integer ramal;
	
	@ManyToOne
	public Pessoa diretor;
	
}
