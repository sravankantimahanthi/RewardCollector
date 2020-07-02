package com.demo.rewardapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.rewardapp.entitiy.Purchase;
import com.demo.rewardapp.entitiy.User;
import com.demo.rewardapp.model.RewardHistory;
import com.demo.rewardapp.repository.PurchaseRepository;
import com.demo.rewardapp.repository.UserRepository;

@Service
public class RewardServiceImpl implements RewardService {

	public static final int REWARD_CAP = 50;
	public static final int SPECIAL_REWARD_CAP = 100;
	public static final int REWARD_VALUE = 1;
	public static final int SPECIAL_REWARD_VALUE = 2;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PurchaseRepository purchaseRepository;

	public boolean checkEligibilityForRewards(float amount) {

		boolean eligibility = true;
		if (amount > REWARD_CAP) {
			eligibility = true;
			calculateRewards(amount);
		} else {
			eligibility = false;
		}
		return eligibility;
	}

	public int calculateRewards(float amount) {
		int rewards = 0;

		if ((amount > REWARD_CAP) && amount < SPECIAL_REWARD_CAP) {
			rewards = calculateNormalReward(amount);
		} else if (amount >= SPECIAL_REWARD_CAP) {
			rewards = calcluateSpecialReward(amount);
		}
		return rewards;
	}

	public int calculateNormalReward(float amount) {
		int normalRewards = 0;
		amount = amount - REWARD_CAP;
		normalRewards = (int) (amount * REWARD_VALUE);

		return normalRewards;
	}

	public int calcluateSpecialReward(float amount) {
		int specialRewards = 0;

		float specialRewardEligibleAmount = amount - SPECIAL_REWARD_CAP;
		float remRewardEligibleAmount = amount - specialRewardEligibleAmount;
		int remRewards = calculateNormalReward(remRewardEligibleAmount);
		specialRewards = (int) (specialRewardEligibleAmount * SPECIAL_REWARD_VALUE);
		specialRewards = specialRewards + remRewards;

		return specialRewards;
	}
	
	public boolean checkIfUserExists(int userId)
	{
		Optional<User> existingUser = userRepository.findById(userId);
		if (existingUser.isPresent()) {
			return true;
		}
		else {
			
			return false;
		}
	}

	@Override
	public String registerUser(User user) {

		if (user != null) {
			if(checkIfUserExists(user.getUserId()))
			{
				return "User exists already. Please submit purchases to earn rewards";
			}
			else
			{
				user = userRepository.save(user);
				return "User successfully regsitered \n" + user.toString();
			}
		}
		else
		{
			return "Invalid user information";
		}
	}

	@Override
	public String recordPurchases(List<Purchase> purchases, int userId) {

		if (purchases == null || purchases.isEmpty()) {
			return "Invaild Purchase transaction. Please check details";
		}
		
		if(!checkIfUserExists(userId))
		{
			return "Invalid user Id. Please check details";
		}
		else
		{
			purchases.forEach(p -> {
				p.setRewardPoints(calculateRewards(p.getPurchaseAmount()));
				p.setUserId(userId);
				//p.getMappedUser().setUserId(Integer.parseInt(userId));
				purchaseRepository.save(p);
			});
			
			return "Purchases recorded successfully";
		}		
	}

	@Override
	public User fetchUser(int userId) {

		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return user.get();
		}

		return null;
	}

	@Override
	public RewardHistory getMonthlyRewardHistory(int userId, String month, String year) {
		
		Optional<List<Purchase>> purchaseHistory = null;
		int rewardTotal = 0;
		
		if(checkIfUserExists(userId))
		{
			if(month != null)
			{				
				purchaseHistory = purchaseRepository.findByPurchaseDateLike(month+"-"+year, userId);
			}
			else
			{
				purchaseHistory = purchaseRepository.findByPurchaseDateLike(year, userId);
			}
			
			if(!purchaseHistory.isEmpty())
			{
				
				for (Purchase purchase : purchaseHistory.get()) {
					rewardTotal = rewardTotal + purchase.getRewardPoints();
				}
			}
			
			RewardHistory history = new RewardHistory();
			if(month!=null)
			{				
				history.setComments("Successfully fetched reward history for " + month + "-" + year);
			}
			else
			{
				history.setComments("Successfully fetched reward history for " + year);
			}
			history.setPurchaseHistory(purchaseHistory.get());
			history.setRewardsCount(rewardTotal);
			history.setUser(userRepository.findById(userId).get());
			
			return history;
		}
		else
		{
			RewardHistory history = new RewardHistory();
			history.setComments("Invalid User Id. Please check details");
			return history;
		}
		
	}

	@Override
	public RewardHistory getYearlyRewardHistory(int userId, String year) {
		
		return getMonthlyRewardHistory(userId,null,year);
	}

	@Override
	public RewardHistory getTotalRewardHistory(int userId) {
		
		int rewardTotal = 0;		
		
		List<Purchase> purchaseHistory = null;
		
		if(checkIfUserExists(userId))
		{
			 purchaseHistory = purchaseRepository.findByUserId(userId); 
			
			 for (Purchase purchase : purchaseHistory) {
					rewardTotal = rewardTotal + purchase.getRewardPoints();
				}
			
			 RewardHistory rewardHistory = new RewardHistory();
				rewardHistory.setUser(userRepository.findById(userId).get());
				rewardHistory.setPurchaseHistory(purchaseHistory);
				rewardHistory.setRewardsCount(rewardTotal);
				rewardHistory.setComments("Successfully fetched reward history");
			
			return rewardHistory;
		}
		else
		{
			RewardHistory history = new RewardHistory();
			history.setComments("Invalid User Id. Please check details");
			return history;
		}
	}

}
