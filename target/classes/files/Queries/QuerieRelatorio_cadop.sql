
use relatorios;
SET GLOBAL local_infile=1;
create table relatorio_cadop (
	Registro_ANS int,
    CNPJ bigint,
    Razao_Social text,
    Nome_Fantasia text,
    Modalidade text,
    Logradouro text,
    Numero varchar(20),
    Complemento text,
    Bairro text,
    Cidade text,
    UF text,
    CEP bigint,
	DDD VARCHAR(5),
    Telefone VARCHAR(20), 
    Fax VARCHAR(20),
    Endereco_eletronico text,
    Representante text,
    Cargo_Representante text,
    Regiao_de_Comercializacao int,
    Data_Registro_ANS datetime
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOAD DATA LOCAL INFILE'C:\\webscraping\\src\\main\\java\\files\\mysql_files\\csvZip\\Relatorio_cadop.csv'
INTO TABLE relatorio_cadop
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ';' 
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(@Registro_ANS, @CNPJ, @Razao_Social, @Nome_Fantasia, @Modalidade, @Logradouro, @Numero, 
 @Complemento, @Bairro, @Cidade, @UF, @CEP, @DDD, @Telefone, @Fax, @Endereco_eletronico, 
 @Representante, @Cargo_Representante, @Regiao_de_Comercializacao, @Data_Registro_ANS)
SET 
    Registro_ANS = NULLIF(@Registro_ANS, ''),
    CNPJ = TRIM(REPLACE(@CNPJ, ',', '')), 
    Razao_Social = NULLIF(@Razao_Social, ''),
    Nome_Fantasia = NULLIF(@Nome_Fantasia, ''),
    Modalidade = NULLIF(@Modalidade, ''),
    Logradouro = NULLIF(@Logradouro, ''),
    Numero = NULLIF(@Numero, ''),
    Complemento = NULLIF(@Complemento, ''),
    Bairro = NULLIF(@Bairro, ''),
    Cidade = NULLIF(@Cidade, ''),
    UF = NULLIF(@UF, ''),
    CEP = NULLIF(@CEP, ''),
    DDD = NULLIF(@DDD, ''),
    Telefone = NULLIF(@Telefone, ''),
    Fax = NULLIF(@Fax, ''),
    Endereco_eletronico = NULLIF(@Endereco_eletronico, ''),
    Representante = NULLIF(@Representante, ''),
    Cargo_Representante = NULLIF(@Cargo_Representante, ''),
    Regiao_de_Comercializacao = NULLIF(@Regiao_de_Comercializacao, ''),
	Data_Registro_ANS = NULLIF(@Data_Registro_ANS, '')