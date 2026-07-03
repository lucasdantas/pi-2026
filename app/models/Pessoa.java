package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {
	
	public String nome;
	public String email;
	public Date nascimento;
	
	@ManyToOne
	public Departamento departamento;
	
	@Enumerated(EnumType.STRING)
	public Status status;
	
	public Pessoa() {
		this.status = Status.ATIVO;
	}
	
}
