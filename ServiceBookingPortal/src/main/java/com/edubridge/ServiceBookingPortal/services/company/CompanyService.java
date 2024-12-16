package com.edubridge.ServiceBookingPortal.services.company;

import java.io.IOException;
import java.util.List;

import com.edubridge.ServiceBookingPortal.dto.AdDTO;

public interface CompanyService {

	boolean postAd(Long userId, AdDTO adDTO) throws IOException;
    
	List<AdDTO>getAllAds(Long userId);
	
	AdDTO getAdById(Long adId);
	
	boolean updateAd(Long adId,AdDTO adDTO) throws IOException;
}
