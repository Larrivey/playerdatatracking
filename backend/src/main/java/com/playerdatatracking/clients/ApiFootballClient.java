package com.playerdatatracking.clients;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.playerdatatracking.entities.keys.Keys;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;


public class ApiFootballClient {
	
	 public void getLeaguesInfo(String apikey) throws IOException {
		 
		 HashMap<String, String> headers = new HashMap<>();
		 headers.put("x-rapidapi-key", apikey);
		 headers.put("x-rapidapi-host", "v3.football.api-sports.io");
		 
		 apiFootballClientCall("GET", "https://v3.football.api-sports.io/leagues", apikey, headers, "src/main/resources/json/apiFotball/leagues/leagues.json", "src/main/resources/json/apiFotball/leagues");
		 
	 }
	 
	 
	 
	 public void getCountriesInfo(String apikey) throws IOException {
		 	 
		 HashMap<String, String> headers = new HashMap<>();
		 headers.put("x-rapidapi-key", apikey);
		 headers.put("x-rapidapi-host", "v3.football.api-sports.io");
		 
		 apiFootballClientCall("GET", "https://v3.football.api-sports.io/countries", apikey, headers, "src/main/resources/json/apiFotball/countries/countries.json", "src/main/resources/json/apiFotball/countries");
	
	 }
	 
	 
	 
	 private void apiFootballClientCall(String method, String url, String apikey, HashMap<String, String> headers, String filePath, String sJsonDir) throws IOException {
		 AsyncHttpClient client = new DefaultAsyncHttpClient();
	        var requestBuilder = client.prepare(method, url);
	        headers.forEach((key, value) -> requestBuilder.setHeader(key, value));
	        CompletableFuture<Response> futureResponse = requestBuilder.execute().toCompletableFuture();
	        futureResponse.thenAccept(response -> {

	            String responseBody = response.getResponseBody();
	            File jsonDir = new File(sJsonDir);
	            if (!jsonDir.exists()) {
	                jsonDir.mkdirs(); // Crea la carpeta si no existe
	            }

	            ObjectMapper mapper = new ObjectMapper();
	            mapper.enable(SerializationFeature.INDENT_OUTPUT);
	            try {
	                Object json = mapper.readValue(responseBody, Object.class);
	                ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
	                try (FileWriter fileWriter = new FileWriter(filePath)) {
	                    fileWriter.write(writer.writeValueAsString(json));
	                    System.out.println("Respuesta guardada en el archivo: " + filePath);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }).join();

	        client.close();
		 
	 }
}