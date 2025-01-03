package jdev.mentoria.lojavirtual.services;

import jdev.mentoria.lojavirtual.model.Acesso;
import jdev.mentoria.lojavirtual.model.Usuario;
import jdev.mentoria.lojavirtual.projections.UserDetailsProjection;
import jdev.mentoria.lojavirtual.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> usuarioDetalhe = usuarioRepository.searchUsuarioAndAcessosByLogin(username);
        if(usuarioDetalhe.size() == 0) {
            throw new UsernameNotFoundException("User not found");
        }
        Usuario user = modelMapper.map(usuarioDetalhe.get(0), Usuario.class);
        for (UserDetailsProjection projection : usuarioDetalhe) {
            user.addAcesso(new Acesso(projection.getAcessoId(), projection.getPerfil()));
        }

        /*forma alterativa
        Usuario user = modelMapper.map(usuarioDetalhe.get(0), Usuario.class);
        usuarioDetalhe.forEach(projection -> user.addAcesso(new Acesso(projection.getAcessoId(), projection.getPerfil())));*/

        return user;
    }
}
