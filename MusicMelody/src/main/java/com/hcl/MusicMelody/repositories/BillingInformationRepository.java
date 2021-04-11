package com.hcl.MusicMelody.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.MusicMelody.models.BillingInformation;
import com.hcl.MusicMelody.models.Song;

@Repository
public interface BillingInformationRepository extends JpaRepository<BillingInformation, Integer>{
    
}
