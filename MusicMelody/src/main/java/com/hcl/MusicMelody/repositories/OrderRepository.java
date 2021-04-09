package com.hcl.MusicMelody.repositories;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class OrderRepository {
    @Autowired  
    private SessionFactory sessionFactory;

 
}
