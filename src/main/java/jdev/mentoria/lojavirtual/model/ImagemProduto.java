package jdev.mentoria.lojavirtual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "imagem_produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_imagem_produto", sequenceName = "seq_imagem_produto", allocationSize = 1, initialValue = 1)
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_imagem_produto")
    private Long id;

    @Column(columnDefinition = "text", nullable = false)
    private String imagemOriginal;

    @Column(columnDefinition = "text", nullable = false)
    private String imagemMiniatura;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_imagemProduto_produto"))
    private Produto produto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImagemProduto)) return false;
        ImagemProduto that = (ImagemProduto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
