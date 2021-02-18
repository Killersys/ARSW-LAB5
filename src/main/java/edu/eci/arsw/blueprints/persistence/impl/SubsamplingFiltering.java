package edu.eci.arsw.blueprints.persistence.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.filtering;

@Service("SubsamplingFiltering")
public class SubsamplingFiltering implements filtering{

    @Override
    public Set<Blueprint> filtrar(Set<Blueprint> map) {
        for (Blueprint BP : map){
        	List<Point> P = new ArrayList<Point>();
            int j = 0;
            while (j < (BP.getPoints().size())){
            	if (j%2 == 0) {
            		P.add(BP.getPoints().get(j));}
            	j++;}
            BP.removeAllPoints();
            for (Point po : P) {
            	BP.addPoint(po);}}
        return map;}}
