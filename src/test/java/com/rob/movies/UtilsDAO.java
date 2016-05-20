package com.rob.movies;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class UtilsDAO {

    @PersistenceContext
    private EntityManager em;
    
	public void deleteTable(){
		em.createQuery("DELETE FROM Movie").executeUpdate();
	}
      
}
