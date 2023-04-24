package br.com.clinica.domain.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco {
    private String cidade;
    private String bairro;
    private Integer numero;
}
