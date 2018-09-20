package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.UsuarioControle;

import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.temporal.Temporal;
import java.util.GregorianCalendar;
import java.beans.PropertyChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrmMenuPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenu mnUsuario;
	private JMenuItem mntmOriginal;
	private JMenuItem mntmEnviados;
	private JLabel label;

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
					FrmMenuPrincipal frame = new FrmMenuPrincipal();
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
	public FrmMenuPrincipal() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				initialize();
			}
		});
		setTitle("Relat\u00F3rio(s)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (arg0.getClickCount() >= 3) {
					JOptionPane.showMessageDialog(null, "www.fabianosoft.wordpress.com");
				}
			}
		});
		setJMenuBar(menuBar);
		
		JMenu mnRelatrio = new JMenu("Relat\u00F3rios");
		mnRelatrio.setMnemonic('r');
		menuBar.add(mnRelatrio);
		
		JMenuItem mntmBaixar = new JMenuItem("Baixar modelo de relat\u00F3rio");
		mntmBaixar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrmBaixar().setVisible(true);
			}
		});
		
		mntmOriginal = new JMenuItem("Disponibilizar relat\u00F3rios");
		mntmOriginal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FrmEnviarOriginal().setVisible(true);
			}
		});
		mnRelatrio.add(mntmOriginal);
		mnRelatrio.add(mntmBaixar);
		
		JMenuItem mntmEnviar = new JMenuItem("Enviar relat\u00F3rio");
		mntmEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrmEnviar().setVisible(true);
			}
		});
		mnRelatrio.add(mntmEnviar);
		
		mntmEnviados = new JMenuItem("Relat\u00F3rios enviados");
		mntmEnviados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrmRelatorioEnviado().setVisible(true);
			}
		});
		mnRelatrio.add(mntmEnviados);
		
		mnUsuario = new JMenu("Cadastro de Usuários");
		mnUsuario.setMnemonic('u');
		menuBar.add(mnUsuario);
		
		JMenuItem mntmGerenciar = new JMenuItem("Cadastrar Usuários");
		mntmGerenciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FrmUsuario().setVisible(true);
			}
		});
		mnUsuario.add(mntmGerenciar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(FrmMenuPrincipal.class.getResource("/img/menu-principal.jpg")));
		label.setBounds(6, 6, 982, 637);
		contentPane.add(label);
	}
	
	private void initialize() {
		try {
			if (!UsuarioControle.u.getTipo().equals("Coordenador")) {
				mntmOriginal.setVisible(false);
				mntmEnviados.setVisible(false);
				mnUsuario.setVisible(false);
			}
			if (UsuarioControle.u.getTipo().equals("Preceptor")) {
				mntmEnviados.setVisible(true);
			}
		} catch (Exception e) {
			// TODO Bloco catch gerado automaticamente
			//e.printStackTrace();
		}
		temp();
	}
		
	private void temp() {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					String t = getTitle();
					while(true) {
						setTitle(t + ": " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new GregorianCalendar().getTime()));
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};
		t.start();
	}
	
}
