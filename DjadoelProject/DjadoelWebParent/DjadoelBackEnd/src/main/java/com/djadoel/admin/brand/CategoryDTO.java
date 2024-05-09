package com.djadoel.admin.brand;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

public class CategoryDTO {

	// Initialized entity and column

	private Integer id;
	
	private String name;

	// Implement constructor

	public CategoryDTO() {

	}

	public CategoryDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	// Implement getter & setter

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
