package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Person_Address;
import at.bmlv.test.demo.dto.Person_AddressDTO;

import java.util.List;

public class Person_AddressMapper implements EntityMapper<Person_AddressDTO, Person_Address> {


    @Override
    public Person_Address toEntity(Person_AddressDTO dto) {
        return new Person_Address(
                dto.getId(),
                dto.getPerson(),
                dto.getAddress()
        );
    }

    @Override
    public Person_AddressDTO toDTO(Person_Address entity) {
        return new Person_AddressDTO();
    }

    @Override
    public List<Person_AddressDTO> toDTOList(List<Person_Address> entities) {
        return null;
    }

    @Override
    public List<Person_Address> toEntityList(List<Person_AddressDTO> dtoList) {
        return null;
    }
}
