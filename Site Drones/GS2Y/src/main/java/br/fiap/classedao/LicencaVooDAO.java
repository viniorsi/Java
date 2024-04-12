package br.fiap.classedao;

import java.sql.Date;
import java.sql.SQLException;

import br.fiap.dao.conexao.Conexao;
import br.fiap.modelos.DroneModelo;
import br.fiap.modelos.LicencaVooModel;

public class LicencaVooDAO extends DAO {
	public void inserir(LicencaVooModel licencaVoo) {
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		sql = "insert into licenca_VOO VALUES(idLicenca_voo_seq.nextval,?,?,?,? )";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, licencaVoo.getDrone().getId());
			ps.setString(2,licencaVoo.getLicencaVoo());
			ps.setDate(3,Date.valueOf(licencaVoo.getDataValidade()) );
			ps.setDate(4,Date.valueOf(licencaVoo.getDataEmissao()) );
			
			
			ps.execute();
			ps.close();
			conexao.desconectar();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Erro ao inserir licenca de voo\n" + e);
		}
	}
	
	public LicencaVooModel pesquisar(Integer idDrone) {
		Conexao conexao = new Conexao();
		connection = conexao.conectar();
		LicencaVooModel licenca = null;
		DroneModelo drone = new DroneModelo();
		drone.setId(idDrone);

		sql = "SELECT * FROM  licenca_VOO WHERE idDrone = ?";
		try {
			ps = connection.prepareStatement(sql);
			ps.setInt(1, idDrone);
			rs = ps.executeQuery();
			while (rs.next()) {
				licenca = new LicencaVooModel();
				licenca.setId(rs.getInt("idLicenca"));
				licenca.setIdDrone(drone);
				licenca.setLicencaVoo(rs.getString("licencaVoo"));
				licenca.setDataValidade(rs.getDate("dataValidade").toLocalDate());
				licenca.setDataEmissao(rs.getDate("dataEmissao").toLocalDate());
			
			}
			ps.close();
			rs.close();
			conexao.desconectar();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Erro encontrar licenca " + e);
		}

		return licenca;
	}
	
	
}
