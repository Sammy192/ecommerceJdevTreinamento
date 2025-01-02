package jdev.mentoria.lojavirtual.repositories;

import jdev.mentoria.lojavirtual.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query(value = "select obj from Usuario obj where obj.login = :login")
    Usuario findUserByLogin(String login);
}
