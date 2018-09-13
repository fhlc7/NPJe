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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import control.FHLC;
import control.RelatorioOriginalControle;
import model.RelatorioOriginal;

public class FrmEnviarOriginal extends JDialog {

	private RelatorioOriginal e;
	
	private JPanel contentPane;
	private JTextField txtNomeDoArquivo;
	private JTable tableRelatorio;
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
					FrmEnviarOriginal frame = new FrmEnviarOriginal();
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
	public FrmEnviarOriginal() {
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
		
		JLabel lblEnviarRelatrioOriginal = new JLabel("Enviar Relat\u00F3rio Original");
		lblEnviarRelatrioOriginal.setFont(new Font("SansSerif", Font.PLAIN, 42));
		lblEnviarRelatrioOriginal.setBounds(268, 40, 459, 54);
		contentPane.add(lblEnviarRelatrioOriginal);
		
		txtNomeDoArquivo = new JTextField();
		txtNomeDoArquivo.setText("Nome do Arquivo");
		txtNomeDoArquivo.setEditable(false);
		txtNomeDoArquivo.setColumns(10);
		txtNomeDoArquivo.setBackground(Color.CYAN);
		txtNomeDoArquivo.setBounds(161, 128, 637, 28);
		contentPane.add(txtNomeDoArquivo);
		
		JLabel label = new JLabel("Nome do Arquivo:");
		label.setBounds(52, 134, 97, 16);
		contentPane.add(label);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviar();
			}
		});
		btnEnviar.setMnemonic('e');
		btnEnviar.setBounds(518, 168, 130, 28);
		contentPane.add(btnEnviar);
		
		JButton btnSelecionarArquivo = new JButton("Selecionar Arquivo");
		btnSelecionarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selecionarArquivo();
			}
		});
		btnSelecionarArquivo.setMnemonic('s');
		btnSelecionarArquivo.setBounds(376, 168, 130, 28);
		contentPane.add(btnSelecionarArquivo);
		
		JScrollPane scrollPaneRelatorio = new JScrollPane();
		scrollPaneRelatorio.setBounds(52, 208, 880, 388);
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
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				atualizarTabela();
			}
		});
		txtPesquisar.setText("Pesquisar");
		txtPesquisar.setBounds(161, 168, 203, 28);
		contentPane.add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setFont(new Font("SansSerif", Font.ITALIC, 12));
		lblPesquisar.setBounds(91, 174, 58, 16);
		contentPane.add(lblPesquisar);
		
		txtId = new JTextField();
		txtId.setHorizontalAlignment(SwingConstants.CENTER);
		txtId.setText("id");
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBackground(Color.CYAN);
		txtId.setBounds(810, 128, 122, 28);
		contentPane.add(txtId);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deletar();
			}
		});
		btnDeletar.setMnemonic('d');
		btnDeletar.setBounds(802, 168, 130, 28);
		contentPane.add(btnDeletar);
		
		JButton btnBaixarVisualizar = new JButton("Baixar & Visualizar");
		btnBaixarVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				baixarVisualizar();
			}
		});
		btnBaixarVisualizar.setMnemonic('b');
		btnBaixarVisualizar.setBounds(660, 168, 130, 28);
		contentPane.add(btnBaixarVisualizar);
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
	
	private void selecionarArquivo() {
		limpar();
		FHLC.escolherArquivo();
		if(FHLC.getInputStream() != null) {
			txtNomeDoArquivo.setText(FHLC.getNomeArquivo());
		}
	}
	
	private void enviar() {
		if (txtNomeDoArquivo.getText().isEmpty() || !txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum relat�rio selecionado\n\nSelecione um Relat�rio");
			initialize();
			txtPesquisar.requestFocus();
			return;
		}
		RelatorioOriginal original = new RelatorioOriginal();
		original.setNomeRelatorio(txtNomeDoArquivo.getText());;
		original.setArquivoInput(FHLC.getInputStream());
		RelatorioOriginalControle.enviar(original);
		initialize();
	}
	
	private void atualizarTabela() {
		tableRelatorio.setModel(RelatorioOriginalControle.defaultTableModel(txtPesquisar.getText().trim()));
		/*table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 1; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(150);
		}*/
		tableRelatorio.getColumnModel().getColumn(1).setPreferredWidth(1000);
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
	
	private void acaoTabela() {
		criarObjetoTabela();
		deObjetoParaFormulario();
	}
	
	private void deletar() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum relat�rio selecionado\n\nSelecione um Relat�rio");
			initialize();
			txtPesquisar.requestFocus();
			return;
		}
		if (JOptionPane.showConfirmDialog(null, "Voc� tem certeza que deseja deletar?", getTitle(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			RelatorioOriginal original = new RelatorioOriginal();
			original.setId(Long.valueOf(txtId.getText()));
			RelatorioOriginalControle.deletar(original);
		}
		initialize();		
	}
	
	private void baixarVisualizar() {
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Nenhum relat�rio selecionado\n\nSelecione um Relat�rio");
			initialize();
			txtPesquisar.requestFocus();
			return;
		}
		FHLC.baixarVisualizar(e.getNomeRelatorio(), e.getArquivoOutput());
		initialize();
	}
	
}
