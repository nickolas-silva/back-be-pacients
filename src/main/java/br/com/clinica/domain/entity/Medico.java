package br.com.clinica.domain.entity;

import br.com.clinica.domain.documentRepository.DocumentEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medico extends DocumentEntity {
    private String nome;
    private Long cpf;
    private Integer telefone;
    private Endereco endereco;

    // @OneToMany(reference = "id_medico")
    // private List<Consulta> consultas;
    // id_especialidade ou atributo especialidade
}
