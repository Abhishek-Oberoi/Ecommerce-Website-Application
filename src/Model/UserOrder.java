package Model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="USERORDER")
public class UserOrder {


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ORDERID", unique = true, nullable = false)
	private Integer orderid;


	@Column(name = "USERID", nullable = false)
	private Integer userid;
	
	@Column(name = "PRODUCTID", nullable = false)
	private Integer productid;
	
	@Column(name = "PRODUCTNAME", length = 225)
	private String productname;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PURCHASEDATE", length = 19)
	private Date purchasedate;

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Date getPurchasedate() {
		return purchasedate;
	}

	public void setPurchasedate(Date purchasedate) {
		this.purchasedate = purchasedate;
	}

	@Override
	public String toString() {
		return "UserOrder [orderid=" + orderid + ", userid=" + userid + ", productid=" + productid + ", productname="
				+ productname + ", purchasedate=" + purchasedate + "]";
	}

	
	
		
}
