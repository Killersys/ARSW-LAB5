package edu.eci.arsw.blueprints.filtro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
@Qualifier("Muestreo")
public class FSubmuestreo implements Filtro
{
	@Override
	public Blueprint filtrar(Blueprint bp) 
	{
		List<Point> puntosBp = bp.getPoints();
		List<Point> puntosFiltrados = new ArrayList<>();
		
		boolean saltar = false;
		
		for(int i=0; i<puntosBp.size(); i++)
		{
			if(!saltar)
			{
				puntosFiltrados.add(puntosBp.get(i));
				saltar = true;
			}
			else {saltar = false;}
		}
		Point[] p = new Point[puntosFiltrados.size()];
		p = puntosFiltrados.toArray(p);
		
		return new Blueprint(bp.getAuthor(), bp.getName(), p);
	}
	
}
