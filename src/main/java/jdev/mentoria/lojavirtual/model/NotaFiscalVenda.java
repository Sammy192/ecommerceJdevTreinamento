package jdev.mentoria.lojavirtual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "nota_fiscal_venda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_nota_fiscal_venda", sequenceName = "seq_nota_fiscal_venda", allocationSize = 1, initialValue = 1)
public class NotaFiscalVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_nota_fiscal_venda")
    private Long id;

    private String numero;
    private String serie;
    private String tipo;
    @Column(columnDefinition = "text")
    private String xml;
    @Column(columnDefinition = "text")
    private String pdf;

    @OneToOne
    @JoinColumn(name = "venda_loja_virtual_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_notaFiscalVenda_vendaLojaVirtual"))
    private VendaLojaVirtual vendaLojaVirtual;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotaFiscalVenda)) return false;
        NotaFiscalVenda that = (NotaFiscalVenda) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
