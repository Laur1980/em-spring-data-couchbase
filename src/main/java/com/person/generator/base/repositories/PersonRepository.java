package com.person.generator.base.repositories;

import com.person.generator.base.documents.PersonDocument;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbasePagingAndSortingRepository;


@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "personDocument")
public interface PersonRepository extends CouchbasePagingAndSortingRepository<PersonDocument,String> {


}
