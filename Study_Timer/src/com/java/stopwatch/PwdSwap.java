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
		frame.setTitle("비밀번호 변경");
		JButton btnAceeptButton = new JButton("확인");
		btnAceeptButton.setBounds(226, 110, 117, 31);
		frame.getContentPane().add(btnAceeptButton);

		JButton btnBefore = new JButton("이전");
		btnBefore.setBounds(86, 110, 97, 31);
		frame.getContentPane().add(btnBefore);

		textField = new JTextField();
		textField.setBounds(218, 40, 166, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel swapLabel = new JLabel("변경할 비밀번호를 입력하세요");
		swapLabel.setBounds(46, 11, 202, 89);
		frame.getContentPane().add(swapLabel);
		swapLabel.toString();
		frame.setVisible(true);

		btnAceeptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ghkrdls");
				int result = dao.mUpdate(Result_ID, textField.getText());
				JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다.");
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
