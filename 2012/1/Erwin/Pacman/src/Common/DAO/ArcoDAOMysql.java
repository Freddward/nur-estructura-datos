/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.DAO;

import Common.Cadena;
import Common.ConexionMySql;
import Common.ObjectDTO;

import java.sql.ResultSet;
import java.util.Iterator;
import org.apache.log4j.Logger;
import pacman.MiFrame;

public class ArcoDAOMysql extends ArcoDAO {

    private static Logger logger = Logger.getLogger(MiFrame.class);

    public ArcoDAOMysql() {
    }

    public Cadena<ArcoDTO> seleccionar(Object llave) throws Exception {
        Cadena<ArcoDTO> lista = new Cadena<ArcoDTO>();
        String id = (String)llave;
        
        ConexionMySql con = (ConexionMySql) ConexionMySql.getOrCreate();
        con.conectar();
        String query = "SELECT `Grafo`,`Origen`,`Destino`"
                + " FROM `ArcoGrafo` "
                + " WHERE `Grafo` = "+id;

        ResultSet rs = con.ejecutar(query);

        if (rs == null) {
            logger.error("Error al traer datos.");
            throw new Exception("Error al traer datos");
        }

        if (!rs.next()) {
            logger.error("No se encontro objeto Arco con id: " + id);
            throw new Exception("No se encontro objeto Arco con id: " + id);
        }
        while (rs.next()) {
            String grafo = rs.getString("Grafo");
            String origen = rs.getString("Origen");
            String destino = rs.getString("Destino");

            ArcoDTO dto = new ArcoDTO(grafo, origen, destino);
            lista.insertar(dto);
        }

        return lista;
    }

    public void insertar(ObjectDTO obj) throws Exception {
        ArcoDTO objArco = (ArcoDTO) obj;

        ConexionMySql con = (ConexionMySql) ConexionMySql.getOrCreate();
        String query = "INSERT INTO `ArcGraph` "
                + " (Graph, Origin, Destiny) VALUES "
                + "( '" + objArco.getGrafo() + "' "
                + " ,'" + objArco.getOrigen() + "',"
                + " ," + objArco.getDestino() + ")";
        logger.debug("EXEC Mysql: " + query);
        int upd = con.ejecutarInsert(query);

        if (upd == 0) {
            throw new Exception("Error: no ha podido insertar los datos");
        }

        objArco.setGrafo("" + upd);
    }

    public void actualizar(ObjectDTO obj) throws Exception {
        ArcoDTO objArco = (ArcoDTO) obj;

        ConexionMySql con = (ConexionMySql) ConexionMySql.getOrCreate();
        String query = "UPDATE ArcGraph SET "
                + " Graph = '" + objArco.getGrafo() + "' "
                + " ,Origin = '" + objArco.getOrigen() + "' "
                + " ,Destiny = '" + objArco.getDestino() + "' "
                + " WHERE Origin = " + objArco.getOrigen();
        logger.debug("EXEC Mysql: " + query);
        int upd = con.ejecutarSimple(query);

        if (upd == 0) {
            throw new Exception("Error: no ha podido actualizar los datos");
        }
    }

    public void eliminar(ObjectDTO obj) throws Exception {
        ArcoDTO objArco = (ArcoDTO) obj;

        if (objArco == null) {
            throw new Exception("El objeto pasado es nulo");
        }
        ConexionMySql con = (ConexionMySql) ConexionMySql.getOrCreate();
        String query = "DELETE FROM `Arco` "
                + " WHERE Graph = " + objArco.getGrafo();
        logger.debug("EXEC Mysql: " + query);
        int upd = con.ejecutarSimple(query);

        if (upd == 0) {
            throw new Exception("Error: no ha podido elimiinar los datos");
        }
    }

    public Cadena<ArcoDTO> seleccionarTodos() throws Exception {

        Cadena<ArcoDTO> lista = new Cadena<ArcoDTO>();

        ConexionMySql con = (ConexionMySql) ConexionMySql.getOrCreate();
        con.conectar();
        String query = "SELECT `Graph`,`Origin`,`Destiny`"
                + " FROM `ArcGraph` ORDER BY `Graph`";

        ResultSet rs = con.ejecutar(query);

        if (rs == null) {
            logger.error("Error al traer datos.");
            throw new Exception("Error al traer datos");
        }

        while (rs.next()) {
            String grafo = rs.getString("Graph");
            String origen = rs.getString("Origin");
            String destino = rs.getString("Destiny");

            ArcoDTO dto = new ArcoDTO(grafo, origen, destino);
            lista.insertar(dto);
        }

        logger.info("Se obtuvieron " + lista.tamano() + " registros");

        return lista;
    }
}
