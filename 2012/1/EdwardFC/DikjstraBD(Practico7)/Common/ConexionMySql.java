/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Common;

import java.sql.*;
import org.apache.log4j.Logger;
import pacman.MiFrame;


/**
 *
 * @author EdwardFC
 */
public class ConexionMySql extends Conexion {
    private static Logger logger = Logger.getLogger(MiFrame.class);

    public static Conexion getOrCreate() {
        if (singleton == null) {
            singleton = new ConexionMySql();
            logger.info("Se inicializa la conexión");
        }
        return singleton;
    }

    private ConexionMySql() {
        this.host = "localhost";
        this.database = "Conexiones";
        this.instance = "";
        this.port = 3306;
        this.username = "root";
        this.password = "root";
    }

    public void conectar() {
        if (this.estaConectado())
            return;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:mysql://" + this.host + "/" + this.database;
        try {
            this.con = 
                    DriverManager.getConnection(url, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Se conectó con base de datos: "+database);
    }

    public void comenzarTransaccion() {
        if (!this.estaConectado())
            this.conectar();

        try {
            con.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void terminarTransaccion() {
        try {
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void desconectar() {

        try {
            if (this.estaConectado())
                con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet ejecutar(String query) {
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            logger.info("Se ejecuta la consulta especificada en el query");
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public boolean estaConectado() {
        if (this.con == null)
            return false;
        try {
            if (this.con.isClosed())
                return false;
        } catch (SQLException e) {
            this.con = null;
            return false;
        }
        return true;
    }

    public int ejecutarSimple(String query) {
        try {
            Statement stmt = con.createStatement();
            int nb = stmt.executeUpdate(query);
            return nb;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int ejecutarInsert(String query) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}

