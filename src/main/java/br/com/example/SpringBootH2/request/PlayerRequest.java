package br.com.example.SpringBootH2.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {

    @NotNull(message = "The field name is required,")
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Please enter a date less than the current date.")
    private LocalDate birthday;

    @NotNull(message = "The field weight is required,")
    @Min(50)
    private double weight;

    @NotNull(message = "The field height is required,")
    @Min(150)
    private double height;

    @NotNull(message = "The field sport is required,")
    private String sport;

}
