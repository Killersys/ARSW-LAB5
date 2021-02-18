package edu.eci.arsw.blueprints;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

@Service
public class App 
{
	public static void main(String arg[])
		{
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);
        
        try {
        	
        	// Prueba de Blueprint
        	
        	bs.addNewBlueprint(new Blueprint("Carlos", "plano1", new Point[] {new Point(1,1)}) ); 	//Añadimos el nuevo blue print
			bs.getBlueprintsByAuthor("Carlos");														//obtenemos el blue print por autor
			bs.addNewBlueprint(new Blueprint("Carlos", "plano2", new Point[] {new Point(1,1)}) ); 	//Añadimos el nuevo blue print
			bs.getBlueprintsByAuthor("Carlos");	
			bs.addNewBlueprint(new Blueprint("Andrés", "plano1", new Point[] {new Point(1,1)}) );
			bs.getBlueprintsByAuthor("Andrés");
			bs.addNewBlueprint(new Blueprint("Camilo", "plano1", new Point[] {new Point(1,1)}) );
			bs.getBlueprintsByAuthor("Camilo");
			
			System.out.println(bs.getBlueprintsByAuthor("Carlos"));		//Obtenemos los blueprint por autor
			System.out.println(bs.getAllBlueprints());					//Obtenemos todos los Blueprints
			System.out.println(bs.getBlueprint("Carlos", "plano1"));	//Obtenemos los blueprint por autor y plano
			
			
        	// Muestreo
        	Point[] puntosMuestreo = new Point[] {new Point(1,1), new Point(7,9), new Point(7,4), new Point(20,30)};
        	bs.addNewBlueprint(new Blueprint("Carlos", "plano3", puntosMuestreo)); 	//Añadimos el nuevo blueprint
			Blueprint bp = bs.getBlueprint("Carlos", "plano3");
			System.out.println("\nFiltro de Muestreo:");
			System.out.println(bp.getPoints().get(0).getX() == 1);
			System.out.println(bp.getPoints().get(0).getY() == 1);
			System.out.println(bp.getPoints().get(1).getX()== 7);
			System.out.println(bp.getPoints().get(1).getY()== 4);
			System.out.println(bp.getPoints().size() == 2);
			
			// Redundancia
			Point[] puntosRedundancia = new Point[] {new Point(10,15), new Point(10,15), new Point(20,30), new Point(20,30)};
			bs.addNewBlueprint(new Blueprint("Camilo", "plano2", puntosRedundancia)); 	//Añadimos el nuevo blueprint
			Blueprint bp2 = bs.getBlueprint("Camilo", "plano2");
			System.out.println("\nFiltro de Redundancia:");
			System.out.println(bp2.getPoints().get(0).getX() == 10);
			System.out.println(bp2.getPoints().get(0).getY() == 15);
			System.out.println(bp2.getPoints().get(1).getX() == 20);
			System.out.println(bp2.getPoints().get(1).getY() == 30);
			System.out.println(bp.getPoints().size() == 2);
			
		} catch (BlueprintNotFoundException e) {
			e.printStackTrace();
		}
	}
}
