package com.example.springboot.services;

import com.example.springboot.dataservice.es.repositories.ContactRepository;
import com.example.springboot.dataservice.es.repositories.entities.Contact;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    /**
     * Create a new contact in Elasticsearch Contact index
     * @param contact
     */
    public void addContact(final Contact contact){
        contactRepository.save(contact);
    }

    /**
     * Fetched all the contacts, paginated with page size.
     * @return list of paginated contacts - default page size of 10 is returned
     */
    public Iterable<Contact> getAll(){
        return contactRepository.findAll();
    }

    /**
     * Retrieve a contact specified by id from the elasticsearch index.
     * @param id
     * @return Optional contact . The Return can be absent, if not present
     */
    public Optional<Contact> getById(String id){
        return contactRepository.findById(id);
    }

    /**
     * Retrieve all contacts specified by ids from the elasticsearch index.
     * @param ids
     * @return list of paginated contacts - default page size of 10 is returned
     */
    public Iterable<Contact> getAllByIds(List<String> ids){
        Iterable<String> itIds = () -> ids.iterator();
        return contactRepository.findAllById(itIds);
    }

    /**
     * Delete a contact marked by id , if present
     * @param id
     */
    public void deleteById(String id){
        contactRepository.deleteById(id);
    }

    /**
     * Delete all the contacts in the index.
     */
    public  void deleteAll(){
        contactRepository.deleteAll();
    }

    public List<Contact> getContact(String firstName, String lastName){




        return contactRepository.findByFirstNameAndLastName(firstName, lastName);
    }


    public List<SearchHit<Contact>> getContacts(String lastName){

        // build query
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        boolQuery.must().add(QueryBuilders.matchQuery("lastName", lastName));

        // Use native search builder to generate ES query
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(boolQuery);

        // Make ESS using Inject ESOperations
        SearchHits<Contact> contactsResponse = elasticsearchOperations.search(builder.build(), Contact.class);

        return contactsResponse.getSearchHits();
    }
}
