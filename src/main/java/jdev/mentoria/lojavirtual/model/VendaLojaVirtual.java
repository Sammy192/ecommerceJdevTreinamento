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
@Table(name = "venda_loja_virtual")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_venda_loja_virtual", sequenceName = "seq_venda_loja_virtual", allocationSize = 1, initialValue = 1)
public class VendaLojaVirtual {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_venda_loja_virtual")
    private Long id;

    @ManyToOne(targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_vendaLojaVirtual_pessoa"))
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "endereco_entrega_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_vendaLojaVirtual_enderecoEntrega"))
    private Endereco enderecoEntrega;

    @ManyToOne
    @JoinColumn(name = "endereco_cobranca_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_vendaLojaVirtual_enderecoCobranca"))
    private Endereco enderecoCobranca;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    private BigDecimal valorDesconto;

    @ManyToOne
    @JoinColumn(name = "forma_pagamento_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_vendaLojaVirtual_formaPagamento"))
    private FormaPagamento formaPagamento;

    @OneToOne
    @JoinColumn(name = "nota_fiscal_venda_id", nullable = false , foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_vendaLojaVirtual_notaFiscalVenda"))
    private NotaFiscalVenda notaFiscalVenda;

    @ManyToOne
    @JoinColumn(name = "cupom_desconto_id", foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_vendaLojaVirtual_cupomDesconto"))
    private CupomDesconto cupomDesconto;

    @Column(nullable = false)
    private BigDecimal valorFrete;

    @Column(nullable = false)
    private Integer diasEntrega;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataVenda;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataEntrega;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendaLojaVirtual)) return false;
        VendaLojaVirtual that = (VendaLojaVirtual) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
