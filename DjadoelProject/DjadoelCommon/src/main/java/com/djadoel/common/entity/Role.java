package com.djadoel.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas entitas yang mewakili peran (role) dalam aplikasi.
// Kode:
//  - Role(): Konstruktor tanpa parameter.
//  - Role(Integer id): Konstruktor dengan parameter ID.
//  - Role(String name): Konstruktor dengan parameter nama peran.
//  - Role(String name, String description): Konstruktor dengan parameter nama peran dan deskripsi peran.
//  - getId(): Metode getter untuk mendapatkan ID peran.
//  - setId(Integer id): Metode setter untuk mengatur ID peran.
//  - getName(): Metode getter untuk mendapatkan nama peran.
//  - setName(String name): Metode setter untuk mengatur nama peran.
//  - getDescription(): Metode getter untuk mendapatkan deskripsi peran.
//  - setDescription(String description): Metode setter untuk mengatur deskripsi peran.
//  - hashCode(): Metode untuk menghasilkan kode hash berdasarkan ID peran.
//  - equals(Object obj): Metode untuk memeriksa kesamaan dua objek peran berdasarkan ID.
//  - toString(): Metode untuk mengubah objek peran menjadi string (menggunakan nama peran).

@Entity
@Table(name = "roles")
public class Role {

// Initialized entity and column

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, nullable = false, unique = true)
	private String name;

	@Column(length = 150, nullable = false)
	private String description;

//	Implement Constructor

	public Role() {

	}

	public Role(Integer id) {
		this.id = id;
	}

	public Role(String name) {
		this.name = name;
	}

	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}

// Implement Getter & Setter	

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

// Implement hash id	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

// Implement hash id		

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

// Implement name to string		

	@Override
	public String toString() {
		return this.name;
	}

}
