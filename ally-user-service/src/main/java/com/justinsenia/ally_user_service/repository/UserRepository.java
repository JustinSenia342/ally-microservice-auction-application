package com.justinsenia.ally_user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.justinsenia.ally_user_service.model.User;


// Repository which extends a Jpa Repository using a User DAO Object 
@Repository 
public interface UserRepository extends JpaRepository<User, Long>{

}
