package com.rob.movies;

import java.util.ArrayList;
import java.util.Hashtable;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// comment
@Path("/movies")
@Stateless
@LocalBean
public class MovieWS {

	private static String[] columnNames = { "title", "description", "director", "country", "yearMade", "budget",
			"rentalPrice", "onLoan", "picture" };

	private static Hashtable<String, Object> columnsAndValues = new Hashtable<String, Object>();

	@EJB
	private MovieDAO movieDao;

	/*
	 * This method receives requests for 'all movies', when nothing is appended
	 * to the URL
	 */

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		System.out.println("Get all movies");
		List<Movie> movie = movieDao.getAllMovies();
		return Response.status(200).entity(movie).build();
	}

	/*
	 * This method uses an ID on the end of the URL to get a movie
	 */

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response findMovieById(@PathParam("id") String id) {
		Movie movieMatch = getMovieById(id);
		return Response.status(200).entity(movieMatch).build();
	}

	/*
	 * This method takes in parameters from the URL and begins the search
	 * process
	 */

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/search")
	public Response findMovieByParams(@QueryParam("title") String title, @QueryParam("director") String director,
			@QueryParam("budget") Double budget, @QueryParam("rentalPrice") Double rentalPrice,
			@QueryParam("description") String description, @QueryParam("country") String country,
			@QueryParam("yearMade") Integer yearMade, @QueryParam("onLoan") String onLoan,
			@QueryParam("picture") String picture) {

		System.out.println("here we are at the transmission party");
		Object[] parametersDerivedFromUrl = { title, description, director, country, yearMade, budget, rentalPrice,
				onLoan, picture };

		StringBuilder erroneousOrMissingData = OtherValidator.ValidateData(parametersDerivedFromUrl);
		ArrayList<Integer> invalidColumns = OtherValidator.getInvalidColumns();
		
		for(Integer i : invalidColumns){
			columnsAndValues.put(columnNames[i], parametersDerivedFromUrl[i]);
		}
				
		if (erroneousOrMissingData.length() > 0) {
			return Response.status(200)
					.entity("<html><h1> The following columns are missing or filled out with erroneous data</h1><br><h2>"
							+ erroneousOrMissingData.toString() + "<h2></html>")
					.build();
		}
		
		List<Movie> movie = movieDao.getMovieBasedOnUnknownNumberOfCriteria(columnsAndValues);
		DataInputValidator.setColumnCounter();
		columnsAndValues.clear();

		
		if (movie.size() == 0) {
			System.out.println(movie.size());
			return Response.status(200).entity("<html>No data to return</html>").build();
		}

		return Response.status(200).entity(movie).build();

	}

	/*
	 * This method takes in parameters for a new movie
	 */

	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveMovie(Movie movie) {
		movieDao.save(movie);
		System.out.println(movie.getPicture());
		movieDao.savePicture(movie.getPicture(), movie.getId());
		return Response.status(201).entity(movie).build();
	}

	/*
	 * This method updates a movie with given parameters
	 */

	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Movie update(Movie movie) {
		System.out.println("Updating movie: " + movie.getTitle());
		movieDao.update(movie);
		return movie;
	}

	/*
	 * This method receives the data used to remove a movie from the database
	 */

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void remove(Movie movie) {
		System.out.println(movie.getId());
		movieDao.remove(movie.getId());
	}

	/*
	 * This method begins the find by ID process
	 */

	public Movie getMovieById(String id) {
		int movieIdAsInt = Integer.parseInt(id);
		Movie movieMatch = movieDao.getMovie(movieIdAsInt);
		return movieMatch;
	}
}
