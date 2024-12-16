package com.edubridge.ServiceBookingPortal.entity;

import com.edubridge.ServiceBookingPortal.dto.UserDto;
import com.edubridge.ServiceBookingPortal.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String password;

	private String name;

	private String lastname;

	private String phone;

	private UserRole role;

	public UserDto getDto() {
		UserDto userDto = new UserDto();
		userDto.setId(id);
		userDto.setName(name);
		userDto.setEmail(email);
		userDto.setRole(role);

		return userDto;

	}

}
