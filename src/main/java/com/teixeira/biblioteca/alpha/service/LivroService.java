package com.teixeira.biblioteca.alpha.service;

import com.teixeira.biblioteca.alpha.dao.LivroDAO;
import com.teixeira.biblioteca.alpha.dto.LivroDTO;
import com.teixeira.biblioteca.alpha.model.Livro;
import com.teixeira.biblioteca.alpha.service.exception.LivroNaoEncontradoException;
import java.util.List;

/**
 *
 * @author lucas
 */
public class LivroService {
    
    private final LivroDAO dao;
    private final IsbnService service;

    public LivroService() {
        this.dao = new LivroDAO();
        this.service = new IsbnService();
    }
    
    public List<LivroDTO> obterLivros() {
        return dao.findAll();
    }

    public LivroDTO pesquisarLivroPorIsbn(String isbn) {
        return dao.pesquisarLivroPorIsbn(isbn);
    }
    
    public void adicionarLivro(String isbn) throws Exception {
        LivroDTO livroIsbn = service.buscarLivroPorIsbn(isbn);
        Livro livro = new Livro(livroIsbn);
        dao.salvar(livro);
    }
    
    public void adicionarLivro(LivroDTO dto) throws Exception {
        Livro livro = new Livro(dto);
        dao.salvar(livro);
    }

    public void editarLivro(LivroDTO dto) {
        dao.editarLivro(dto);
    }

    public void excluirLivro(String isbn) throws LivroNaoEncontradoException {
        dao.excluirLivro(isbn);
    }
    
}
