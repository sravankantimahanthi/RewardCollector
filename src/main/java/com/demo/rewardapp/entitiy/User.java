package com.demo.rewardapp.entitiy;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;
	private String userName;
	private String gender;
	
	//@OneToMany(fetch = FetchType.LAZY,  cascade = CascadeType.ALL, mappedBy = "user")
	//@OneToMany(mappedBy = "mappedUser", cascade = CascadeType.ALL)
	//@JoinColumn(name="user_id", referencedColumnName="user_id")
	//private List<Purchase> purchases;

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int userId) {
		this.userId = userId;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/*
	 * public List<Purchase> getPurchases() { return purchases; }
	 * 
	 * public void setPurchases(List<Purchase> purchases) { this.purchases =
	 * purchases; }
	 */
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("userId:" + this.getUserId() + "\n");
		sb.append("userName:" + this.getUserName() + "\n");
		sb.append("userGender:" + this.getGender() + "\n");
		
		/*
		 * purchases.forEach( p -> sb.append("purchaseId:" + p.getPurchaseId() + "\t" +
		 * "purchaseItem:" + p.getPurchaseItem() + "\t" + "purchaseDate:" +
		 * p.getPurchaseDate() + "\t" + "purchaseAmount:" + p.getPurchaseAmount() + "\t"
		 * + "Rewards:" + p.getRewardPoints()));
		 */
		
		return sb.toString();
	}

}
