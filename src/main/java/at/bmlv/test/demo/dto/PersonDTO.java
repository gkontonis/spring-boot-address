package at.bmlv.test.demo.dto;

import at.bmlv.test.demo.domain.Person_Address;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {
    private UUID id;
    @NotEmpty( message = "Vorname darf nicht leer sein" )
    @Size( max = 255 )
    private String firstName;
    @NotEmpty( message = "Nach darf nicht leer sein" )
    @Size( max = 255 )
    private String lastName;
    @Size( max = 1 )
    private Character gender;

    private List<Person_Address> person_addressList;
}
