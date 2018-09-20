package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import control.FHLC;
import control.RelatorioEnviadoControle;
import model.RelatorioEnviado;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import java.awt.Component;
import javax.swing.KeyStroke;

public class FrmRelatorioEnviado extends JDialog {

	private RelatorioEnviado e;
	
	private JPanel contentPane;
	private JTextField txtNomeDoArquivo;
	private JTextField txtPesquisar;
	private JTable tableRelatorio;

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
					FrmRelatorioEnviado frame = new FrmRelatorioEnviado();
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
	public FrmRelatorioEnviado() {
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
		
		JLabel label = new JLabel("Nome do Arquivo:");
		label.setForeground(SystemColor.text);
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		label.setBounds(52, 135, 99, 16);
		contentPane.add(label);
		
		txtNomeDoArquivo = new JTextField();
		txtNomeDoArquivo.setEditable(false);
		txtNomeDoArquivo.setColumns(10);
		txtNomeDoArquivo.setBackground(Color.WHITE);
		txtNomeDoArquivo.setBounds(163, 129, 630, 28);
		contentPane.add(txtNomeDoArquivo);
		
		JLabel label_1 = new JLabel("Pesquisar:");
		label_1.setForeground(SystemColor.text);
		label_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		label_1.setBounds(54, 175, 97, 16);
		contentPane.add(label_1);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				atualizarTabela();
			}
		});
		txtPesquisar.setColumns(10);
		txtPesquisar.setBounds(161, 169, 632, 28);
		contentPane.add(txtPesquisar);
		
		JButton btnBaixarVisualizar = new JButton("Baixar/Visualizar");
		btnBaixarVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				baixarVisualizar();
			}
		});
		btnBaixarVisualizar.setMnemonic('b');
		btnBaixarVisualizar.setBounds(805, 169, 129, 28);
		contentPane.add(btnBaixarVisualizar);
		
		JScrollPane scrollPaneRelatorio = new JScrollPane();
		scrollPaneRelatorio.setBounds(54, 204, 880, 388);
		contentPane.add(scrollPaneRelatorio);
		
		tableRelatorio = new JTable();
		tableRelatorio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				acaoTabela();
			}
		});
		tableRelatorio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				acaoTabela(arg0);
			}
		});
		scrollPaneRelatorio.setViewportView(tableRelatorio);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setMnemonic('v');
		btnVoltar.setBounds(844, 604, 90, 28);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(FrmRelatorioEnviado.class.getResource("/img/relatorios-enviados.jpg")));
		lblNewLabel.setBounds(6, 6, 982, 660);
		contentPane.add(lblNewLabel);
	}
	
	private void initialize() {
		limpar();
		atualizarTabela();
		txtPesquisar.requestFocus();
	}
	
	private void limpar() {
		txtNomeDoArquivo.setText(null);
		//txtId.setText(null);
		txtPesquisar.setText(null);
		e = null;
		FHLC.setInputStream(null);
		FHLC.setNomeArquivo(null);
	}
	
	private void atualizarTabela() {
		tableRelatorio.setModel(RelatorioEnviadoControle.defaultTableModel(txtPesquisar.getText().trim()));
		/*tableRelatorio.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 1; i < tableRelatorio.getColumnCount(); i++) {
			tableRelatorio.getColumnModel().getColumn(i).setPreferredWidth(150);
		}*/
		//tableRelatorio.getColumnModel().getColumn(1).setPreferredWidth(1000);
	}
	
	private void acaoTabela() {
		criarObjetoTabela();
		deObjetoParaFormulario();
	}
	
	private void acaoTabela(MouseEvent event) {
		criarObjetoTabela();
		deObjetoParaFormulario();
		if (event.getClickCount() > 1) {
			if(JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja deletar este relatório enviado\n\n\n\n"
										+ "id: " + e.getId() + "\n\n"
										+ "Nome do Arquivo: " + e.getNomeArquivo() + "\n\n"
										+ "ID Usuário: " + e.getUsuario().getId() + "\n\n"
										+ "Usuário: " + e.getUsuario().getUsuario() + "\n\n"
										+ "Tipo: " + e.getUsuario().getTipo() + "\n\n"
										+ "Matrícula: " + e.getUsuario().getMatricula() + "\n\n"
										+ "Nome Completo: " + e.getUsuario().getNomeCompleto() + "\n\n"
										+ "E-mail: " + e.getUsuario().getEmail() + "\n\n"
										+ "Fone: " + e.getUsuario().getFone(),
										getTitle(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
											RelatorioEnviadoControle.deletar(e);
											atualizarTabela();
										}
		}
	}
	
	private void criarObjetoTabela(){
		try {
			int linha = tableRelatorio.getSelectedRow();
			int coluna = 0;
			for (int i = 0; i < tableRelatorio.getColumnCount(); i++) {
				if (tableRelatorio.getColumnName(i).equals("id")) {
					coluna = i;
					break;
				}
			}
			int id = Integer.valueOf(tableRelatorio.getValueAt(linha, coluna).toString());
			e = RelatorioEnviadoControle.get(id);
		} catch (Exception e) {
			// TODO Bloco catch gerado automaticamente
			//e.printStackTrace();
		}
	}
	
	private void deObjetoParaFormulario(){
		try {
			//txtId.setText(String.valueOf(e.getId()));
			txtNomeDoArquivo.setText(e.getNomeArquivo());
		} catch (Exception e) {
			// TODO Bloco catch gerado automaticamente
			//e.printStackTrace();
		}
	}
	
	private void baixarVisualizar() {
		if (txtNomeDoArquivo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum relatório selecionado\n\nSelecione um relatório");
			initialize();
			txtPesquisar.requestFocus();
			return;
		}
		FHLC.baixarVisualizar(e.getNomeArquivo(), e.getArquivoOutput());
		initialize();
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
