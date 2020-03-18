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
import java.util.TreeMap;

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
    public static void almacenarDatosFichero(TreeMap<String, Persona> lista, File fichero){
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        Persona p;
        
        try {
           if (! fichero.exists()) fichero.createNewFile(); //Si el fichero no existiese se crea 
            //Se crea el fichero
            
            if(! lista.isEmpty()){
                fos = new FileOutputStream(fichero);
                salida = new ObjectOutputStream(fos);               
                salida.writeObject(lista);
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
    public static TreeMap<String, Persona> obtenerDatosFichero(File fichero) throws IOException {
        TreeMap<String, Persona> lista = new TreeMap<>();
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        Persona p;
        
        try{
            if(! fichero.exists()) throw new Exception("El fichero no se encunetra. ");
            
            fis = new FileInputStream(fichero);
            entrada = new ObjectInputStream(fis);
            
            lista = (TreeMap<String, Persona>) entrada.readObject();
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
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
