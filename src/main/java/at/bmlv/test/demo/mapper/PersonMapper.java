package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Person;
import at.bmlv.test.demo.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonMapper implements EntityMapper<PersonDTO, Person> {
    private Person_AddressMapper personAddressMapper;

    @Autowired
    public PersonMapper(@Lazy Person_AddressMapper personAddressMapper) {
        this.personAddressMapper = personAddressMapper;
    }

    @Override
    public Person toEntity(PersonDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Person(dto.getUuid(), dto.getFirstName(), dto.getLastName(), dto.getGender(), personAddressMapper.toEntityWithAddressList(dto.getPerson_addressList()));
    }

    @Override
    public PersonDTO toDTO(Person entity) {
        if (entity == null) {
            return null;
        }
        return new PersonDTO(entity.getUuid(), entity.getFirstName(), entity.getLastName(), entity.getGender(), personAddressMapper.toDTOWithAddressList(entity.getPerson_addressList()));
    }

    @Override
    public List<PersonDTO> toDTOList(List<Person> entities) {
        if (entities == null) {
            return List.of();
        }

        List<PersonDTO> dtoList = new ArrayList<>();
        for (Person p : entities) {
            dtoList.add(toDTO(p));
        }
        return dtoList;
    }

    @Override
    public List<Person> toEntityList(List<PersonDTO> dtoList) {
        if (dtoList == null) {
            return List.of();
        }

        List<Person> personList = new ArrayList<>();
        for (PersonDTO p : dtoList) {
            personList.add(toEntity(p));
        }
        return personList;
    }


    //******
    //Without Person_Address
    //******
    public Person toEntityWithOutPerson_Address(PersonDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Person(dto.getUuid(), dto.getFirstName(), dto.getLastName(), dto.getGender(), null);
    }

    public PersonDTO toDTOWithOutPerson_Address(Person entity) {
        if (entity == null) {
            return null;
        }
        return new PersonDTO(entity.getUuid(), entity.getFirstName(), entity.getLastName(), entity.getGender(), null);
    }

    public List<PersonDTO> toDTOWithOutPerson_AddressList(List<Person> entities) {
        if (entities == null) {
            return List.of();
        }

        List<PersonDTO> dtoList = new ArrayList<>();
        for (Person p : entities) {
            dtoList.add(toDTOWithOutPerson_Address(p));
        }
        return dtoList;
    }

    public List<Person> toEntityWithOutPerson_AddressList(List<PersonDTO> dtoList) {
        if (dtoList == null) {
            return List.of();
        }

        List<Person> personList = new ArrayList<>();
        for (PersonDTO p : dtoList) {
            personList.add(toEntityWithOutPerson_Address(p));
        }
        return personList;
    }
}
