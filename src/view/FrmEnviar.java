package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import control.FHLC;
import control.RelatorioEnviadoControle;
import control.UsuarioControle;
import model.RelatorioEnviado;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class FrmEnviar extends JDialog {

	private JPanel contentPane;
	private JTextField txtNomeDoArquivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEnviar frame = new FrmEnviar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmEnviar() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				initialize();
			}
		});
		setResizable(false);
		setTitle("Relat\u00F3rio(s)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNomeDoArquivo = new JTextField();
		txtNomeDoArquivo.setEditable(false);
		txtNomeDoArquivo.setColumns(10);
		txtNomeDoArquivo.setBackground(Color.WHITE);
		txtNomeDoArquivo.setBounds(161, 128, 771, 28);
		contentPane.add(txtNomeDoArquivo);
		
		JLabel label = new JLabel("Nome do Arquivo:");
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		label.setForeground(SystemColor.text);
		label.setBounds(52, 134, 99, 16);
		contentPane.add(label);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviar();
			}
		});
		btnEnviar.setMnemonic('e');
		btnEnviar.setBounds(802, 168, 130, 28);
		contentPane.add(btnEnviar);
		
		JButton btnSelecionarArquivo = new JButton("Selecionar Arquivo");
		btnSelecionarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionarArquivo();
			}
		});
		btnSelecionarArquivo.setMnemonic('s');
		btnSelecionarArquivo.setBounds(659, 168, 130, 28);
		contentPane.add(btnSelecionarArquivo);
		
		JButton button = new JButton("Voltar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setMnemonic('v');
		button.setBounds(842, 602, 90, 28);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(FrmEnviar.class.getResource("/img/enviar-relatorios.jpg")));
		lblNewLabel.setBounds(6, 6, 982, 660);
		contentPane.add(lblNewLabel);
	}
	
	private void initialize() {
		limpar();
	}
	
	private void limpar() {
		txtNomeDoArquivo.setText(null);
		//e = null;
		FHLC.setInputStream(null);
		FHLC.setNomeArquivo(null);
	}
	
	private void selecionarArquivo() {
		limpar();
		FHLC.escolherArquivo();
		if(FHLC.getInputStream() != null) {
			txtNomeDoArquivo.setText(FHLC.getNomeArquivo());
		}
	}
	
	private void enviar() {
		if (txtNomeDoArquivo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum relatório selecionado\n\nSelecione um relatório");
			initialize();
			return;
		}
		RelatorioEnviado enviado = new RelatorioEnviado();
		enviado.setNomeArquivo(txtNomeDoArquivo.getText());;
		enviado.setArquivoInput(FHLC.getInputStream());
		enviado.setUsuario(UsuarioControle.u);
		RelatorioEnviadoControle.enviar(enviado);
		dispose();//initialize();
	}
	
}
