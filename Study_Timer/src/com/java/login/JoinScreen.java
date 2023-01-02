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

		setTitle("회원가입");
		JLabel title = new JLabel("회원가입", JLabel.CENTER);

		JButton join = new JButton("회원가입");
		JButton cancel = new JButton("취소");

		JTextField id = new JTextField(10);
		JTextField pwd = new JTextField(10);
		JTextField name = new JTextField(10);
		JTextField phone = new JTextField(10);

		// form panel
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		idPanel.add(new JLabel("아이디 : "));
		idPanel.add(id);

		JPanel pwdPanel = new JPanel();
		pwdPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwdPanel.add(new JLabel("비밀번호 : "));
		pwdPanel.add(pwd);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		namePanel.add(new JLabel("이    름 : "));
		namePanel.add(name);

		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		phonePanel.add(new JLabel("연 락 처 : "));
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

		// 이벤트 처리
		join.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "회원가입") {

					if (dao.getIdByCheck(id.getText())) {
						dao.insertData(id.getText(), pwd.getText(), name.getText(), phone.getText());

						JOptionPane.showMessageDialog(null, "아이디 : " + id.getText() + ", 비밀번호 : " + pwd.getText()
								+ ", 이 름 : " + name.getText() + ", 연락처 : " + phone.getText());
					} else {
						JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다!!!!!");
					}
				}
			}
		});

		// 취소 버튼을 클릭했을 때 이벤트 처리
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new LoginScreen();
				dispose();
			}
		});
	}
}