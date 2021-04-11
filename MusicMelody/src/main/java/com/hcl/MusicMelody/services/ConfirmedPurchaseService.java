package com.hcl.MusicMelody.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.MusicMelody.models.ConfirmedPurchase;
import com.hcl.MusicMelody.repositories.ConfirmedPurchaseRepository;

@Service
public class ConfirmedPurchaseService {
	
    @Autowired
    private ConfirmedPurchaseRepository purchaseRepo;
	public Optional<ConfirmedPurchase> getSongById(Integer purchaseId) {
    	return purchaseRepo.findById(purchaseId);
    }
	
	public ConfirmedPurchase saveOrUpdate(ConfirmedPurchase confirmedPurchase) {
		return purchaseRepo.save(confirmedPurchase);
		
	}
}
