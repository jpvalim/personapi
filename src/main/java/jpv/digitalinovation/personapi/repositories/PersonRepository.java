package jpv.digitalinovation.personapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import jpv.digitalinovation.personapi.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
