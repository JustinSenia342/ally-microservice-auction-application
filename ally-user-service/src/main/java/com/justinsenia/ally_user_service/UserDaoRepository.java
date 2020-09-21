package com.justinsenia.ally_user_service;

import org.springframework.data.repository.CrudRepository;

import com.justinsenia.ally_user_service.UserDao;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

public interface UserDaoRepository extends CrudRepository<UserDao, Integer>{

}
