package jdev.mentoria.lojavirtual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item_venda_loja")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_item_venda_loja", sequenceName = "seq_item_venda_loja", allocationSize = 1, initialValue = 1)
public class ItemVendaLoja {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_item_venda_loja")
    private Long id;

    private Double quantidade;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_ItemVendaLoja_produto"))
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "venda_loja_virtual_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_ItemVendaLoja_vendaLojaVirtual"))
    private VendaLojaVirtual vendaLojaVirtual;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemVendaLoja)) return false;
        ItemVendaLoja that = (ItemVendaLoja) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
