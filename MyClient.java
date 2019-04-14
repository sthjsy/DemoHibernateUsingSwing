package mypack;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyClient extends JFrame {
	private JTextField tfeno;
	private JTextField tfename;
	private JTextField tfesal;
	private JTextArea taop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyClient frame = new MyClient();
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
	public MyClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400);
		getContentPane().setLayout(null);
		
		JLabel lbleno = new JLabel("Enter Emplyee No");
		lbleno.setBounds(67, 11, 149, 14);
		getContentPane().add(lbleno);
		
		tfeno = new JTextField();
		tfeno.setBounds(67, 36, 132, 20);
		getContentPane().add(tfeno);
		tfeno.setColumns(10);
		
		JLabel lblename = new JLabel("Enter Employee Name");
		lblename.setBounds(67, 61, 129, 14);
		getContentPane().add(lblename);
		
		tfename = new JTextField();
		tfename.setBounds(66, 86, 133, 20);
		getContentPane().add(tfename);
		tfename.setColumns(10);
		
		JLabel lblesal = new JLabel("Enter Employee Salary");
		lblesal.setBounds(67, 116, 129, 19);
		getContentPane().add(lblesal);
		
		tfesal = new JTextField();
		tfesal.setBounds(67, 146, 132, 20);
		getContentPane().add(tfesal);
		tfesal.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Configuration cfg=new Configuration();
				cfg.configure();
				SessionFactory sf=cfg.buildSessionFactory();
				Session s=sf.openSession();
				Transaction t=s.beginTransaction();
				
				Emp e=new Emp();
				e.setEno(tfeno.getText());
				e.setEname(tfename.getText());
				e.setEsal(tfesal.getText());
				
				s.save(e);
				JOptionPane.showMessageDialog(null, s.save(e).toString());
				taop.setText("Employee No. "+s.save(e)+"Save......");
				t.commit();
				s.close();
				sf.close();
			}
		});
		btnSubmit.setBounds(91, 191, 89, 23);
		getContentPane().add(btnSubmit);
		
		JLabel lblOutputl = new JLabel("Output");
		lblOutputl.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOutputl.setBounds(110, 245, 89, 23);
		getContentPane().add(lblOutputl);
		
		taop=new JTextArea();
        taop.setBounds(40, 275, 210, 60);
		getContentPane().add(taop);
	}
}
