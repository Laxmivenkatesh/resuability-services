package com.resuability.services.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.resuability.services.model.Team;

public interface TeamRepository extends MongoRepository<Team, String> {
	List<Team> findByNameContaining(String title);	
	List<Team> findByIdIn(List<String> ids);
	Optional<Team> findById(String id);
}

