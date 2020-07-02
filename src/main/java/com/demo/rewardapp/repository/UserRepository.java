
  package com.demo.rewardapp.repository;
  
  import org.springframework.data.jpa.repository.JpaRepository;
  import org.springframework.data.repository.CrudRepository;
  import org.springframework.stereotype.Repository;
  import org.springframework.transaction.annotation.Transactional;

import com.demo.rewardapp.entitiy.User;
  
  @Repository
  @Transactional
  public interface UserRepository extends JpaRepository<User, Integer> {
  
  }
 