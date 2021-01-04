/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deviceconvertor;

import java.io.IOException;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 *
 * @author olivier.baudion
 */
public class Main {
    
    public static void main(String[] args) throws IOException{
        UserInputHandler uih=new UserInputHandler();
        uih.displayMenu();
        uih.processChoice(uih.getChoice());
        
        
        
    }
}
