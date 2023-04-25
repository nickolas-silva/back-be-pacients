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
import br.com.clinica.domain.entity.Medico;
import br.com.clinica.domain.repository.MedicoRepository;
import br.com.clinica.estruturas.List;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/medico")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class MedicoController {

    private MedicoRepository medicoRepository;

    @GetMapping("/page/{page}")
    public ResponseEntity<List<Medico>> getMedicos(@PathVariable final Integer page) {
        return new ResponseEntity<>(medicoRepository.findAll(page), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Medico> getMedico(@PathVariable final String uuid) {

        return new ResponseEntity<>(medicoRepository.findByUuid(uuid),
                HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Medico> getMedicoByNome(@PathVariable final String nome) {
        return new ResponseEntity<>(medicoRepository.findBySpecificField("nome", nome),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medico> createMedico(@RequestBody final Medico medico) {

        return new ResponseEntity<>(medicoRepository.save(medico),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Medico> updateMedico(@RequestBody final Medico medico) {

        return new ResponseEntity<>(medicoRepository.update(medico),
                HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> deleteMedico(@PathVariable final String uuid) {
        medicoRepository.deleteByUuid(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
