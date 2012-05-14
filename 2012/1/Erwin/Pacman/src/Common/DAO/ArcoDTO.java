/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.DAO;

import Common.ObjectDTO;
import java.util.Date;


public class ArcoDTO extends ObjectDTO {

    private String grafo;
    private String origen;
    private String destino;

    public ArcoDTO() {
    }

    public ArcoDTO(String _grafo, String _origen, String _destino) {
        this.grafo = _grafo;
        this.origen = _origen;
        this.destino = _destino;
    }

    @Override
    public Object obtenerColumna(int col) {
        switch (col) {
            case 0:
                return this.grafo;
            case 1:
                return this.origen;
            case 2:
                return this.destino;
            default:
                return null;
        }
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getGrafo() {
        return grafo;
    }

    public void setGrafo(String grafo) {
        this.grafo = grafo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

}
