package at.bmlv.test.demo.rest.service;

import at.bmlv.test.demo.dto.Person_AddressDTO;
import at.bmlv.test.demo.mapper.Person_AddressMapper;
import at.bmlv.test.demo.repository.Person_AddressRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Person_AddressService {

    private final Person_AddressRepository person_addressRepository;
    private final Person_AddressMapper person_addressMapper;

    public Person_AddressService(Person_AddressRepository personAddressRepository, Person_AddressMapper personAddressMapper) {
        this.person_addressRepository = personAddressRepository;
        this.person_addressMapper = personAddressMapper;
    }

    public Person_AddressDTO create(Person_AddressDTO person_addressDTO) {
        return person_addressMapper.toDTO(person_addressRepository.save(person_addressMapper.toEntity(person_addressDTO)));
    }

    //Updated not needed?

    public List<Person_AddressDTO> findAll(Pageable page) {
        return person_addressRepository.findAll().stream().map(person_addressMapper::toDTO).toList();
    }

    public Optional<Person_AddressDTO> findById(Long id) {
        return person_addressRepository.findById(id).map(person_addressMapper::toDTO);
    }

    public List<Person_AddressDTO> findBySearch(String search, Pageable page) {
        return person_addressRepository.findBySearch(search, page).stream().map(person_addressMapper::toDTO).toList();
    }

    public void deletePerson_AddressByID(Long id) {
        person_addressRepository.deleteById(id);
    }


}
