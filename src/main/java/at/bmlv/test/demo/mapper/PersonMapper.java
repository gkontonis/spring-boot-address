package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Person;
import at.bmlv.test.demo.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;

public class PersonMapper implements EntityMapper<PersonDTO, Person> {
    private final Person_AddressMapper personAddressMapper;

    public PersonMapper(Person_AddressMapper personAddressMapper) {
        this.personAddressMapper = personAddressMapper;
    }


    @Override
    public Person toEntity(PersonDTO dto) {
        return new Person(dto.getUuid(), dto.getFirstName(), dto.getLastName(), dto.getGender(), personAddressMapper.toEntityList(dto.getPerson_addressList()));
    }

    @Override
    public PersonDTO toDTO(Person entity) {
        return new PersonDTO(entity.getUuid(), entity.getFirstName(), entity.getLastName(), entity.getGender(), personAddressMapper.toDTOList(entity.getPerson_addressList()));
    }

    @Override
    public List<PersonDTO> toDTOList(List<Person> entities) {
        List<PersonDTO> dtoList = new ArrayList<>();
        for (Person p : entities) {
            dtoList.add(toDTO(p));
        }
        return dtoList;
    }

    @Override
    public List<Person> toEntityList(List<PersonDTO> dtoList) {
        List<Person> personList = new ArrayList<>();
        for (PersonDTO p : dtoList) {
            personList.add(toEntity(p));
        }
        return personList;
    }
}
