package com.playerdatatracking.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.common.Methods;
import com.playerdatatracking.entities.players.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.db.PlayerDataDBException;
import com.playerdatatracking.exceptions.operations.PlayerInputException;
import com.playerdatatracking.operations.Crypto.AESCrypto;
import com.playerdatatracking.operations.apiFootball.GetAllCountries;
import com.playerdatatracking.operations.apiFootball.GetAllLeagues;
import com.playerdatatracking.operations.apikeys.KeysManagement;
import com.playerdatatracking.operations.manualdata.AddPlayer;
import com.playerdatatracking.operations.manualdata.DeletePlayer;
import com.playerdatatracking.operations.manualdata.GetAllPlayers;
import com.playerdatatracking.operations.manualdata.GetPlayer;
import com.playerdatatracking.operations.manualdata.XslImport;
import com.playerdatatracking.repositories.players.MANUAL_TRACKED_PLAYERRepository;
import com.playerdatatracking.requests.GenericRequest;
import com.playerdatatracking.responses.GenericResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MainController {
	
//	---------ENVIRONMENT
	@Autowired
	private Environment env;
	@Autowired
	private ResourceLoader resourceLoader;
//	---------CLIENTS---------
	@Autowired
	private PlayerDataClient pdClient;
	
	
//	---------RFEPOSITORIES---------
	@Autowired
	private MANUAL_TRACKED_PLAYERRepository repository;
//  ---------OPERATIONS---------
	private AddPlayer operationAddPlayer = new AddPlayer();
	private GetAllPlayers operationGetAllPlayers = new GetAllPlayers();
	private XslImport operationXslImport = new XslImport();
	private GetAllLeagues operationGetAllLeagues = new GetAllLeagues();
	private AESCrypto operationCrypto = new AESCrypto();
	private KeysManagement operationKeys = new KeysManagement();
	private DeletePlayer operationDeletePlayer = new DeletePlayer();
	private GetPlayer oeprationGetPlayer = new GetPlayer();
	private GetAllCountries operationGetCountries = new GetAllCountries();
	
	
// 	---------RESPONSES---------	
	GenericResponse response = new GenericResponse();
	
	
	@GetMapping("/secretkey")
	public GenericResponse getSecretKey() throws Exception {
    	response = new GenericResponse();
		try {
			response = operationCrypto.getNewSecretKey();
		} catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
		}
		return response;
	}
	@PostMapping("/apiKey")
	public GenericResponse storeApiKey(@RequestBody GenericRequest request){
    	response = new GenericResponse();
		operationKeys.setPdClient(pdClient);
		operationKeys.setEnv(this.env);
		try {
			response = operationKeys.storeKey(request.getNewKey(), request.getMail(), request.getPlan(), request.getIdService());
		} catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
		
		return response;
	}
	@GetMapping("/apiKey")
	public GenericResponse getApiKey(@RequestBody GenericRequest request) {
    	response = new GenericResponse();
		operationKeys.setPdClient(pdClient);
		operationKeys.setEnv(this.env);
		try {
			response = operationKeys.getKeyByKey(request.getApiKey());
		} catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
		
		return response;
	
	}
	
	
	@GetMapping("/myapiKeys")
	public GenericResponse getApiKeysbyMail(@RequestBody GenericRequest request) {
    	response = new GenericResponse();
		operationKeys.setPdClient(pdClient);
		operationKeys.setEnv(this.env);
		try {
			response = operationKeys.getKeyByMail(request.getMail());
		} catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
		return response;
	}
    @GetMapping("/players")
    public GenericResponse listPlayers() {
    	response = new GenericResponse();
    	operationGetAllPlayers.setPdClient(pdClient);
    	try {
    		response = operationGetAllPlayers.ejecutar();
    	} catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
        return response;
    }
    
    @PostMapping("/xslBackendImport")
    public GenericResponse xslImport() {
    	response = new GenericResponse();
    	String path = env.getProperty("xsl.path");
    	operationXslImport.setPdClient(pdClient);
    	operationXslImport.setResourceLoader(resourceLoader);
    	try {
    		response = operationXslImport.ejecutar(path);
    	} catch (Exception e) {
    		response.setCODE(Methods.exceptionCodeManagement(e));
    		response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
    	}
    	return response;
    }
    
    @PostMapping("/leagues")
    public GenericResponse getAllLeagues(@RequestBody GenericRequest request) {
    	response = new GenericResponse();
    	operationGetAllLeagues.setEnv(env);
    	operationGetAllLeagues.setPdClient(pdClient);
    	try {
    		response = operationGetAllLeagues.ejecutar(request);
    	} catch (Exception e) {
    		response.setCODE(Methods.exceptionCodeManagement(e));
    		response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
    	}
    	return response;
    }
    @PostMapping("/countries")
    public GenericResponse getAllCountries (@RequestBody GenericRequest request) {
    	response = new GenericResponse();
    	operationGetCountries.setEnv(env);
    	operationGetCountries.setPdClient(pdClient);
    	try {
    		response = operationGetCountries.ejecutar(request);
    	} catch (Exception e) {
    		response.setCODE(Methods.exceptionCodeManagement(e));
    		response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
    	}
    	return response;
    	
    }
    
    @PostMapping("/updateCountries")
    public GenericResponse updateCountries() {
    	response = new GenericResponse();
    	operationGetCountries.setEnv(env);
    	operationGetCountries.setPdClient(pdClient);
    	try {
    		operationGetCountries.updateLeagues();
    		response.setCODE(Constants.CODE_OK);
    		response.setDescription("OK");
    	} catch (Exception e) {
    		response.setCODE(Methods.exceptionCodeManagement(e));
    		response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
    	}
    	return response;
    }
    
    
    @PostMapping("/updateLeagues")
    public GenericResponse updateLeagues() {
    	response = new GenericResponse();
    	operationGetAllLeagues.setEnv(env);
    	operationGetAllLeagues.setPdClient(pdClient);
    	try {
    		operationGetAllLeagues.updateLeagues();
    		response.setCODE(Constants.CODE_OK);
    		response.setDescription("OK");
    	} catch (Exception e) {
    		response.setCODE(Methods.exceptionCodeManagement(e));
    		response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
    	}
    	return response;
    }
    
    @PostMapping("/player")
    public GenericResponse addPlayer(@RequestBody GenericRequest request) throws PlayerDataDBException, PlayerInputException, ParseException {
    	response = new GenericResponse();
    	operationAddPlayer.setPdClient(pdClient);
        try {
        	MANUAL_TRACKED_PLAYER player = Methods.bindRequestAsPlayer(request);
        	response = operationAddPlayer.ejecutar(player);
        } catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
        return response;
    }
    
    @DeleteMapping("/player")
    public GenericResponse deletePlayer(@RequestBody GenericRequest request) {
    	response = new GenericResponse();
    	operationDeletePlayer.setPdClient(pdClient);
    	try {
    		response = operationDeletePlayer.ejecutar(request.getNombre());
    	} catch (Exception e) {
        	response.setCODE(Methods.exceptionCodeManagement(e));
        	response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
        return response;
    }
    @GetMapping("/player/{id}")
    public GenericResponse<MANUAL_TRACKED_PLAYER> getPlayer(@PathVariable("id") Long id) {
    	response = new GenericResponse();
        oeprationGetPlayer.setPdClient(pdClient);
        try {
            response = oeprationGetPlayer.ejecutar(id);
        } catch (Exception e) {
            response.setCODE(Methods.exceptionCodeManagement(e));
            response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
        }
        return response;
    }

    
    
    
    
}