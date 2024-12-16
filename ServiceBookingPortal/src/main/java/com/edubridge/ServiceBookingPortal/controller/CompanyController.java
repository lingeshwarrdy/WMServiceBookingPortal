package com.edubridge.ServiceBookingPortal.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edubridge.ServiceBookingPortal.dto.AdDTO;
import com.edubridge.ServiceBookingPortal.services.company.CompanyService;
@CrossOrigin( "http://localhost:4200")
@RestController
@RequestMapping("/api/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping("/ad/{userId}")
	public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDTO adDTO) throws IOException {
		boolean sucess = companyService.postAd(userId, adDTO);
		if (sucess) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	@GetMapping("/ads/{userId}")
	public ResponseEntity<?> getAllAdsByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(companyService.getAllAds(userId));
	}
	
	@GetMapping("ad/{adId}")
	public ResponseEntity<?>getAdById(@PathVariable Long adId){
		AdDTO adDTO =companyService.getAdById(adId);
		if(adDTO !=null) {
			return ResponseEntity.ok(adDTO);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("ad/{adId}")
	public ResponseEntity<?>updateAd(@PathVariable Long adId,@ModelAttribute AdDTO adDTO) throws IOException{
		
		boolean success =companyService.updateAd(adId,adDTO);
	
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
			
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	
	
	 

}