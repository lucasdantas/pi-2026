package models;

import java.util.Date;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Pessoa extends Model {
	
	public String nome;
	public String email;
	public Date nascimento;

}
