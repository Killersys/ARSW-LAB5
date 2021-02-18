/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filtro.Filtro;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class BlueprintsServices {
   
    @Autowired
    BlueprintsPersistence bpp=null;
    
    // En la siguiente parte el usuario debe aclarar que tipo de filtrado desea, si quiere filtrado por Muestreo cambiar la etiquita @Qualifier por "Muestreo", pero si desea un filtrado por Redundancia, cambiar la etiqueta @Qualifier por "Redundancia"."
    // Tipos de Filtros "Muestreo", "Redundancia"
    
    
    @Autowired
    @Qualifier("Muestreo")
    Filtro filtro = null;
    
    
    public void addNewBlueprint(Blueprint bp){
        try {
			bpp.saveBlueprint(bp);
		} catch (BlueprintPersistenceException e) {
			e.printStackTrace();
		}
    }
    
    public Set<Blueprint> getAllBlueprints(){
        return bpp.getAllBlueprints();
    }
    
    /**
     * 
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String name) throws BlueprintNotFoundException{
    	
    	// Blueprint con el nombre del autor y su plano.
    	return filtro.filtrar(bpp.getBlueprint(author, name));
    }
    
    /**
     * 
     * @param author blueprint's author
     * @return all the blueprints of the given author
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException{
    	Set<Blueprint> filtrar = bpp.getAllBlueprintsByAuthor(author);
    	for(Blueprint bp : filtrar){
    		bp = filtro.filtrar(bp);
    	}
    	return filtrar;
    }
    
}
