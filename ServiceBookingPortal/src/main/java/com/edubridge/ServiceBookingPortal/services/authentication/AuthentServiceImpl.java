 
package com.edubridge.ServiceBookingPortal.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edubridge.ServiceBookingPortal.dto.SignupRequestDTO;
import com.edubridge.ServiceBookingPortal.dto.UserDto;
import com.edubridge.ServiceBookingPortal.entity.User;
import com.edubridge.ServiceBookingPortal.enums.UserRole;
import com.edubridge.ServiceBookingPortal.repository.UserRepository;

@Service
public class AuthentServiceImpl implements AuthentService {

	@Autowired
	private UserRepository userRepository;

	public UserDto signupClient(SignupRequestDTO signupRequestDTO) {

		User user = new User();

		user.setName(signupRequestDTO.getName());
		user.setLastname(signupRequestDTO.getLastname());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		//user.setPassword(signupRequestDTO.getPassword());
		
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));

		user.setRole(UserRole.CLIENT);

		return userRepository.save(user).getDto();
	}

	public Boolean presentByEmail(String email) {
		return userRepository.findFirstByEmail(email) != null;

	}
	
	public UserDto signupCompany(SignupRequestDTO signupRequestDTO) {

		User user = new User();

		user.setName(signupRequestDTO.getName());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		user.setPassword(signupRequestDTO.getPassword());
		
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));


		user.setRole(UserRole.COMPANY);

		return userRepository.save(user).getDto();
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
