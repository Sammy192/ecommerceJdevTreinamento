package jdev.mentoria.lojavirtual.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AcessoDTO {

        private Long id;

        @NotNull(message = "Perfil cannot be null")
        private String perfil;
}
