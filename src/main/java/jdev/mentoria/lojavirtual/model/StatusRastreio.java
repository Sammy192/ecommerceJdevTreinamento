package jdev.mentoria.lojavirtual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "status_rastreio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_status_rastreio", sequenceName = "seq_status_rastreio", allocationSize = 1, initialValue = 1)
public class StatusRastreio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_status_rastreio")
    private Long id;

    private String centroDistribuicao;
    private String cidade;
    private String estado;
    private String status;

    @ManyToOne
    @JoinColumn(name = "venda_loja_virtual_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_statusRastreio_vendaLojaVirtual"))
    private VendaLojaVirtual vendaLojaVirtual;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatusRastreio)) return false;
        StatusRastreio that = (StatusRastreio) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
