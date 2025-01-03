package jdev.mentoria.lojavirtual.projections;

public interface UserDetailsProjection {

    String getLogin();
    String getSenha();
    Long getAcessoId();
    String getPerfil();
}