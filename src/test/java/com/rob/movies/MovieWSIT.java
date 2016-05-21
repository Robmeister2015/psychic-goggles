//package com.rob.movies;
//
//import static org.junit.Assert.*;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.experimental.categories.Category;
//import org.junit.runner.RunWith;
//
//import junitparams.JUnitParamsRunner;
//import junitparams.Parameters;
//
//@RunWith(JUnitParamsRunner.class)
//public class MovieWSIT {
//
//	final String GET_ALL_ENTITIES = "http://localhost:8080/MoviesMavenProject/movies";
//	final String GET_SINGLE_ENTITY = "";
//	final String POST_ENTITY = "";
//	final String PUT_ENTITY = "";
//	
//	final String GET_REQUEST = "GET";
//	final String POST_REQUEST = "POST";
//	final String PUT_REQUEST = "PUT";
//	final String DELETE_REQUEST = "DELETE";
//	
//	final String USER_AGENT = "User Agent";
//	
//	URL obj;
//	HttpURLConnection con;
//	JSONArray jsonArr;
//	JSONObject json;
//	
//	@Before
//	public void setUp(){
//		
//	}
//
//	@Parameters
//	public Object[] movieParams(){
//		return new Object[]{
//				new Object[]{0, 2, "A Movie", "A movie about things", "Senor Spielbergo", "Mexico", 2009, 120, 12.5, "y", "C:\\UsersA00226084\\Pictures\\AAA.PNG"},
//				new Object[]{1, 3, "A Movie2", "A movie about things3", "Senor Spielbergo", "Mexico", 2009, 120, 12.5, "y", "C:\\UsersA00226084\\Pictures\\AAA.PNG"},
//				new Object[]{2, 4, "Scary Movie2", "A scary movie2", "Steven Spielberg", "America", 1970, 999.999, 12.5, "y", "C:/Windows/test/a.jpg"},
//				new Object[]{3, 5, "Scary Movie 2", "Another scarier movie", "Judd Apatow", "Ireland", 2015, 999.999, 4.5, "n", "C:/test/b.jpg"},
//				new Object[]{4, 6, "Scariest Movie", "The Scariest Movie", "Steven Spielbergo", "America", 1970, 999.999, 12.5, "y", "C:/Windows/test/b.jpg"},
//				new Object[]{5, 7, "Scariest Movie", "The Scariest Movie", "Steven Spielbergo", "America", 1970, 999.999, 12.5, "y", "C:/Windows/test/b.jpg"},
//				new Object[]{6, 8, "Scariest Movie", "The Scariest Movie", "Steven Spielbergo", "America", 1970, 999.999, 12.5, "y", "C:/Windows/test/b.jpg"},
//				new Object[]{7, 9, "Scariest Movie", "The Scariest Movie", "Steven Spielbergo", "America", 1970, 999.999, 12.5, "y", "C:/Windows/test/b.jpg"},
//				new Object[]{8, 10, "Scariest Movie", "The Scariest Movie", "Steven Spielbergo", "America", 1970, 999.999, 12.5, "y", "C:/Windows/test/b.jpg"},
//				new Object[]{9, 11, "Scariest Movie", "The Scariest Movie", "Steven Spielbergo", "America", 1970, 999.999, 12.5, "y", "C:/Windows/test/b.jpg"}
//		};
//	}
//	
//	@Test
//	@Parameters(method="movieParams")
//	public void testGetRequestToGetAllEntities(int getId, int id, String title, String description, String director,
//			String country, int year, double budget, double rentalPrice, String onLoan, String picture) throws IOException {
//		
//		obj = new URL(GET_ALL_ENTITIES);
//		con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod(GET_REQUEST);
//		
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();
//		
//		assertEquals(200, con.getResponseCode());
//		json = new JSONObject("{\"movies\":" + response.toString() + "}");
//		jsonArr = json.getJSONArray("movies");
//		
//			json = jsonArr.getJSONObject(getId);
//		assertEquals(id, json.getInt("id"));
//		System.out.println(json.getInt("id"));
//		assertEquals(title, json.getString("title"));
//		assertEquals(description, json.getString("description"));
//		assertEquals(budget, json.getDouble("budget"), 0.001);
//		assertEquals(onLoan, json.getString("onLoan"));
//		assertEquals(director, json.getString("director"));
//		assertEquals(country, json.get("country"));
//		assertEquals(year, json.getInt("year"));
//		assertEquals(rentalPrice, json.getDouble("rentalPrice"), 0.001);
//		assertEquals(picture, json.get("picture"));
//	}
//}
