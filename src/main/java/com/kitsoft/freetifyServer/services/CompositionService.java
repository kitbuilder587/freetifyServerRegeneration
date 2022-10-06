package com.kitsoft.freetifyServer.services;

import com.kitsoft.freetifyServer.entities.Composition;
import com.kitsoft.freetifyServer.entities.User;
import com.kitsoft.freetifyServer.exceptions.IllegalCompositionArgumentsException;
import com.kitsoft.freetifyServer.repositories.CompositionRepository;
import com.kitsoft.freetifyServer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompositionService {
    private CompositionRepository compositionRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    public CompositionService(CompositionRepository compositionRepository){
        this.compositionRepository = compositionRepository;
    }

    public void addComposition(Composition composition){
        if(composition.getName() == null) throw new IllegalCompositionArgumentsException("Name is null");
        if(composition.getAuthorName() == null) throw new IllegalCompositionArgumentsException("Author is null");

        compositionRepository.save(composition);
    }

    public List<Composition> getCompositions(){
        return (List<Composition>) compositionRepository.findAll();
    }

    public void addListener(Long compositionId, String username){
        Composition composition = compositionRepository.findById(compositionId).orElseThrow(() -> new RuntimeException("illegalId"));
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("illegalUsername"));
        composition.getListeners().add(user);
        compositionRepository.save(composition);
    }

}
