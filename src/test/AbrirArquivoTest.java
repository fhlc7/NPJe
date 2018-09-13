package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conect.Conexao;

public class AbrirArquivoTest {

	public static void main(String[] args) {
		/*
		try {// os arquivos não podem ter espaço
			Runtime.getRuntime().exec("cmd.exe /C start WINWORD.exe \\\\FHLC-PC\\Users\\FHLC\\Desktop\\PROCURAÇÃO_NPJ.docx");
		} catch (IOException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}*/
		
		getFile(1);
		
	}
	
	public static File getFile(int id) {
		/*Connection c = Conexao.getConnection();// busca uma conexao com o banco
		File f = null;
		try {
			PreparedStatement ps = c.prepareStatement("SELECT id, arquivo FROM marcielisom WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				byte[] bytes = rs.getBytes("arquivo");
				String nome = "deu certo.docx";

				// converte o array de bytes em file
				f = new File("C:\\Users\\FHLC\\Desktop\\Renato\\Enviado\\" + nome);
				FileOutputStream fos = new FileOutputStream(f);
				fos.write(bytes);
				fos.close();
			}
			rs.close();
			ps.close();
			c.close();
			return f;
		} catch (SQLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}*/
		return null;
	}
	
}
