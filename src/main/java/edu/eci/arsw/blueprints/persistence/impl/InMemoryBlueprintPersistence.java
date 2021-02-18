/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import static java.lang.reflect.Array.set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service("InMemoryBlueprintPersistence")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
       
        Point[] pts=new Point[]{new Point(20, 75),new Point(90, 95)};
        Point[] pts1=new Point[]{new Point(35, 85),new Point(10, 220)};
        
        Blueprint bp=new Blueprint("Jairo", "lienzo",pts);
        Blueprint bp1=new Blueprint("Eduardo", "Lienzo4",pts1);
        Blueprint bp2=new Blueprint("Eduardo", "Lienzo6",pts);
        
        
        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp1.getAuthor(),bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }
    
    @Override
    public Set<Blueprint> getBlueprintsByAuthor(String A) throws BlueprintNotFoundException{
        Set<Blueprint> list = new HashSet<>();
        blueprints.forEach((key,value) -> {
            if(key.getElem1().equals(A)){
                list.add(value);
            }
        });
        return list;
    }
    
    @Override
    public Set<Blueprint> getAllBlueprints() throws BlueprintNotFoundException{
        Set<Blueprint> list = new HashSet<>();
        blueprints.forEach((key,value) -> {
            list.add(value);
        });
        return list;
    }

	@Override
	public void setBluePrint(String author, String name, Blueprint b) {
		
		blueprints.remove(new Tuple<>(b.getAuthor(), b.getName()));
		Blueprint bp=new Blueprint(author, name);
		bp.setPoints(b.getPoints());
		blueprints.put(new Tuple<>(b.getAuthor(),b.getName()), bp);
	}
    
}

