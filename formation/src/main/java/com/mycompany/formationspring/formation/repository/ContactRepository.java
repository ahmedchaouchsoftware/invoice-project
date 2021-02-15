package com.mycompany.formationspring.formation.repository;

import com.mycompany.formationspring.formation.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
