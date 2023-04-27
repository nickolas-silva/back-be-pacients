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
import br.com.clinica.domain.entity.Consulta;
import br.com.clinica.domain.repository.ConsultaRepository;
import br.com.clinica.estruturas.List;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/consulta")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ConsultaController {

    private ConsultaRepository consultaRepository;

    @GetMapping("/page/{page}")
    public ResponseEntity<List<Consulta>> getConsulta(@PathVariable final Integer page) {
        return new ResponseEntity<>(consultaRepository.findAll(page), HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Consulta> getConsulta(@PathVariable final String uuid) {

        return new ResponseEntity<>(consultaRepository.findByUuid(uuid),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Consulta> createConsulta(@RequestBody final Consulta consulta) {

        return new ResponseEntity<>(consultaRepository.save(consulta),
                HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Consulta> updateConsulta(@RequestBody final Consulta consulta) {

        return new ResponseEntity<>(consultaRepository.update(consulta),
                HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<HttpStatus> deleteConsulta(@PathVariable final String uuid) {
        consultaRepository.deleteByUuid(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
