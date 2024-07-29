package com.playerdatatracking.clients;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;


public class ApiFootballClient {
	 public void getThings() throws IOException {
	        AsyncHttpClient client = new DefaultAsyncHttpClient();
	        
	        // Realiza la solicitud HTTP GET
	        CompletableFuture<Response> futureResponse = client.prepare("GET", "https://v3.football.api-sports.io/leagues")
	    		.setHeader("x-rapidapi-key", "7980b33b1cf376626065507793ba5d71")
	    		.setHeader("x-rapidapi-host", "v3.football.api-sports.io")
	            .execute()
	            .toCompletableFuture();

	        futureResponse.thenAccept(response -> {

	            String responseBody = response.getResponseBody();
	            String filePath = "src/main/resources/json/apiFotball/leagues/response.json";
	            File jsonDir = new File("src/main/resources/json/apiFotball/leagues");
	            if (!jsonDir.exists()) {
	                jsonDir.mkdirs(); // Crea la carpeta si no existe
	            }
	            try (FileWriter fileWriter = new FileWriter(filePath)) {
	                fileWriter.write(responseBody);
	                System.out.println("Respuesta guardada en el archivo: " + filePath);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }).join();

	        // Cierra el cliente
	        client.close();
	    }
	}