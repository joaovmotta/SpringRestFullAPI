package br.com.example.SpringBootH2.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {

    @NotNull(message = "The field name is required,")
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = "Por favor insira uma data menor que a data de hoje.")
    private LocalDate birthday;

    @NotNull(message = "The field weight is required,")
    private double weight;

    @NotNull(message = "The field height is required,")
    private double height;

    @NotNull(message = "hTe field sport is required,")
    private String sport;

}
