package com.example.springboot.dataservice.es.repositories.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Document(indexName = "contact", createIndex = true, writeTypeHint = WriteTypeHint.FALSE)
@Setting(settingPath = "/contacts/settings.json")
@Mapping(mappingPath = "/contacts/mappings.json")
@Getter
@Setter
public class Contact {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String firstName;

    @Field(type = FieldType.Text)
    private String lastName;

    @Field(type = FieldType.Text)
    private String email;

    @Field(type = FieldType.Text)
    private String phone;

    @Field(type = FieldType.Text)
    private String group;

}
