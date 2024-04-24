package edu.upiicsa.interfaz_admin_db.controller;

import edu.upiicsa.interfaz_admin_db.entity.Animal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class AnimalController {

    @RequestMapping("/animales")
    public String animales(@ModelAttribute("connection")Connection connection, Model model) {

        List<Animal> animales = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM animal");

            while(resultSet.next()) {
                var animal = Animal.builder()
                        .noHabitad(resultSet.getInt("NoHabitad"))
                        .nombreAnim(resultSet.getString("NombreAnim"))
                        .familia(resultSet.getString("Familia"))
                        .genero(resultSet.getString("Genero"))
                        .especie(resultSet.getString("Especie"))
                        .alimentacion(resultSet.getString("Alimentacion"))
                        .grupoSanguineo(resultSet.getString("Grupo_Sanguineo"))
                        .build();
                animales.add(animal);
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        model.addAttribute("animales", animales);

        return "animales";
    }

}
