package control;

import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conect.Conexao;
import dao.RelatorioEnviadoDAO;
import dao.RelatorioOriginalDAO;
import model.RelatorioEnviado;
import model.RelatorioOriginal;

public class RelatorioEnviadoControle {

	public static void enviar(RelatorioEnviado e){
		try {
			Conexao.conectar();
			RelatorioEnviadoDAO dao = new RelatorioEnviadoDAO();
			dao.inserir(e);
			Conexao.commit();
			JOptionPane.showMessageDialog(null, "Salvo com sucesso");
		} catch (Exception ex) {
			Conexao.rollback();
			JOptionPane.showMessageDialog(null, "Erro ao tentar salvar: " + ex);
		} finally {
			Conexao.desconectar();
		}
	}
	
	public static DefaultTableModel defaultTableModel(String procurar){
		DefaultTableModel defaultTableModel = new DefaultTableModel(null, new Object[] {
				"id",
				"Nome do Arquivo",
				"ID Usuário",
				"Usuário",
				"Tipo",
				"Matrícula",
				"Nome Completo",
				"E-mail",
				"Fone"
				}){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try {
			Conexao.conectar();
			RelatorioEnviadoDAO dao = new RelatorioEnviadoDAO();
			for (RelatorioEnviado e : dao.lista(procurar)) {
				defaultTableModel.addRow(new Object[] {
					e.getId(),
					e.getNomeArquivo(),
					e.getUsuario().getId(),
					e.getUsuario().getUsuario(),
					e.getUsuario().getTipo(),
					e.getUsuario().getMatricula(),
					e.getUsuario().getNomeCompleto(),
					e.getUsuario().getEmail(),
					e.getUsuario().getFone()
				});
			}
			Conexao.commit();
		} catch (Exception e) {
			Conexao.rollback();
			JOptionPane.showMessageDialog(null, "Erro ao tentar criar tabela: " + e);
		} finally {
			Conexao.desconectar();
		}
		return defaultTableModel;
	}
	
	public static RelatorioEnviado get(int id){
		RelatorioEnviado e = new RelatorioEnviado();
		try {
			Conexao.conectar();
			RelatorioEnviadoDAO dao = new RelatorioEnviadoDAO();
			e = dao.get(id);
			Conexao.commit();
		} catch (Exception ex) {
			Conexao.rollback();
			JOptionPane.showMessageDialog(null, "Erro ao tentar retornar: " + ex);
		} finally {
			Conexao.desconectar();
		}
		return e;
	}
	
	public static void deletar(RelatorioEnviado e){
		try {
			Conexao.conectar();
			RelatorioEnviadoDAO dao = new RelatorioEnviadoDAO();
			dao.delete(e);
			Conexao.commit();
			JOptionPane.showMessageDialog(null, "Deletado com sucesso");
		} catch (Exception ex) {
			Conexao.rollback();
			JOptionPane.showMessageDialog(null, "Erro ao tentar deletar: " + ex);
		} finally {
			Conexao.desconectar();
		}
	}
	
}
