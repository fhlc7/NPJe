package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.logging.SimpleFormatter;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import control.UsuarioControle;
import model.Usuario;
import javax.swing.ImageIcon;

public class FrmUsuario extends JFrame {

	private Usuario usuario = new Usuario();
	
	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtUsuario;
	private JPasswordField pwdSenha;
	private JPasswordField pwdConfirmarSenha;
	private JTextField txtProcurar;
	private JTable table;
	private JComboBox comboBoxTipo;
	private JTextField txtNomeCompleto;
	private JTextField txtMatricula;
	private JTextField txtEmail;
	private JTextField txtFone;

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
					FrmUsuario frame = new FrmUsuario();
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
	public FrmUsuario() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizar();
			}
		});
		setTitle("Usu\u00E1rio");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmUsuario.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(950, 625));
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(6, 116, 938, 154);
		panel.add(panel_1);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(55, 12, 43, 16);
		
		txtId = new JTextField();
		txtId.setBounds(108, 6, 122, 28);
		txtId.setBackground(Color.WHITE);
		txtId.setEditable(false);
		txtId.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setBounds(55, 46, 47, 16);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(108, 40, 352, 28);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(55, 80, 39, 16);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setBounds(108, 74, 122, 28);
		pwdSenha.setText("Senha");
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar senha:");
		lblConfirmarSenha.setBounds(236, 80, 96, 16);
		
		pwdConfirmarSenha = new JPasswordField();
		pwdConfirmarSenha.setBounds(338, 74, 122, 28);
		pwdConfirmarSenha.setText("Confirmar senha");
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(55, 113, 27, 16);
		
		comboBoxTipo = new JComboBox();
		comboBoxTipo.setBounds(108, 108, 352, 26);
		comboBoxTipo.setModel(new DefaultComboBoxModel(new String[] {"Coordenador", "Preceptor", "Estagi\u00E1rio"}));
		panel_1.setLayout(null);
		panel_1.add(lblCdigo);
		panel_1.add(lblUsuario);
		panel_1.add(lblSenha);
		panel_1.add(lblTipo);
		panel_1.add(comboBoxTipo);
		panel_1.add(pwdSenha);
		panel_1.add(lblConfirmarSenha);
		panel_1.add(pwdConfirmarSenha);
		panel_1.add(txtId);
		panel_1.add(txtUsuario);
		
		JLabel lblNomeCompleto = new JLabel("Nome Completo:");
		lblNomeCompleto.setBounds(472, 46, 94, 16);
		panel_1.add(lblNomeCompleto);
		
		txtNomeCompleto = new JTextField();
		txtNomeCompleto.setBounds(578, 40, 307, 28);
		panel_1.add(txtNomeCompleto);
		txtNomeCompleto.setColumns(10);
		
		JLabel lblMatrcula = new JLabel("Matr\u00EDcula:");
		lblMatrcula.setBounds(514, 12, 52, 16);
		panel_1.add(lblMatrcula);
		
		txtMatricula = new JTextField();
		txtMatricula.setBounds(578, 6, 307, 28);
		panel_1.add(txtMatricula);
		txtMatricula.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(527, 80, 39, 16);
		panel_1.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(578, 74, 307, 28);
		panel_1.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblFone = new JLabel("Fone:");
		lblFone.setBounds(535, 113, 31, 16);
		panel_1.add(lblFone);
		
		try {
			txtFone = new JFormattedTextField(new MaskFormatter("(##) ####-####"));
		} catch (ParseException e1) {
			// TODO Bloco catch gerado automaticamente
			e1.printStackTrace();
		}
		txtFone.setBounds(578, 107, 307, 28);
		panel_1.add(txtFone);
		txtFone.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(6, 271, 938, 272);
		panel.add(panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("Procurar:");
		label.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 12));
		
		txtProcurar = new JTextField();
		txtProcurar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				recarregarTabela();
			}
		});
		txtProcurar.setText("Procurar");
		txtProcurar.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtProcurar.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 972, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtProcurar, GroupLayout.DEFAULT_SIZE, 895, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 282, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(txtProcurar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				editar();
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				editar();
			}
		});
		scrollPane.setViewportView(table);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(92, 565, 374, 32);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 5, 0, 0));
		
		JButton button = new JButton("Atualizar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizar();
			}
		});
		button.setMnemonic('a');
		panel_3.add(button);
		
		JButton button_1 = new JButton("Novo");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novo();
			}
		});
		button_1.setMnemonic('n');
		panel_3.add(button_1);
		
		JButton button_2 = new JButton("Salvar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvar();
			}
		});
		button_2.setMnemonic('s');
		panel_3.add(button_2);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnExcluir.setMnemonic('d');
		panel_3.add(btnExcluir);
		
		JButton button_4 = new JButton("Fechar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_4.setMnemonic('f');
		panel_3.add(button_4);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(FrmUsuario.class.getResource("/img/cadastro-de-usuarios.jpg")));
		lblNewLabel.setBounds(6, 6, 938, 613);
		panel.add(lblNewLabel);
	}
	
	private void atualizar(){
		limpar();
		recarregarTabela();
		txtProcurar.requestFocus();
	}
	
	private void limpar(){
		String limpar = null;
		txtId.setText(limpar);
		txtUsuario.setText(limpar);
		pwdSenha.setText(limpar);
		pwdConfirmarSenha.setText(limpar);
		comboBoxTipo.setSelectedItem(0);
		txtMatricula.setText(limpar);
		txtNomeCompleto.setText(limpar);
		txtEmail.setText(limpar);
		txtFone.setText(limpar);
		txtProcurar.setText(limpar);
	}

	private boolean verificar(){
		if (txtUsuario.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite o usuário");
			txtUsuario.requestFocus();
			return false;
		}
		if (String.valueOf(pwdSenha.getPassword()).isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite a senha");
			pwdSenha.requestFocus();
			return false;
		}
		if (String.valueOf(pwdConfirmarSenha.getPassword()).isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite a confirmação de Senha");
			pwdConfirmarSenha.requestFocus();
			return false;
		}
		if (!String.valueOf(pwdSenha.getPassword()).equals(String.valueOf(pwdConfirmarSenha.getPassword()))){
			JOptionPane.showMessageDialog(null, "As senhas não coincidem\n\nTente novamente");
			pwdSenha.setText(null);
			pwdConfirmarSenha.setText(null);
			pwdSenha.requestFocus();
			return false;
		}
		if (comboBoxTipo.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(null, "Escolha ou digite o tipo");
			comboBoxTipo.requestFocus();
			return false;
		}
		if (comboBoxTipo.getSelectedItem().toString().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Escolha ou digite o tipo");
			comboBoxTipo.requestFocus();
			return false;
		}
		if (txtMatricula.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite a matrícula");
			txtMatricula.requestFocus();
			return false;
		}
		if (txtNomeCompleto.getText().trim().isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite o nome completo");
			txtNomeCompleto.requestFocus();
			return false;
		}
		return true;
	}
	
	private void criarObjetoFormulario(){
		try {
			usuario.setId(Integer.valueOf(txtId.getText()));
		} catch (Exception e) {
			usuario.setId(0);
		}
		usuario.setUsuario(txtUsuario.getText());
		usuario.setSenha(String.valueOf(pwdSenha.getPassword()));
		usuario.setTipo(comboBoxTipo.getSelectedItem().toString());
		usuario.setMatricula(txtMatricula.getText());
		usuario.setNomeCompleto(txtNomeCompleto.getText());
		usuario.setEmail(txtEmail.getText());
		usuario.setFone(txtFone.getText());
	}
	
	private void salvar(){
		if(verificar()){
			criarObjetoFormulario();
			UsuarioControle.salvar(usuario);
			atualizar();
		}
	}
	
	private void recarregarTabela(){
		table.setModel(UsuarioControle.defaultTableModel(txtProcurar.getText().trim()));
		/*table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		for (int i = 1; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(150);
		}*/
	}

	private void criarObjetoTabela(){
		int linha = table.getSelectedRow();
		int coluna = 0;
		for (int i = 0; i < table.getColumnCount(); i++) {
			if (table.getColumnName(i).equals("id")) {
				coluna = i;
				break;
			}
		}
		int id = Integer.valueOf(table.getValueAt(linha, coluna).toString());
		usuario = UsuarioControle.getUsuario(id);
	}
	
	private void deObjetoParaFormulario(){
		limpar();
		txtId.setText(String.valueOf(usuario.getId()));
		txtUsuario.setText(usuario.getUsuario());
		pwdSenha.setText(usuario.getSenha());
		pwdConfirmarSenha.setText(usuario.getSenha());
		comboBoxTipo.setSelectedItem(usuario.getTipo());
		txtMatricula.setText(usuario.getMatricula());
		txtNomeCompleto.setText(usuario.getNomeCompleto());
		txtEmail.setText(usuario.getEmail());
		txtFone.setText(usuario.getFone());
	}
	
	private void editar(){
		try {
			criarObjetoTabela();
			deObjetoParaFormulario();
		} catch (Exception e) {
			System.out.println("Selecionou coluna");
		}
	}
	
	private void deletar() {
		criarObjetoFormulario();
		if (usuario.getId() != 0) {
			if (JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir este usuário?", getTitle(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				UsuarioControle.deletar(usuario);
				atualizar();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Selecione um usuário na tabela para excluir");
		}
	}
	
	private void novo(){
		limpar();
		txtUsuario.requestFocus();
	}
}
