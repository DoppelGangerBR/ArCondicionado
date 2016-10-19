package Model;

import java.sql.*;

public class Conexao {
	private Connection con;
	private PreparedStatement prs;

	public Conexao() {
		try {

			String url = "jdbc:postgresql://localhost:5432/ArCondicionado";
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, "postgres", "123");

			System.out.println(" Conexão filezinha");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sua Conexao deu falha");
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void updateSql(String sql) {
		try {
			prs = con.prepareStatement(sql);
			prs.executeUpdate();
		} catch (SQLException e) {
			try {
				prs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public ResultSet executeSql(String sql) {
		ResultSet rs = null;
		try {
			prs = con.prepareStatement(sql);
			rs = prs.executeQuery();
		} catch (SQLException e) {
			try {
				prs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return rs;
	}

	public Connection getConnetion() {
		return con;
	}

	public void fecharConexao() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}