/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MEMO0464
 */
public class Grafo {
    NodoVertice vertice;
    NodoVertice Ini;
    NodoVertice Fin;
    int i=0;
    
    public Grafo(){
        vertice = null;
    }
    
    public boolean insertarVertice(char dato){
        NodoVertice nuevo = new NodoVertice(dato);
        if(nuevo == null)return false;
        
        if(vertice == null){
            vertice = nuevo;
            i++;
            return true;
        }
        //el nuevo se enlaza al final de la lista de VERTICE
        irUltimo();
        vertice.sig = nuevo;
        nuevo.ant = vertice;
        i++;
        return true;
        
    }

    private void irUltimo() {
       while(vertice.sig != null){
           vertice = vertice.sig;       
       }
    }
    
    private void irPrimero(){
        while(vertice.ant != null){
            vertice = vertice.ant;
        }
    }
    
    private NodoVertice buscarVertice(char dato){
        if(vertice == null) return null;
        irPrimero();
        
        for(NodoVertice buscar = vertice; buscar != null; buscar = buscar.sig){
            if(buscar.dato == dato){
                return buscar;
            }
        }
        return null;
    }
    
    public boolean insertarArista(char origen, char destino){
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);
        
        if(nodoOrigen == null || nodoDestino == null){
            return false;
        }
        return nodoOrigen.insertarArista(nodoDestino);
    }
    
    public boolean eliminarArista(char origen, char destino){
        NodoVertice nodoOrigen = buscarVertice(origen);
        NodoVertice nodoDestino = buscarVertice(destino);
        if(nodoOrigen == null || nodoDestino == null){
            return false;
        }
        return nodoOrigen.eliminarArista(nodoDestino);
    }
    
    public boolean unSoloVertice(){
        return vertice.ant == null && vertice.sig == null;
    }
    
    public boolean elminarVertice(char dato){
        if(vertice == null) return false;
        NodoVertice temp = buscarVertice(dato);
        if(temp == null) return false;
        
        //1 que el vertice No tenga aristas a otros vertices
        if(temp.arista != null)return false;
        //2 que otros vertices No tengan aristas a este vertice a eliminar
        quitaAristaDeOtrosVertice(temp);
        //esta temp en el primero
        if(temp == vertice){
            if(unSoloVertice()) vertice = null;
            else{
                vertice = temp.sig;
                temp.sig.ant = temp.sig = null;
            }
            i--;
            return true;
        }
        //esta en el ultimo
        if(temp.sig == null){
            temp.ant.sig = temp.ant = null;
            i--;
            return true;
        }
        //temp esta en medio
        temp.ant.sig = temp.sig;
        temp.sig.ant = temp.ant;
        temp.sig = temp.ant = null;
        i--;
        return true;
    }

    private void quitaAristaDeOtrosVertice(NodoVertice NodoEliminar) {
        irPrimero();
        for(NodoVertice buscar  = vertice; buscar != null; buscar = buscar.sig){
            buscar.eliminarArista(NodoEliminar);
        }
    }
    
    public String matriz(){ 
        
        int con=0;
        if(vertice == null) return null;
          String[][] m = new String [i][i];

            for(int x = 0; x<m.length; x++){
                for(int y = 0; y<m.length; y++){
                m[x][y]=0+"";
                }   
            }
            irPrimero();
            for(int t = 0; t<m.length; t++){
                for(int s = 0; t<m.length; s++){
            if(vertice.arista == null){
                t++;
                s=0;
                vertice = vertice.sig;
            }else{
                m[t][s] ="X";
                vertice.arista = vertice.arista.abajo; 
            }    
           }   
          }     

        return mostrarM(m);
    }
    
    public String mostrarM(String[][] cad){
        String cade="";
        for(int x = 0; x<cad.length; x++){
                for(int y = 0; y<cad.length; y++){
                    cade+= "["+ cad[x][y]+"]";
                }
                cade+="\n";
        }
        return cade;
    }
    
    public String Camino(){
       
        String Cadena="";

        if(vertice == null){
            return "No hay nodos";
        }
        if(vertice == vertice){
            return "- " + vertice.dato + " -";
        }
        for(NodoVertice temp = vertice; temp.sig!=null; temp=temp.sig){
            Cadena+=" - "+temp.dato;
        }
        return Cadena+" - "+vertice.dato+" -";

    }
    
    public String ListaAdy(char dato){
        NodoVertice N = buscarVertice(dato);
        String Cadena = "";
        if(N.arista == null){  // no hay aristas en el vertice
            Cadena = "Nodo: " + N.dato + "\n"+ "No tiene aristas";
            return Cadena;
        }
        
        if(N.arista.abajo == null){ // una sola arista en el vertice
            Cadena = "Nodo: "+  N.dato+"\n - "+N.arista.direccion.dato+" -";
            return Cadena;
        }
        
        Cadena+="Nodo: "+N.dato+"\n- ";
        
        for(NodoArista temp = N.arista; temp != null; temp = temp.abajo){ // mas de una aristaaaa
            
            Cadena += temp.direccion.dato+" - ";
            
        }
       return Cadena;
    }
}
