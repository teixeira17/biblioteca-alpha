package com.teixeira.biblioteca.alpha.dao;

import com.teixeira.biblioteca.alpha.dto.LivroDTO;
import com.teixeira.biblioteca.alpha.model.Livro;
import com.teixeira.biblioteca.alpha.service.exception.LivroNaoEncontradoException;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author lucas
 */
public class LivroDAO {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("bibliotecaPU");

    //operações de leitura
    private <R> R executarComEntityManager(Function<EntityManager, R> operacao) {
        EntityManager em = emf.createEntityManager();
        try {
            return operacao.apply(em);
        } finally {
            em.close();
        }
    }

    //operações de transacao/gravacao
    private void executarTransacao(Consumer<EntityManager> operacao) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            operacao.accept(em);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    
    //método auxiliar jpa para buscar livro por isbn
    private Livro buscarLivroPorIsbn(EntityManager em, String isbn) {
        return em.createQuery("SELECT l FROM Livro l WHERE l.isbn = :isbn", Livro.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }

    public List<LivroDTO> findAll() {
        return executarComEntityManager(em -> em.createQuery("SELECT l FROM Livro l", Livro.class)
                .getResultList()
                .stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList()));
    }

    public void salvar(Livro livro) {
        executarTransacao(em -> em.persist(livro));
    }

    public LivroDTO pesquisarLivroPorIsbn(String isbn) {
        return executarComEntityManager(em -> {
            try {
                Livro livro = buscarLivroPorIsbn(em, isbn);
                
                return new LivroDTO(livro);
            } catch (NoResultException e) {
                return null;
            }
        });
    }

    public void editarLivro(LivroDTO dadosEditados) {
        executarTransacao(em -> {
            Livro livro = buscarLivroPorIsbn(em, dadosEditados.getIsbn());

            livro.setTitulo(dadosEditados.getTitulo());
            livro.setAutores(dadosEditados.getAutores());
            livro.setDataPublicacao(dadosEditados.getDataPublicacao());
            livro.setEditora(dadosEditados.getEditora());

            em.merge(livro);
        });
    }

    public void excluirLivro(String isbn) throws LivroNaoEncontradoException {
        executarTransacao(em -> {
            try {
                Livro livro = buscarLivroPorIsbn(em, isbn);
                
                em.remove(em.contains(livro) ? livro : em.merge(livro));
            } catch (NoResultException e) {
                try {
                    throw new LivroNaoEncontradoException("Livro não encontrado");
                } catch (LivroNaoEncontradoException ex) {
                    Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
