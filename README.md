

# Sistema de Web Scraping e Visualização de Dados

## Visão Geral do Projeto
Este projeto é uma aplicação full-stack desenvolvida para automatizar a coleta, processamento e visualização de dados extraídos de arquivos PDF disponíveis na web. Ele integra várias tecnologias, incluindo Java, Python, MySQL e Vue.js, para criar um pipeline contínuo desde a aquisição de dados até a visualização amigável para o usuário.

## Funcionalidades
- **Web Scraping**: Localiza e faz o download automático de arquivos PDF da internet utilizando um scraper baseado em Java.
- **Extração de Dados**: Extrai e organiza dados dos PDFs em tabelas estruturadas no formato CSV usando Python.
- **Armazenamento de Dados**: Armazena os dados processados em um banco de dados MySQL, garantindo integridade e persistência.
- **Comunicação via API**: Uma API baseada em Python facilita a comunicação entre o banco de dados MySQL e a camada de apresentação.
- **Visualização de Dados**: Exibe os dados por meio de uma interface web interativa construída com Vue.js.

## Tecnologias Utilizadas
- **Java**: Web scraping e automação de download de PDFs.
- **Python**: Extração de dados de PDFs, geração de CSV e desenvolvimento da API.
- **MySQL**: Banco de dados relacional para armazenamento e gerenciamento de dados extraídos.
- **Vue.js**: Framework front-end para criar uma interface de usuário dinâmica e interativa.
- **JavaScript**: Melhora a interatividade do front-end em conjunto com o Vue.js.

## Instalação e Configuração

### Pré-requisitos
- Java Development Kit (JDK) 11 ou superior
- Python 3.8 ou superior
- Servidor MySQL
- Node.js e npm (para Vue.js)
- Bibliotecas Python necessárias: `PyPDF2`, `pandas`, `Flask` (ou similar para API)
- Bibliotecas Java necessárias: `Jsoup` (ou similar para scraping)
- Dependências do Vue.js (instaladas via npm)

### Passos
3. **Instalar Dependências**:
   - Para Java:
     ```bash
     cd scraper
     mvn install
     ```
   - Para Vue.js:
     ```bash
     npm install
     ```

4. **Executar a Aplicação**:
   - Inicie o scraper Java:
     
   - Inicie a API Python:
     ```bash
     python app.py
     ```
   - Inicie o front-end Vue.js:
     ```bash
     npm run serve
     ```

5. **Acessar a Aplicação**:
   - Abra o navegador e acesse `http://localhost:8080` (ou a porta especificada pelo servidor Vue.js) para visualizar o painel interativo.

## Uso
1. O scraper Java é executado periodicamente para baixar novos PDFs de fontes web especificadas.
2. O script de extração Python processa os PDFs e gera arquivos CSV.
3. Os dados em CSV são automaticamente inseridos no banco de dados MySQL.
4. A API Python fornece os dados para o front-end Vue.js.
5. Os usuários podem interagir com a interface web para visualizar e explorar os dados.

## Licença
Este projeto está licenciado sob a Licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

## Agradecimentos
Este projeto foi desenvolvido para consolidar conhecimentos em integração de sistemas, processamento de dados e desenvolvimento web moderno. Agradecimentos especiais às comunidades de código aberto por trás de Java, Python, MySQL e Vue.js.



