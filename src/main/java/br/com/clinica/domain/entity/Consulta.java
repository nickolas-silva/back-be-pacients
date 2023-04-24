package br.com.clinica.domain.entity;

import br.com.clinica.domain.documentRepository.DocumentEntity;
import br.com.clinica.domain.documentRepository.annotations.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Consulta extends DocumentEntity {
    private Integer sala;
    private Integer data;
    private EnumSituacao situacao;
    private String id_medico;
    private String id_paciente;

    @ManyToOne(atribute = "id_medico")
    private Medico medico;

    @ManyToOne(atribute = "id_paciente")
    private Paciente paciente;
}
