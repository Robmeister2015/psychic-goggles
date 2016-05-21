//package com.rob.movies;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//import java.util.List;
//import javax.ejb.EJB;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.Archive;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//		@RunWith(Arquillian.class)
//		public class IntegrationTest {
//			
//			@Deployment
//			public static Archive<?> createTestArchive() {
//				JavaArchive archive = ShrinkWrap
//						.create(JavaArchive.class, "Test.jar")
//						.addClasses(MovieDAO.class, MovieWS.class,
//								Movie.class, UtilsDAO.class)
//						.addAsManifestResource("META-INF/persistence.xml",
//								"persistence.xml")
//						.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//				archive.addClass(UtilsDAO.class);
//				archive.addClass(DataInputValidator.class);
//				archive.addClass(Movie.class);
//				archive.addClass(Movie.class);
//				archive.addClass(Movie.class);
//				archive.addClass(DataInputValidator.class);
//				archive.addClass(UtilsDAO.class);
//				archive.addClass(UtilsDAO.class);
//				archive.addClass(OtherValidator.class);
//				return archive;
//
//			}
//			
//			List<Movie> allMovies;
//			 
//			@PersistenceContext
//		    private EntityManager em;
//			
//			@EJB
//			private MovieWS MovieWS;
//			
//			@EJB
//			private MovieDAO movieDAO;
//			
//			@EJB
//			private UtilsDAO utilsDAO;
//			 
//			@Before
//			public void setUp() {
//				allMovies = new ArrayList<Movie>();
//				allMovies = movieDAO.getAllMovies();
//				
//				utilsDAO.deleteTable();
//				Movie movie=new Movie();
//				movie.setTitle("Test Movie2");
//				movie.setDescription("Test Description2");
//				movie.setDirector("Test Director2");
//				movie.setCountry("Test Country2");
//				movie.setYearMade(2000);
//				movie.setBudget(200);
//				movie.setRentalPrice(12.50);
//				movie.setOnLoan("y2");
//				movie.setPicture("C:/testpictu2re.jpg");
//				movieDAO.save(movie);
//			}
//			
//			@Test
//			public void testGetAllMovies() {
//				List<Movie> movies = movieDAO.getAllMovies();
//				assertEquals("Data fetch = data persisted", movies.size(), 1);
//			}
//			
//			@After
//			public void tearDown(){
//				
//				for(Movie m : allMovies){
//					movieDAO.save(m);
//				}
//			}
//			
//			
//			
//}
