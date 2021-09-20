package com.resuability.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.resuability.services.model.Component;
import com.resuability.services.model.Team;

public interface ComponentRepository extends MongoRepository<Component, String> {
	List<Component> findByNameContaining(String title);	
	Optional<Component> findById(String id);
	List<Component> findByIdIn(List<String> ids);

}

