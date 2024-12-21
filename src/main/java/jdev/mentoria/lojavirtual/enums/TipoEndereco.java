package jdev.mentoria.lojavirtual.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public enum TipoEndereco {

    COBRANCA("Cobrança"),
    ENTREGA("Entrega");

    private String descricao;
}
