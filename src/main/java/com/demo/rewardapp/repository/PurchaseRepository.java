
  package com.demo.rewardapp.repository;
  
  import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
  import org.springframework.transaction.annotation.Transactional;

import com.demo.rewardapp.entitiy.Purchase;
  
  @Repository
  @Transactional
  public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	  
	  @Query(value="select * from purchase p where p.purchase_date like %:dateStr% and p.user_id = :userId", nativeQuery=true)
	  Optional<List<Purchase>> findByPurchaseDateLike(@Param("dateStr") String dateStr, @Param("userId") int userId);
	  
	  List<Purchase> findByUserId(int userId);
  
  }
 