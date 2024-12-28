package jdev.mentoria.lojavirtual.repositories;

import jdev.mentoria.lojavirtual.model.Acesso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
}
