package com.teixeira.biblioteca.alpha.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas
 */
@XmlRootElement(name = "livro")
public class LivroJAXB {
    
    private String titulo;
    private String autores;
    private String dataPublicacao;
    private String isbn;
    private String editora;
    
    public LivroJAXB() {}
    
    @XmlElement
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    
    @XmlElement
    public String getAutores() { return autores; }
    public void setAutores(String autores) { this.autores = autores; }
    
    @XmlElement
    public String getDataPublicacao() { return dataPublicacao; }
    public void setDataPublicacao(String dataPublicacao) { this.dataPublicacao = dataPublicacao; }
    
    @XmlElement
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    @XmlElement
    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }
    
}
