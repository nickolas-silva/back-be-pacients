package br.com.clinica.domain.repository;

import org.springframework.stereotype.Component;

import br.com.clinica.domain.documentRepository.implementation.DocumentRepository;
import br.com.clinica.domain.entity.Consulta;

@Component
public class ConsultaRepository extends DocumentRepository<Consulta> {

    public ConsultaRepository() {
        super(Consulta.class);
    }

}
