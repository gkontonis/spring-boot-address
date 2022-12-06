package at.bmlv.test.demo.dto;

import at.bmlv.test.demo.domain.Address;
import at.bmlv.test.demo.domain.Person;
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
public class Person_AddressDTO {
    private Long id;
    @NotEmpty( message = "Person darf nicht leer sein" )
    @Size( max = 255 )
    private Person person;
    @NotEmpty( message = "Addresse darf nicht leer sein" )
    @Size( max = 255 )
    private Address address;
}
