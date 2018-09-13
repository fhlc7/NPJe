package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conect.Conexao;
import control.UsuarioControle;
import model.Usuario;

public class UsuarioDAO {
	
	public void inserir(Usuario usuario) throws SQLException{
		String sql = "INSERT INTO `usuario` ("
				+ "`id`, "
				+ "`usuario`, "
				+ "`senha`, "
				+ "`tipo`, "
				+ "matricula, "
				+ "nome_completo, "
				+ "email, "
				+ "fone"
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		int i = 0;
		ps.setObject(++i, usuario.getId());
		ps.setObject(++i, usuario.getUsuario());
		ps.setObject(++i, usuario.getSenha());
		ps.setObject(++i, usuario.getTipo());
		ps.setObject(++i, usuario.getMatricula());
		ps.setObject(++i, usuario.getNomeCompleto());
		ps.setObject(++i, usuario.getEmail());
		ps.setObject(++i, usuario.getFone());
		ps.execute();
		ps.close();
	}
	
	public List<Usuario> lista(String procurar) throws SQLException{
		List<Usuario> list = new ArrayList<Usuario>();
		String sql = "SELECT * FROM Usuario WHERE id LIKE ? OR usuario LIKE ? OR Tipo LIKE ? OR matricula LIKE ? OR nome_completo LIKE ? OR email LIKE ? OR fone LIKE ? ";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		ps.setString(1, "%" + procurar + "%");
		ps.setString(2, "%" + procurar + "%");
		ps.setString(3, "%" + procurar + "%");
		ps.setString(4, "%" + procurar + "%");
		ps.setString(5, "%" + procurar + "%");
		ps.setString(6, "%" + procurar + "%");
		ps.setString(7, "%" + procurar + "%");
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			Usuario usuario = new Usuario();
			int i = 0;
			usuario.setId(rs.getInt(++i));
			usuario.setUsuario(rs.getString(++i));
			usuario.setSenha(rs.getString(++i));
			usuario.setTipo(rs.getString(++i));
			usuario.setMatricula(rs.getString(++i));
			usuario.setNomeCompleto(rs.getString(++i));
			usuario.setEmail(rs.getString(++i));
			usuario.setFone(rs.getString(++i));
			list.add(usuario);
		}
		rs.close();
		ps.close();
		return list;
	}
	
	public Usuario getUsuario(int id) throws SQLException{
		Usuario usuario = new Usuario();
		String sql = "SELECT * FROM Usuario WHERE id = ?";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			int i = 0;
			usuario.setId(rs.getInt(++i));
			usuario.setUsuario(rs.getString(++i));
			usuario.setSenha(rs.getString(++i));
			usuario.setTipo(rs.getString(++i));
			usuario.setMatricula(rs.getString(++i));
			usuario.setNomeCompleto(rs.getString(++i));
			usuario.setEmail(rs.getString(++i));
			usuario.setFone(rs.getString(++i));
		}
		rs.close();
		ps.close();
		return usuario;
	}

	public void alterar(Usuario usuario) throws SQLException{
		String sql = "UPDATE `usuario` SET "
				+ "`usuario` = ?, "
				+ "`Senha` = ?, "
				+ "`Tipo` = ?, "
				+ "matricula = ?, "
				+ "nome_completo = ?, "
				+ "email = ?, "
				+ "fone = ? "
				+ "WHERE id = ?;";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		int i = 0;
		ps.setObject(++i, usuario.getUsuario());
		ps.setObject(++i, usuario.getSenha());
		ps.setObject(++i, usuario.getTipo());
		ps.setObject(++i, usuario.getMatricula());
		ps.setObject(++i, usuario.getNomeCompleto());
		ps.setObject(++i, usuario.getEmail());
		ps.setObject(++i, usuario.getFone());
		ps.setObject(++i, usuario.getId());
		ps.execute();
		ps.close();
	}
	
	public void delete(Usuario usuario) throws SQLException{
		String sql = "DELETE FROM usuario WHERE id = ?;";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		ps.setObject(1, usuario.getId());
		ps.execute();
		ps.close();
	}
	
	public boolean login(Usuario usuario) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
		PreparedStatement ps = Conexao.conexao.prepareStatement(sql);
		ps.setString(1, usuario.getUsuario());
		ps.setString(2, usuario.getSenha());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			UsuarioControle.u = getUsuario(rs.getInt(1));
			return true;
		}
		rs.close();
		ps.close();
		return false;
	}
	
}
