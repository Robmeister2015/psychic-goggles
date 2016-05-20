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

	/*
	 * Method to get all movies from the database
	 */

	public List<Movie> getAllMovies() {
		Query query = em.createQuery("SELECT m FROM Movie m");
		return query.getResultList();
	}

	/*
	 * Method that fetches a movie by an ID appended to the URL
	 */

	public Movie getMovie(int id) {
		return em.find(Movie.class, id);
	}

	/*
	 * Method that saves a movie to the database
	 */

	public void save(Movie movie) {
		em.persist(movie);
	}

	/*
	 * This method updates an entry in the database with the given details
	 */

	public void update(Movie movie) {
		Movie m = em.find(Movie.class, movie.getId());
		m.setBudget(movie.getBudget());
		m.setCountry(movie.getCountry());
		m.setDescription(movie.getDescription());
		m.setOnLoan(movie.getOnLoan());
		m.setPicture(movie.getPicture());
		m.setRentalPrice(movie.getRentalPrice());
		m.setTitle(movie.getTitle());
		m.setYearMade(movie.getYearMade());
		em.persist(m);
	}

	/*
	 * This method removes a movie from the database
	 */

	public void remove(int id) {
		Movie m = em.find(Movie.class, id);
		em.remove(m);
	}

	public Movie getMovie(String title) {
		return em.find(Movie.class, title);
	}

	public void savePicture(String picLocation, int id) {
		File source = new File(picLocation);
		File dest = new File(id + ".jpg");
		System.out.println("File: " + dest.exists());
		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Movie> getMovieBasedOnUnknownNumberOfCriteria(Hashtable columnsAndValues) {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = qb.createQuery();
		List<Predicate> predicates = new ArrayList<Predicate>();
		Enumeration e = columnsAndValues.keys();
		Root<Movie> movie = cq.from(Movie.class);
		System.out.println("Size of hashtable: " + columnsAndValues.size());
		while (e.hasMoreElements()) {
			String s = (String) e.nextElement();
			predicates.add(qb.equal(movie.get(s), columnsAndValues.get(s)));
			System.out.println(s + " " + columnsAndValues.get(s));
		}
		cq.multiselect(movie).where(predicates.toArray(new Predicate[] {}));

		em.createQuery(cq).getResultList();
		System.out.println("Here we are.");

		List<Object> obj = em.createQuery(cq).getResultList();
		List<Movie> movies = new ArrayList<Movie>();
		for (Object o : obj) {
			Movie m = (Movie) o;
			movies.add(m);
		}
		return movies;

	}
}
