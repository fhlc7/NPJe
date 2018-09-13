package control;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import conect.Conexao;
import dao.RelatorioOriginalDAO;
import dao.UsuarioDAO;
import model.RelatorioOriginal;
import model.Usuario;

public class RelatorioOriginalControle {

	public static void enviar(RelatorioOriginal e){
		try {
			Conexao.conectar();
			RelatorioOriginalDAO dao = new RelatorioOriginalDAO();
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
				}){
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		try {
			Conexao.conectar();
			RelatorioOriginalDAO dao = new RelatorioOriginalDAO();
			for (RelatorioOriginal e : dao.lista(procurar)) {
				defaultTableModel.addRow(new Object[] {
					e.getId(),
					e.getNomeRelatorio()
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
	
	public static RelatorioOriginal get(int id){
		RelatorioOriginal e = new RelatorioOriginal();
		try {
			Conexao.conectar();
			RelatorioOriginalDAO dao = new RelatorioOriginalDAO();
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
	
	public static void deletar(RelatorioOriginal e){
		try {
			Conexao.conectar();
			RelatorioOriginalDAO dao = new RelatorioOriginalDAO();
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
