#Servico
INSERT INTO `jp-esloc`.`servico` (`codigo`, `definicao`, `descricao`, `tempo_estimado`, `valor_estimado`, `grupo_codigo`) VALUES ('DC', 'Dia de Campo', 'Realização de Dia de campo', '1', '0', '5');

#Grupo Servico
INSERT INTO `jp-esloc`.`grupo_servico` (`codigo`, `descricao`) VALUES ('1', 'Credito Rural');
INSERT INTO `jp-esloc`.`grupo_servico` (`codigo`, `descricao`) VALUES ('2', 'Segurança hídrica');
INSERT INTO `jp-esloc`.`grupo_servico` (`codigo`, `descricao`) VALUES ('3', 'ATER - Pequenos animais');
INSERT INTO `jp-esloc`.`grupo_servico` (`codigo`, `descricao`) VALUES ('4', 'ATER - Culturas');
INSERT INTO `jp-esloc`.`grupo_servico` (`codigo`, `descricao`) VALUES ('5', 'ATER - Bovinocultura');
INSERT INTO `jp-esloc`.`grupo_servico` (`codigo`, `descricao`) VALUES ('6', 'Agroindustria');
INSERT INTO `jp-esloc`.`grupo_servico` (`codigo`, `descricao`) VALUES ('7', 'Gestão de negócios ');

#Empresa
INSERT INTO `jp-esloc`.`empresa_ater` (`cnpj`, `endereco`, `nome_empresa`) VALUES ('43237815000100', 'Rua Israel Pinehrio', 'EMATER/MG');

#UREGI
INSERT INTO `jp-esloc`.`uregi` (`cpi`, `email`, `endereco`, `fonefixo`, `municipio`, `empresa_cnpj`) VALUES ('G0013', 'gv@emater.mg.gov.br', 'Israel Pinheiro', '3332717288', 'Governador Valadares', '43237815000100');

#Esloc
INSERT INTO `jp-esloc`.`esloc` (`cpi`, `email`, `endereco`, `fonefixo`, `municipio`, `uregi_cpi`) VALUES ('h0684', 'tarumirim@emater.mg.gov.br', 'Av. Cunha', '3332331530', 'Tarumirim', 'G0013');

#Tecnico
INSERT INTO `jp-esloc`.`tecnico` (`matricula`, `conselho`, `password`, `permissao`, `registro`, `agente_cpf`, `esloc_cpi`) VALUES ('10639', 'CFTA', 'jacare', 'TECNICO', '04459471604', '04459471604', 'H0684');

#Persona
INSERT INTO `jp-esloc`.`persona` (`cpf`, `contato`, `email`, `emissor`, `produtores_codigo`, `index_conclusao`) VALUES ('04459471604', '33999065029', 'joao.gusmao@emater.mg.gov.br', '04459471604', '04459471604', '3');

