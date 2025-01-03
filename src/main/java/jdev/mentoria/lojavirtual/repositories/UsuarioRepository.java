package jdev.mentoria.lojavirtual.repositories;

import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query(value = "select obj from Usuario obj where obj.login = :login")
    Usuario findUserByLogin(String login);

    @Query(nativeQuery = true, value = """
		    SELECT usuario.login AS login, usuario.senha, acesso.id AS acessoId, acesso.perfil
		    FROM usuario
		    INNER JOIN usuario_acesso ON usuario.id = usuario_acesso.usuario_id
		    INNER JOIN acesso ON acesso.id = usuario_acesso.acesso_id
		    WHERE usuario.login = :login
		""")
    List<UserDetailsProjection> searchUsuarioAndAcessosByLogin(String login);
}
