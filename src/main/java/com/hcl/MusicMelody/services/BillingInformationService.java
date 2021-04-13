package com.hcl.MusicMelody.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.MusicMelody.models.BillingInformation;
import com.hcl.MusicMelody.repositories.BillingInformationRepository;

@Service
public class BillingInformationService {
	
	@Autowired
	private BillingInformationRepository billRepo;
	public Optional<BillingInformation> getBillingInformationById(Integer billId) {
    	return billRepo.findById(billId);
    }
	
	public BillingInformation saveOrUpdate(BillingInformation billingInformation) {
		return billRepo.save(billingInformation);
	}
}
