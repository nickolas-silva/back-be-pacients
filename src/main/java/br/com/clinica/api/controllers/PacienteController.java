package br.com.clinica.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.clinica.domain.entity.Paciente;
import br.com.clinica.domain.repository.PacienteRepository;
import br.com.clinica.estruturas.List;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/paciente")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PacienteController {

    private PacienteRepository pacienteRepository;

    @GetMapping("/page/{page}")
    public ResponseEntity<List<Paciente>> getPacientes(
            @PathVariable final Integer page) {
        return new ResponseEntity<>(pacienteRepository.findAll(page), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Paciente> getPaciente(@PathVariable final String uuid) {

        return new ResponseEntity<>(pacienteRepository.findByUuid(uuid),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody final Paciente paciente) {

        return new ResponseEntity<>(pacienteRepository.save(paciente),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Paciente> updatePaciente(@RequestBody final Paciente paciente) {

        return new ResponseEntity<>(pacienteRepository.update(paciente),
                HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> deletePaciente(@PathVariable final String uuid) {
        pacienteRepository.deleteByUuid(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
