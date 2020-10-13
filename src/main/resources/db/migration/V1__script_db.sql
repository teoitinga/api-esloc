-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- -----------------------------------------------------
-- Schema jp-esloc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jp-esloc` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `jp-esloc` ;

-- -----------------------------------------------------
-- Table `jp-esloc`.`empresa_ater`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`empresa_ater` (
  `cnpj` VARCHAR(255) NOT NULL,
  `endereco` VARCHAR(255) NULL DEFAULT NULL,
  `nome_empresa` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`cnpj`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`uregi`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`uregi` (
  `cpi` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `endereco` VARCHAR(255) NULL DEFAULT NULL,
  `fonefixo` VARCHAR(255) NULL DEFAULT NULL,
  `municipio` VARCHAR(255) NULL DEFAULT NULL,
  `empresa_cnpj` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`cpi`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FK2t6rxughmkdmwq65b6vrjxth3` ON `jp-esloc`.`uregi` (`empresa_cnpj` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`esloc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`esloc` (
  `cpi` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `endereco` VARCHAR(255) NULL DEFAULT NULL,
  `fonefixo` VARCHAR(255) NULL DEFAULT NULL,
  `municipio` VARCHAR(255) NULL DEFAULT NULL,
  `uregi_cpi` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`cpi`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKpa4mt682umbuf533jxq2a3nl3` ON `jp-esloc`.`esloc` (`uregi_cpi` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`persona` (
  `cpf` VARCHAR(255) NOT NULL,
  `atualizacao` DATE NULL DEFAULT NULL,
  `cadastro` DATE NULL DEFAULT NULL,
  `categoria` VARCHAR(255) NULL DEFAULT NULL,
  `conselho_registro` VARCHAR(255) NULL DEFAULT NULL,
  `contato` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `emissor` VARCHAR(255) NOT NULL,
  `escolaridade` INT(11) NULL DEFAULT NULL,
  `index_conclusao` INT(11) NULL DEFAULT NULL,
  `municipio` VARCHAR(255) NULL DEFAULT NULL,
  `nascimento` DATE NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `sexo` VARCHAR(255) NULL DEFAULT NULL,
  `cpf_emissor` VARCHAR(255) NULL DEFAULT NULL,
  `endereco_residencial` VARCHAR(255) NULL DEFAULT NULL,
  `produtores_codigo` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKg47vh0tibykfokq0j1giay8eu` ON `jp-esloc`.`persona` (`produtores_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`tecnico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`tecnico` (
  `matricula` VARCHAR(255) NOT NULL,
  `conselho` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `permissao` VARCHAR(255) NULL DEFAULT NULL,
  `registro` VARCHAR(255) NULL DEFAULT NULL,
  `agente_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `esloc_cpi` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`matricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKgrofks5xbbq3dmwb8t8yy2flk` ON `jp-esloc`.`tecnico` (`agente_cpf` ASC) VISIBLE;

CREATE INDEX `FKg1nu2cd1it7hkws4x1n2cvub2` ON `jp-esloc`.`tecnico` (`esloc_cpi` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`atendimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`atendimento` (
  `codigo` VARCHAR(255) NOT NULL,
  `data_atendimento` DATE NULL DEFAULT NULL,
  `atualizacao` DATE NULL DEFAULT NULL,
  `cadastro` DATE NULL DEFAULT NULL,
  `emissor` VARCHAR(255) NOT NULL,
  `publicar` VARCHAR(255) NULL DEFAULT NULL,
  `recomendacoes` VARCHAR(255) NULL DEFAULT NULL,
  `status_atendimento` VARCHAR(255) NULL DEFAULT NULL,
  `responsavel_tecnico_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `atendimento_data` DATE NULL DEFAULT NULL,
  `publico` VARCHAR(255) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `emissor_matricula` VARCHAR(255) NOT NULL,
  `propriedade_rural_codigo` VARCHAR(255) NOT NULL,
  `responsavel_tecnico_matricula` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKss91jlt3oe1cre1frqiyobbs8` ON `jp-esloc`.`atendimento` (`responsavel_tecnico_cpf` ASC) VISIBLE;

CREATE INDEX `FK3innis6wqldqmc200oybgtrcd` ON `jp-esloc`.`atendimento` (`emissor_matricula` ASC) VISIBLE;

CREATE INDEX `FKf9m3vmt0oa4eabfmel56tjwup` ON `jp-esloc`.`atendimento` (`responsavel_tecnico_matricula` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`atendimento_produtores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`atendimento_produtores` (
  `atendimento_codigo` VARCHAR(255) NOT NULL,
  `produtores_cpf` VARCHAR(255) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FK5d4vop1aenqgek5e2pfd6dni8` ON `jp-esloc`.`atendimento_produtores` (`produtores_cpf` ASC) VISIBLE;

CREATE INDEX `FKt848waum227fkfpa7ycemo2mb` ON `jp-esloc`.`atendimento_produtores` (`atendimento_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`bancos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`bancos` (
  `prefixo_agencia` VARCHAR(255) NOT NULL,
  `atualizacao` DATE NULL DEFAULT NULL,
  `cadastro` DATE NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `endereco` VARCHAR(255) NULL DEFAULT NULL,
  `fone_comercial` VARCHAR(255) NULL DEFAULT NULL,
  `fone_gerente` VARCHAR(255) NULL DEFAULT NULL,
  `municipio` VARCHAR(255) NULL DEFAULT NULL,
  `nome_agencia` VARCHAR(255) NULL DEFAULT NULL,
  `nome_gerente` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`prefixo_agencia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`boi_price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`boi_price` (
  `codigo` VARCHAR(255) NOT NULL,
  `cadastro` DATETIME(6) NULL DEFAULT NULL,
  `categoria_animal` VARCHAR(255) NULL DEFAULT NULL,
  `valor` DECIMAL(8,2) NULL DEFAULT NULL,
  `emissor_matricula` VARCHAR(255) NULL DEFAULT NULL,
  `propriedade_info_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKlxbfcc9aautunkqygf8vjaxv1` ON `jp-esloc`.`boi_price` (`emissor_matricula` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`conversao_temporal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`conversao_temporal` (
  `sigla` VARCHAR(255) NOT NULL,
  `conv_ano` DECIMAL(8,2) NULL DEFAULT NULL,
  `conv_dia` DECIMAL(8,2) NULL DEFAULT NULL,
  `conv_mes` DECIMAL(8,2) NULL DEFAULT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`sigla`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`documento_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`documento_type` (
  `codigo` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`documento` (
  `codigo` VARCHAR(255) NOT NULL,
  `atualizacao` DATETIME(6) NULL DEFAULT NULL,
  `cadastro` DATETIME(6) NULL DEFAULT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `atendimento_codigo` VARCHAR(255) NULL DEFAULT NULL,
  `documento_codigo` VARCHAR(255) NULL DEFAULT NULL,
  `emissor_matricula` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKl6nif7jbqm8gbmuxu48flk9pd` ON `jp-esloc`.`documento` (`atendimento_codigo` ASC) VISIBLE;

CREATE INDEX `FK8dyfs3wpwhix7oq8g6w0lmkwf` ON `jp-esloc`.`documento` (`documento_codigo` ASC) VISIBLE;

CREATE INDEX `FKebulxynp50ifjr50yxca58fld` ON `jp-esloc`.`documento` (`emissor_matricula` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`grupo_producao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`grupo_producao` (
  `codigo` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `especificacoes` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`grupo_servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`grupo_servico` (
  `codigo` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`hibernate_sequence` (
  `next_val` BIGINT(20) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`item_projeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`item_projeto` (
  `codigo` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `unidade` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`item_atendimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`item_atendimento` (
  `codigo` VARCHAR(255) NOT NULL,
  `cadastro` DATETIME(6) NULL DEFAULT NULL,
  `qtd` INT(11) NOT NULL,
  `beneficiario_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `emissor_matricula` VARCHAR(255) NULL DEFAULT NULL,
  `item_unitario_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FK94rpmhkcyrkcdk911h9tt52v1` ON `jp-esloc`.`item_atendimento` (`beneficiario_cpf` ASC) VISIBLE;

CREATE INDEX `FKehopgucgffxoihc9i5r1sxwkg` ON `jp-esloc`.`item_atendimento` (`emissor_matricula` ASC) VISIBLE;

CREATE INDEX `FK3jpj6okeg4xhncbvljd0pjhki` ON `jp-esloc`.`item_atendimento` (`item_unitario_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`linha_de_credito`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`linha_de_credito` (
  `prefixo_agencia` VARCHAR(255) NOT NULL,
  `atualizacao` DATE NULL DEFAULT NULL,
  `cadastro` DATE NULL DEFAULT NULL,
  `carencia_maxima` INT(11) NULL DEFAULT NULL,
  `nome_da_linha` INT(11) NULL DEFAULT NULL,
  `prazo` INT(11) NULL DEFAULT NULL,
  `prazo_maximo` INT(11) NULL DEFAULT NULL,
  `taxa_de_juros_anual` DECIMAL(8,2) NULL DEFAULT NULL,
  PRIMARY KEY (`prefixo_agencia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`milk_price`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`milk_price` (
  `codigo` VARCHAR(255) NOT NULL,
  `cadastro` DATETIME(6) NULL DEFAULT NULL,
  `valor` DECIMAL(8,2) NULL DEFAULT NULL,
  `emissor_matricula` VARCHAR(255) NULL DEFAULT NULL,
  `propriedade_info_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FK7wup498koa4ivl74g074jlw60` ON `jp-esloc`.`milk_price` (`emissor_matricula` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`producao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`producao` (
  `codigo` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `unidade` VARCHAR(255) NULL DEFAULT NULL,
  `grupo_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKi4ye6fkhkjbv1mryuso956pyd` ON `jp-esloc`.`producao` (`grupo_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`producao_familiar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`producao_familiar` (
  `codigo` VARCHAR(255) NOT NULL,
  `quantidade_por_unidade` DECIMAL(8,2) NULL DEFAULT NULL,
  `renda_auferida` DECIMAL(8,2) NULL DEFAULT NULL,
  `renda_estimada` DECIMAL(8,2) NULL DEFAULT NULL,
  `valor_por_unidade` DECIMAL(8,2) NULL DEFAULT NULL,
  `beneficio` VARCHAR(255) NULL DEFAULT NULL,
  `outras_rendas` VARCHAR(255) NULL DEFAULT NULL,
  `programa` VARCHAR(255) NULL DEFAULT NULL,
  `programa_social` VARCHAR(255) NULL DEFAULT NULL,
  `qtd` DECIMAL(8,2) NULL DEFAULT NULL,
  `comprador_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `producao_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKj7lukf3swhvgtr4xg4h70otr4` ON `jp-esloc`.`producao_familiar` (`comprador_cpf` ASC) VISIBLE;

CREATE INDEX `FKc2b3gt8o75ap5k8bckjbaqk1c` ON `jp-esloc`.`producao_familiar` (`producao_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`producao_familiar_producoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`producao_familiar_producoes` (
  `producao_familiar_codigo` VARCHAR(255) NOT NULL,
  `producoes_codigo` VARCHAR(255) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `UK_4ix5og6ni4dxnlj6rjpgqfiqf` ON `jp-esloc`.`producao_familiar_producoes` (`producoes_codigo` ASC) VISIBLE;

CREATE INDEX `FKdoxtigmto04t202lvmdtpbucm` ON `jp-esloc`.`producao_familiar_producoes` (`producao_familiar_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`programa_municipal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`programa_municipal` (
  `codigo` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `fim_vigencia` DATE NULL DEFAULT NULL,
  `inicio_vigencia` DATE NULL DEFAULT NULL,
  `nome_do_programa` VARCHAR(255) NULL DEFAULT NULL,
  `parceiros` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`programa_municipal_item_atendimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`programa_municipal_item_atendimento` (
  `programa_municipal_codigo` VARCHAR(255) NOT NULL,
  `item_atendimento_codigo` VARCHAR(255) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `UK_ieuydtd2kk107q0bhvxeayy12` ON `jp-esloc`.`programa_municipal_item_atendimento` (`item_atendimento_codigo` ASC) VISIBLE;

CREATE INDEX `FKsi63ecxb4xglqefhahggja3ob` ON `jp-esloc`.`programa_municipal_item_atendimento` (`programa_municipal_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`projeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`projeto` (
  `codigo` VARCHAR(255) NOT NULL,
  `atualizacao` DATETIME(6) NULL DEFAULT NULL,
  `cadastro` DATETIME(6) NULL DEFAULT NULL,
  `data_pagamento` DATE NULL DEFAULT NULL,
  `prazo` INT(11) NOT NULL,
  `qtd_parcelas` INT(11) NOT NULL,
  `tx_juros` DECIMAL(8,2) NULL DEFAULT NULL,
  `valor_financiado` DECIMAL(8,2) NULL DEFAULT NULL,
  `valor_orcado` DECIMAL(8,2) NULL DEFAULT NULL,
  `banco_prefixo_agencia` VARCHAR(255) NULL DEFAULT NULL,
  `emissor_matricula` VARCHAR(255) NULL DEFAULT NULL,
  `linha_de_credito_prefixo_agencia` VARCHAR(255) NULL DEFAULT NULL,
  `responsavel_tecnico_matricula` VARCHAR(255) NULL DEFAULT NULL,
  `tempo_aplicacao_juros_sigla` VARCHAR(255) NULL DEFAULT NULL,
  `tempo_prazo_sigla` VARCHAR(255) NULL DEFAULT NULL,
  `unidade_familiar_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKoqv3vina29dbftf6f2qqn8fo2` ON `jp-esloc`.`projeto` (`banco_prefixo_agencia` ASC) VISIBLE;

CREATE INDEX `FK4tvpltg9o8au7c34dhwfoaeeu` ON `jp-esloc`.`projeto` (`emissor_matricula` ASC) VISIBLE;

CREATE INDEX `FKlke599kh4ycac51pjyuhg3rox` ON `jp-esloc`.`projeto` (`linha_de_credito_prefixo_agencia` ASC) VISIBLE;

CREATE INDEX `FKsrgqb1hxl3gnxtx9wnk70a9um` ON `jp-esloc`.`projeto` (`responsavel_tecnico_matricula` ASC) VISIBLE;

CREATE INDEX `FKjy48uygspvjmdm7htpxo70qdb` ON `jp-esloc`.`projeto` (`tempo_aplicacao_juros_sigla` ASC) VISIBLE;

CREATE INDEX `FKqv12ik79tax7f5c4m5genja2j` ON `jp-esloc`.`projeto` (`tempo_prazo_sigla` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`propriedade_rural`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`propriedade_rural` (
  `ccir` VARCHAR(255) NOT NULL,
  `area_total` DECIMAL(8,2) NULL DEFAULT NULL,
  `atualizacao` DATE NULL DEFAULT NULL,
  `cadastro` DATE NULL DEFAULT NULL,
  `condicao_posse` INT(11) NULL DEFAULT NULL,
  `latitude` VARCHAR(255) NULL DEFAULT NULL,
  `longitude` VARCHAR(255) NULL DEFAULT NULL,
  `matricula` VARCHAR(255) NULL DEFAULT NULL,
  `nirf` VARCHAR(255) NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `recibo_car` VARCHAR(255) NULL DEFAULT NULL,
  `roteiro_de_acesso` VARCHAR(255) NULL DEFAULT NULL,
  `emissor_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `proprietario_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `codigo` VARCHAR(255) NOT NULL,
  `caracterizacao` INT(11) NULL DEFAULT NULL,
  `localizacao` VARCHAR(255) NULL DEFAULT NULL,
  `perimetro` TINYBLOB NULL DEFAULT NULL,
  `roteiro` VARCHAR(255) NULL DEFAULT NULL,
  `emissor_matricula` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`ccir`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FK82eaqchij8on3mr3dhyuhvohg` ON `jp-esloc`.`propriedade_rural` (`emissor_cpf` ASC) VISIBLE;

CREATE INDEX `FKq5dq4pmctxgqdn3tosuqes846` ON `jp-esloc`.`propriedade_rural` (`emissor_matricula` ASC) VISIBLE;

CREATE INDEX `FK77jxp5io36hq3em8240cglrof` ON `jp-esloc`.`propriedade_rural` (`proprietario_cpf` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`projetos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`projetos` (
  `codigo` VARCHAR(255) NOT NULL,
  `atualizacao` DATE NULL DEFAULT NULL,
  `cadastro` DATE NULL DEFAULT NULL,
  `data_pagamento` DATE NULL DEFAULT NULL,
  `prazo` INT(11) NOT NULL,
  `qtd_parcelas` INT(11) NULL DEFAULT NULL,
  `taxa_juros` DECIMAL(8,2) NULL DEFAULT NULL,
  `valor_financiado` DECIMAL(8,2) NULL DEFAULT NULL,
  `valor_orcado` DECIMAL(8,2) NULL DEFAULT NULL,
  `banco_prefixo_agencia` VARCHAR(255) NULL DEFAULT NULL,
  `emissor_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `linha_de_credito_prefixo_agencia` VARCHAR(255) NULL DEFAULT NULL,
  `responsavel_tecnico_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `tempo_aplicacao_juros_sigla` VARCHAR(255) NULL DEFAULT NULL,
  `tempo_prazo_sigla` VARCHAR(255) NULL DEFAULT NULL,
  `unidade_familiar_ccir` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKhxp7e0f6nc7hykfu3dd38179s` ON `jp-esloc`.`projetos` (`banco_prefixo_agencia` ASC) VISIBLE;

CREATE INDEX `FKb65xigtlgdj67b00pogahtajv` ON `jp-esloc`.`projetos` (`emissor_cpf` ASC) VISIBLE;

CREATE INDEX `FKgfq7dsum44mlwaol698ovc5ia` ON `jp-esloc`.`projetos` (`linha_de_credito_prefixo_agencia` ASC) VISIBLE;

CREATE INDEX `FKobgeepumeboe40cp1t6p3djus` ON `jp-esloc`.`projetos` (`responsavel_tecnico_cpf` ASC) VISIBLE;

CREATE INDEX `FKniyq1v5xonn70cerx05vvqysj` ON `jp-esloc`.`projetos` (`tempo_aplicacao_juros_sigla` ASC) VISIBLE;

CREATE INDEX `FK4xveetkq8st27m6shtlvx0s1v` ON `jp-esloc`.`projetos` (`tempo_prazo_sigla` ASC) VISIBLE;

CREATE INDEX `FKssas06npbfqwq8u47qoo57ue1` ON `jp-esloc`.`projetos` (`unidade_familiar_ccir` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`propriedade_rural_membros_familiares`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`propriedade_rural_membros_familiares` (
  `propriedade_rural_codigo` VARCHAR(255) NOT NULL,
  `membros_familiares_cpf` VARCHAR(255) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `UK_5f74xlpkjltpb0bw404tfp2js` ON `jp-esloc`.`propriedade_rural_membros_familiares` (`membros_familiares_cpf` ASC);


-- -----------------------------------------------------
-- Table `jp-esloc`.`renda_familiar_anual`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`renda_familiar_anual` (
  `codigo` VARCHAR(255) NOT NULL,
  `atualizacao` DATETIME(6) NULL DEFAULT NULL,
  `cadastro` DATETIME(6) NULL DEFAULT NULL,
  `emissor_matricula` VARCHAR(255) NULL DEFAULT NULL,
  `responsavel_tecnico_matricula` VARCHAR(255) NULL DEFAULT NULL,
  `segundo_titular_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `unidade_familiar_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKdb7x0fn6f6y6e91mo5jyb5pr1` ON `jp-esloc`.`renda_familiar_anual` (`emissor_matricula` ASC) VISIBLE;

CREATE INDEX `FK3jlo2nbskhukl0cxqyu8k9oyx` ON `jp-esloc`.`renda_familiar_anual` (`responsavel_tecnico_matricula` ASC) VISIBLE;

CREATE INDEX `FK2l11em6v7atht31xbjc2rb4hm` ON `jp-esloc`.`renda_familiar_anual` (`segundo_titular_cpf` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`renda_familiar_anual_producao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`renda_familiar_anual_producao` (
  `renda_familiar_anual_codigo` VARCHAR(255) NOT NULL,
  `producao_codigo` VARCHAR(255) NOT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE UNIQUE INDEX `UK_k6u5ixslu9u8qkaebxkfhpay2` ON `jp-esloc`.`renda_familiar_anual_producao` (`producao_codigo` ASC) VISIBLE;

CREATE INDEX `FKi3dqltb1vfhwjxi15wd2gtwuc` ON `jp-esloc`.`renda_familiar_anual_producao` (`renda_familiar_anual_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`servico` (
  `codigo` VARCHAR(255) NOT NULL,
  `definicao` VARCHAR(255) NULL DEFAULT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `tempo_estimado` INT(11) NOT NULL,
  `valor_estimado` DECIMAL(8,2) NULL DEFAULT NULL,
  `grupo_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKc5cwdmp7ur6kqct9l997c13iq` ON `jp-esloc`.`servico` (`grupo_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`servicos_atd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`servicos_atd` (
  `codigo` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `observacoes` VARCHAR(255) NULL DEFAULT NULL,
  `valor_dae` DECIMAL(8,2) NULL DEFAULT NULL,
  `valor_total` DECIMAL(8,2) NULL DEFAULT NULL,
  `atendimento_codigo` VARCHAR(255) NULL DEFAULT NULL,
  `servico_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FKok5te81fcn5y29jara4w4whxj` ON `jp-esloc`.`servicos_atd` (`atendimento_codigo` ASC) VISIBLE;

CREATE INDEX `FK3tss2if82ipee3ip4an00whdb` ON `jp-esloc`.`servicos_atd` (`servico_codigo` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `jp-esloc`.`tecnicos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`tecnicos` (
  `matricula` VARCHAR(255) NOT NULL,
  `conselho` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `permissao` VARCHAR(255) NULL DEFAULT NULL,
  `registro` VARCHAR(255) NULL DEFAULT NULL,
  `agente_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `esloc_cpi` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`matricula`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;

CREATE INDEX `FK9bc73ms1f75ynft3hrsbyxt9q` ON `jp-esloc`.`tecnicos` (`agente_cpf` ASC) VISIBLE;

CREATE INDEX `FKnbukchui0555rja9b1opjw5wd` ON `jp-esloc`.`tecnicos` (`esloc_cpi` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
