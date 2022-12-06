package at.bmlv.test.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDTO {
    private Long id;
    @NotEmpty( message = "Land darf nicht leer sein" )
    @Size( max = 255 )
    private String countryName;
    @NotEmpty( message = "Land-Abkuerzung darf nicht leer sein" )
    @Size( max = 255 )
    private String countryNameAbbreviation;

}
