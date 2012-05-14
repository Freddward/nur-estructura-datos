/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import Common.DAO.ArcoDTO;

/**
 *
 * @author Melissa
 */
public interface IDAO {
    
    public Cadena<ArcoDTO> seleccionar(Object llave) throws Exception;
    
    public void insertar(ObjectDTO obj) throws Exception;
    
    public void actualizar(ObjectDTO obj) throws Exception;
    
    public void eliminar(ObjectDTO obj) throws Exception;
}