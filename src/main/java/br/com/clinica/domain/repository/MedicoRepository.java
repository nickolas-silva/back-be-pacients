package br.com.clinica.domain.repository;

import org.springframework.stereotype.Component;

import br.com.clinica.domain.documentRepository.implementation.DocumentRepository;
import br.com.clinica.domain.entity.Medico;

@Component
public class MedicoRepository extends DocumentRepository<Medico> {
    public MedicoRepository() {
        super(Medico.class);
    }
}
