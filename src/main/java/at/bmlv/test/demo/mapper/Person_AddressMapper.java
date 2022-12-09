package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Person_Address;
import at.bmlv.test.demo.dto.Person_AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Person_AddressMapper implements EntityMapper<Person_AddressDTO, Person_Address> {
    private PersonMapper personMapper;
    private AddressMapper addressMapper;

    @Override
    public Person_Address toEntity(Person_AddressDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Person_Address(dto.getId(), personMapper.toEntity(dto.getPerson()), addressMapper.toEntity(dto.getAddress()));
    }

    @Override
    public Person_AddressDTO toDTO(Person_Address entity) {
        if (entity == null) {
            return null;
        }
        return new Person_AddressDTO(entity.getId(), personMapper.toDTO(entity.getPerson()), addressMapper.toDTO(entity.getAddress()));
    }

    @Override
    public List<Person_AddressDTO> toDTOList(List<Person_Address> entities) {
        if (entities == null) {
            return List.of();
        }

        List<Person_AddressDTO> dtoList = new ArrayList<>();
        for (Person_Address pa : entities) {
            dtoList.add(toDTO(pa));
        }
        return dtoList;
    }

    @Override
    public List<Person_Address> toEntityList(List<Person_AddressDTO> dtoList) {
        if (dtoList == null) {
            return List.of();
        }

        List<Person_Address> person_addressList = new ArrayList<>();
        for (Person_AddressDTO person_addressDTO : dtoList) {
            person_addressList.add(toEntity(person_addressDTO));
        }
        return person_addressList;
    }

    public PersonMapper getPersonMapper() {
        return personMapper;
    }

    @Autowired
    public void setPersonMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public AddressMapper getAddressMapper() {
        return addressMapper;
    }

    @Autowired
    public void setAddressMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }
}
