package com.rob.movies;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
// comment
@Path("/movies")
@Stateless
@LocalBean
public class MovieWS {

	@EJB
	private MovieDAO movieDao;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		System.out.println("Get all movies");
		List<Movie> movie = movieDao.getAllMovies();
		return Response.status(200).entity(movie).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response findMovieById(@PathParam("id") String id) {
		if (id.contains("=")) {
			Movie movieMatch = getMovieByTitle(id);
			return Response.status(200).entity(movieMatch).build();
		} else {
			Movie movieMatch = getMovieById(id);
			return Response.status(200).entity(movieMatch).build();
		}
	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveMovie(Movie movie) {
		movieDao.save(movie);
		movieDao.savePicture(movie.getPicture(), movie.getId());
		return Response.status(201).entity(movie).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Movie update(Movie movie) {
		System.out.println("Updating movie: " + movie.getTitle());
		movieDao.update(movie);
		return movie;
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(Movie movie) {
		System.out.println(movie.getId());
		movieDao.remove(movie.getId());
	}

	public Movie getMovieByTitle(String id) {

		int something = id.indexOf("=");
		System.out.println(something);
		System.out.println(id);
		id = id.substring(something + 1);
		List<Movie> allMovies = movieDao.getAllMovies();
		Movie movieMatch = new Movie();

		for (Movie m : allMovies) {
			if (m.getTitle().equals(id)) {
				int movieId = m.getId();
				movieMatch = movieDao.getMovie(movieId);
			} else {
				System.out.println("No match!");
			}

		}
		return movieMatch;
	}

	public Movie getMovieById(String id) {
		int movieIdAsInt = Integer.parseInt(id);
		Movie movieMatch = movieDao.getMovie(movieIdAsInt);
		return movieMatch;
	}
}
