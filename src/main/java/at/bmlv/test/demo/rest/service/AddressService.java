package at.bmlv.test.demo.rest.service;

import at.bmlv.test.demo.domain.Address;
import at.bmlv.test.demo.dto.AddressDTO;
import at.bmlv.test.demo.mapper.AddressMapper;
import at.bmlv.test.demo.mapper.Person_AddressMapper;
import at.bmlv.test.demo.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;


    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Transactional
    public AddressDTO create(AddressDTO addressDTO) {
        return addressMapper.toDTO(addressRepository.save(addressMapper.toEntity(addressDTO)));
    }
    @Transactional
    public void update(AddressDTO addressDTO) {
        Address address = addressMapper.toEntity(addressDTO);
        addressRepository.update(
                address.getStreetName(),
                address.getHouseNumber(),
                address.getFlatNumber(),
                address.getPlace(),
                address.getId());
    }

    public List<AddressDTO> findAll(Pageable page) {
        return addressRepository.findAll().stream().map(addressMapper::toDTO).toList();
    }

    public Optional<AddressDTO> findById(Long id) {
        return addressRepository.findById(id).map(addressMapper::toDTO);
    }

    public List<AddressDTO> findBySearch(String search, Pageable page) {
        return addressRepository.findBySearch(search, page).stream().map(addressMapper::toDTO).toList();
    }
    @Transactional
    public void deleteByAddressID(Long id) {
        addressRepository.deleteById(id);
    }
}
