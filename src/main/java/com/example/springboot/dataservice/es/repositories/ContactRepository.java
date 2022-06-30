package com.example.springboot.dataservice.es.repositories;

import com.example.springboot.dataservice.es.repositories.entities.Contact;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ContactRepository extends ElasticsearchRepository<Contact, String> {
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);

    @Query("{\"match\": {\"firstName\": {\"query\": \"?0\"}}}")
    List<Contact> findByFirstName(String name);
}
