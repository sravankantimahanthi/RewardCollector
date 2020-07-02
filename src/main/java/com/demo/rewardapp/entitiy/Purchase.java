package com.demo.rewardapp.entitiy;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NamedNativeQueries({
    @NamedNativeQuery(
            name    =   "getMonthlyReward",
            query   =   "SELECT * " +
                        "FROM purchase" + 
                        "WHERE purchase_date LIKE ?",
                        resultClass=Purchase.class)
    /*),
    @NamedNativeQuery(
            name    =   "getAllEmployeesByDeptId",
            query   =   "SELECT id, firstName, lastName, email, department.id, department.name " +
                        "FROM employee, department " + 
                        "WHERE department.id = ?",
                        resultClass=EmployeeEntity.class
    )*/
})
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int purchaseId;
	private int userId;
	private String purchaseItem;
	private String purchaseDate;
	private float purchaseAmount;
	private int rewardPoints;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "user_id", referencedColumnName="user_id", updatable =
	 * true, insertable = true) private User mappedUser;
	 */
	
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	public String getPurchaseItem() {
		return purchaseItem;
	}
	public void setPurchaseItem(String purchaseItem) {
		this.purchaseItem = purchaseItem;
	}
//	public Date getPurchaseDate() {
//		return purchaseDate;
//	}
//	public void setPurchaseDate(Date purchaseDate) {
//		this.purchaseDate = purchaseDate;
//	}
	public float getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(float purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public int getRewardPoints() {
		return rewardPoints;
	}
	public void setRewardPoints(int rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("purchaseId:" + this.getPurchaseId() + "\n");
		sb.append("userId:" + this.getUserId() + "\n");
		sb.append("purchaseItem:" + this.getPurchaseItem() + "\n");
		sb.append("purchaseAmount:" + this.getPurchaseAmount() + "\n");
		sb.append("purchaseDate:" + this.getPurchaseDate() + "\n");
		sb.append("rewardPoints:" + this.getRewardPoints() + "\n");	
		
		return sb.toString();
	}

	/*
	 * public User getMappedUser() { return mappedUser; } public void
	 * setMappedUser(User mappedUser) { this.mappedUser = mappedUser; }
	 */
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
