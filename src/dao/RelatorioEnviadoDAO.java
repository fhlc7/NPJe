package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import conect.Conexao;
import control.UsuarioControle;
import model.RelatorioEnviado;
import model.RelatorioOriginal;

public class RelatorioEnviadoDAO {

	public void inserir(RelatorioEnviado e) throws SQLException{
		String sql = "INSERT INTO `relatorio_enviado`(`nome_arquivo`, `arquivo`, id_usuario) VALUES (?, ?, ?);";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		int i = 0;
		ps.setObject(++i, e.getNomeArquivo());
		ps.setObject(++i, e.getArquivoInput());
		ps.setObject(++i, e.getUsuario().getId());
		ps.execute();
		ps.close();
	}
	
	public void delete(RelatorioEnviado e) throws SQLException{
		String sql = "DELETE FROM relatorio_enviado WHERE id = ?;";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		ps.setObject(1, e.getId());
		ps.execute();
		ps.close();
	}

	public RelatorioEnviado get(int id) throws SQLException{
		RelatorioEnviado e = new RelatorioEnviado();
		String sql = "SELECT * FROM relatorio_enviado WHERE id = ?";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			int i = 0;
			e.setId(rs.getInt(++i));
			e.setNomeArquivo(rs.getString(++i));
			e.setArquivoInput(rs.getBinaryStream(++i));
			e.setArquivoOutput(rs.getBytes(i));
			UsuarioDAO d = new UsuarioDAO();
			e.setUsuario(d.getUsuario(rs.getInt(++i)));
		}
		rs.close();
		ps.close();
		return e;
	}

	public List<RelatorioEnviado> lista(String procurar) throws SQLException{
		List<RelatorioEnviado> list = new ArrayList<RelatorioEnviado>();
		String sql = "SELECT * FROM relatorio_enviado WHERE id LIKE ? OR nome_arquivo LIKE ? OR id_usuario IN (SELECT id FROM usuario WHERE id LIKE ? OR usuario LIKE ? OR tipo LIKE ? OR matricula LIKE ? OR nome_completo LIKE ? OR email LIKE ? OR fone LIKE ?) ORDER BY id DESC;";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		for (int i = 1; i <= 9; i++) {
			ps.setString(i, "%" + procurar + "%");
		}
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			RelatorioEnviado e = new RelatorioEnviado();
			int i = 0;
			e.setId(rs.getInt(++i));
			e.setNomeArquivo(rs.getString(++i));
			e.setArquivoInput(rs.getBinaryStream(++i));
			e.setArquivoOutput(rs.getBytes(i));
			UsuarioDAO d = new UsuarioDAO();
			e.setUsuario(d.getUsuario(rs.getInt(++i)));
			list.add(e);
		}
		rs.close();
		ps.close();
		return list;
	}
	
}
