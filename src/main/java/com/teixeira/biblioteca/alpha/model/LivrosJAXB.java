package com.teixeira.biblioteca.alpha.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lucas
 */
@XmlRootElement(name = "livros")
public class LivrosJAXB {
    
    private List<LivroJAXB> livro;
    
    @XmlElement(name = "livro")
    public List<LivroJAXB> getLivro() {
        return livro;
    }
    
    public void setLivro(List<LivroJAXB> livro) {
        this.livro = livro;
    }
    
}
