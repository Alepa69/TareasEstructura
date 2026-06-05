
package modelo;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class HeapSort {
        public void heapSort(int data[]){
        int size = data.length;
        for(int i = size/2 - 1; i >= 0; i--){
            organizarHeap(i, data, size);
        }
        
        for(int i = data.length - 1; i >=0; i--){
            int datoTemporal = data[0];
            data[0] = data[i];
            data[i] = datoTemporal;
            size--;
            organizarHeap(0, data, size);
        }
        
    }
    
    public int hijoIzquierdo(int i){
        return 2 * i + 1;
    }
    
    public int hijoDerecho(int i){
        return 2 * i + 2;
    }
    
    
    public void  organizarHeap(int i, int data[], int size){
        int idxElementoMayor = i;
        int idxIzquierdo = hijoIzquierdo(i);
        int idxDerecho = hijoDerecho(i);
        
        if (idxIzquierdo < size && data[idxIzquierdo] > data[idxElementoMayor]) {
            idxElementoMayor = idxIzquierdo;
        }
        
        if (idxDerecho < size && data[idxDerecho] > data[idxElementoMayor]) {
            idxElementoMayor = idxDerecho;
        }
        
        if (idxElementoMayor != i) {
            int datoTemporal = data[i];
            data[i] = data[idxElementoMayor];
            data[idxElementoMayor] = datoTemporal; 
            
            organizarHeap(idxElementoMayor, data, size);
        }
    }
    
    public <T extends Comparable>void heapSortObj(ArrayList data){
        int size = data.size();
        for(int i = size/2 - 1; i >= 0; i--){
            organizarHeapObj(i, data, size);
        }
        
        for(int i = data.size() - 1; i >=0; i--){
            T datoTemporal = (T) data.get(0);
            data.set(0, data.get(i));
            data.set(i, datoTemporal);
            size--;
            organizarHeapObj(0, data, size);
        }
    }
    
    public <T extends Comparable>void organizarHeapObj(int i, ArrayList data, int size){
        int idxElementoMayor = i;
        int idxIzquierdo = hijoIzquierdo(i);
        int idxDerecho = hijoDerecho(i);
        
        if (idxIzquierdo < size && ((T)data.get(idxIzquierdo)).compareTo(data.get(idxElementoMayor)) > 0) {
            idxElementoMayor = idxIzquierdo;
        }
        
        if (idxDerecho < size && ((T)data.get(idxDerecho)).compareTo(data.get(idxElementoMayor)) > 0) {
            idxElementoMayor = idxDerecho;
        }
        
        
        
        if (idxElementoMayor != i) {
            T datoTemporal = (T) data.get(i);
            data.set(i, data.get(idxElementoMayor));
            data.set(idxElementoMayor, datoTemporal);
            
            organizarHeapObj(idxElementoMayor, data, size);
        }
    }
    
    
    public <T extends Comparable>void heapSortObjDes(ArrayList data){
        int size = data.size();
        for(int i = size/2 - 1; i >= 0; i--){
            organizarHeapObjDes(i, data, size);
        }
        
        for(int i = data.size() - 1; i >=0; i--){
            T datoTemporal = (T) data.get(0);
            data.set(0, data.get(i));
            data.set(i, datoTemporal);
            size--;
            organizarHeapObjDes(0, data, size);
        }
    }
    
    public <T extends Comparable>void organizarHeapObjDes(int i, ArrayList data, int size){
        int idxElementoMayor = i;
        int idxIzquierdo = hijoIzquierdo(i);
        int idxDerecho = hijoDerecho(i);
        
        if (idxIzquierdo < size && ((T)data.get(idxIzquierdo)).compareTo(data.get(idxElementoMayor)) < 0) {
            idxElementoMayor = idxIzquierdo;
        }
        
        if (idxDerecho < size && ((T)data.get(idxDerecho)).compareTo(data.get(idxElementoMayor)) < 0) {
            idxElementoMayor = idxDerecho;
        }
        
        
        
        if (idxElementoMayor != i) {
            T datoTemporal = (T) data.get(i);
            data.set(i, data.get(idxElementoMayor));
            data.set(idxElementoMayor, datoTemporal);
            
            organizarHeapObjDes(idxElementoMayor, data, size);
        }
    }
}
