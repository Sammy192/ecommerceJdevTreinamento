package jdev.mentoria.lojavirtual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "acesso")
@Getter
@Setter
@SequenceGenerator(name ="seq_acesso", sequenceName = "seq_acesso", allocationSize = 1, initialValue = 1)
public class Acesso implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_acesso")
    private Long id;

    @Column(nullable = false)
    private String perfil;
    @Override
    public String getAuthority() {
        return this.perfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acesso acesso = (Acesso) o;
        return Objects.equals(id, acesso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
