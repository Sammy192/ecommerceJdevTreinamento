package jdev.mentoria.lojavirtual.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class PessoaJuridica extends Pessoa {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String cnpj;
    @Column(nullable = false)
    private String inscEstadual;
    private String inscMunicipal;

    @Column(nullable = false)
    private String nomeFantasia;

    @Column(nullable = false)
    private String razaoSocial;

    private String categoria;
}
