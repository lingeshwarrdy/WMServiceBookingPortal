package com.edubridge.ServiceBookingPortal.controller;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edubridge.ServiceBookingPortal.dto.AdDTO;
import com.edubridge.ServiceBookingPortal.dto.AuthenticationRequest;
import com.edubridge.ServiceBookingPortal.dto.SignupRequestDTO;
import com.edubridge.ServiceBookingPortal.dto.UserDto;
import com.edubridge.ServiceBookingPortal.entity.User;
import com.edubridge.ServiceBookingPortal.repository.UserRepository;
import com.edubridge.ServiceBookingPortal.services.authentication.AuthentService;
import com.edubridge.ServiceBookingPortal.services.company.CompanyService;
import com.edubridge.ServiceBookingPortal.services.jwt.UserDetailsServiceImpl;
import com.edubridge.ServiceBookingPortal.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin("http://localhost:4200")
@RestController
public class AuthenticationController {

	@Autowired
	private AuthentService authentService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	public static final String TOKEN_PREFIX = "Bearer";

	public static final String HEADER_STRING = "Authorization";

	@PostMapping("/client/sign-up")
	public ResponseEntity<?> signupClient(@RequestBody SignupRequestDTO signupRequestDTO) {
		if (authentService.presentByEmail(signupRequestDTO.getEmail())) {
			return new ResponseEntity<>("Client already exists with this Email", HttpStatus.NOT_ACCEPTABLE);
		}

		UserDto createdUser = authentService.signupClient(signupRequestDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

	@PostMapping("/company/sign-up")
	public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDTO signupRequestDTO) {
		if (authentService.presentByEmail(signupRequestDTO.getEmail())) {
			return new ResponseEntity<>("Company already exists with this Email", HttpStatus.NOT_ACCEPTABLE);
		}

		UserDto createdUser = authentService.signupCompany(signupRequestDTO);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

	/*
	 * @PostMapping("/authenticate") public ResponseEntity<?>
	 * authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
	 * boolean isAuthenticated =
	 * authentService.authenticate(authenticationRequest.getUsername(),
	 * authenticationRequest.getPassword()); if (isAuthenticated) { return
	 * ResponseEntity.ok("Authentication Successful"); } else { return
	 * ResponseEntity.status(HttpStatus.UNAUTHORIZED).
	 * body("Incorrect username or password"); } }
	 */
	@PostMapping({"/authenticate"})
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws IOException, JSONException {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(),authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password", e);

		}
		
		final UserDetails userDetails =userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt =jwtUtil.generateToken(userDetails.getUsername());
		
		User user =userRepository.findFirstByEmail(authenticationRequest.getUsername());
		
		
		response.getWriter().write(new JSONObject()
				.put("userId", user.getId())
				.put("role",user.getRole())
				.toString()
				);
		
		response.addHeader("Access-Control-Expose-Headers","Authorization");
		response.addHeader("Access-Control-Allow-Headers", "Authorization" +
		                 "X-PINGOTHER,Origin, X-Requested-With,Content-Type,Accept,X-Custom-header");
		response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwt);
	}
}
