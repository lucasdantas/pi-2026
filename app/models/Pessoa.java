package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {
	
	public String login;
	public String senha;
	
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
	
	public static boolean existeUsuario(String login, String senha) {
		Pessoa pessoa = Pessoa.find("login = ?1 and senha = ?2", login, senha).first();
		if (pessoa == null) {
			return false;
		} else {
			return true;
		}
	}
	
}
