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
        return new Person_Address(dto.getId(), personMapper.toEntityWithOutPerson_Address(dto.getPerson()), addressMapper.toEntityWithoutPerson_Address(dto.getAddress()));
    }

    @Override
    public Person_AddressDTO toDTO(Person_Address entity) {
        return new Person_AddressDTO(entity.getId(), personMapper.toDTOWithOutPerson_Address(entity.getPerson()), addressMapper.toDTOWithoutPerson_Address(entity.getAddress()));
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

    //------------------------------------------------------------
    //Person
    //------------------------------------------------------------

    public Person_Address toEntityWithPerson(Person_AddressDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Person_Address(dto.getId(), personMapper.toEntityWithOutPerson_Address(dto.getPerson()), null);
    }

    public Person_AddressDTO toDTOWithPerson(Person_Address entity) {
        if (entity == null) {
            return null;
        }
        return new Person_AddressDTO(entity.getId(), personMapper.toDTOWithOutPerson_Address(entity.getPerson()), null);
    }

    public List<Person_Address> toEntityWithPersonList(List<Person_AddressDTO> dtoList) {
        if (dtoList == null) {
            return List.of();
        }

        List<Person_Address> person_addressList = new ArrayList<>();
        for (Person_AddressDTO person_addressDTO : dtoList) {
            person_addressList.add(toEntityWithPerson(person_addressDTO));
        }
        return person_addressList;
    }

    public List<Person_AddressDTO> toDTOWithPersonList(List<Person_Address> entities) {
        if (entities == null) {
            return List.of();
        }

        List<Person_AddressDTO> dtoList = new ArrayList<>();
        for (Person_Address pa : entities) {
            dtoList.add(toDTOWithPerson(pa));
        }
        return dtoList;
    }

    //------------------------------------------------------------
    //Address
    //------------------------------------------------------------
    public Person_Address toEntityWithAddress(Person_AddressDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Person_Address(dto.getId(), null, addressMapper.toEntityWithoutPerson_Address(dto.getAddress()));
    }

    public Person_AddressDTO toDTOWithAddress(Person_Address entity) {
        if (entity == null) {
            return null;
        }
        return new Person_AddressDTO(entity.getId(), null, addressMapper.toDTOWithoutPerson_Address(entity.getAddress()));
    }

    public List<Person_Address> toEntityWithAddressList(List<Person_AddressDTO> dtoList) {
        if (dtoList == null) {
            return List.of();
        }
        List<Person_Address> person_addressList = new ArrayList<>();
        for (Person_AddressDTO person_addressDTO : dtoList) {
            person_addressList.add(toEntityWithAddress(person_addressDTO));
        }
        return person_addressList;
    }

    public List<Person_AddressDTO> toDTOWithAddressList(List<Person_Address> entities) {
        if (entities == null) {
            return List.of();
        }
        List<Person_AddressDTO> dtoList = new ArrayList<>();
        for (Person_Address pa : entities) {
            dtoList.add(toDTOWithAddress(pa));
        }
        return dtoList;
    }

    //------------------------------------------------------------
    //Getter setter
    //------------------------------------------------------------
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
