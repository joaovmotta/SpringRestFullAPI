package br.com.example.SpringBootH2.representation.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

import static br.com.example.SpringBootH2.infra.constants.DefaultErrorMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequest {

    @NotNull(message = REQUIRED_FIELD_IS_NULL)
    private String name;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past(message = FUTURE_OR_PRESENT_DATE)
    private LocalDate birthday;

    @NotNull(message = REQUIRED_FIELD_IS_NULL)
    @Min(value = 50, message = WEIGHT_LESS_THAN_FIFITY)
    private double weight;

    @NotNull(message = REQUIRED_FIELD_IS_NULL)
    @Min(value = 150, message = HEIGHT_LESS_THAN_ONE_HUNDRED_AND_FIFITY)
    private double height;

    @NotNull(message = REQUIRED_FIELD_IS_NULL)
    private String sport;

}
