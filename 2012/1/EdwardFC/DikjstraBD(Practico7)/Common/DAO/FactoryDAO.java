/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common.DAO;

/**
 *
 * @author EdwardFC
 */
public abstract class FactoryDAO {

    private static FactoryDAO instancia = null;

    public static FactoryDAO getOrCreate() {
        if (instancia == null) {
            instancia = FactoryDAOMysql.getOrCreate();
        }

        return instancia;
    }
    public abstract ArcoDAO newArcoDao();
}
