package com.rob.movies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.io.FileUtils;

@Stateless
@LocalBean
public class MovieDAO {

	@PersistenceContext
	private EntityManager em;
	
	public List<Movie> getAllMovies() {
		Query query=em.createQuery("SELECT m FROM Movie m");
		return query.getResultList();
	}
	
	public Movie getMovie(int id) {
		return em.find(Movie.class, id);
	}
	
	public void save(Movie movie){
		em.persist(movie);
	}
	
	public void update(Movie movie){
		Movie m = em.find(Movie.class, movie.getId());
		m.setBudget(movie.getBudget());
		m.setCountry(movie.getCountry());
		m.setDescription(movie.getDescription());
		m.setOnLoan(movie.getOnLoan());
		m.setPicture(movie.getPicture());
		m.setRentalPrice(movie.getRentalPrice());
		m.setTitle(movie.getTitle());
		m.setYear(movie.getYear());
		em.persist(m);
	}
	
	public void remove(int id){
		Movie m = em.find(Movie.class, id);
		  em.remove(m);
	}
	
	public Movie getMovie(String title) {
		return em.find(Movie.class, title);
	}
	
	public void savePicture(String picLocation, int id){
		File source = new File(picLocation);
		File dest = new File(id + ".jpg");
		try {
		    FileUtils.copyFile(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public void getMovieBasedOnUnknownNumberOfCriteria(Hashtable columnsAndValues){
		CriteriaBuilder qb = em.getCriteriaBuilder();
	    CriteriaQuery<Object> cq = qb.createQuery();
	    List<Predicate> predicates = new ArrayList<Predicate>();
	    Enumeration e = columnsAndValues.keys();
		Root<Movie> movie = cq.from(Movie.class);
		
		while(e.hasMoreElements()){
	        predicates.add(
	                qb.equal(movie.get("someAttribute"), param1));
		}
		 cq.select(movie)
         .where(predicates.toArray(new Predicate[]{}));
 //execute query and do something with result
 em.createQuery(cq).getResultList();
	    }
	    
	   
	}
}
