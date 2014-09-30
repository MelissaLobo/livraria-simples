package controller;

import javax.faces.bean.ManagedBean;

import model.Autor;
import persistence.LivroDAO;

@ManagedBean
public class AutorController {

	private Autor autor = new Autor();

	public Autor getAutor() {
		return autor;
	}

	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		
		new LivroDAO<Autor>(Autor.class).adiciona(this.autor);
		return "livro?faces-redirect=true";
	}
}
