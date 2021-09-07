package model;

public class JavaBeans {

	private int idCod;
	private String nome;
	private String fone;
	private String email;
		
	public JavaBeans() {
	}
	
	public JavaBeans(int idCod, String nome, String fone, String email) {
		this.idCod = idCod;
		this.nome = nome;
		this.fone = fone;
		this.email = email;
	}
	public int getIdCod() {
		return idCod;
	}
	public void setIdCod(int i) {
		this.idCod = i;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "idCod = " + idCod + "\n"
			  + "nome = " + nome  + "\n"
			  + "fone = " + fone  + "\n"
			  + "Email= " + email + "\n";
	}
	
}
