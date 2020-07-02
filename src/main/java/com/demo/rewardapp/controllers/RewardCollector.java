package com.demo.rewardapp.controllers;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rewardapp.entitiy.Purchase;
import com.demo.rewardapp.entitiy.User;
import com.demo.rewardapp.model.RewardHistory;
import com.demo.rewardapp.service.RewardService;

@RestController
@RequestMapping(value="/reward-app")
public class RewardCollector {

	@Autowired
	private RewardService rewardSvc;
	
	  @RequestMapping(value = "/record-purchases/{userId}", method = RequestMethod.POST) 
	  public String purchase(@RequestBody List<Purchase> purchases, @PathVariable int userId)
	  { 
		return rewardSvc.recordPurchases(purchases, userId);  
	  }
	 
	  @RequestMapping(value = "/reg-user", method = RequestMethod.POST) 
	  public String registerUser(@RequestBody User user)
	  { 
		return rewardSvc.registerUser(user);
	  }
	  
	  @RequestMapping(value = "/fetch-user/{userId}", method = RequestMethod.GET) 
	  public User fetchUser(@PathVariable int userId)
	  { 
		return rewardSvc.fetchUser(userId);
	  }
	  
	  @RequestMapping(value = "/monthly-reward-history/{userId}/{month}/{year}") 
	  public RewardHistory getMonthlyRewardHistory(@PathVariable int userId, @PathVariable String month, @PathVariable String year)
	  { 
		return rewardSvc.getMonthlyRewardHistory(userId, month, year);
	  }
	  
	  @RequestMapping(value = "/yearly-reward-history/{userId}/{year}") 
	  public RewardHistory getYearlyRewardHistory(@PathVariable int userId, @PathVariable String year)
	  { 
		return rewardSvc.getYearlyRewardHistory(userId, year);
	  }
	  
	  @RequestMapping(value = "/total-reward-history/{userId}") 
	  public RewardHistory getRewardHistory(@PathVariable int userId)
	  { 
		  return rewardSvc.getTotalRewardHistory(userId);
	  }
	
}
