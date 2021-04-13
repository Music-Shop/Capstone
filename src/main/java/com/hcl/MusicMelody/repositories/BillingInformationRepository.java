package com.hcl.MusicMelody.repositories;

import com.hcl.MusicMelody.models.BillingInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingInformationRepository extends JpaRepository<BillingInformation, Integer>{
    
}
