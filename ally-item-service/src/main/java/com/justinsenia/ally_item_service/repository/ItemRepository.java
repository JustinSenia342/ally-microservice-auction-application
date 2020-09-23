package com.justinsenia.ally_item_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.justinsenia.ally_item_service.model.Item;


// Repository which extends a Jpa Repository using a User DAO Object 
@Repository 
public interface ItemRepository extends JpaRepository<Item, Long>{

}
