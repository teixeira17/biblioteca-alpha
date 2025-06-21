package com.teixeira.biblioteca.alpha.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.teixeira.biblioteca.alpha.dao.LivroDAO;
import com.teixeira.biblioteca.alpha.dto.LivroDTO;
import com.teixeira.biblioteca.alpha.model.Livro;
import com.teixeira.biblioteca.alpha.model.LivroJAXB;
import com.teixeira.biblioteca.alpha.model.LivrosJAXB;
import com.teixeira.biblioteca.alpha.service.exception.ErroImportarArquivoCSVException;
import com.teixeira.biblioteca.alpha.service.exception.ErroImportarArquivoXMLException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author lucas
 */
public class ArquivoService {

    LivroService service = new LivroService();

    public void processarArquivo(File arquivo) throws IOException, FileNotFoundException, CsvValidationException, ErroImportarArquivoCSVException, ErroImportarArquivoXMLException {
        String nome = arquivo.getName().toLowerCase();

        if (nome.endsWith(".csv")) {
            importarCsv(arquivo);
        } else {
            importarXml(arquivo);
        }
    }

    private void importarCsv(File arquivo) throws FileNotFoundException, IOException, CsvValidationException, ErroImportarArquivoCSVException {
        try (CSVReader reader = new CSVReader(new FileReader(arquivo))) {
            String[] linha;
            while ((linha = reader.readNext()) != null) {
                LivroDTO livro = new LivroDTO();
                livro.setTitulo(linha[0]);
                livro.setAutores(linha[1]);
                livro.setDataPublicacao(linha[2]);
                livro.setIsbn(linha[3]);
                livro.setEditora(linha[4]);
                
                LivroDTO livroEditar = service.pesquisarLivroPorIsbn(livro.getIsbn());
                if(livroEditar != null) {
                    service.editarLivro(livro);
                } else {
                    service.adicionarLivro(livro);
                }
                
            }
        } catch (Exception e) {
            throw new ErroImportarArquivoCSVException("<html>Erro ao importar arquivo, verifique o arquivo e importe novamente<br>O arquivo deve conter apenas as informações do livro, separados por vírgula<html>");
        }
    }

    private void importarXml(File arquivo) throws ErroImportarArquivoXMLException {
        try {
            JAXBContext context = JAXBContext.newInstance(LivrosJAXB.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            LivrosJAXB livros = (LivrosJAXB) unmarshaller.unmarshal(arquivo);

            for (LivroJAXB livrojaxb : livros.getLivro()) {
                LivroDTO livro = new LivroDTO(livrojaxb);
                LivroDTO livroExistente = service.pesquisarLivroPorIsbn(livro.getIsbn());
                
                if(livroExistente != null) {
                    service.editarLivro(livro);
                } else {
                    service.adicionarLivro(livro);
                }
            }
        } catch (Exception e) {
            throw new ErroImportarArquivoXMLException("Erro ao importar arquivo, verifique o arquivo e importe novamente");
        }
    }

}
