package jdev.mentoria.lojavirtual.services;

import jdev.mentoria.lojavirtual.repositories.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository acessoRepository;
}
