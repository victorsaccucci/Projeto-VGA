package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;

public class PainelListagemClientes extends JPanel {
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JTable table;
	private JTable table_1;

	public PainelListagemClientes() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNome.setBounds(42, 49, 45, 13);
		add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(97, 44, 283, 28);
		add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("CPF:");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel.setBounds(418, 45, 45, 20);
		add(lblNewLabel);
		
		txtCpf = new JTextField();
		txtCpf.setColumns(10);
		txtCpf.setBounds(456, 44, 160, 28);
		add(txtCpf);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblEmail.setBounds(42, 102, 45, 13);
		add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(97, 101, 519, 28);
		add(txtEmail);
		
		table_1 = new JTable();
		table_1.setBounds(23, 166, 902, 324);
		add(table_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(651, 102, 85, 27);
		add(btnBuscar);
		
	

	}
}
