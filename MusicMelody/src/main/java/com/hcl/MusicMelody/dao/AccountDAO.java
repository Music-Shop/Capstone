package com.hcl.MusicMelody.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.MusicMelody.models.UserCred;
 
@Transactional
@Repository
public class AccountDAO {
 
    @Autowired
    private SessionFactory sessionFactory;
 
    public UserCred findAccount(String userName) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(UserCred.class, userName);
    }

}
