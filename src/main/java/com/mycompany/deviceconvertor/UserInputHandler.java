/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deviceconvertor;

import java.io.IOException;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import static org.beryx.textio.TextIoFactory.getTextIO;
import org.beryx.textio.TextTerminal;

/**
 *
 * @author olivier.baudion
 */
public class UserInputHandler {
    private Affichage terminal;

    public UserInputHandler() {
        terminal=new Affichage();
    }
    private int choice;
    private double amount;
    private double result;
    private Formula formule;
    private  CurrencyConvertor curconv;
    
    public double getAmount() {
        return amount;
    }
    
    public int getChoice() {
        choice = getUserChoice();
        curconv = new CurrencyConvertor();
        return choice;
    }
    
    public void displayMenu(){
        terminal.afficher("Bienvenue dans conversion des devises\n");
        terminal.afficher("\t Appuyer 1 pour faire une conversion\n");
        terminal.afficher("\t Appuyer 2 pour definir une nouvelle formule\n");
        terminal.afficher("\t Appuyer 3 pour supprimer une formule\n");
        terminal.afficher("\t Appuyer 4 pour quitter le programme \n");
    }
    public int  getUserChoice(){
         return getTextIO().newIntInputReader()
                      .withMinVal(1)
                      .withDefaultValue(1)
                      .read("Faites Votre choix ");
    }
    
    public void processChoice(int choix) throws IOException{
        switch(choice){
            case 1:curconv.displayFormulasMenu();
                   getConversionInfos();
                   submitConversion();
                   curconv.displayresult(amount, result, choix);
                   displayMenu();
                   processChoice(getChoice());
                  break;
            case 2:getformulaInfos();
                   curconv.saveFormula(formule);
                   displayMenu();
                   processChoice(getChoice());
                break;
            case 3:curconv.displayFormulasMenu();
                   getDeletionInfos();
                   submitDeletion();
                   displayMenu();
                   processChoice(getChoice());
                break;
            case 4: terminal.close();
                    break;
            default:break;
        }
    }
    private double getAmountToConvert(){
        return getTextIO().newDoubleInputReader()
                          .read("Saisir montant a Convertir");
    }
    private Formula getformulaInfos() throws IOException{
        curconv = new CurrencyConvertor();
         terminal.afficher("Entrer la devise d'entrée");
        String from=getTextIO().newStringInputReader()                
                      .read("Saisir devise de depart");
        terminal.afficher("Entrer la devise de sortie");
        String into=getTextIO().newStringInputReader()                
                      .read("Saisir devise final");
        terminal.afficher("Entrer le taux");
        double taux=getTextIO().newDoubleInputReader()
                          .read("Saisir le taux de conversion");
        formule = new Formula(curconv.getLastFormula().get().getId()+1,from, into, taux);
        //formule.setId(curconv.getLastId()+1);
        return formule;
    }
    
    private void getConversionInfos(){
        choice = getUserChoice();
        amount = getAmountToConvert();
        //double resultat = 
    }
    private void getDeletionInfos(){
        choice = getUserChoice();       
    }
    private void submitConversion() throws IOException{
       result = curconv.performConversion(amount, choice);
    }
    private void submitDeletion() throws IOException{
        curconv.performDeletion(choice);
    }
   
}
