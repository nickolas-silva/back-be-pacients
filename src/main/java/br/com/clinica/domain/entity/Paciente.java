package br.com.clinica.domain.entity;

import br.com.clinica.domain.documentRepository.DocumentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paciente extends DocumentEntity {
    private String nome;
    private Long cpf;
    private Integer idade;
    private Integer telefone;
    private Endereco endereco;

    // @OneToMany(reference = "id_paciente")
    // private List<Consulta> consultas;
}
