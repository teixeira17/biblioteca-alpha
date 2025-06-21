package com.teixeira.biblioteca.alpha.model;

import com.teixeira.biblioteca.alpha.dto.LivroDTO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "titulo")
    private String titulo;

    @Column(name = "autores")
    private String autores;

    @Column(name = "data_publicacao")
    private String dataPublicacao;

    @Column(nullable = false, name = "isbn")
    private String isbn;

    @Column(name = "editora")
    private String editora;
    
    public Livro(){}

    public Livro(String titulo, String autores, String dataPublicacao, String isbn, String editora) {
        this.titulo = titulo;
        this.autores = autores;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
        this.editora = editora;
    }

    public Livro(LivroDTO livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autores = livro.getAutores();
        this.dataPublicacao = livro.getDataPublicacao();
        this.isbn = livro.getIsbn();
        this.editora = livro.getEditora();
    }

    public Livro(LivroJAXB livro) {
        this.titulo = livro.getTitulo();
        this.autores = livro.getAutores();
        this.dataPublicacao = livro.getDataPublicacao();
        this.isbn = livro.getIsbn();
        this.editora = livro.getEditora();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "Livro{" + "titulo=" + titulo + ", autores=" + autores + ", dataPublicacao=" + dataPublicacao + ", isbn=" + isbn + ", editora=" + editora + '}';
    }
    
    

}
