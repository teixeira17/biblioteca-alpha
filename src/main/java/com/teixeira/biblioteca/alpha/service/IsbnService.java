package com.teixeira.biblioteca.alpha.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teixeira.biblioteca.alpha.dto.AuthorReference;
import com.teixeira.biblioteca.alpha.dto.AutorResponse;
import com.teixeira.biblioteca.alpha.dto.LivroDTO;
import com.teixeira.biblioteca.alpha.dto.LivroResponse;
import com.teixeira.biblioteca.alpha.service.exception.ErroImportarArquivoCSVException;
import com.teixeira.biblioteca.alpha.service.exception.LivroNaoEncontradoException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas
 */
public class IsbnService {

    private static final String API_URL_LIVRO = "https://openlibrary.org/isbn/";
    private static final String API_URL_AUTHOR = "https://openlibrary.org/";

    private final ObjectMapper mapper = new ObjectMapper();

    public LivroDTO buscarLivroPorIsbn(String isbn) throws LivroNaoEncontradoException, Exception {
        try {
            URL url = new URL(API_URL_LIVRO + isbn + ".json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Verifica se o livro existe na API
            if (conn.getResponseCode() == 404) {
                throw new LivroNaoEncontradoException("Livro não encontrado com o ISBN: " + isbn);
            }

            LivroResponse response = mapper.readValue(conn.getInputStream(), LivroResponse.class);

            String titulo = response.title != null ? response.title : "";
            String dataPublicacao = response.publish_date != null ? response.publish_date : "";
            String editora = (response.publishers != null && !response.publishers.isEmpty() ? response.publishers.get(0) : "");
            List<String> autorKeys = new ArrayList<>();

            if (response.authors != null) {
                for (AuthorReference ref : response.authors) {
                    autorKeys.add(ref.key);
                }
            }

            String nomeAutores = buscarNomesDosAutores(autorKeys);

            return new LivroDTO(titulo, nomeAutores, dataPublicacao, isbn, editora);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new Exception("Erro ao buscar o livro, " + e.getMessage());
        }
    }

    public String buscarNomesDosAutores(List<String> keys) throws Exception {
        List<String> nomesAutores = new ArrayList<>();

        for (String key : keys) {
            try {
                URL url = new URL(API_URL_AUTHOR + key + ".json"); // Monta a URL para buscar o autor
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Verifica se a requisição foi bem-sucedida
                if (conn.getResponseCode() == 404) {
                    throw new ErroImportarArquivoCSVException("Autor não encontrado.");
                }

                AutorResponse autor = mapper.readValue(conn.getInputStream(), AutorResponse.class);
                if (autor.name != null) {
                    nomesAutores.add(autor.name);
                }

            } catch (IOException e) {
                throw new Exception("Erro ao buscar o autor: " + e.getMessage());
            }
        }

        return nomesAutores.isEmpty() ? "" : String.join(", ", nomesAutores); // Retorna "" se autores estiver em branco na OpenLibrary
    }
}
