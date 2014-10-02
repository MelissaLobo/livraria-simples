package controller;

import javax.faces.bean.ManagedBean;

import model.Autor;
import persistence.AutorDAO;

@ManagedBean
public class AutorController {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		new AutorDAO().create(this.autor);
		return "livro?faces-redirect=true";
	}
}
