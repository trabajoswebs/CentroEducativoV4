/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centroeducativov4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Acer
 */
public class CentroEducativoV4 {

    public static String curso;
    public static double pagoPorHoraExtra;
    static ArrayList<Persona> lista = new ArrayList<Persona>(); 
    static TreeMap<String, String> tmEEEE = new TreeMap<String, String>();//Map con entidades bancarias
    static TreeMap<String, String> tmEEEESSSS = new TreeMap<String, String>();//Map con sucursales bancarias
    static TreeMap<String, String> tmCC = new TreeMap<String, String>();//Map con nombres de cursos
    static TreeMap<String, String> tmCCASIGNA = new TreeMap<String, String>();//Map con curso+asignatura

    public static void setCurso(String cur) {
        curso = cur;
    }

    public static void setPagoPorHoraExtra(double pPorHoraExtra) {
        pagoPorHoraExtra = pPorHoraExtra;
    }

    public static void setLista(ArrayList<Persona> list) {
        lista = list;
    }

    public static void setTmEEEE(TreeMap<String, String> tmpEEEE) {
        tmEEEE = tmpEEEE;
    }

    public static void setTmEEEESSSS(TreeMap<String, String> tmpEEEESSSS) {
        tmEEEESSSS = tmpEEEESSSS;
    }

    public static void setTmCC(TreeMap<String, String> tmpCC) {
        tmCC = tmpCC;
    }

    public static void setTmCCASIGNA(TreeMap<String, String> tmpCCASIGNA) {
        tmCCASIGNA = tmpCCASIGNA;
    }

    public static String getCurso() {
        return curso;
    }

    public static double getPagoPorHoraExtra() {
        return pagoPorHoraExtra;
    }

    public static ArrayList<Persona> getLista() {
        return lista;
    }

    public static TreeMap<String, String> getTmEEEE() {
        return tmEEEE;
    }

    public static TreeMap<String, String> getTmEEEESSSS() {
        return tmEEEESSSS;
    }

    public static TreeMap<String, String> getTmCC() {
        return tmCC;
    }

    public static TreeMap<String, String> getTmCCASIGNA() {
        return tmCCASIGNA;
    }

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
            //Se carga TreeMap y las variables globales
            TablasCursos.cargaGlobales();
            Cuenta.cargaEntidadesBancarias(tmEEEE);
            Cuenta.cargaSucursalesBancarias(tmEEEESSSS);
            TablasCursos.cargaCursos(tmCC);
            TablasCursos.cargaCursosAsignaturas(tmCCASIGNA);
            
            // CREAMOS UN FICHERO PARA GUARDAR LOS PROFESORES
            File ruta = new File("Profesores");//crea un objeto con una ruta
            File fPersonas = new File(ruta, "Personas3.dat");//crea un objeto fichero en la ruta

            
            try {
                if (fPersonas.exists()) {//Si existe el fichero podemos leer los registros que tiene
                    lista = FuncionesFicheros.obtenerDatosFichero(fPersonas);
                 
                } else {
                    fPersonas.createNewFile();
                }
            } catch (IOException e) {
                System.out.println("Ha ocurrido un error con el fichero: " + e.getMessage());
            }
         
            Boolean salir = false;

            int option = 0;
            do {
                System.out.println("\n*************** MENU PRINCIPAL ***************\n");
                System.out.println("\t1. MANTENIMIENTO ALUMNOS");
                System.out.println("\t2. MANTENIMIENTO PROFESORES");             
                System.out.println("\t3. LISTADO DE NOMBRES DE PROFESORES Y ALUMNOS");
                System.out.println("\t4. LISTADO DE NOMBRES DE PROFESORES");
                System.out.println("\t5. LISTADO DE NOMBRES DE ALUMNOS");
                System.out.println("\t0. FIN DE LA APLICACION");
                System.out.print("\n\t   Opcion seleccionada: ");

                option = sc.nextInt();

                switch (option) {
                    case 1:
                        subMenuAlumno();
                        break;
                    case 2:
                        subMenuProfesores();
                        break;                    
                    case 3:
                        imprimirListados("Curso Académico: %s\nLISTADO DE PROFESORES Y ALUMNOS\nAPELLIDOS/NOMBRE\n", true, true);
                        break;
                    case 4:
                        imprimirListados("Curso Académico: %s\nLISTADO DE PROFESORES\nAPELLIDOS/NOMBRE\n", true, false);
                        break;
                    case 5:
                        imprimirListados("Curso Académico: %s\nLISTADO DE ALUMNOS\nAPELLIDOS/NOMBRE\n", false, true);
                        break;
                    case 0:
                    default:
                        FuncionesFicheros.almacenarDatosFichero(lista, fPersonas);
                        salir = true;
                        break;

                }

            } while (!salir);
        
    }

    /**
     *
     */
    public static void subMenuProfesores() {
        boolean correcto;
        int opt = 0;
        String key;
        Profesor profe;
        Persona per = null;
        correcto = false;
        // CREAMOS UN FICHERO PARA GUARDAR LOS PROFESORES
        File ruta = new File("Profesores");//crea un objeto con una ruta
        File fPersonas = new File(ruta, "Personas3.dat");//crea un objeto fichero en la ruta

        do {

            try {                
                System.out.println("\n\n\n *************** MANTENIMIENTO DE PROFESORES ***************\n");
                System.out.println("\t1. ALTA DE UN PROFESOR");
                System.out.println("\t2. BAJA DE UN PROFESOR");
                System.out.println("\t3. CONSULTA DE DATOS PERSONALES DE UN PROFESOR");
                System.out.println("\t4. INTRODUCIR HORAS EXTRAORDINARIAS DE UN MES");
                System.out.println("\t5. LISTADO DE PROFESORES. DATOS PERSONALES");
                System.out.println("\t6. LISTADO DE PROFESORES. CLASES QUE IMPARTEN");
                System.out.println("\t7. LISTADO DE NOMINAS DE UN MES");
                System.out.println("\t8. MANTENIMIENTO DE ASIGNATURAS IMPARTIDAS POR CADA PROFESOR");
                System.out.println("\t0. VUELTA AL MENU PRINCIPAL");
                System.out.print("\n\t   Opcion seleccionada: ");
                opt = sc.nextInt();
                switch (opt) {
                    case 1:
                        System.out.println("Opcion seleccionada: Alta profesor");
                         try {
                            profe = new Profesor();
                            profe.nuevoProfesor();
                            key = profe.getApellidos() + ", " + profe.getNombre();
                            System.out.println(key);
                            
                            if (contieneKey(key, lista)) {
                                throw new Exception("Este nombre ya existe. No puedo grabarlo");
                            }
                            
                            FuncionesFicheros.almacenarDatosFichero(lista, fPersonas);  //Guardamos el objeto en el fichero
                            
                            lista.add(profe); // Lo añadimos a la lista
                            
                        } catch (FileNotFoundException fnf) {
                            System.out.println(fnf.getMessage());                        
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());                        
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        correcto = false;
                        do {
                            try {
                                System.out.println("Opción seleccionada: Baja Profesor");
                                System.out.println("indique el nombre del profesor:");
                                String n = sc.nextLine();
                                System.out.println("indique el apellido del profesor:");
                                String a = sc.nextLine();
                                key = a + ", " + n;
                                
                                if (contieneKey(key, lista)) {
                                    throw new Exception("El nombre que desea eliminar no existe");
                                }
                                eliminarObjetoLista(key, lista);
                                correcto = true;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } while (!correcto);
                        break;
                    case 3:
                        correcto = false;
                        System.out.println("Opcion seleccionada: Consulta datos perosnales profesor");
                        do {
                            try {
                                sc.nextLine();
                                System.out.println("Indique el nombre del profesor: ");
                                String n = sc.nextLine();
                                System.out.println("Indique los apellidos del profesor: ");
                                String a = sc.nextLine();
                                key = a + ", " + n;

                                 if (contieneKey(key, lista)) {
                                    per = getObjetoPersonaByKey(key, lista);
                                } else {
                                    throw new Exception("El profesor no existe.");
                                }
                                 
                                if (per instanceof Profesor) {
                                    profe = (Profesor) per;
                                    System.out.println(per.toString());
                                } else {
                                    throw new Exception("No se trata de un profesor. ");
                                }
                                correcto = true;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }

                        } while (!correcto);
                        break;
                    case 4:
                        correcto = false;
                        int mes,
                         horas;
                        do {
                            try {
                                System.out.println("Opcion seleccionada: Introducir horas extras por mes: ");
                                System.out.println("Horas extraordinarias por los profesores en el mes de: ");
                                mes = sc.nextInt();
                                
                                if (mes < 0 && mes > 12) {
                                    throw new Exception("Mes incorrecto.");
                                }

                                Iterator it = lista.iterator();

                                while (it.hasNext()) {
                                    
                                    if (it.next() instanceof Profesor) {
                                        profe = (Profesor) it.next();
                                        System.out.println("Nombre del profesor: ");
                                        System.out.println(profe.getApellidos() + ", " + profe.getNombre());
                                        System.out.println("Horas realizadas: ");
                                        horas = sc.nextInt();
                                        if (horas > 20) {
                                            throw new Exception("No se puede exceder de mas de 20 horas al mes");
                                        }
                                        profe.setHorasExtra(mes, horas);
                                    }
                                }
                                correcto = true;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                                sc.nextLine();
                            }

                        } while (!correcto);
                        break;
                    case 5:
                        System.out.println("Opción seleccionada: Imprime datos personales de los profesores.");
                        Iterator it = lista.iterator();
                        
                        while (it.hasNext()) {                            
                            per = (Persona) it.next();
                            if (per instanceof Profesor) {
                                profe = (Profesor) per;                                
                                System.out.println(profe.ImprimeProfesor());
                                System.out.println();
                            }
                        }
                        break;

                    case 6:
                        System.out.println("Opción seleccionada: Datos de las clases que imparten los profesores.");
                        
                        it = lista.iterator();
                        
                        while (it.hasNext()) {                            
                            per = (Persona) it.next();
                            if (per instanceof Profesor) {
                                profe = (Profesor) per;
                                System.out.println(profe.imprimeAsignaturas());                        
                                System.out.println();
                            }
                        }
                        break;

                    case 7:
                        
                        do {
                            System.out.println("Opción seleccionada: Imprimir nomina de cada profesor según el mes: ");
                            try {
                                correcto = false;
                                System.out.println("Nómnias del mes 1 a 12: ");
                                mes = sc.nextInt();
                                if (mes < 1 || mes > 12) {
                                    throw new InputMismatchException("Error al introducir el mes del año: ");
                                }
                                it = lista.iterator();
                                while (it.hasNext()) {                            
                                    per = (Persona) it.next();
                                    if (per instanceof Profesor) {
                                        profe = (Profesor) per;
                                        System.out.println(profe.ImprimirNominas(mes));
                                    }
                                    System.out.println();
                                }
                                correcto = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Error: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        } while (! correcto);
                        break;
                    case 8:
                        correcto = false;

                        do {
                            System.out.println("Opción seleccionada: Mantenimiento de asignaturas impartidas por cada profesor: ");
                            sc.nextLine();
                            System.out.println("Nombre del profesor: ");
                            String n = sc.nextLine();
                            System.out.println("Apellidos del Profesor: ");
                            String a = sc.nextLine();
                            key = a + ", " + n;
                            per = getObjetoPersonaByKey(key, lista);
                            if (per instanceof Persona) {
                                profe = (Profesor) per;
                                System.out.println(profe.toString());
                                profe.asignaturasProfesor();
                                correcto = true;
                            }
                        } while (!correcto);
                        System.out.println("");
                        break;
                    case 0:
                        correcto = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine();
            }
        } while (!correcto);
    }

    /**
     *
     */
    public static void subMenuAlumno() {
        int opt = 0;
        String key;
        do {
            
            try {
                System.out.println("\n\n\n *************** MANTENIMIENTO DE ALUMNOS ***************\n");
                System.out.println("\t1. ALTA DE UN ALUMNO");
                System.out.println("\t2. BAJA DE UN ALUMNO");
                System.out.println("\t3. CONSULTA DE DATOS PERSONALES DE UN ALUMNO");
                System.out.println("\t4. INTRODUCIR NOTAS DE UNA ASIGNATURA Y EVALUACION A TODOS LOS MATRICULADOS");
                System.out.println("\t5. LISTADO DE ALUMNOS DE UN GRUPO. DATOS PERSONALES");
                System.out.println("\t6. LISTADO DE ALUMNOS MATRICULADOS EN UNA ASIGNATURA");
                System.out.println("\t7. LISTADO DE BOLETINES DE NOTAS DE UNA EVALUACION Y CURSO");
                System.out.println("\t0. VUELTA AL MENU PRINCIPAL");
                System.out.print("\n\t   Opcion seleccionada: ");
                opt = sc.nextInt();
                switch (opt) {
                    case 0:
                        break;
                    case 1:
                        System.out.println("\t1. ALTA DE UN ALUMNO");
                        Alumno alumn = new Alumno();
                        alumn.pideDatos();

                        String nombreCompleto = alumn.getApellidos() + ", " + alumn.getNombre();
                        try {
                            lista.add(alumn);
                            System.out.println("El alumno ha sido dado de alta correctamente.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 2:
                        try {
                            System.out.println("\t2. BAJA DE UN ALUMNO");
                            nombreCompleto = getKey();

                            if (contieneKey(nombreCompleto, lista)) {
                                Persona per = getObjetoPersonaByKey(nombreCompleto, lista);
                                
                                if (per instanceof Alumno) {
                                    lista.remove(per);
                                } else {
                                    throw new Exception("No se puede eliminar este registro al no tratarse de un alumno. ");
                                }
                            } else {
                                throw new Exception("No existe ningun alumno con este nombre. ");
                            }

                            System.out.println("El alumno ha sido dado de baja.");

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 3:
                        try {
                            System.out.println("\t3. CONSULTA DE DATOS PERSONALES DE UN ALUMNO");
                            nombreCompleto = getKey();

                            if (contieneKey(nombreCompleto, lista)) {
                                Persona per = getObjetoPersonaByKey(nombreCompleto, lista);
                                
                                if (per instanceof Alumno) {
                                    Alumno alum = (Alumno) per;
                                    System.out.println(alum.toString());
                                } else {
                                    throw new Exception("No se puede eliminar este registro al no tratarse de un alumno. ");
                                }
                            } else {
                                throw new Exception("No existe ningun alumno con este nombre. ");
                            }

                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        String rept = "N";                        
                        String asignatura;
                        int nroEval;
                        String[] evaluacion = {"Primera Evaluación", "Segunda Evaluación", "Tercera Evaluación", "Evaluación Ordinaria", "Evaluación Extraordinaria"};
                        System.out.println("\t4. INTRODUCIR NOTAS DE UNA ASIGNATURA Y EVALUACION A TODOS LOS MATRICULADOS");

                        do {
                            try {
                                boolean anyAlumHasAsig = true;
                                sc.nextLine();
                                System.out.println("\nIndique el código de la asignatura que desea evaluar.");
                                asignatura = sc.nextLine().toUpperCase();

                                if (CentroEducativoV4.tmCCASIGNA.containsKey(asignatura)) {
                                    System.out.println("\nIndique la Evaluación:\n");

                                    for (int i = 0; i < evaluacion.length; i++) {
                                        System.out.println("\t" + (i + 1) + ". " + evaluacion[i] + ".");
                                    }
                                    nroEval = sc.nextInt();

                                    if ((nroEval > (evaluacion.length)) || (nroEval <= 0)) {
                                        throw new Exception("Debe indicar correctamente el número de la evaluación.");
                                    }

                                    nroEval--;
                                    System.out.println("\n\tOpción elegida: " + evaluacion[nroEval] + "\n");

                                    Iterator it = lista.iterator();

                                    while (it.hasNext()) {
                                        Object p = it.next();
                                        if (p instanceof Alumno) {
                                            Alumno al = (Alumno) p;
                                            
                                            Iterator itAsignatura = al.getTmAsignaturasAlumno().keySet().iterator();

                                            try {
                                                while (itAsignatura.hasNext()) {
                                                    String keyAsig = (String) itAsignatura.next();
                                                    if (asignatura.equalsIgnoreCase(keyAsig)) { // Comprueba que el alunmo este matriculado en la misma asignatura
                                                        
                                                        if(anyAlumHasAsig == true) anyAlumHasAsig = false;                                                        
                                                        
                                                        System.out.println("Alumno: " + al.getApellidos() + ", " + al.getNombre() + ":");
                                                        
                                                        int[] nota = new int[5];
                                                        boolean rp = false;
                                                        do {

                                                            try {
                                                                System.out.println("Nota de la asignatura: " + tmCCASIGNA.get(keyAsig) + "(" + keyAsig + "): ");
                                                                nota[nroEval] = sc.nextInt();

                                                                if (nota[nroEval] < 0 || nota[nroEval] > 10) {
                                                                    throw new Exception("La nota de la asignatura debe ser de 0 a 10\n");
                                                                }
                                                                rp = false;
                                                            } catch (Exception ex) {
                                                                rp = true;
                                                                System.out.println("Excepción: " + ex.getMessage());
                                                            }

                                                        } while (rp);
                                                        al.getTmAsignaturasAlumno().get(keyAsig).setNotas(nota);
                                                    }

                                                }
                                                
                                            } catch (Exception e) {
                                                System.out.println("Error: " + e.getMessage());
                                            }

                                        }
                                    }
                                } else {
                                    throw new Exception("El código de la asignatura no es correcto. ");
                                }
                                if(anyAlumHasAsig) System.out.println("No existe ningún alumno matriculado en esa asignatura.\n");
                                rept = "N";
                            } catch (Exception ex) {
                                rept = "S";
                                System.out.println("\nHa ocurrido una excepción: " + ex.getMessage());
                            }
                        } while (rept.equalsIgnoreCase("S"));

                        break;
                    case 5:
                        sc.nextLine();
                        System.out.println("\t5. LISTADO DE ALUMNOS DE UN CURSO. DATOS PERSONALES");
                        boolean listado = true;
                        do {
                            try {
                                System.out.println("Indique el código de un curso: ");
                                String curso = sc.nextLine().toUpperCase();
                                if (tmCC.containsKey(curso)) {
                                    Iterator it = lista.iterator();
                                    while (it.hasNext()) {
                                        Persona per = (Persona) it.next();
                                        if (per instanceof Alumno) {
                                            alumn = (Alumno) per;
                                            if (alumn.getCurso().equalsIgnoreCase(curso)) {
                                                System.out.println(alumn.toString());
                                            }
                                        }
                                    }
                                    listado = true;
                                } else {
                                    throw new Exception("Ese curso no existe. ");
                                }
                            } catch (Exception e) {
                                listado = false;
                                System.out.println("Error al introducir el código del curso: " + e.getMessage());
                            }
                        } while (!listado);

                        break;
                    case 6:
                        sc.nextLine();
                        System.out.println("\t6. LISTADO DE ALUMNOS MATRICULADOS EN UNA ASIGNATURA");
                        listado = true;
                        do {
                            try {
                                System.out.println("Indique una Asignatura: ");
                                String asig = sc.nextLine().toUpperCase();
                                if (tmCCASIGNA.containsKey(asig)) {
                                    Iterator it = lista.iterator();
                                    while (it.hasNext()) {
                                        Persona per = (Persona) it.next();
                                        if (per instanceof Alumno) {
                                            alumn = (Alumno) per;
                                            if (alumn.getTmAsignaturasAlumno().containsKey(asig)) {
                                                System.out.println(alumn.toString());
                                            }
                                        }
                                    }
                                    listado = true;
                                } else {
                                    throw new Exception("Esa asignatura no existe. ");
                                }
                            } catch (Exception e) {
                                listado = false;
                                System.out.println("Error al introducir la asignatura: " + e.getMessage());
                            }
                        } while (!listado);

                        break;
                    case 7:
                        System.out.println("\t7. LISTADO DE BOLETINES DE NOTAS DE UNA EVALUACION Y CURSO");
                        boolean correcto = false;
                        do{
                            sc.nextLine();
                        try {
                            System.out.println("Indique el código del curso: ");
                            String codCurso = sc.nextLine();
                            System.out.println("Indique el número de la evaluación: ");
                            int eval = sc.nextInt();
                            Iterator it = lista.iterator();
                            while (it.hasNext()) {
                                Persona per = (Persona) it.next();
                                if (per instanceof Alumno) {
                                    alumn = (Alumno) per;
                                    System.out.println(alumn.boletinNotas(codCurso, eval));
                                }
                            }
                            correcto = true;
                        } catch (InputMismatchException  e) {
                            correcto = false;
                            System.out.println("Ha occurrido una excepción: " + e.getMessage());
                        }
                        }while(! correcto);
                        
                        break;
                    default:
                        break;
                }
            } catch (Exception ex) {
                sc.nextLine();
                System.out.println("Ha ocurrido una excepción en el submenu Alumno: " + ex.getMessage());
            }
        } while (opt != 0);
    }

    /**
     *
     * @return
     */
    public static String getKey() {
        StringBuilder nombreCompleto = new StringBuilder();
        String nombre, apellido;
        sc.nextLine();
        System.out.println("Indica el nombre:");
        nombre = sc.nextLine();
        System.out.println("Indica el apellido:");
        apellido = sc.nextLine();

        nombreCompleto.append(apellido);
        nombreCompleto.append(", ");
        nombreCompleto.append(nombre);
        return nombreCompleto.toString();
    }

   
    /**
     *
     * @param text
     * @param p
     * @param a
     */
    public static void imprimirListados(String text, boolean p, boolean a) {
        System.out.printf(text, getCurso());

        for (Persona persona : lista) {
            if (p && persona instanceof Profesor) {
                  System.out.printf("%s%s, %s\n", "(P)", persona.getApellidos(), persona.getNombre());
            } else if (a && persona instanceof Alumno) {
                Alumno alumno = (Alumno) persona;
                System.out.printf("(%s) %s, %s\n", alumno.getCurso(), alumno.getApellidos(), alumno.getNombre());
            }
        }

    }

   /**
    * 
    * @param key
    * @param lista
    * @return 
    */
    public static boolean contieneKey(String key, ArrayList<Persona> lista){        
        boolean containsKey = false;
        String k;
        if (! lista.isEmpty()) {            
            for (Persona persona : lista) {
                k = persona.getApellidos() + ", " + persona.getNombre();
                if(key.equalsIgnoreCase(k)) containsKey = true;
            }
        }
        return containsKey;
    }
    
    /**
     * 
     * @param key
     * @param lista 
     */
    public static void eliminarObjetoLista(String key, ArrayList<Persona> lista){
        String k;
        
        if (! lista.isEmpty()) {
            for (Persona persona : lista) {
                k = persona.getApellidos() + ", " + persona.getNombre();
                if(key.equalsIgnoreCase(k)){
                    lista.remove(persona);
                    break;
                }
            }
        }
        
    }
    
    /**
     * 
     * @param key
     * @param lista
     * @return 
     */
    public static Persona getObjetoPersonaByKey(String key, ArrayList<Persona> lista){
        String k;
        int id = 0;
        Persona per = new Persona();
        
        if (! lista.isEmpty()) {
            for (Persona persona : lista) {
                
                k = persona.getApellidos() + ", " + persona.getNombre();
                
                if(key.equalsIgnoreCase(k)){
                    per = lista.get(id);
                }
                id++;
            }
        }
        return per;
    }
    
}