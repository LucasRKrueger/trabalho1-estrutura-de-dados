import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	private JFrame frame;
	private JTextField txtArquivo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 275);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblArquivo = new JLabel("Arquivo:");
		lblArquivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArquivo.setBounds(10, 9, 53, 20);
		frame.getContentPane().add(lblArquivo);
		
		txtArquivo = new JTextField();
		txtArquivo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtArquivo.setBounds(73, 10, 247, 19);
		frame.getContentPane().add(txtArquivo);
		txtArquivo.setColumns(10);
				
		JTextPane txtRetorno = new JTextPane();
		txtRetorno.setEditable(false);
		txtRetorno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtRetorno.setBounds(10, 39, 416, 89);
		frame.getContentPane().add(txtRetorno);
		
		JTextPane txtTags = new JTextPane();
		txtTags.setEditable(false);
		txtTags.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtTags.setBounds(10, 138, 416, 89);
		frame.getContentPane().add(txtTags);
		
		JButton btnAnalisar = new JButton("Analisar");
		btnAnalisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Arquivo arquivo = new Arquivo();
					AvaliaTags validacaoTags = new AvaliaTags();
					
					txtRetorno.setText("");
					txtTags.setText("");
										
					String arquivoFormatado = arquivo.ValidarArquivo(txtArquivo.getText());
					           
		            String retorno = validacaoTags.validarEstrutura(arquivoFormatado);

		            txtRetorno.setText(retorno);
		            txtTags.setText(validacaoTags.retornarFrequenciaTags());
					
				} catch (Exception ex){
					txtRetorno.setText(ex.getMessage());
		        }
			}
		});
		btnAnalisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAnalisar.setBounds(330, 10, 96, 21);
		frame.getContentPane().add(btnAnalisar);
	}
}
