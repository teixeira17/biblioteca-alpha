# 📚 Biblioteca Alpha

Aplicação Java para gerenciamento de livros com interface Swing, persistência via JPA, e integração automática com a API da Open Library para cadastro via ISBN. Permite importação de arquivos `.csv` e `.xml`, edição, exclusão e busca de registros em tempo real.

## 🚀 Funcionalidades

- Cadastro automático de livros pelo ISBN via API Open Library
- Consulta e listagem de livros com filtros em tempo real
- Edição e exclusão de livros existentes
- Importação de dados via arquivos XML e CSV
- Armazenamento persistente com JPA (Hibernate)
- Interface gráfica leve usando Java Swing

## 🛠️ Tecnologias

- Java 8
- Maven
- JPA (Hibernate)
- Swing (GUI)
- Jackson (integração com APIs JSON)
- JAXB (importação de arquivos XML)
- OpenCSV (para leitura de arquivos `.csv`)
- Postgres

## 📁 Estrutura

com.teixeira.biblioteca.alpha ├── controller # Controladores de interface ├── dao # Acesso a dados ├── dto # Objetos de transferência de dados ├── model # Entidades JPA ├── service # Lógica de negócio ├── util # Utilitários de importação, parsing, etc. └── view # Telas Swing


## 🧪 Como executar

1. Clone este repositório
2. Compile o projeto com:

   ```bash
   mvn clean package
Execute com:

bash
java -jar target/biblioteca-alpha.jar

3. Use a interface Swing para cadastrar ou importar livros.

📄 Formatos de importação

CSV
csv
O Nome do Vento,Patrick Rothfuss,2007,9788578270692,Arqueiro

XML
xml
<livros>
  <livro>
    <titulo>O Hobbit</titulo>
    <autores>J.R.R. Tolkien</autores>
    <dataPublicacao>1937</dataPublicacao>
    <isbn>9788595084742</isbn>
    <editora>HarperCollins</editora>
  </livro>
</livros>

📚 API Externa
Os dados dos livros são recuperados da Open Library API, a partir do ISBN informado.

👨‍💻 Autor
Desenvolvido por Lucas Teixeira 📍 São Paulo, Brasil
