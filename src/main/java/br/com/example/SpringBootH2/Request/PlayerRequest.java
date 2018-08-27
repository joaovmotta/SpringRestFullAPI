package br.com.example.SpringBootH2.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PlayerRequest {

    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;

    private double weight;

    private double height;

    private String sport;
}
