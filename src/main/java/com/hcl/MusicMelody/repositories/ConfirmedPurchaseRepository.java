package com.hcl.MusicMelody.repositories;

import com.hcl.MusicMelody.models.ConfirmedPurchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmedPurchaseRepository extends JpaRepository<ConfirmedPurchase, Integer>{

}
