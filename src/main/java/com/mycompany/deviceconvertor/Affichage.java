/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deviceconvertor;

import java.awt.Color;
import java.util.function.Consumer;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 *
 * @author olivier.baudion
 */
public class Affichage {
        private TextIO textIO; 
        private TextTerminal terminal; ;
        
        

        public Affichage() {
             this.textIO = TextIoFactory.getTextIO();
             this.terminal = textIO.getTextTerminal();
             terminal.getProperties().setInputColor("white");
             terminal.getProperties().setPromptBold(true);
             terminal.getProperties().setPromptColor(Color.yellow);
             terminal.getProperties().setInputBold(true);
        }

        
        
        public  void close(){
            terminal.abort();
        }
        public  void afficher(String message){
             
             terminal.println(message);
        }
        public  void afficherEn(String message, Color col){
             
             
            
                         
        }
        
}
