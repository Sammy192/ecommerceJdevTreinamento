create sequence seq_acesso start 1 increment 1;
create sequence seq_avaliacao_produto start 1 increment 1;
create sequence seq_categoria_produto start 1 increment 1;
create sequence seq_conta_pagar start 1 increment 1;
create sequence seq_conta_receber start 1 increment 1;
create sequence seq_cupom_desconto start 1 increment 1;
create sequence seq_endereco start 1 increment 1;
create sequence seq_forma_pagamento start 1 increment 1;
create sequence seq_imagem_produto start 1 increment 1;
create sequence seq_item_venda_loja start 1 increment 1;
create sequence seq_marca_produto start 1 increment 1;
create sequence seq_nota_fiscal_compra start 1 increment 1;
create sequence seq_nota_fiscal_venda start 1 increment 1;
create sequence seq_nota_item_produto start 1 increment 1;
create sequence seq_pessoa start 1 increment 1;
create sequence seq_status_rastreio start 1 increment 1;
create sequence seq_usuario start 1 increment 1;
create sequence seq_venda_loja_virtual start 1 increment 1;

    create table acesso (
       id int8 not null,
        perfil varchar(255) not null,
        primary key (id)
    );

    create table avaliacao_produto (
       id int8 not null,
        comentario varchar(255) not null,
        nota int4 not null,
        pessoa_id int8 not null,
        produto_id int8 not null,
        primary key (id)
    );

    create table categoria_produto (
       id int8 not null,
        nome_desc varchar(255) not null,
        primary key (id)
    );

    create table conta_pagar (
       id int8 not null,
        data_pagamento date,
        data_vencimento date not null,
        descricao varchar(255) not null,
        status_conta_pagar varchar(255) not null,
        valor_desconto numeric(19, 2),
        valor_total numeric(19, 2) not null,
        pessoa_id int8 not null,
        pessoa_fornecedor_id int8 not null,
        primary key (id)
    );

    create table conta_receber (
       id int8 not null,
        data_pagamento date,
        data_vencimento date not null,
        descricao varchar(255) not null,
        status_conta_receber varchar(255) not null,
        valor_desconto numeric(19, 2),
        valor_total numeric(19, 2) not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    create table cupom_desconto (
       id int8 not null,
        cod_descricao varchar(255) not null,
        data_validade_cupom date not null,
        valor_percent_desconto numeric(19, 2),
        valor_real_desconto numeric(19, 2),
        primary key (id)
    );

    create table endereco (
       id int8 not null,
        bairro varchar(255) not null,
        cep varchar(255) not null,
        cidade varchar(255) not null,
        complemento varchar(255),
        numero varchar(255) not null,
        rua varchar(255) not null,
        tipo_endereco varchar(255) not null,
        uf varchar(255) not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    create table forma_pagamento (
       id int8 not null,
        descricao varchar(255) not null,
        primary key (id)
    );

    create table imagem_produto (
       id int8 not null,
        imagem_miniatura text not null,
        imagem_original text not null,
        produto_id int8 not null,
        primary key (id)
    );

    create table item_venda_loja (
       id int8 not null,
        quantidade float8 not null,
        produto_id int8 not null,
        venda_loja_virtual_id int8 not null,
        primary key (id)
    );

    create table marca_produto (
       id int8 not null,
        nome_desc varchar(255) not null,
        primary key (id)
    );

    create table nota_fiscal_compra (
       id int8 not null,
        data_compra date not null,
        descricao_obs varchar(255),
        numero_nota varchar(255) not null,
        serie_nota varchar(255) not null,
        valor_desconto numeric(19, 2),
        valor_icms numeric(19, 2) not null,
        valor_total numeric(19, 2) not null,
        conta_pagar_id int8 not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    create table nota_fiscal_venda (
       id int8 not null,
        numero varchar(255) not null,
        pdf text not null,
        serie varchar(255) not null,
        tipo varchar(255) not null,
        xml text not null,
        venda_loja_virtual_id int8 not null,
        primary key (id)
    );

    create table nota_item_produto (
       id int8 not null,
        quantidade float8 not null,
        nota_fiscal_compra_id int8 not null,
        produto_id int8 not null,
        primary key (id)
    );

    create table pessoa_fisica (
       id int8 not null,
        email varchar(255) not null,
        nome varchar(255) not null,
        telefone varchar(255) not null,
        cpf varchar(255) not null,
        data_nascimento date,
        primary key (id)
    );

    create table pessoa_juridica (
       id int8 not null,
        email varchar(255) not null,
        nome varchar(255) not null,
        telefone varchar(255) not null,
        categoria varchar(255),
        cnpj varchar(255) not null,
        insc_estadual varchar(255) not null,
        insc_municipal varchar(255),
        nome_fantasia varchar(255) not null,
        razao_social varchar(255) not null,
        primary key (id)
    );

    create table produto (
       id int8 not null,
        alerta_qtd_estoque boolean,
        altura float8 not null,
        ativo boolean not null,
        descricao text not null,
        largura float8 not null,
        link_externo varchar(255),
        nome varchar(255) not null,
        peso float8 not null,
        profundidade float8 not null,
        qtd_alerta_estoque int4,
        qtd_clique int4,
        qtd_estoque int4 not null,
        tipo_unidade varchar(255) not null,
        valor_venda numeric(19, 2) not null,
        primary key (id)
    );

    create table status_rastreio (
       id int8 not null,
        centro_distribuicao varchar(255),
        cidade varchar(255),
        estado varchar(255),
        status varchar(255),
        venda_loja_virtual_id int8 not null,
        primary key (id)
    );

    create table usuario (
       id int8 not null,
        data_atualizacao_senha date not null,
        login varchar(255) not null,
        senha varchar(255) not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    create table usuario_acesso (
       usuario_id int8 not null,
        acesso_id int8 not null
    );

    create table venda_loja_virtual (
       id int8 not null,
        data_entrega date not null,
        data_venda date not null,
        dias_entrega int4 not null,
        valor_desconto numeric(19, 2),
        valor_frete numeric(19, 2) not null,
        valor_total numeric(19, 2) not null,
        cupom_desconto_id int8,
        endereco_cobranca_id int8 not null,
        endereco_entrega_id int8 not null,
        forma_pagamento_id int8 not null,
        nota_fiscal_venda_id int8 not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    alter table if exists usuario_acesso 
       add constraint UK_fhwpg5wu1u5p306q8gycxn9ky unique (acesso_id);

    alter table if exists usuario_acesso 
       add constraint unique_acesso_user unique (usuario_id, acesso_id);

    alter table if exists avaliacao_produto 
       add constraint fk_avaliacaoProduto_produto 
       foreign key (produto_id) 
       references produto;

    alter table if exists imagem_produto 
       add constraint fk_imagemProduto_produto 
       foreign key (produto_id) 
       references produto;

    alter table if exists item_venda_loja 
       add constraint fk_ItemVendaLoja_produto 
       foreign key (produto_id) 
       references produto;

    alter table if exists item_venda_loja 
       add constraint fk_ItemVendaLoja_vendaLojaVirtual 
       foreign key (venda_loja_virtual_id) 
       references venda_loja_virtual;

    alter table if exists nota_fiscal_compra 
       add constraint fk_notaFiscalCompra_contaPagar 
       foreign key (conta_pagar_id) 
       references conta_pagar;

    alter table if exists nota_fiscal_venda 
       add constraint fk_notaFiscalVenda_vendaLojaVirtual 
       foreign key (venda_loja_virtual_id) 
       references venda_loja_virtual;

    alter table if exists nota_item_produto 
       add constraint fk_notaItemProduto_notaFiscalCompra 
       foreign key (nota_fiscal_compra_id) 
       references nota_fiscal_compra;

    alter table if exists nota_item_produto 
       add constraint fk_notaItemProduto_produto 
       foreign key (produto_id) 
       references produto;

    alter table if exists status_rastreio 
       add constraint fk_statusRastreio_vendaLojaVirtual 
       foreign key (venda_loja_virtual_id) 
       references venda_loja_virtual;

    alter table if exists usuario_acesso 
       add constraint acesso_fk 
       foreign key (acesso_id) 
       references acesso;

    alter table if exists usuario_acesso 
       add constraint usuario_fk 
       foreign key (usuario_id) 
       references usuario;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_cupomDesconto 
       foreign key (cupom_desconto_id) 
       references cupom_desconto;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_enderecoCobranca 
       foreign key (endereco_cobranca_id) 
       references endereco;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_enderecoEntrega 
       foreign key (endereco_entrega_id) 
       references endereco;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_formaPagamento 
       foreign key (forma_pagamento_id) 
       references forma_pagamento;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_notaFiscalVenda 
       foreign key (nota_fiscal_venda_id) 
       references nota_fiscal_venda;
create sequence seq_acesso start 1 increment 1;
create sequence seq_avaliacao_produto start 1 increment 1;
create sequence seq_categoria_produto start 1 increment 1;
create sequence seq_conta_pagar start 1 increment 1;
create sequence seq_conta_receber start 1 increment 1;
create sequence seq_cupom_desconto start 1 increment 1;
create sequence seq_endereco start 1 increment 1;
create sequence seq_forma_pagamento start 1 increment 1;
create sequence seq_imagem_produto start 1 increment 1;
create sequence seq_item_venda_loja start 1 increment 1;
create sequence seq_marca_produto start 1 increment 1;
create sequence seq_nota_fiscal_compra start 1 increment 1;
create sequence seq_nota_fiscal_venda start 1 increment 1;
create sequence seq_nota_item_produto start 1 increment 1;
create sequence seq_pessoa start 1 increment 1;
create sequence seq_status_rastreio start 1 increment 1;
create sequence seq_usuario start 1 increment 1;
create sequence seq_venda_loja_virtual start 1 increment 1;

    create table acesso (
       id int8 not null,
        perfil varchar(255) not null,
        primary key (id)
    );

    create table avaliacao_produto (
       id int8 not null,
        comentario varchar(255) not null,
        nota int4 not null,
        pessoa_id int8 not null,
        produto_id int8 not null,
        primary key (id)
    );

    create table categoria_produto (
       id int8 not null,
        nome_desc varchar(255) not null,
        primary key (id)
    );

    create table conta_pagar (
       id int8 not null,
        data_pagamento date,
        data_vencimento date not null,
        descricao varchar(255) not null,
        status_conta_pagar varchar(255) not null,
        valor_desconto numeric(19, 2),
        valor_total numeric(19, 2) not null,
        pessoa_id int8 not null,
        pessoa_fornecedor_id int8 not null,
        primary key (id)
    );

    create table conta_receber (
       id int8 not null,
        data_pagamento date,
        data_vencimento date not null,
        descricao varchar(255) not null,
        status_conta_receber varchar(255) not null,
        valor_desconto numeric(19, 2),
        valor_total numeric(19, 2) not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    create table cupom_desconto (
       id int8 not null,
        cod_descricao varchar(255) not null,
        data_validade_cupom date not null,
        valor_percent_desconto numeric(19, 2),
        valor_real_desconto numeric(19, 2),
        primary key (id)
    );

    create table endereco (
       id int8 not null,
        bairro varchar(255) not null,
        cep varchar(255) not null,
        cidade varchar(255) not null,
        complemento varchar(255),
        numero varchar(255) not null,
        rua varchar(255) not null,
        tipo_endereco varchar(255) not null,
        uf varchar(255) not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    create table forma_pagamento (
       id int8 not null,
        descricao varchar(255) not null,
        primary key (id)
    );

    create table imagem_produto (
       id int8 not null,
        imagem_miniatura text not null,
        imagem_original text not null,
        produto_id int8 not null,
        primary key (id)
    );

    create table item_venda_loja (
       id int8 not null,
        quantidade float8 not null,
        produto_id int8 not null,
        venda_loja_virtual_id int8 not null,
        primary key (id)
    );

    create table marca_produto (
       id int8 not null,
        nome_desc varchar(255) not null,
        primary key (id)
    );

    create table nota_fiscal_compra (
       id int8 not null,
        data_compra date not null,
        descricao_obs varchar(255),
        numero_nota varchar(255) not null,
        serie_nota varchar(255) not null,
        valor_desconto numeric(19, 2),
        valor_icms numeric(19, 2) not null,
        valor_total numeric(19, 2) not null,
        conta_pagar_id int8 not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    create table nota_fiscal_venda (
       id int8 not null,
        numero varchar(255) not null,
        pdf text not null,
        serie varchar(255) not null,
        tipo varchar(255) not null,
        xml text not null,
        venda_loja_virtual_id int8 not null,
        primary key (id)
    );

    create table nota_item_produto (
       id int8 not null,
        quantidade float8 not null,
        nota_fiscal_compra_id int8 not null,
        produto_id int8 not null,
        primary key (id)
    );

    create table pessoa_fisica (
       id int8 not null,
        email varchar(255) not null,
        nome varchar(255) not null,
        telefone varchar(255) not null,
        cpf varchar(255) not null,
        data_nascimento date,
        primary key (id)
    );

    create table pessoa_juridica (
       id int8 not null,
        email varchar(255) not null,
        nome varchar(255) not null,
        telefone varchar(255) not null,
        categoria varchar(255),
        cnpj varchar(255) not null,
        insc_estadual varchar(255) not null,
        insc_municipal varchar(255),
        nome_fantasia varchar(255) not null,
        razao_social varchar(255) not null,
        primary key (id)
    );

    create table produto (
       id int8 not null,
        alerta_qtd_estoque boolean,
        altura float8 not null,
        ativo boolean not null,
        descricao text not null,
        largura float8 not null,
        link_externo varchar(255),
        nome varchar(255) not null,
        peso float8 not null,
        profundidade float8 not null,
        qtd_alerta_estoque int4,
        qtd_clique int4,
        qtd_estoque int4 not null,
        tipo_unidade varchar(255) not null,
        valor_venda numeric(19, 2) not null,
        primary key (id)
    );

    create table status_rastreio (
       id int8 not null,
        centro_distribuicao varchar(255),
        cidade varchar(255),
        estado varchar(255),
        status varchar(255),
        venda_loja_virtual_id int8 not null,
        primary key (id)
    );

    create table usuario (
       id int8 not null,
        data_atualizacao_senha date not null,
        login varchar(255) not null,
        senha varchar(255) not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    create table usuario_acesso (
       usuario_id int8 not null,
        acesso_id int8 not null
    );

    create table venda_loja_virtual (
       id int8 not null,
        data_entrega date not null,
        data_venda date not null,
        dias_entrega int4 not null,
        valor_desconto numeric(19, 2),
        valor_frete numeric(19, 2) not null,
        valor_total numeric(19, 2) not null,
        cupom_desconto_id int8,
        endereco_cobranca_id int8 not null,
        endereco_entrega_id int8 not null,
        forma_pagamento_id int8 not null,
        nota_fiscal_venda_id int8 not null,
        pessoa_id int8 not null,
        primary key (id)
    );

    alter table if exists usuario_acesso 
       add constraint UK_fhwpg5wu1u5p306q8gycxn9ky unique (acesso_id);

    alter table if exists usuario_acesso 
       add constraint unique_acesso_user unique (usuario_id, acesso_id);

    alter table if exists avaliacao_produto 
       add constraint fk_avaliacaoProduto_produto 
       foreign key (produto_id) 
       references produto;

    alter table if exists imagem_produto 
       add constraint fk_imagemProduto_produto 
       foreign key (produto_id) 
       references produto;

    alter table if exists item_venda_loja 
       add constraint fk_ItemVendaLoja_produto 
       foreign key (produto_id) 
       references produto;

    alter table if exists item_venda_loja 
       add constraint fk_ItemVendaLoja_vendaLojaVirtual 
       foreign key (venda_loja_virtual_id) 
       references venda_loja_virtual;

    alter table if exists nota_fiscal_compra 
       add constraint fk_notaFiscalCompra_contaPagar 
       foreign key (conta_pagar_id) 
       references conta_pagar;

    alter table if exists nota_fiscal_venda 
       add constraint fk_notaFiscalVenda_vendaLojaVirtual 
       foreign key (venda_loja_virtual_id) 
       references venda_loja_virtual;

    alter table if exists nota_item_produto 
       add constraint fk_notaItemProduto_notaFiscalCompra 
       foreign key (nota_fiscal_compra_id) 
       references nota_fiscal_compra;

    alter table if exists nota_item_produto 
       add constraint fk_notaItemProduto_produto 
       foreign key (produto_id) 
       references produto;

    alter table if exists status_rastreio 
       add constraint fk_statusRastreio_vendaLojaVirtual 
       foreign key (venda_loja_virtual_id) 
       references venda_loja_virtual;

    alter table if exists usuario_acesso 
       add constraint acesso_fk 
       foreign key (acesso_id) 
       references acesso;

    alter table if exists usuario_acesso 
       add constraint usuario_fk 
       foreign key (usuario_id) 
       references usuario;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_cupomDesconto 
       foreign key (cupom_desconto_id) 
       references cupom_desconto;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_enderecoCobranca 
       foreign key (endereco_cobranca_id) 
       references endereco;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_enderecoEntrega 
       foreign key (endereco_entrega_id) 
       references endereco;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_formaPagamento 
       foreign key (forma_pagamento_id) 
       references forma_pagamento;

    alter table if exists venda_loja_virtual 
       add constraint fk_vendaLojaVirtual_notaFiscalVenda 
       foreign key (nota_fiscal_venda_id) 
       references nota_fiscal_venda;
