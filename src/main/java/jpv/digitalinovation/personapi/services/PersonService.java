package jpv.digitalinovation.personapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpv.digitalinovation.personapi.dto.MessageResponseDTO;
import jpv.digitalinovation.personapi.entities.Person;
import jpv.digitalinovation.personapi.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public MessageResponseDTO createPerson(Person person){
		Person savedPerson = personRepository.save(person);
		
		return MessageResponseDTO.builder()
				.message("Created person with ID " + savedPerson.getId())
				.build();
				
	}
}
