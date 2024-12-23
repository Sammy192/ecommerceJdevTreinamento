package jdev.mentoria.lojavirtual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cupom_desconto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_cupom_desconto", sequenceName = "seq_cupom_desconto", allocationSize = 1, initialValue = 1)
public class CupomDesconto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desconto")
    private Long id;

    private String codDescricao;
    private BigDecimal valorRealDesconto;
    private BigDecimal valorPercentDesconto;
    @Temporal(TemporalType.DATE)
    private Date dataValidadeCupom;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CupomDesconto)) return false;
        CupomDesconto that = (CupomDesconto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
