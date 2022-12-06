package at.bmlv.test.demo.mapper;

import at.bmlv.test.demo.domain.Address;
import at.bmlv.test.demo.domain.Person;
import at.bmlv.test.demo.dto.AddressDTO;
import at.bmlv.test.demo.dto.PersonDTO;

import java.util.List;

public class AddressMapper implements EntityMapper<AddressDTO, Address>  {
    @Override
    public Address toEntity(AddressDTO dto) {
        return null;
    }

    @Override
    public AddressDTO toDTO(Address entity) {
        return null;
    }

    @Override
    public List<AddressDTO> toDTOList(List<Address> entities) {
        return null;
    }

    @Override
    public List<Address> toEntityList(List<AddressDTO> dtoList) {
        return null;
    }
}
