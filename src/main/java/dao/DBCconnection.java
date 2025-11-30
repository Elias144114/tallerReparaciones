package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;
/**
 * El metodo para conectar la base de datos al proyecto
 *  
 * @author Elias
 */
public class DBCconnection {
	private static DBCconnection instance;

	Connection conexionMySQL = null;

	private DBCconnection() {
		try {
	


			MysqlDataSource dataSource = new MysqlDataSource();


			
			Properties props = new Properties();
			FileInputStream file = new FileInputStream("src\\main\\resources\\conexion.properties");
			props.load(file);

			dataSource.setUrl(props.getProperty("url"));
			dataSource.setUser(props.getProperty("user"));
			dataSource.setPassword(props.getProperty("password"));
			file.close();


			// 1.4 Main
			conexionMySQL = dataSource.getConnection();
			System.out.println("> Conexión establecida correctamente");
		} catch (SQLException | IOException e) {
			System.err.println("> Error al conectar con mysql: " + e.getMessage());
		}

	}

	public static DBCconnection getInstance() {
		if (instance == null) {
			instance = new DBCconnection();
		}
		return instance;
	}

	public Connection getConnection() {
		return instance.conexionMySQL;
	}
}
