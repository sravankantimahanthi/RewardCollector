package com.demo.rewardapp.model;

import java.util.List;

import com.demo.rewardapp.entitiy.Purchase;
import com.demo.rewardapp.entitiy.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RewardHistory {
	
	private int rewardsCount;
	private User user;
	private List<Purchase> purchaseHistory;
	private String comments;
	
	public int getRewardsCount() {
		return rewardsCount;
	}
	public void setRewardsCount(int rewardsCount) {
		this.rewardsCount = rewardsCount;
	}
	public List<Purchase> getPurchaseHistory() {
		return purchaseHistory;
	}
	public void setPurchaseHistory(List<Purchase> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
