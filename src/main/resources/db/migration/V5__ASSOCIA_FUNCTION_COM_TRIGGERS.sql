CREATE TRIGGER validaChavePessoa
	BEFORE INSERT OR UPDATE
	ON avaliacao_produto
	FOR EACH ROW
	EXECUTE PROCEDURE validaChavePessoa();

CREATE TRIGGER validaChavePessoa
	BEFORE INSERT OR UPDATE
	ON conta_pagar
	FOR EACH ROW
	EXECUTE PROCEDURE validaChavePessoa();

CREATE TRIGGER validaChavePessoaFornecedor
	BEFORE INSERT OR UPDATE
	ON conta_pagar
	FOR EACH ROW
	EXECUTE PROCEDURE validaChavePessoaFornecedor();

CREATE TRIGGER validaChavePessoa
	BEFORE INSERT OR UPDATE
	ON endereco
	FOR EACH ROW
	EXECUTE PROCEDURE validaChavePessoa();

CREATE TRIGGER validaChavePessoa
	BEFORE INSERT OR UPDATE
	ON nota_fiscal_compra
	FOR EACH ROW
	EXECUTE PROCEDURE validaChavePessoa();

CREATE TRIGGER validaChavePessoa
	BEFORE INSERT OR UPDATE
	ON usuario
	FOR EACH ROW
	EXECUTE PROCEDURE validaChavePessoa();

CREATE TRIGGER validaChavePessoa
	BEFORE INSERT OR UPDATE
	ON venda_loja_virtual
	FOR EACH ROW
	EXECUTE PROCEDURE validaChavePessoa();
