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

	public ConfirmedPurchase getSongById(Integer purchaseId) {
		if(purchaseRepo.findById(purchaseId).isPresent()) return purchaseRepo.findById(purchaseId).get();
    	return null;
    }
	
	public ConfirmedPurchase saveOrUpdate(ConfirmedPurchase confirmedPurchase) {
		return purchaseRepo.save(confirmedPurchase);
		
	}
}
