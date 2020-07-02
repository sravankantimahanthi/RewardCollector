package com.demo.rewardapp;

public class Test {
	
	public static final int REWARD_CAP = 50;
	public static final int DOUBLE_REWARD_CAP = 100;
	public static final int REWARD_VALUE = 1;
	public static final int DOUBLE_REWARD_VALUE = 2;
	
	public boolean checkEligibilityForRewards(float amount) {
		
		boolean eligibility = true;
		if(amount > 50)
		{
			eligibility = true;
			calculateRewards(amount);
		}
		else {
			eligibility = false;
		}
		return eligibility;
	}

	public static int calculateRewards(float amount) {
		int rewards = 0;
		
		if((amount > REWARD_CAP) && amount < DOUBLE_REWARD_CAP)
		{
			rewards = normalReward(amount);
		}
		else if(amount >= DOUBLE_REWARD_CAP)
		{
			rewards = specialReward(amount);
		}
		return rewards;
	}
	
	public static int normalReward(float amount)
	{
		int normalRewards = 0;
		amount = amount - REWARD_CAP;
		normalRewards = (int) (amount * REWARD_VALUE);
		
		return normalRewards;
	}
	
	public static int specialReward(float amount)
	{
		int specialRewards = 0;
		
		float doubleRewardEligibleAmount = amount - DOUBLE_REWARD_CAP;
		float rewardEligibleAmount = amount - doubleRewardEligibleAmount;
		int remRewards = normalReward(rewardEligibleAmount);
		specialRewards = (int) (doubleRewardEligibleAmount * DOUBLE_REWARD_VALUE);
		specialRewards = specialRewards + remRewards;
		
		return specialRewards;
	}

	public static void main(String[] args) {
		
		int result = calculateRewards(132);
		System.out.println(result);
		

	}

}
