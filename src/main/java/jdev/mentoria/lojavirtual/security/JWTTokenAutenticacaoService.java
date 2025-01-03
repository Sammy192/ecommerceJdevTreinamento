package jdev.mentoria.lojavirtual.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import jdev.mentoria.lojavirtual.config.ApplicationContextLoad;
import jdev.mentoria.lojavirtual.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

/*Criar a autenticação e retonar também a autenticação JWT*/
@Service
@Component
public class JWTTokenAutenticacaoService {

    @Autowired
    private UsuarioService usuarioService;

    /*Token de validade de 11 dias*/
    private static final long EXPIRATION_TIME = 959990000;

    /*Chave de senha para juntar com o JWT*/
    private static final String SECRET = "ss/-*-*sds565dsd-s/d-s*dsds";

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String HEADER_STRING = "Authorization";

    /*Gera o token e retorna a resposta para o cliente o com JWT*/
    public void addAuthentication(HttpServletResponse response, String username) throws Exception {
        /*Monta o Token*/
        String JWT = Jwts.builder()/*Chama o gerador de token*/
                .setSubject(username) /*Adiciona o user*/
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact(); /*Temp de expiração*/

        /*Exe: Bearer *-/a*dad9s5d6as5d4s5d4s45dsd54s.sd4s4d45s45d4sd54d45s4d5s.ds5d5s5d5s65d6s6d*/
        String token = TOKEN_PREFIX + " " + JWT;

        /*Retorna para a tela e para o cliente, outra API, navegador, aplicativo, javascript, outra chamadajava*/
        response.addHeader(HEADER_STRING, token);

        liberacaoCors(response);

        /*Usado para ver no Postman para teste*/
        response.getWriter().write("{\"Authorization\": \"" + token + "\"}");
    }

    /*Retorna o usuário validado com token ou caso nao seja valido retona null*/
    public Authentication getAuthetication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String token = request.getHeader(HEADER_STRING);
        try {
            if (token != null) {

                String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();

                /*Faz a validacao do token do usuário na requisicao e obtem o USER*/
                String user = Jwts.parser().
                        setSigningKey(SECRET)
                        .parseClaimsJws(tokenLimpo)
                        .getBody().getSubject();

                if (Objects.nonNull(user)) {
                    UserDetails usuario = ApplicationContextLoad
                                            .getApplicationContext()
                                            .getBean(UsuarioService.class)
                                            .loadUserByUsername(user);

                    if (Objects.nonNull(usuario)) {
                        return new UsernamePasswordAuthenticationToken(
                                usuario.getUsername(),
                                usuario.getPassword(),
                                usuario.getAuthorities());
                    }
                }
            }

        } catch (SignatureException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Token inválido");
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token expirado");
        } finally {
            liberacaoCors(response);
        }

        return null;
    }


    private void liberacaoCors(HttpServletResponse response) {
        if (response.getHeader("Access-Control-Allow-Origin") == null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
        }
        if (response.getHeader("Access-Control-Allow-Headers") == null) {
            response.addHeader("Access-Control-Allow-Headers", "*");
        }
        if (response.getHeader("Access-Control-Request-Headers") == null) {
            response.addHeader("Access-Control-Request-Headers", "*");
        }
        if (response.getHeader("Access-Control-Allow-Methods") == null) {
            response.addHeader("Access-Control-Allow-Methods", "*");
        }
    }

}
