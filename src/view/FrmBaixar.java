package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.FHLC;
import control.RelatorioOriginalControle;
import model.RelatorioOriginal;

import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class FrmBaixar extends JDialog {

	private RelatorioOriginal e;
	
	private JPanel contentPane;
	private JTable tableRelatorio;
	private JTextField txtNomeDoArquivo;
	private JTextField txtPesquisar;
	private JTextField txtId;

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
					FrmBaixar frame = new FrmBaixar();
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
	public FrmBaixar() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
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
		
		JScrollPane scrollPaneRelatorio = new JScrollPane();
		scrollPaneRelatorio.setBounds(55, 213, 880, 388);
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
				acaoTabela();
			}
		});
		scrollPaneRelatorio.setViewportView(tableRelatorio);
		
		JButton btnBaixarVisualizar = new JButton("Baixar/Visualizar");
		btnBaixarVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				baixarVisualizar();
			}
		});
		btnBaixarVisualizar.setMnemonic('b');
		btnBaixarVisualizar.setBounds(813, 173, 122, 28);
		contentPane.add(btnBaixarVisualizar);
		
		JLabel lblNomeDoArquivo = new JLabel("Nome do Arquivo:");
		lblNomeDoArquivo.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNomeDoArquivo.setForeground(SystemColor.text);
		lblNomeDoArquivo.setBounds(55, 139, 124, 16);
		contentPane.add(lblNomeDoArquivo);
		
		txtNomeDoArquivo = new JTextField();
		txtNomeDoArquivo.setBackground(Color.WHITE);
		txtNomeDoArquivo.setEditable(false);
		txtNomeDoArquivo.setText("Nome do Arquivo");
		txtNomeDoArquivo.setBounds(160, 133, 641, 28);
		contentPane.add(txtNomeDoArquivo);
		txtNomeDoArquivo.setColumns(10);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setForeground(SystemColor.text);
		lblPesquisar.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPesquisar.setBounds(57, 179, 87, 16);
		contentPane.add(lblPesquisar);
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				atualizarTabela();
			}
		});
		txtPesquisar.setText("Pesquisar");
		txtPesquisar.setBounds(160, 173, 641, 28);
		contentPane.add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		txtId = new JTextField();
		txtId.setText("id");
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBackground(Color.WHITE);
		txtId.setBounds(813, 133, 122, 28);
		contentPane.add(txtId);
		
		JButton button = new JButton("Voltar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button.setMnemonic('v');
		button.setBounds(845, 613, 90, 28);
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(FrmBaixar.class.getResource("/img/baixar-relatorios.jpg")));
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
		txtId.setText(null);
		txtPesquisar.setText(null);
		e = null;
		FHLC.setInputStream(null);
		FHLC.setNomeArquivo(null);
	}
	
	private void atualizarTabela() {
		tableRelatorio.setModel(RelatorioOriginalControle.defaultTableModel(txtPesquisar.getText().trim()));
		/*table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 1; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(150);
		}*/
		tableRelatorio.getColumnModel().getColumn(1).setPreferredWidth(1000);
	}
	
	private void acaoTabela() {
		criarObjetoTabela();
		deObjetoParaFormulario();
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
			e = RelatorioOriginalControle.get(id);
		} catch (Exception e) {
			// TODO Bloco catch gerado automaticamente
			//e.printStackTrace();
		}
	}
	
	private void deObjetoParaFormulario(){
		try {
			txtId.setText(String.valueOf(e.getId()));
			txtNomeDoArquivo.setText(e.getNomeRelatorio());
		} catch (Exception e) {
			// TODO Bloco catch gerado automaticamente
			//e.printStackTrace();
		}
	}
	
	private void baixarVisualizar() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum relatório selecionado");
			initialize();
			txtPesquisar.requestFocus();
			return;
		}
		FHLC.baixarVisualizar(e.getNomeRelatorio(), e.getArquivoOutput());
		initialize();
	}
	
}
