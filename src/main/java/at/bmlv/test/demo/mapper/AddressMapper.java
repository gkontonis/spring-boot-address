package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Address;
import at.bmlv.test.demo.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper implements EntityMapper<AddressDTO, Address> {
    private Person_AddressMapper personAddressMapper;

    @Autowired
    public AddressMapper(@Lazy Person_AddressMapper personAddressMapper) {
        this.personAddressMapper = personAddressMapper;
    }

    @Override
    public Address toEntity(AddressDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Address(dto.getId(), dto.getStreetName(), dto.getHouseNumber(), dto.getFlatNumber(), dto.getPlace(), personAddressMapper.toEntityWithPersonList(dto.getPerson_addressList()));
    }

    @Override
    public AddressDTO toDTO(Address entity) {
        if (entity == null) {
            return null;
        }
        return new AddressDTO(entity.getId(), entity.getStreetName(), entity.getHouseNumber(), entity.getFlatNumber(), entity.getPlace(), personAddressMapper.toDTOWithPersonList(entity.getPerson_addressList()));
    }

    @Override
    public List<AddressDTO> toDTOList(List<Address> entities) {
        if (entities == null) {
            return List.of();
        }
        List<AddressDTO> dtoList = new ArrayList<>();
        for (Address a : entities) {
            dtoList.add(toDTO(a));
        }
        return dtoList;
    }

    @Override
    public List<Address> toEntityList(List<AddressDTO> dtoList) {
        if (dtoList == null) {
            return List.of();
        }
        List<Address> entityList = new ArrayList<>();
        for (AddressDTO a : dtoList) {
            entityList.add(toEntity(a));
        }
        return entityList;
    }

    //******
    //Without Person_Address
    //******

    public Address toEntityWithoutPerson_Address(AddressDTO dto) {
        if (dto == null) {
            return null;
        }
        return new Address(dto.getId(), dto.getStreetName(), dto.getHouseNumber(), dto.getFlatNumber(), dto.getPlace(), null);
    }

    public AddressDTO toDTOWithoutPerson_Address(Address entity) {
        if (entity == null) {
            return null;
        }
        return new AddressDTO(entity.getId(), entity.getStreetName(), entity.getHouseNumber(), entity.getFlatNumber(), entity.getPlace(), null);
    }


    public List<AddressDTO> toDTOWithoutPerson_AddressList(List<Address> entities) {
        if (entities == null) {
            return List.of();
        }
        List<AddressDTO> dtoList = new ArrayList<>();
        for (Address a : entities) {
            dtoList.add(toDTOWithoutPerson_Address(a));
        }
        return dtoList;
    }


    public List<Address> toEntityWithoutPerson_AddressList(List<AddressDTO> dtoList) {
        if (dtoList == null) {
            return List.of();
        }
        List<Address> entityList = new ArrayList<>();
        for (AddressDTO a : dtoList) {
            entityList.add(toEntityWithoutPerson_Address(a));
        }
        return entityList;
    }
}
