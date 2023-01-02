package com.java.stopwatch;

import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.java.model.MemberDao;

public class PwdSwap {

	private JFrame frame;
	private JTextField textField;
	String Result_ID;
	MemberDao dao = new MemberDao();

	public PwdSwap(String id) {
		initialize();
		Result_ID = id;

	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 251);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("��й�ȣ ����");
		JButton btnAceeptButton = new JButton("Ȯ��");
		btnAceeptButton.setBounds(226, 110, 117, 31);
		frame.getContentPane().add(btnAceeptButton);

		JButton btnBefore = new JButton("����");
		btnBefore.setBounds(86, 110, 97, 31);
		frame.getContentPane().add(btnBefore);

		textField = new JTextField();
		textField.setBounds(218, 40, 166, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel swapLabel = new JLabel("������ ��й�ȣ�� �Է��ϼ���");
		swapLabel.setBounds(46, 11, 202, 89);
		frame.getContentPane().add(swapLabel);
		swapLabel.toString();
		frame.setVisible(true);

		btnAceeptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ghkrdls");
				int result = dao.mUpdate(Result_ID, textField.getText());
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ����Ǿ����ϴ�.");
				System.out.println(result);
			}
		});
		btnBefore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.dispose();
			}
		});
		{

		}
	}

}
