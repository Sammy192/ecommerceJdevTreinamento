package jdev.mentoria.lojavirtual.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_produto", allocationSize = 1, initialValue = 1)
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cupom_desconto")
    private Long id;

    @Column(nullable = false)
    private String tipoUnidade;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "text", length = 2000, nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false)
    private Double largura;

    @Column(nullable = false)
    private Double altura;

    @Column(nullable = false)
    private Double profundidade;

    @Column(nullable = false)
    private BigDecimal valorVenda = BigDecimal.ZERO;

    @Column(nullable = false)
    private Integer qtdEstoque = 0;

    private Integer qtdAlertaEstoque = 0;

    private String linkExterno;

    private Boolean alertaQtdEstoque = Boolean.FALSE;

    private Integer qtdClique = 0;

    @Column(nullable = false)
    private Boolean ativo = Boolean.TRUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
