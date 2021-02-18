package edu.eci.arsw.blueprints.filtro;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
@Qualifier("Redundancia")
public class FRedundancia implements Filtro
{
	@Override
	public Blueprint filtrar(Blueprint bp) 
	{
		List<Point> puntosBp = bp.getPoints();
		List<Point> puntosFiltrados = new ArrayList<>();
		
		Point punto = null;
		
		for(int i=0; i<puntosBp.size(); i++)
		{
			if (punto != null && (punto.getX() == puntosBp.get(i).getX() || (punto.getY() == puntosBp.get(i).getY())))
			{
				puntosFiltrados.add(puntosBp.get(i));
			}
			punto = puntosBp.get(i);
		}
		
		Point[] p = new Point[puntosFiltrados.size()];
		p = puntosFiltrados.toArray(p);
		
		return new Blueprint(bp.getAuthor(), bp.getName(), p);
	}
}
