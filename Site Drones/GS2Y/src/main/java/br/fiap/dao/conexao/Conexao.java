package br.fiap.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private final String url = "jdbc:oracle:thin:@XXXXXX";
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String user = "XXXXXX";
	private final String password = "XXXXX";
	private Connection conexao;

	public Connection conectar() {
		try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar o driver");
		} catch (SQLException e) {
			System.out.println("Erro ao estabelecer conexão com o banco de dados\n" + e);
		}

		return conexao;
	}

	public void desconectar() {
		try {
			conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro ao desconectar do banco de dados\n" + e);
		}
	}
}
