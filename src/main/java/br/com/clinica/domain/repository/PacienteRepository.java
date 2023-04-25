package br.com.clinica.domain.repository;

import org.springframework.stereotype.Component;

import br.com.clinica.domain.documentRepository.implementation.DocumentRepository;
import br.com.clinica.domain.entity.Paciente;

@Component
public class PacienteRepository extends DocumentRepository<Paciente> {
    public PacienteRepository() {
        super(Paciente.class);
    }
}
