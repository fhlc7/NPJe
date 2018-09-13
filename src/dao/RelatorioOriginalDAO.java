package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conect.Conexao;
import model.RelatorioOriginal;
import model.Usuario;

public class RelatorioOriginalDAO {

	public void inserir(RelatorioOriginal e) throws SQLException{
		String sql = "INSERT INTO `relatorio_original`(`nome_relatorio`, `arquivo`) VALUES (?, ?);";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		int i = 0;
		ps.setObject(++i, e.getNomeRelatorio());
		ps.setObject(++i, e.getArquivoInput());
		ps.execute();
		ps.close();
	}
	
	public void delete(RelatorioOriginal e) throws SQLException{
		String sql = "DELETE FROM relatorio_original WHERE id = ?;";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		ps.setObject(1, e.getId());
		ps.execute();
		ps.close();
	}

	public RelatorioOriginal get(int id) throws SQLException{
		RelatorioOriginal e = new RelatorioOriginal();
		String sql = "SELECT * FROM relatorio_original WHERE id = ?";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			int i = 0;
			e.setId(rs.getInt(++i));
			e.setNomeRelatorio(rs.getString(++i));
			e.setArquivoInput(rs.getBinaryStream(++i));
			e.setArquivoOutput(rs.getBytes(i));
		}
		rs.close();
		ps.close();
		return e;
	}

	public List<RelatorioOriginal> lista(String procurar) throws SQLException{
		List<RelatorioOriginal> list = new ArrayList<RelatorioOriginal>();
		String sql = "SELECT * FROM relatorio_original WHERE id LIKE ? OR nome_relatorio LIKE ? ORDER BY nome_relatorio ASC;";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		ps.setString(1, "%" + procurar + "%");
		ps.setString(2, "%" + procurar + "%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			RelatorioOriginal e = new RelatorioOriginal();
			int i = 0;
			e.setId(rs.getInt(++i));
			e.setNomeRelatorio(rs.getString(++i));
			e.setArquivoInput(rs.getBinaryStream(++i));
			e.setArquivoOutput(rs.getBytes(i));
			list.add(e);
		}
		rs.close();
		ps.close();
		return list;
	}
	
}
