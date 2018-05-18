package br.com.example.SpringBootH2.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column( name = "name")
    private String name;

    @Column(name = "birthday")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birthday;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;
}
