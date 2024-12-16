package com.edubridge.ServiceBookingPortal.dto;

import com.edubridge.ServiceBookingPortal.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
	private Long id;

	private String email;

	private String password;

	private String name;

	private String lastname;

	private String phone;

	private UserRole role;

}
