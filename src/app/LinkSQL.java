package app; /**
 * Created by Bloody on 24/05/2017.
 */

import java.sql.*;

public class LinkSQL {
    private Connection con;
    private Statement stmt;

    private LinkSQL() {
    }

    private static LinkSQL INSTANCE = new LinkSQL();

    public static LinkSQL getInstance() {
        return INSTANCE;
    }

    public void connexionServeur(String nomUtilisateur, String MotDePasse) {

        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@iutdoua-oracle.univ-lyon1.fr:1521:orcl", nomUtilisateur, MotDePasse);
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeRequete(String requete) {
        try {
            return stmt.executeQuery(requete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}