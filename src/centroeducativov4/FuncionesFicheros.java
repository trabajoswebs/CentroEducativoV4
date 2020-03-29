/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centroeducativov4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Johan Manuel
 */
public class FuncionesFicheros {
    
    /**
     * 
     * @param lista
     * @param fichero 
     */
    public static void almacenarDatosFichero(ArrayList<Persona> lista, File fichero){
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        
        try {
            if (!fichero.exists()) {
                fichero.createNewFile(); //Si el fichero no existiese se crea 
            }            //Se crea el fichero

            fos = new FileOutputStream(fichero);
            salida = new ObjectOutputStream(fos);
            
            if (! lista.isEmpty()) {
                for (Persona persona : lista) { //Para proposito de la práctica se graba de objeto a objeto, 
                    salida.writeObject(persona);//sin embargo, se puede serializar el ArrayList directamente
                }
                
                salida.flush();
            }
            
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: "+e.getMessage());
        } catch (IOException e) {
            System.out.println("Error "+e.getMessage());
        } finally {
            try {
                if(fos !=null) fos.close();
                if(salida !=null) salida.close();
            } catch (IOException e) {
                System.out.println("Error al intenta guardar el fichero serializable " +e.getMessage());
            }
        }
    }

    /**
     * Carga el treeMap con los datos del fichero Persona3.txt
     * @param fichero
     * @return
     * @throws IOException 
     */
    public static ArrayList obtenerDatosFichero(File fichero) throws IOException {
        ArrayList<Persona> lista = new ArrayList<>();
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        
        try{
            if(! fichero.exists()) throw new Exception("El fichero no se encunetra. ");
            
            fis = new FileInputStream(fichero);
            entrada = new ObjectInputStream(fis);
            Persona per = (Persona) entrada.readObject();
                    
            while(per != null){
                lista.add(per);
                per = (Persona) entrada.readObject();
            }
            
            System.out.println("despues");
            entrada.close();
        } catch (FileNotFoundException e) {
            System.out.println("Excepción fichero no encontrado: " +e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Excepción de clase " +e.getMessage());
        } catch (IOException e) {
            
        } catch (Exception e) {
            System.out.println("Excepción general: " +e.getMessage());
            
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return lista;
    }
}
