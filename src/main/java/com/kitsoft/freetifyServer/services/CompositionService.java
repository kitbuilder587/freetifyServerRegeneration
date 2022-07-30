package com.kitsoft.freetifyServer.services;

import com.kitsoft.freetifyServer.entities.Composition;
import com.kitsoft.freetifyServer.exceptions.IllegalCompositionArgumentsException;
import com.kitsoft.freetifyServer.repositories.CompositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompositionService {
    private CompositionRepository compositionRepository;

    @Autowired
    public CompositionService(CompositionRepository compositionRepository){
        this.compositionRepository = compositionRepository;
    }

    public void addComposition(Composition composition){
        if(composition.getName() == null) throw new IllegalCompositionArgumentsException("Name is null");
        if(composition.getAuthorName() == null) throw new IllegalCompositionArgumentsException("Author is null");

        compositionRepository.save(composition);
    }

}
