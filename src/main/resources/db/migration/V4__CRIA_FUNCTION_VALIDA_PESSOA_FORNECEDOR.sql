CREATE OR REPLACE FUNCTION validaChavePessoaFornecedor()
RETURNS TRIGGER AS $$
DECLARE
    existe INTEGER;
BEGIN
    SELECT COUNT(1)
    INTO existe
    FROM (
        SELECT id
		FROM pessoa_fisica
		WHERE id = NEW.pessoaFornecedor_id
        UNION ALL
        SELECT id
		FROM pessoa_juridica
		WHERE id = NEW.pessoaFornecedor_id
    ) subquery;

    IF existe = 0 THEN
        RAISE EXCEPTION 'Não foi encontrado o ID e PK da pessoa fornecedor para realizar a associação do cadastro';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE PLPGSQL;


