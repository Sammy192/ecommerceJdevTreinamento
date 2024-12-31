package jdev.mentoria.lojavirtual.testsUtils;

import jdev.mentoria.lojavirtual.dtos.AcessoDTO;

public class Factory {

    public static AcessoDTO createAcessoDTOAdmin() {
        return new AcessoDTO(1L, "ROLE_ADMIN");
    }

    public static AcessoDTO createAcessoDTOOperador() {
        return new AcessoDTO(2L, "ROLE_OPERADOR");
    }
}
