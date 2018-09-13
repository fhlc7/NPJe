package control;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FHLC {

	private static InputStream inputStream;
	private static String nomeArquivo;
	
	public static InputStream getInputStream() {
		return inputStream;
	}

	public static void setInputStream(InputStream inputStream) {
		FHLC.inputStream = inputStream;
	}
	
	public static String getNomeArquivo() {
		return nomeArquivo;
	}

	public static void setNomeArquivo(String nomeArquivo) {
		FHLC.nomeArquivo = nomeArquivo;
	}

	public static void escolherArquivo() {
		JFileChooser jFileChooser = new JFileChooser();
		File file = new File(System.getProperty("user.home"));
		jFileChooser.setCurrentDirectory(file);
		int resultado = jFileChooser.showSaveDialog(null);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			File file2 = jFileChooser.getSelectedFile();
			setNomeArquivo(file2.getName().replaceAll(" ", "_"));
			FileInputStream fileInputStream = null;
			try {
				fileInputStream = new FileInputStream(file2);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao tentar setar fileInputStream: " + e);//e.printStackTrace();
			}
			setInputStream(fileInputStream);
		} else if (resultado == JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Nenhum arquivo selecionado");
		}
	}
	
	public static void baixarVisualizar(String na, byte[] bs) {
		try {
			String d = "C:/Renatorio_Calango";
			String dc = d + "/" + na;
			File f = new File(d);
			if (!f.exists()) {
				f.mkdir();
			};
			f = new File(dc);
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(bs);
			fos.close();
			//Runtime.getRuntime().exec("cmd.exe /C " + dc);//Runtime.getRuntime().exec("cmd.exe /C start " + dc);
			Desktop.getDesktop().open(f);//java.awt.Desktop.getDesktop().open(new File(dc));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao tentar Baixar & Visualizar Relatório: " + e);
		}
	}
	
}
