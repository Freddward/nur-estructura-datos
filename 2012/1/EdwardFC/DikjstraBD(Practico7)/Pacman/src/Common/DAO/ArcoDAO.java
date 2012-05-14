/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.DAO;

import Common.Cadena;
import Common.IDAO;

/**
 *
 * @author EdwardFC
 */
public abstract class ArcoDAO implements IDAO {
    public abstract Cadena<ArcoDTO> seleccionarTodos() throws Exception;
}