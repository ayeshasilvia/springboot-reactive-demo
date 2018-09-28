package com.reactivespring.demo;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends KeyValueRepository<Game, String> {

    @Override
    List<Game> findAll();
}
