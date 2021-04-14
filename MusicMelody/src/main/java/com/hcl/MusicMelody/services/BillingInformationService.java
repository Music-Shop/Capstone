package com.hcl.MusicMelody.services;

import com.hcl.MusicMelody.models.BillingInformation;
import com.hcl.MusicMelody.repositories.BillingInformationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingInformationService {
	
	@Autowired
	private BillingInformationRepository billRepo;

	public BillingInformation getBillingInformationById(Integer billId) {
		if(billRepo.findById(billId).isPresent()) return billRepo.findById(billId).get();
    	return null;
    }
	
	public BillingInformation saveOrUpdate(BillingInformation billingInformation) {
		return billRepo.save(billingInformation);
	}
}
