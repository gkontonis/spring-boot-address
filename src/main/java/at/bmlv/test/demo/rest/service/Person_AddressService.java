package at.bmlv.test.demo.rest.service;

import at.bmlv.test.demo.domain.Address;
import at.bmlv.test.demo.domain.Person;
import at.bmlv.test.demo.domain.Person_Address;
import at.bmlv.test.demo.dto.Person_AddressDTO;
import at.bmlv.test.demo.mapper.Person_AddressMapper;
import at.bmlv.test.demo.repository.AddressRepository;
import at.bmlv.test.demo.repository.PersonRepository;
import at.bmlv.test.demo.repository.Person_AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class Person_AddressService {

    private final Person_AddressRepository person_addressRepository;
    private final Person_AddressMapper person_addressMapper;

    private final PersonRepository personRepository;

    private final AddressRepository addressRepository;

    public Person_AddressService(Person_AddressRepository personAddressRepository, Person_AddressMapper personAddressMapper, PersonRepository personRepository, AddressRepository addressRepository) {
        this.person_addressRepository = personAddressRepository;
        this.person_addressMapper = personAddressMapper;
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Person_AddressDTO create(Person_AddressDTO dto) {
        Optional<Person> person = personRepository.findById(dto.getPerson().getUuid());
        Optional<Address> address = addressRepository.findById(dto.getAddress().getId());

        if (person.isEmpty() || address.isEmpty()) {
            throw new EntityNotFoundException();
        }

        Person_Address person_address = new Person_Address();
        person_address.setPerson(person.get());
        person_address.setAddress(address.get());

        return person_addressMapper.toDTO(person_addressRepository.save(person_address));
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

    @Transactional
    public void deletePerson_AddressByID(Long id) {
        person_addressRepository.deleteById(id);
    }


}
