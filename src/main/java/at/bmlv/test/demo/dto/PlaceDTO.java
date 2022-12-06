package at.bmlv.test.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PlaceDTO {
    private Long id;
    @NotEmpty( message = "Ort darf nicht leer sein" )
    @Size( max = 255 )
    private String placeName;
    @NotEmpty( message = "Bundesland darf nicht leer sein" )
    @Size( max = 255 )
    private String state;
    @NotEmpty( message = "Postleitzahl darf nicht leer sein" )
    @Size( max = 5 )
    private Integer postcode;
}
