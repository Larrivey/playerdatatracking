package com.playerdatatracking.operations;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.playerdatatracking.clients.PlayerDataClient;
import com.playerdatatracking.common.Constants;
import com.playerdatatracking.common.Methods;
import com.playerdatatracking.entities.MANUAL_TRACKED_PLAYER;
import com.playerdatatracking.exceptions.PlayerDataDBException;
import com.playerdatatracking.responses.GenericResponse;


@Component
public class XslImport {
	
	
private GenericResponse response = new GenericResponse();


	private PlayerDataClient pdClient;
	
	// Formatear la fecha para imprimirla
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
    private ResourceLoader resourceLoader;

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
	
	
	
	public PlayerDataClient getPdClient() {
		return pdClient;
	}

	public void setPdClient(PlayerDataClient pdClient) {
		this.pdClient = pdClient;
	}
	
	
	public GenericResponse ejecutar(String filePath) throws PlayerDataDBException, ParseException {
            Resource resource = resourceLoader.getResource("classpath:" + filePath);
            try (FileInputStream fis = new FileInputStream(resource.getFile().getPath()); 
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            // Saltar la primera fila si tiene encabezados
            if (iterator.hasNext()) {
                iterator.next();
            }

            while (iterator.hasNext()) {
                Row row = iterator.next();
                String name = row.getCell(0).getStringCellValue();
                MANUAL_TRACKED_PLAYER isAlreadyInDB = pdClient.getPlayerbyName(name);
                if (isAlreadyInDB==null) {
                	MANUAL_TRACKED_PLAYER player = new MANUAL_TRACKED_PLAYER();
                	player.setNombre(name);
                	player.setNota((float) row.getCell(1).getNumericCellValue());
                	player.setClub(row.getCell(2).getStringCellValue());
                	player.setPosicion(row.getCell(3).getStringCellValue());
                	player.setMostLikeDestination(row.getCell(5).getStringCellValue());
                	player.setLikeable(row.getCell(6).getStringCellValue());
                	List<String> qualities = Arrays.asList(row.getCell(4).getStringCellValue().split("\\s*,\\s*"));
                	player.setQualities(qualities);
                	
                	
                	String fechaInsercion = row.getCell(7).getStringCellValue();
                	Date date = null;
                	if (fechaInsercion!=null || !fechaInsercion.equals("")) {
                		date = formatter.parse(fechaInsercion);
                	}
                	else
                		date = new Date();
                	player.setDate(date);
                	
                	
                	pdClient.savePlayer(player);
                }
            }
            response.setCODE(Constants.CODE_OK);
            response.setDescription("OK");
            return response;

        } catch (IOException e) {
            response.setCODE(Methods.exceptionCodeManagement(e));
            response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
            return response;
        } catch (ParseException e) {
            response.setCODE(Methods.exceptionCodeManagement(e));
            response.setDescription(e.getClass().getSimpleName() + "[]: " + e.getMessage());
            return response;
        }
    }
}