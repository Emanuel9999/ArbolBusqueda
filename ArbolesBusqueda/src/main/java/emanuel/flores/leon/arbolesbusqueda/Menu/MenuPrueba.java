/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package emanuel.flores.leon.arbolesbusqueda.Menu;

import emanuel.flores.leon.arbolesbusqueda.AVL;
import emanuel.flores.leon.arbolesbusqueda.ArbolB;
import emanuel.flores.leon.arbolesbusqueda.ArbolBinarioBusqueda;
import emanuel.flores.leon.arbolesbusqueda.ArbolMViasBusqueda;
import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionCantidadClaves;
import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionClaveNoExiste;
import emanuel.flores.leon.arbolesbusqueda.Exception.ExceptionOrdenInvalido;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pc
 */
public class MenuPrueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NullPointerException, ExceptionClaveNoExiste, ExceptionOrdenInvalido, ExceptionCantidadClaves {
        // TODO code application logic here
        Scanner entrada = new Scanner(System.in);
        byte opcion;
        do{
            System.out.println("escoja una opcion numerica"+"\n 1) respuesta1"
                                                        +"\n 2) respuesta2"
                                                        +"\n 3) respuesta de 3 a 5"
                                                        +"\n 4) respuesta de 6 a 7"
                                                        +"\n 5) respuesta 8"
                                                        +"\n 6) respuesta de 10 a 11"
                                                        +"\n 7) salir");
            System.out.println("Elegir Opcion:");
            opcion = entrada.nextByte();
            switch (opcion){
                case 1:
                    respuesta1();
                break;
                case 2:
                    respuesta2();
                break;
                case 3:
                    respuesta3_5();
                break;
                case 4:
                    respuesta6_7();
                break;
                case 5:
                    respuesta8();
                break;
                case 6:
                    respuesta10_11();
                break;
            }
            
        }while (opcion < 7);
         
                                                         
    }
    private static void respuesta10_11() throws ExceptionOrdenInvalido, NullPointerException, ExceptionClaveNoExiste{
        ArbolMViasBusqueda<Integer,Boolean> vias = new ArbolMViasBusqueda<>(4);
        //Insertar y eliminar en un arbol Mvias
        //Elementos insertados 120 200 80 110 98 130 140 70 75 150 560 500 50 400 134 190 72 170 160 158
        vias.insertar(120, false);
        vias.insertar(200, true);
        vias.insertar(80, false);
        
        vias.insertar(110, false);
        vias.insertar(98,  true);
        vias.insertar(130, true);
        vias.insertar(140, false);
        vias.insertar(70, false);
        vias.insertar(75, false);
        vias.insertar(150, true);
        vias.insertar(560, true);
        vias.insertar(500, true);
        vias.insertar(50, false);
        vias.insertar(400, false);
        
        vias.insertar(134, false);
        vias.insertar(190, false);
        vias.insertar(72, true);
        vias.insertar(170, true);
        vias.insertar(160, true);
        vias.insertar(158, true);
        
//        vias.insertar(194, "S1");
//        vias.insertar(196, "S2");
//        vias.insertar(191, "S4");
//        vias.insertar(197, "S3");
        System.out.println("Elementos insertados en el arbol M vias \n "
                + "120 200 80 110 98 130 140 70 75 150 560 500 50 400 134 190 72 170 160 158\n");
        System.out.println("PorNiveles:" + vias.recorridoPorNiveles());
        System.out.println("PreOrden:" +vias.recorridoPreOrden());
        System.out.println("InOrden:" +vias.recorridoInOrden());
        System.out.println("PostOrden:" +vias.recorridoPostOrden());
        System.out.println("Cantidad de Nodos:" + vias.size());
        
        Boolean eliminado = vias.eliminar(170);
        
        System.out.println('\n'+"El valor eliminado 170:" + eliminado + '\n');
        
        System.out.println("PorNiveles:" + vias.recorridoPorNiveles());
        System.out.println("PreOrden:" +vias.recorridoPreOrden());
        System.out.println("InOrden:" +vias.recorridoInOrden());
        System.out.println("PostOrden:" +vias.recorridoPostOrden());
        System.out.println("Cantidad de Nodos:" + vias.size());
        
    }
    private static void respuesta8() throws ExceptionOrdenInvalido{
        ArbolB<Integer, String> vias = new ArbolB<>(5);
        //El metodo insertar de un arbol B
        //Dados los elementos insertar 80 150 40 200 201 100 153 250 254 110 263 120 270 274
        //                             170 180 160
        vias.insertar(80,"A"); vias.insertar(150,"B"); vias.insertar(40,"C");
        vias.insertar(200,"D"); vias.insertar(201,"E"); vias.insertar(100,"F");
        vias.insertar(153,"G"); vias.insertar(250,"H"); vias.insertar(254,"I");
        vias.insertar(110,"J"); vias.insertar(263,"K"); vias.insertar(120,"L");
        vias.insertar(270,"M"); vias.insertar(274,"N"); vias.insertar(170,"O");
        vias.insertar(180,"P"); vias.insertar(160,"Q");
        
        System.out.println("Dados los elementos insertados en el arbolB 80 150 40 200 201 100 153 250 254 110 263 120 270 274 170 180 160");
        System.out.println("Recorrido Por Niveles:"+vias.recorridoPorNiveles());
    }
    private static void respuesta6_7() throws NullPointerException, ExceptionClaveNoExiste{
        //insetar y eliminar en un AVL
        //Dados los elementos insertados 45 70 30 15 7 90 60 110 130
        AVL<Integer,Boolean> arbolAVL = new AVL<>(); 
        arbolAVL.insertar(45, false); arbolAVL.insertar(70, true);
        arbolAVL.insertar(30, true); arbolAVL.insertar(15, false);
        arbolAVL.insertar(7, true); arbolAVL.insertar(90, false);
        arbolAVL.insertar(60, false); arbolAVL.insertar(110, false);
        arbolAVL.insertar(130, false);
        
        System.out.println("Insertar y eliminar en un AVL \n Los elementos insertar en el siguiente Orden\n"
                + "45 70 30 15 7 90 60 110 130 ");
        System.out.println("El recorrido Por niveles:" + arbolAVL.recorridoPorNiveles());
        Boolean w = arbolAVL.eliminar(45);
        System.out.println("El elemento a eliminar 45\n El recorrido PorNiveles:" + arbolAVL.recorridoPorNiveles());
        
    }
    private static void respuesta3_5() throws ExceptionOrdenInvalido{
        //respuesta para la pregunta 3,4 y 5
        //Recorridos postOrden, inOrden y preOrden4
        ArbolB<Integer, String> arbolB = new ArbolB<>(5);
        ArbolMViasBusqueda<Integer, String> arbolM = new ArbolMViasBusqueda<>(5); 
        
        arbolB.insertar(80,"A"); arbolB.insertar(150,"B"); arbolB.insertar(40,"C");
        arbolB.insertar(200,"D"); arbolB.insertar(201,"E"); arbolB.insertar(100,"F");
        arbolB.insertar(153,"G"); arbolB.insertar(250,"H"); arbolB.insertar(254,"I");
        arbolB.insertar(110,"J"); arbolB.insertar(263,"K"); arbolB.insertar(120,"L");
        arbolB.insertar(270,"M"); arbolB.insertar(274,"N"); arbolB.insertar(170,"O");
        arbolB.insertar(180,"P"); arbolB.insertar(160,"Q");
        
        arbolM.insertar(80,"A"); arbolM.insertar(150,"B"); arbolM.insertar(40,"C");
        arbolM.insertar(200,"D"); arbolM.insertar(201,"E"); arbolM.insertar(100,"F");
        arbolM.insertar(153,"G"); arbolM.insertar(250,"H"); arbolM.insertar(254,"I");
        arbolM.insertar(110,"J"); arbolM.insertar(263,"K"); arbolM.insertar(120,"L");
        arbolM.insertar(270,"M"); arbolM.insertar(274,"N"); arbolM.insertar(170,"O");
        arbolM.insertar(180,"P"); arbolM.insertar(160,"Q");
        
        System.out.println("Recorridos de las Arboles\n");
        System.out.println( "Elementos insertados en el siguiente orden en arbolB y Mvias \n"
                + "80 150 40 200 201 100 153 250 254 110 263 120 270 274 170 180 160\n");
        
        //Los recorridos para ArbolM comienza a partir de la linea 370
        System.out.println("Recorridos PostOrden para arbolB:"+arbolB.recorridoPostOrden());
        System.out.println("Recorridos PostOrden para arbolM:"+arbolM.recorridoPostOrden());
        
        System.out.println("Recorridos PreOrden para arbolB:"+arbolB.recorridoPreOrden());
        System.out.println("Recorridos PreOrden para arbolM:"+arbolM.recorridoPreOrden());
        
        System.out.println("Recorridos InOrden para arbolB:"+arbolB.recorridoInOrden());
        System.out.println("Recorridos InOrden para arbolM:"+arbolM.recorridoInOrden());
        
    }
    private static void respuesta2(){
        //Recorrido postOrden iterativo
        ArbolBinarioBusqueda<Integer,String> arbol1 = new ArbolBinarioBusqueda<>();
        arbol1.insertar(50,"Emanuel");
        arbol1.insertar(80,"Veronica");
        arbol1.insertar(100,"Vanessa");
        arbol1.insertar(30,"Natalia");
        arbol1.insertar(35,"Carla");
        arbol1.insertar(95,"Ana");
        arbol1.insertar(90,"Stive");
        arbol1.insertar(15,"Nineth");
        arbol1.insertar(60,"Alberto");
        arbol1.insertar(110,"Alvaro");
        arbol1.insertar(93,"Julia");
        arbol1.insertar(20,"Ferran"); 
        
        //El metodo comienza a partir de la linea 273 de la clase a la que pertenece
        System.out.println("Recorrido PosOrden\n"
                + "Elementos insertados en el siguiente orden en arbol \n"
                + "50 80 100 30 35 95 90 15 60 110 93 20\n");
        System.out.println("Recorrido de en PosOrden\n");
        System.out.println("Recorrido en postOrden:"+arbol1.recorridoPostOrden());
        System.out.println("Recorrido en postOrden iterativo:"+arbol1.recorridoPostOrdenIterativo());
        
    }
    private static void respuesta1() throws ExceptionCantidadClaves{
        //Reconstruccion de arbol
        ArbolBinarioBusqueda<Integer,String> arbol1 = new ArbolBinarioBusqueda<>();
        ArbolBinarioBusqueda<Integer,String> arbolDeprueba = new ArbolBinarioBusqueda<>();
        
        List<String>  valoresInOrden = new ArrayList<>();
        List<String>  valoresPostOrden = new ArrayList<>();

        

        arbol1.insertar(70,"Emanuel");
        arbol1.insertar(30,"Veronica");
        arbol1.insertar(100,"Vanessa");
        arbol1.insertar(85,"Natalia");
        arbol1.insertar(60,"Carlos");
        arbol1.insertar(20,"Juan");
        arbol1.insertar(110,"Rufino");
        arbol1.insertar(75,"Nineth");
        arbol1.insertar(93,"Alberto");
        arbol1.insertar(25,"Alvaro");
        arbol1.insertar(27,"Julio");
        arbol1.insertar(80,"Ariana");
        arbol1.insertar(22,"Andrea");
 
        Integer numero; 
        for (int i=0; i<arbol1.size(); i++){
            numero = arbol1.recorridoInOrden().get(i);
            valoresInOrden.add(arbol1.buscar(numero));
            
            numero =arbol1.recorridoPostOrden().get(i);
            valoresPostOrden.add(arbol1.buscar(numero));
        }
        //El metodo comienza a partir de la linea 309 de la clase a la que pertenece
        arbolDeprueba.reconstruirArbol(arbol1.recorridoInOrden(), arbol1.recorridoPostOrden(),
                                       valoresInOrden, valoresPostOrden);
        System.out.println("Reconstruccion del arbol binario\n"
                + "Elementos insertados en el siguiente orden en arbol \n"
                + "70 30 100 85 60 20 110 75 93 25 27 80 22\n");
        System.out.println("Recorridos del arbol: 'arbol1' ");
        System.out.println("Recorrido InOrden:"+arbol1.recorridoInOrden());
        System.out.println("Recorrido PosOrden:"+arbol1.recorridoPostOrden());
        System.out.println("Recorrido porNivel:" +arbol1.recorridoPorNiveles());
        System.out.println("Cantidad de nodos:" + arbol1.size());
        
        arbol1.vaciaArbol();
        
        System.out.println("Recorridos del arbol de binario vacio: 'arbol1'");
        System.out.println("Recorrido InOrden:"+arbol1.recorridoInOrden());
        System.out.println("Recorrido PosOrden:"+arbol1.recorridoPostOrden());
        System.out.println("Recorrido porNivel:" + arbol1.recorridoPorNiveles());
        System.out.println("Cantidad de nodos:" + arbol1.size());
        
        System.out.println("Recorridos del arbol de binario reconstruido en el: 'arbolDePrueba'");
        System.out.println("Recorrido InOrden:"+arbolDeprueba.recorridoInOrden());
        System.out.println("Recorrido PosOrden:"+arbolDeprueba.recorridoPostOrden());
        System.out.println("Recorrido porNivel:" + arbolDeprueba.recorridoPorNiveles());
        System.out.println("Cantidad de nodos:" + arbolDeprueba.size());
        
        
        
        
    }
   
}
