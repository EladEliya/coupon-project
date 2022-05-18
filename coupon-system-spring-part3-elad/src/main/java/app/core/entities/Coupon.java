package app.core.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@ToString (exclude ="customers")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Coupon {
	
	public enum Category {
		FOOD, ELECTRICITY, RESTURANT, VACATION, TRAVEL;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
//	private int companyId; // will remain null - contains
	private Category category;
	private String title;
	private String description;
	@JoinColumn(name = "start_date")
	private LocalDate startDate;
	@JoinColumn(name = "end_date")
	private LocalDate endDate;
	private int amount;
	private double price;
	private String image;
	@ManyToOne(cascade = { CascadeType.DETACH , CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "company_id")
	private Company company;
	@ManyToMany(cascade = { CascadeType.DETACH , CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name = "customer_coupon",
	joinColumns = @JoinColumn(name = "coupon_id"),
	inverseJoinColumns = @JoinColumn(name = "customer_id"))
	@JsonIgnore
	private List<Customer> customers;
	
	

}
