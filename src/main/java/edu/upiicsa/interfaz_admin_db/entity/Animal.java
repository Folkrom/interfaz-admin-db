package edu.upiicsa.interfaz_admin_db.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Animal {
    private Integer noHabitad;
    private String nombreAnim;
    private String familia;
    private String genero;
    private String especie;
    private String alimentacion;
    private String grupoSanguineo;
}
