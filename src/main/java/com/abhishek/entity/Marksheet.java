package com.abhishek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "marksheet")
public class Marksheet {

	@Id
	@Column(name = "stud_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stud_id;

	@Column(name = "stud_name")
	@NotBlank(message = "Should not be blank")
	@Size(min = 1, max = 50,message = "Between 1 and 50")
	private String stud_name;

	@NotNull(message = "should not be null")
	@Column(name = "roll_no")
	private Integer roll_no;

	@NotNull(message = "Should be in between 0 to 100")
	@Column(name = "physics")
	@Min(0)
	@Max(100)
//	@Size(max = 100, message = "should not be greater than 100")
	private Integer physics;

	@NotNull(message = "Shouls be in between 0 to 100")
	@Column(name = "chemistry")
	@Min(0)
	@Max(100)
//	@Size(max = 100, message = "should not be greater than 100")
	private Integer chemistry;

	@NotNull(message = "should be in between 0 to 100")
	@Column(name = "maths")
	@Min(0)
	@Max(100)
//	@Size(max = 100, message = "should not be greater than 100")
	private Integer maths;

	@NotNull(message = "Should be in between 0 to 100")
	@Column(name = "biology")
//	@Size(max = 100, message = "should not be greater than 100")
	@Max(100)
	@Min(0)
	private Integer biology;

	@NotBlank(message = "Required college Name")
	@Column(name = "college_name")
	private String college_name;

	public int getStud_id() {
		return stud_id;
	}

	public void setStud_id(int stud_id) {
		this.stud_id = stud_id;
	}

	public String getStud_name() {
		return stud_name;
	}

	public void setStud_name(String stud_name) {
		this.stud_name = stud_name;
	}

	public Integer getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(Integer roll_no) {
		this.roll_no = roll_no;
	}

	

	public Integer getPhysics() {
		return physics;
	}

	public void setPhysics(Integer physics) {
		this.physics = physics;
	}

	public Integer getChemistry() {
		return chemistry;
	}

	public void setChemistry(Integer chemistry) {
		this.chemistry = chemistry;
	}

	public Integer getMaths() {
		return maths;
	}

	public void setMaths(Integer maths) {
		this.maths = maths;
	}

	public Integer getBiology() {
		return biology;
	}

	public void setBiology(Integer biology) {
		this.biology = biology;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}

}
