package com.djadoel.common.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

// Created by: Ridho Suhaebi Arrowi
// IDE: Spring Tool Suite 4
// Information: ridhosuhaebi01@gmail.com
// Fungsi: Kelas entitas yang mewakili pengguna (user) dalam aplikasi.
// Kode:
//  - User(): Konstruktor tanpa parameter.
//  - User(String email, String password, String firstName, String lastName): Konstruktor dengan parameter email, password, nama depan, dan nama belakang pengguna.
//  - getId(): Metode getter untuk mendapatkan ID pengguna.
//  - setId(Integer id): Metode setter untuk mengatur ID pengguna.
//  - getEmail(): Metode getter untuk mendapatkan alamat email pengguna.
//  - setEmail(String email): Metode setter untuk mengatur alamat email pengguna.
//  - getPassword(): Metode getter untuk mendapatkan kata sandi pengguna.
//  - setPassword(String password): Metode setter untuk mengatur kata sandi pengguna.
//  - getFirstName(): Metode getter untuk mendapatkan nama depan pengguna.
//  - setFirstName(String firstName): Metode setter untuk mengatur nama depan pengguna.
//  - getLastName(): Metode getter untuk mendapatkan nama belakang pengguna.
//  - setLastName(String lastName): Metode setter untuk mengatur nama belakang pengguna.
//  - getPhotos(): Metode getter untuk mendapatkan nama foto pengguna.
//  -  setPhotos(String photos): Metode setter untuk mengatur nama foto pengguna.
//  - isEnabled(): Metode getter untuk memeriksa apakah pengguna diaktifkan.
//  - setEnabled(boolean enabled): Metode setter untuk mengatur status aktivasi pengguna.
//  - getRoles(): Metode getter untuk mendapatkan peran (role) yang dimiliki pengguna.
//  - setRoles(Set<Role> roles): Metode setter untuk mengatur peran (role) pengguna.
//  - addRole(Role role): Metode untuk menambahkan peran (role) ke daftar peran pengguna.
//  - toString(): Metode untuk mengubah objek pengguna menjadi string (menampilkan ID, alamat email, nama depan, nama belakang, dan daftar peran).
//  - getPhotosImagePath(): Metode untuk mendapatkan path (lokasi) gambar profil pengguna.

@Entity
@Table(name = "users")
public class User {

// Initialized entity and column

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 120, nullable = false, unique = true)
	private String email;

	@Column(length = 60, nullable = false)
	private String password;

	@Column(name = "first_name", length = 60, nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 60, nullable = false)
	private String lastName;

	@Column(length = 60)
	private String photos;

	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

	private Set<Role> roles = new HashSet<>();

// Implement Constructor	

	public User() {

	}

	public User(String email, String password, String firstName, String lastName) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

// Implement Getter & Setter

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role role) {
		this.roles.add(role);
	}

// Implement id, email, firsname, lastname, and roles to string

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", roles=" + roles + "]";
	}

	@Transient
	public String getPhotosImagePath() {
		if (id == null || photos == null)
			return "/images/default-user.png";

		return "/user-photos/" + this.id + "/" + this.photos;
	}
}
