package com.teixeira.biblioteca.alpha.controller;

import com.teixeira.biblioteca.alpha.dto.LivroDTO;
import com.teixeira.biblioteca.alpha.service.LivroService;
import com.teixeira.biblioteca.alpha.service.exception.LivroNaoEncontradoException;
import java.util.List;

/**
 *
 * @author lucas
 */
public class LivroController {
    
    private final LivroService service;

    public LivroController() {
        this.service = new LivroService();
    }
    
    public List<LivroDTO> obterLivros() {
        return service.obterLivros();
    }
    
    public LivroDTO verificarSeJaPossuiLivroCadastrado(String isbn){
        return service.pesquisarLivroPorIsbn(isbn);
    }
    
    public void adicionarLivro(String isbn) throws LivroNaoEncontradoException, Exception {
        service.adicionarLivro(isbn);
    }

    public void editarLivro(LivroDTO dto) {      
        service.editarLivro(dto);
    }

    public void excluirLivro(String isbn) throws LivroNaoEncontradoException {
        service.excluirLivro(isbn);
    }
    
}
