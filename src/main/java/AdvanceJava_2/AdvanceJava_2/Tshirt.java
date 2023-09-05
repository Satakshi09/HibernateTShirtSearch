package AdvanceJava_2.AdvanceJava_2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Tshirt {
	@Id
	@Column(name="product_id")
	private String id;
	private String name;
	private String color;
	private String gender;
	private String size;
	private Double price;
	private Double rating;
	private String availblity;
	
	
	
	public Tshirt() {
		super();
	}
	
	
	
	public Double getPrice() {
		return price;
	}



	public void setPrice(Double price) {
		this.price = price;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getAvailblity() {
		return availblity;
	}
	public void setAvailblity(String availblity) {
		this.availblity = availblity;
	}


	@Override
	public String toString() {
		return "Tshirt [id=" + id + ", name=" + name + ", color=" + color + ", gender=" + gender + ", size=" + size
				+ ", rating=" + rating + ", availblity=" + availblity + "]";
	}
}	    