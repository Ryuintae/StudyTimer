package com.java.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import com.java.model.MemberDao;

@SuppressWarnings("serial")
public class JoinScreen extends JFrame {

	String choice = null;

	MemberDao dao = new MemberDao();

	Color lightColor = new Color(252, 221, 176);

	public JoinScreen() {

		setTitle("ȸ������");
		JLabel title = new JLabel("ȸ������", JLabel.CENTER);

		JButton join = new JButton("ȸ������");
		JButton cancel = new JButton("���");

		JTextField id = new JTextField(10);
		JTextField pwd = new JTextField(10);
		JTextField name = new JTextField(10);
		JTextField phone = new JTextField(10);

		// form panel
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		idPanel.add(new JLabel("���̵� : "));
		idPanel.add(id);

		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwdPanel.add(new JLabel("��й�ȣ : "));
		pwdPanel.add(pwd);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		namePanel.add(new JLabel("��    �� : "));
		namePanel.add(name);

		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		phonePanel.add(new JLabel("�� �� ó : "));
		phonePanel.add(phone);

		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 1));
		formPanel.add(idPanel);
		formPanel.add(pwdPanel);
		formPanel.add(namePanel);
		formPanel.add(phonePanel);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(formPanel);
		
		JPanel panel = new JPanel();
		panel.add(join);
		panel.add(cancel);

		add(title, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);

		setBounds(200, 200, 250, 300);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);

		id.setText(id.getText());

		// �̺�Ʈ ó��
		join.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "ȸ������") {

					if (dao.getIdByCheck(id.getText())) {
						dao.insertData(id.getText(), pwd.getText(), name.getText(), phone.getText());

						JOptionPane.showMessageDialog(null, "���̵� : " + id.getText() + ", ��й�ȣ : " + pwd.getText()
								+ ", �� �� : " + name.getText() + ", ����ó : " + phone.getText());
					} else {
						JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ���̵��Դϴ�!!!!!");
					}
				}
			}
		});

		// ��� ��ư�� Ŭ������ �� �̺�Ʈ ó��
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LoginScreen();
				dispose();
			}
		});
	}
}