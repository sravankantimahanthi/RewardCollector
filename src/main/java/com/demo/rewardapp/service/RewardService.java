package com.demo.rewardapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.rewardapp.entitiy.Purchase;
import com.demo.rewardapp.entitiy.User;
import com.demo.rewardapp.model.RewardHistory;

public interface RewardService {

	public boolean checkEligibilityForRewards(float purchaseAmount);
	
	public int calculateRewards(float purchaseAmount);
	
	public String registerUser(User user);
	
	public String recordPurchases(List<Purchase> purchases, int userId);

	public User fetchUser(int userId);
	
	public RewardHistory getMonthlyRewardHistory(int userId, String month, String year);
	
	public RewardHistory getYearlyRewardHistory(int userId, String year);
	
	public RewardHistory getTotalRewardHistory(int userId);
}
