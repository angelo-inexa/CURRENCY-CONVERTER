/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deviceconvertor;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.firefox.NotConnectedException;

/**
 *
 * @author olivier.baudion
 */
public class CurrencyConvertorTest {
    private Formula formule;
    private String fichiercsvpath;
    public CurrencyConvertorTest() {
        //this.formule = new Formula();
        var fichiercsv = "devise.csv";
        ClassLoader classLoader = getClass().getClassLoader();
        var pathres = classLoader.getResource(fichiercsv);
        var fich = pathres.getFile();
        File filecsv = new File(fich);
        this.fichiercsvpath = filecsv.getAbsolutePath();
    }
        
    @BeforeEach
    public void setUp() {
    }
    
    

    /**
     * Test of saveFormula method, of class CurrencyConvertor.
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveFormula() throws Exception {
        
        // Given        
          CurrencyConvertor cc = new CurrencyConvertor();
          formule=new Formula(20,"XOF","CEDIS",0.011);
          
        // When
          
          cc.saveFormula(formule);
                        
        //Then
          assertTrue(formule.equals(cc.getLastFormula().get()));
          
                 
    }

    /**
     * Test of displayFormulasMenu method, of class CurrencyConvertor.
     */
    @Test
    public void testDisplayFormulasMenu() {
       
    }

    /**
     * Test of displayFormulasList method, of class CurrencyConvertor.
     */
    @Test
    public void testDisplayFormulasList() {
        
    }

    /**
     * Test of performConversion method, of class CurrencyConvertor.
     */
    @Test
    public void testPerformConversion() throws IOException {
        // Given
        double amount = 10;
        int id = 3;
        CurrencyConvertor cc = new CurrencyConvertor();
        formule = cc.getElementByIndex(id).get();
        double resultat = 0;
        // When
        if (formule.getId() > 0) {
            resultat = cc.performConversion(10, 3);
        }else{
            throw new IOException("Cette formule n'existe pas");
        }

        //Then
        assertEquals(2000.00, resultat, 0.00001);
    }

    
    /**
     * Test of getElementByIndex method, of class CurrencyConvertor.
     */
    @Test
    public void testGetElementByIndex() throws IOException {
        // Given
            int id=1;
            CurrencyConvertor cc = new CurrencyConvertor();
        
        // When 
           if(cc.getElementByIndex(id).isPresent())
               formule=cc.getElementByIndex(id).get();
           else
               throw new IOException("Cette formule n'existe pas");
           
        // Then
           assertEquals(id , formule.getId());
    }
    
    
    /**
     * Test of performDeletion method, of class CurrencyConvertor.
     */
    @Test
    public void testPerformDeletion() throws IOException {
        // Given
            int id=1;
            CurrencyConvertor cc = new CurrencyConvertor();
        
        // When 
           if(cc.getElementByIndex(id).isPresent())
               cc.performDeletion(id);
           else
               throw new IOException("Cette formule n'existe pas");
        // Then
           assertTrue(cc.getElementByIndex(id).isEmpty());
           //verifier si la formule existe tjrs 
        
    }

    

    /**
     * Test of displayresult method, of class CurrencyConvertor.
     */
    @Test
    public void testDisplayresult() {
        
    }
    
}
