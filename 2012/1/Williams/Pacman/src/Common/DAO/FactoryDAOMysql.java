/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.DAO;

/**
 *
 * @author EdwardFC
 */
public class FactoryDAOMysql extends FactoryDAO {

    private static FactoryDAOMysql instancia = null;

    public static FactoryDAOMysql getOrCreate() {
        if (instancia == null) {
            instancia = new FactoryDAOMysql();
        }

        return instancia;
    }

    private FactoryDAOMysql() {
    }

    public ArcoDAO newArcoDao() {
        return new ArcoDAOMysql();
    }
}
