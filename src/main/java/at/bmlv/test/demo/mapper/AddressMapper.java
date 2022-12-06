package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Address;
import at.bmlv.test.demo.domain.Person;
import at.bmlv.test.demo.dto.AddressDTO;
import at.bmlv.test.demo.dto.PersonDTO;

import java.util.ArrayList;
import java.util.List;

public class AddressMapper implements EntityMapper<AddressDTO, Address> {
    private final Person_AddressMapper personAddressMapper;

    public AddressMapper(Person_AddressMapper personAddressMapper) {
        this.personAddressMapper = personAddressMapper;
    }

    //TODO mby Needs PlaceMapper
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
