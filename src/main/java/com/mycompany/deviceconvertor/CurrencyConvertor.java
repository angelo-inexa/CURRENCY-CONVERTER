/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.deviceconvertor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 *
 * @author olivier.baudion
 */
public class CurrencyConvertor {
    private String fichiercsvpath;    
    boolean exist = false;
    private String line;
    private List<String> listeformule;
    private TextTerminal terminal; 
    private  TextIO textIO;
    
    public CurrencyConvertor() {
            textIO = TextIoFactory.getTextIO();
            terminal = textIO.getTextTerminal();
            listeformule=new ArrayList<String>();
            var fichiercsv = "devise.csv";
            ClassLoader classLoader = getClass().getClassLoader();
            var pathres = classLoader.getResource(fichiercsv);
            var fich = pathres.getFile();
            File filecsv = new File(fich);
            this.fichiercsvpath = filecsv.getAbsolutePath();
        
    }

    
    public void saveFormula(Formula formule) throws IOException {
        try {
               //terminal.println(getLastId()+1+"");
                FileWriter file = new FileWriter(fichiercsvpath, true);
                file.append(formule.getId() + "");
                file.append(";");
                file.append(formule.getFrom());
                file.append(";");
                file.append(formule.getInto());
                file.append(";");
                file.append(formule.getTaux() + "");
                file.append("\n");
                file.flush();
                file.close();
            
        } catch (Exception e) {
               terminal.println("Ce fichier est deja ouvert dans un autre programme\n");
        }

    }
    private void deleteFormula(){
         try {

            FileWriter file = new FileWriter(fichiercsvpath);
            file.append("Id;From;To;Taux\n");
            for(String ligne:listeformule){
                file.append(ligne+"\n");                
            }
            file.flush();
            file.close();
            
        } catch (Exception e) {
            terminal.println("Ce fichier est deja ouvert dans un autre programme");
        }
    }

    public void displayFormulasMenu() throws IOException {
        Supplier<Stream< String>> lignecsv =readFormulas();
        lignecsv.get().skip(1).forEach((x) -> {
            String[] contenu = x.split(";");
            String message = "Appuyez % pour Convertir % => % ";
            for (String detail : contenu) {
                message = message.replaceFirst("(?:%)+", detail);
            }
            terminal.println(message);
        });
    }
    public void displayFormulasList() throws IOException {
         Supplier<Stream<String>> lignecsv = readFormulas();         
        lignecsv.get().skip(1).forEach((x) -> {
            String[] contenu = x.split(";");
            String message = "1 % = % % ";
            terminal.printf("1 %s = %s %s", contenu[1], contenu[3], contenu[2]);
        });
    }

    private Supplier<Stream<String>> readFormulas() throws IOException {
        Supplier<Stream<String>> supplier = () -> {
            try {
               return Files.lines(Paths.get(fichiercsvpath));
            } catch (IOException ex) {
                //Logger.getLogger(CurrencyConvertor.class.getName()).log(Level.SEVERE, null, ex);
                return Stream.empty();
            }
            
        };
        return supplier;
        //return Files.lines(Paths.get(fichiercsvpath));
        
    }

    public double performConversion(double amount, int formulaindex) throws IOException {
        Formula cc = getElementByIndex(formulaindex).get();
        double resultat = amount * cc.getTaux();
        return resultat;
    }
    
    public void performDeletion(int id) throws IOException{
        Supplier<Stream<String>> lignecsv = readFormulas();
        //listeformule.add(readFormulas().findFirst().get()) ;
        
        lignecsv.get().skip(1).forEach((x)->{
            
            if(Integer.valueOf(getFirstColonne(x)) !=id){
                listeformule.add(x);
            }
        });
        this.deleteFormula();
        listeformule=new ArrayList<>();
        
    }

    public Optional<Formula> getElementByIndex(int index) throws IOException {
        Supplier<Stream<String>> lignecsv = readFormulas();
        
        lignecsv.get().skip(1).forEach(x -> {
            if (getFirstColonne(x) == index) {
                exist = true;
                line = x;
            }
        });
        if (exist) {
            String[] res = line.split(";");

            return Optional.ofNullable(new Formula(Integer.valueOf(res[0]), res[1], res[2], Double.valueOf(res[3])));
        } else {
            return Optional.empty();
        }
        
    }

    private int getFirstColonne(String line) {
        String[] res = line.split(";");
        return Integer.valueOf(res[0]);
    }
    
    private  int getLastId() throws IOException{
        Supplier<Stream<String>> lignecsv = readFormulas();
        int taille = (int) lignecsv.get().count();
        if (taille > 1) {
            int id = getFirstColonne(lignecsv.get().skip(taille - 1).findFirst().get().toString());
            
            return id;
        } else {
            
            return 0;
        }
              
    }
    
    
    public  Optional<Formula> getLastFormula() throws IOException{
        Supplier<Stream<String>> lignecsv = readFormulas();
        int taille = (int) lignecsv.get().count();
        if (taille > 1) {
            String[] ligne = lignecsv.get().skip(taille - 1).findFirst().get().split(";");
            Formula formule = new Formula(Integer.valueOf(ligne[0]), ligne[1], ligne[2], Double.valueOf(ligne[3]));
            
            return Optional.of(formule);
        } else {
            
            return Optional.empty();
        }
              
    }
    
    
    public void displayresult(double amount, double resultat,int index ) throws IOException{
        Formula formule=getElementByIndex(index).get();
        terminal.printf("%.0f %s = %.0f %s \n", amount, formule.getFrom(), resultat, formule.getInto());
        
    }
    
}
