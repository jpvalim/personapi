package jpv.digitalinovation.personapi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpv.digitalinovation.personapi.dto.MessageResponseDTO;
import jpv.digitalinovation.personapi.dto.PersonDTO;
import jpv.digitalinovation.personapi.entities.Person;
import jpv.digitalinovation.personapi.mapper.PersonMapper;
import jpv.digitalinovation.personapi.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	public MessageResponseDTO createPerson(PersonDTO personDTO){
		
		Person personToSave = personMapper.toModel(personDTO);
		Person savedPerson = personRepository.save(personToSave);
		
		
		return MessageResponseDTO.builder()
				.message("Created person with ID " + savedPerson.getId())
				.build();
				
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream().map(personMapper :: toDTO).collect(Collectors.toList());
	}
}
