package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import control.UsuarioControle;
import model.Usuario;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;

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
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmLogin.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		setResizable(false);
		setTitle("Login");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(SystemColor.activeCaptionBorder);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(360, 287, 259, 229);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsuario.setBounds(31, 51, 55, 16);
		panel.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tecladoFormulario(e);
			}
		});
		txtUsuario.setBounds(98, 45, 122, 28);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSenha.setBounds(31, 85, 55, 16);
		panel.add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				tecladoFormulario(e);
			}
		});
		txtSenha.setBounds(98, 79, 122, 28);
		panel.add(txtSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBackground(SystemColor.inactiveCaption);
		btnEntrar.setBounds(42, 130, 180, 31);
		panel.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		btnEntrar.setFont(new Font("Calibri", Font.PLAIN, 12));
		btnEntrar.setMnemonic('e');
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(FrmLogin.class.getResource("/img/tela-login.jpg")));
		lblNewLabel.setBounds(6, 6, 982, 643);
		contentPane.add(lblNewLabel);
	}

	private void login() {
		String nomeUsuario, senha;
		nomeUsuario = txtUsuario.getText().trim();
		senha = new String(txtSenha.getPassword());
		if(nomeUsuario.isEmpty()){
			JOptionPane.showMessageDialog(null, "Informe o usuário");
			txtUsuario.requestFocus();
			return;
		}
		if(senha.isEmpty()){
			JOptionPane.showMessageDialog(null, "Digite a senha");
			txtSenha.requestFocus();
			return;
		}
		Usuario usuario = new Usuario();
		usuario.setUsuario(nomeUsuario);
		usuario.setSenha(senha);
		if(UsuarioControle.login(usuario)){
			//existe
			JOptionPane.showMessageDialog(null, "Seja bem vindo " + usuario.getUsuario());
			FrmMenuPrincipal menu = new FrmMenuPrincipal();
			menu.setVisible(true);
			limpar();
			dispose();
		} else {
			//não existe
			JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto(s)\n\nTente novamente");
			limpar();
		}
	}
	
	private void tecladoFormulario(KeyEvent e) {
		if(e.getKeyCode() == 10) login();// 10 é o código da tecla enter
	}
	
	private void limpar(){
		txtUsuario.setText(null);
		txtSenha.setText(null);
		txtUsuario.requestFocus();
	}
	
}
