-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema jp-esloc
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jp-esloc
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jp-esloc` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `jp-esloc` ;

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
  `password` DATE NULL DEFAULT NULL,
  `permissao` VARCHAR(255) NULL DEFAULT NULL,
  `sexo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  PRIMARY KEY (`codigo`),
  INDEX `FKss91jlt3oe1cre1frqiyobbs8` (`responsavel_tecnico_cpf` ASC) VISIBLE,
  CONSTRAINT `FKss91jlt3oe1cre1frqiyobbs8`
    FOREIGN KEY (`responsavel_tecnico_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`atendimento_produtores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`atendimento_produtores` (
  `atendimento_codigo` VARCHAR(255) NOT NULL,
  `produtores_cpf` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `UK_td859g5bgkksxfvnf809x2td1` (`produtores_cpf` ASC) VISIBLE,
  INDEX `FKt848waum227fkfpa7ycemo2mb` (`atendimento_codigo` ASC) VISIBLE,
  CONSTRAINT `FK5d4vop1aenqgek5e2pfd6dni8`
    FOREIGN KEY (`produtores_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`),
  CONSTRAINT `FKt848waum227fkfpa7ycemo2mb`
    FOREIGN KEY (`atendimento_codigo`)
    REFERENCES `jp-esloc`.`atendimento` (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  `cadastro` DATE NULL DEFAULT NULL,
  `categoria_animal` VARCHAR(255) NULL DEFAULT NULL,
  `valor_por_unidade` DECIMAL(8,2) NULL DEFAULT NULL,
  `emissor_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `produtor_info_cpf` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `FK1u7pe9ainay7cixu66graue5m` (`emissor_cpf` ASC) VISIBLE,
  INDEX `FKelebbeu26ky6j0r3oqpnyc4qt` (`produtor_info_cpf` ASC) VISIBLE,
  CONSTRAINT `FK1u7pe9ainay7cixu66graue5m`
    FOREIGN KEY (`emissor_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`),
  CONSTRAINT `FKelebbeu26ky6j0r3oqpnyc4qt`
    FOREIGN KEY (`produtor_info_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`producao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`producao` (
  `codigo` VARCHAR(255) NOT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `unidade` VARCHAR(255) NULL DEFAULT NULL,
  `grupo_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `FKi4ye6fkhkjbv1mryuso956pyd` (`grupo_codigo` ASC) VISIBLE,
  CONSTRAINT `FKi4ye6fkhkjbv1mryuso956pyd`
    FOREIGN KEY (`grupo_codigo`)
    REFERENCES `jp-esloc`.`grupo_producao` (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`producao_familiar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`producao_familiar` (
  `codigo` VARCHAR(255) NOT NULL,
  `quantidade_por_unidade` DECIMAL(8,2) NULL DEFAULT NULL,
  `renda_auferida` DECIMAL(8,2) NULL DEFAULT NULL,
  `renda_estimada` DECIMAL(8,2) NULL DEFAULT NULL,
  `valor_por_unidade` DECIMAL(8,2) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`producao_familiar_producoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`producao_familiar_producoes` (
  `producao_familiar_codigo` VARCHAR(255) NOT NULL,
  `producoes_codigo` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `UK_4ix5og6ni4dxnlj6rjpgqfiqf` (`producoes_codigo` ASC) VISIBLE,
  INDEX `FKdoxtigmto04t202lvmdtpbucm` (`producao_familiar_codigo` ASC) VISIBLE,
  CONSTRAINT `FKdoxtigmto04t202lvmdtpbucm`
    FOREIGN KEY (`producao_familiar_codigo`)
    REFERENCES `jp-esloc`.`producao_familiar` (`codigo`),
  CONSTRAINT `FKgn3gov65f8rq0fkgrx5c87w1j`
    FOREIGN KEY (`producoes_codigo`)
    REFERENCES `jp-esloc`.`producao` (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  PRIMARY KEY (`ccir`),
  INDEX `FK82eaqchij8on3mr3dhyuhvohg` (`emissor_cpf` ASC) VISIBLE,
  INDEX `FK77jxp5io36hq3em8240cglrof` (`proprietario_cpf` ASC) VISIBLE,
  CONSTRAINT `FK77jxp5io36hq3em8240cglrof`
    FOREIGN KEY (`proprietario_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`),
  CONSTRAINT `FK82eaqchij8on3mr3dhyuhvohg`
    FOREIGN KEY (`emissor_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


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
  PRIMARY KEY (`codigo`),
  INDEX `FKhxp7e0f6nc7hykfu3dd38179s` (`banco_prefixo_agencia` ASC) VISIBLE,
  INDEX `FKb65xigtlgdj67b00pogahtajv` (`emissor_cpf` ASC) VISIBLE,
  INDEX `FKgfq7dsum44mlwaol698ovc5ia` (`linha_de_credito_prefixo_agencia` ASC) VISIBLE,
  INDEX `FKobgeepumeboe40cp1t6p3djus` (`responsavel_tecnico_cpf` ASC) VISIBLE,
  INDEX `FKniyq1v5xonn70cerx05vvqysj` (`tempo_aplicacao_juros_sigla` ASC) VISIBLE,
  INDEX `FK4xveetkq8st27m6shtlvx0s1v` (`tempo_prazo_sigla` ASC) VISIBLE,
  INDEX `FKssas06npbfqwq8u47qoo57ue1` (`unidade_familiar_ccir` ASC) VISIBLE,
  CONSTRAINT `FK4xveetkq8st27m6shtlvx0s1v`
    FOREIGN KEY (`tempo_prazo_sigla`)
    REFERENCES `jp-esloc`.`conversao_temporal` (`sigla`),
  CONSTRAINT `FKb65xigtlgdj67b00pogahtajv`
    FOREIGN KEY (`emissor_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`),
  CONSTRAINT `FKgfq7dsum44mlwaol698ovc5ia`
    FOREIGN KEY (`linha_de_credito_prefixo_agencia`)
    REFERENCES `jp-esloc`.`linha_de_credito` (`prefixo_agencia`),
  CONSTRAINT `FKhxp7e0f6nc7hykfu3dd38179s`
    FOREIGN KEY (`banco_prefixo_agencia`)
    REFERENCES `jp-esloc`.`bancos` (`prefixo_agencia`),
  CONSTRAINT `FKniyq1v5xonn70cerx05vvqysj`
    FOREIGN KEY (`tempo_aplicacao_juros_sigla`)
    REFERENCES `jp-esloc`.`conversao_temporal` (`sigla`),
  CONSTRAINT `FKobgeepumeboe40cp1t6p3djus`
    FOREIGN KEY (`responsavel_tecnico_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`),
  CONSTRAINT `FKssas06npbfqwq8u47qoo57ue1`
    FOREIGN KEY (`unidade_familiar_ccir`)
    REFERENCES `jp-esloc`.`propriedade_rural` (`ccir`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`renda_familiar_anual`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`renda_familiar_anual` (
  `codigo` VARCHAR(255) NOT NULL,
  `atualizacao` DATE NULL DEFAULT NULL,
  `cadastro` DATE NULL DEFAULT NULL,
  `emissor_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `responsavel_tecnico_cpf` VARCHAR(255) NULL DEFAULT NULL,
  `unidade_familiar_ccir` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `FKpld41yl70bhiutgx3mcgye4cn` (`emissor_cpf` ASC) VISIBLE,
  INDEX `FKb56rixxxug2np6707qv33v58b` (`responsavel_tecnico_cpf` ASC) VISIBLE,
  INDEX `FK1okvnidudj7i9ifrybr16mt39` (`unidade_familiar_ccir` ASC) VISIBLE,
  CONSTRAINT `FK1okvnidudj7i9ifrybr16mt39`
    FOREIGN KEY (`unidade_familiar_ccir`)
    REFERENCES `jp-esloc`.`propriedade_rural` (`ccir`),
  CONSTRAINT `FKb56rixxxug2np6707qv33v58b`
    FOREIGN KEY (`responsavel_tecnico_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`),
  CONSTRAINT `FKpld41yl70bhiutgx3mcgye4cn`
    FOREIGN KEY (`emissor_cpf`)
    REFERENCES `jp-esloc`.`persona` (`cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`renda_familiar_anual_producao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`renda_familiar_anual_producao` (
  `renda_familiar_anual_codigo` VARCHAR(255) NOT NULL,
  `producao_codigo` VARCHAR(255) NOT NULL,
  UNIQUE INDEX `UK_k6u5ixslu9u8qkaebxkfhpay2` (`producao_codigo` ASC) VISIBLE,
  INDEX `FKi3dqltb1vfhwjxi15wd2gtwuc` (`renda_familiar_anual_codigo` ASC) VISIBLE,
  CONSTRAINT `FK1f02c3cdphp2c1wr0n4ghsa0v`
    FOREIGN KEY (`producao_codigo`)
    REFERENCES `jp-esloc`.`producao_familiar` (`codigo`),
  CONSTRAINT `FKi3dqltb1vfhwjxi15wd2gtwuc`
    FOREIGN KEY (`renda_familiar_anual_codigo`)
    REFERENCES `jp-esloc`.`renda_familiar_anual` (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`servico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`servico` (
  `codigo` VARCHAR(255) NOT NULL,
  `definicao` VARCHAR(255) NULL DEFAULT NULL,
  `descricao` VARCHAR(255) NULL DEFAULT NULL,
  `tempo_estimado` INT(11) NULL DEFAULT NULL,
  `valor_estimado` DECIMAL(8,2) NULL DEFAULT NULL,
  `grupo_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `FKc5cwdmp7ur6kqct9l997c13iq` (`grupo_codigo` ASC) VISIBLE,
  CONSTRAINT `FKc5cwdmp7ur6kqct9l997c13iq`
    FOREIGN KEY (`grupo_codigo`)
    REFERENCES `jp-esloc`.`grupo_servico` (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `jp-esloc`.`servicos_atd`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jp-esloc`.`servicos_atd` (
  `codigo` VARCHAR(255) NOT NULL,
  `servico_descricao` VARCHAR(255) NULL DEFAULT NULL,
  `observacoes` VARCHAR(255) NULL DEFAULT NULL,
  `valor_dae` DECIMAL(8,2) NULL DEFAULT NULL,
  `valor_total_servico` DECIMAL(8,2) NULL DEFAULT NULL,
  `atendimentofk_codigo` VARCHAR(255) NULL DEFAULT NULL,
  `servico_codigo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `FKr7hnfaby5n1wcef1ijnhrh9gk` (`atendimentofk_codigo` ASC) VISIBLE,
  INDEX `FK3tss2if82ipee3ip4an00whdb` (`servico_codigo` ASC) VISIBLE,
  CONSTRAINT `FK3tss2if82ipee3ip4an00whdb`
    FOREIGN KEY (`servico_codigo`)
    REFERENCES `jp-esloc`.`servico` (`codigo`),
  CONSTRAINT `FKr7hnfaby5n1wcef1ijnhrh9gk`
    FOREIGN KEY (`atendimentofk_codigo`)
    REFERENCES `jp-esloc`.`atendimento` (`codigo`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
