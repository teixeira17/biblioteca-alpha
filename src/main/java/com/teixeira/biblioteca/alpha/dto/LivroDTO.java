package com.teixeira.biblioteca.alpha.dto;

import com.teixeira.biblioteca.alpha.model.Livro;
import com.teixeira.biblioteca.alpha.model.LivroJAXB;

/**
 *
 * @author lucas
 */
public class LivroDTO {

    private Long id;
    private String titulo;
    private String autores;
    private String dataPublicacao;
    private String isbn;
    private String editora;

    public LivroDTO() {
    }

    public LivroDTO(String titulo, String autores, String dataPublicacao, String isbn, String editora) {
        this.titulo = titulo;
        this.autores = autores;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
        this.editora = editora;
    }

    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autores = livro.getAutores();
        this.dataPublicacao = livro.getDataPublicacao();
        this.isbn = livro.getIsbn();
        this.editora = livro.getEditora();
    }

    public LivroDTO(LivroJAXB livrojaxb) {
        this.titulo = livrojaxb.getTitulo();
        this.autores = livrojaxb.getAutores();
        this.dataPublicacao = livrojaxb.getDataPublicacao();
        this.isbn = livrojaxb.getIsbn();
        this.editora = livrojaxb.getEditora();
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

}
