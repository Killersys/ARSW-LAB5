/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.filtering;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jairo
 */
@Service("RedundancyFiltering")
public class RedundancyFiltering implements filtering{

    @Override
    public Set<Blueprint> filtrar(Set<Blueprint> map) {
        for (Blueprint BP : map){
        	List<Point> P = new ArrayList<Point>();
            int j = 0;
            while (j < (BP.getPoints().size())){
            	if (j==0) {
            		P.add(BP.getPoints().get(j));
            		j++;}
            	else if ((BP.getPoints().get(j).getX()==P.get(P.size()-1).getX()) &&  (BP.getPoints().get(j).getY()==P.get(P.size()-1).getY())){
                    j++;}
                else {
                	P.add(BP.getPoints().get(j));
                	j++;}}
            BP.removeAllPoints();
            for (Point po : P) {
            	BP.addPoint(po);}}
        return map;}}
