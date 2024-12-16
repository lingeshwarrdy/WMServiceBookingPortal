package com.edubridge.ServiceBookingPortal.services.authentication;

import com.edubridge.ServiceBookingPortal.dto.SignupRequestDTO;
import com.edubridge.ServiceBookingPortal.dto.UserDto;

public interface AuthentService {

	UserDto signupClient(SignupRequestDTO signupRequestDTO);
	
	Boolean presentByEmail(String email);
	
	UserDto signupCompany(SignupRequestDTO signupRequestDTO);

	boolean authenticate(String username, String password);

}
