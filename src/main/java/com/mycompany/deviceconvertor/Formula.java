/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deviceconvertor;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 *
 * @author olivier.baudion
 */
public class Formula {
    private TextIO textIO ;
    private TextTerminal terminal ;
    private int id;
    private String from;
    private String into;
    private double taux;

    public Formula() {
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getInto() {
        return into;
    }

    public double getTaux() {
        return taux;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public Formula(int id, String from, String into, double taux ) {
        this.id = id;
        this.from = from;
        this.into = into;
        this.taux = taux;
        
    }

    public Formula(String from, String into, double taux) {
        this.from = from;
        this.into = into;
        this.taux = taux;
    }
  
    
    
    
    @Override
    public  boolean equals(Object obj){
        Formula f = (Formula) obj;
       if(id==f.getId() && from.equals(f.getFrom()) && into.equals(f.getInto()) &&  taux==f.getTaux()) 
          return true;
       else
           return false;
    }
    
}
