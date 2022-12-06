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

public class AddressDTO {
    private Long id;
    @NotEmpty( message = "Strassenname darf nicht leer sein" )
    @Size(max = 255, message = "Street name must contain 1-255 character") //nur zum test
    private String streetName;
    @NotEmpty( message = "Hausnummer darf nicht leer sein" )
    @Size( max = 255 )
    private Integer houseNumber;
    @NotEmpty( message = "Tuernummer darf nicht leer sein" )
    @Size( max = 255 )
    private Integer flatNumber;

}
