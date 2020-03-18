/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centroeducativov4;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Notas implements Serializable{
    
    private static final long serialVersionUID = 4L; // http://chuwiki.chuidiang.org/index.php?title=Serializaci%C3%B3n_de_objetos_en_java#Serial_Version_UID
    
    public int notas[];

    public void setNotas(int[] notas) {
        this.notas = notas;
    }

    public int[] getNotas() {
        return notas;
    }
    
    public Notas(){ 
        notas = new int[5];  
    }
}

