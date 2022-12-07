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
        return new Address(dto.getId(), dto.getStreetName(), dto.getHouseNumber(), dto.getFlatNumber(), dto.getPlace(), personAddressMapper.toEntityList(dto.getPerson_addressList()));
    }

    @Override
    public AddressDTO toDTO(Address entity) {
        return new AddressDTO(entity.getId(), entity.getStreetName(), entity.getHouseNumber(), entity.getFlatNumber(), entity.getPlace(), personAddressMapper.toDTOList(entity.getPerson_addressList()));
    }

    @Override
    public List<AddressDTO> toDTOList(List<Address> entities) {
        List<AddressDTO> dtoList = new ArrayList<>();
        for (Address a : entities) {
            dtoList.add(toDTO(a));
        }
        return dtoList;
    }

    @Override
    public List<Address> toEntityList(List<AddressDTO> dtoList) {
        List<Address> entityList = new ArrayList<>();
        for (AddressDTO a : dtoList) {
            entityList.add(toEntity(a));
        }
        return entityList;
    }
}
