package com.kitsoft.freetifyServer.repositories;

import com.kitsoft.freetifyServer.entities.Composition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompositionRepository extends CrudRepository<Composition,Long> {
    Composition findById(long id);
    Composition findByName(String name);
}
