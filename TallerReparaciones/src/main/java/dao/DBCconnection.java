package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBCconnection {
	private static DBCconnection instance;

	Connection conexionMySQL = null;

	private DBCconnection() {
		try {
			// Instanciar un datasource con mysql para que nos devuelva una conexion

			// 1.1 Pasarle las propiedades a pelo
			MysqlDataSource dataSource = new MysqlDataSource();
//				dataSource.setServerName("localhost");
//				dataSource.setPortNumber(3306);
//				dataSource.setDatabaseName("prueba");
//				dataSource.setUser("root");
//				dataSource.setPassword("root");

			// 1.2 Hacer con un FileInputStream
			Properties props = new Properties();
			FileInputStream file = new FileInputStream("src\\main\\resources\\conexion.properties");
			props.load(file);

			dataSource.setUrl(props.getProperty("url"));
			dataSource.setUser(props.getProperty("user"));
			dataSource.setPassword(props.getProperty("password"));
			file.close();

			// 1.3 Cargar manualmente el driver (NO ACONSEJADO)
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				conexion = DriverManager.getConnection("jdbc:mysql://localhost/tallerdereparaciones", "root", "root");

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
