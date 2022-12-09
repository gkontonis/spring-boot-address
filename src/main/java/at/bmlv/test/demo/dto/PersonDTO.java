package at.bmlv.test.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private UUID uuid;
    @NotEmpty( message = "Vorname darf nicht leer sein" )
    @Size( max = 255 )
    private String firstName;
    @NotEmpty( message = "Nachname darf nicht leer sein" )
    @Size( max = 255 )
    private String lastName;
    @Size( max = 1 )
    private Character gender;

    private List<Person_AddressDTO> person_addressList;
}
