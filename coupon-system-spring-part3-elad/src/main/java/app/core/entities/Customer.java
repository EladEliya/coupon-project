package app.core.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = "coupons" )
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	@JoinColumn(name = "first_name")
	private String firstName ;
	@JoinColumn(name = "last_name")
	private String lastName ;
	private String email ;
	private String password ;
	@ManyToMany (cascade = { CascadeType.DETACH , CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable (name= "customer_coupon" , 
	joinColumns = @JoinColumn(name = "customer_id"),
	inverseJoinColumns =  @JoinColumn(name = "coupon_id"))
	@JsonIgnore
	private List<Coupon> coupons;
	
	
	public void addCoupon (Coupon coupon) {
		if (this.coupons == null) {
			this.coupons = new ArrayList<>();
		}
		this.coupons.add(coupon);
	}
	

}
