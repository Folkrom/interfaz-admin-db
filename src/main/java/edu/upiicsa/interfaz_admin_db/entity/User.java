package edu.upiicsa.interfaz_admin_db.entity;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private String username;
    private String password;
}