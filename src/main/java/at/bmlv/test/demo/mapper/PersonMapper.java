package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Person;
import at.bmlv.test.demo.dto.PersonDTO;

import java.util.List;

public class PersonMapper implements EntityMapper<PersonDTO, Person> {
    private Person_AddressMapper personAddressMapper;

    public PersonMapper(Person_AddressMapper personAddressMapper) {
        this.personAddressMapper = personAddressMapper;
    }

    @Override
    public Person toEntity(PersonDTO dto) {
        return new Person(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getGender(), personAddressMapper.toEntity(dto.g));
    }

    @Override
    public PersonDTO toDTO(Person entity) {
        return null;
    }

    @Override
    public List<PersonDTO> toDTOList(List<Person> entities) {
        return null;
    }

    @Override
    public List<Person> toEntityList(List<PersonDTO> dtoList) {
        return null;
    }
}
