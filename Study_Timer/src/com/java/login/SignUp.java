package com.java.login;

import java.awt.event.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

import com.java.model.MemberDao;

@SuppressWarnings("serial")
class SignUp extends JFrame implements ActionListener {

	JPanel panel;
	JFrame frm; // frame
	JLabel lblid, lblpw, lblname, lblemail; //
	JButton b1, b2, b3; // 버튼3개
	JTextField txtid, txtpw, txtname, txtemail;
	Container contentPane = getContentPane();

	MemberDao dao = new MemberDao();

	SignUp() {
		init();

		b1.addActionListener(this);
		b2.addActionListener(this);

	}

	public void init() {

		JFrame frm = new JFrame(); // 프레임 생성
		frm.setResizable(false);
		frm.setTitle("회원가입");
		frm.setSize(500, 500);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setVisible(true);

		JPanel panel1 = new JPanel();
		contentPane.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel1.setLayout(new BoxLayout(frm, BoxLayout.Y_AXIS));
		panel1.add(lblname = new JLabel("Name"));
		panel1.add(new JTextField());

		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel2.add(lblid = new JLabel("ID"));
		panel2.add(new JTextField());

		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel3.add(lblpw = new JLabel("PW"));
		panel3.add(new JTextField(10));

		JPanel panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		panel4.add(lblemail = new JLabel("EMAIL"));
		panel4.add(new JTextField(10));

		JPanel panel5 = new JPanel();
		panel5.setLayout(new FlowLayout());
		panel5.add(panel1);
		panel5.add(panel2);
		panel5.add(panel3);
		panel5.add(panel4);

		frm.getContentPane().add(panel5, BorderLayout.CENTER);

		frm.pack();

		b1 = new JButton("가입");
		b2 = new JButton("취소");

		frm.getContentPane().add(panel5);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String s = null;
			boolean isOk = false;
			BufferedWriter bw = new BufferedWriter(new FileWriter("members.txt", true));
			BufferedReader br = new BufferedReader(new FileReader("members.txt"));

			if (e.getSource() == b1) {
				while ((s = br.readLine()) != null) {

					System.out.println("test");
					dao.insertData(txtid.getText(), txtpw.getText(), txtname.getText(), txtemail.getText());

					// 아이디 중복
					String[] array = s.split("/");
					if (array[0].equals(txtid.getText())) {
						isOk = true;
						break;
					}
				}
				// 정보 입력시 중복이 없으면 데이터 보냄
				if (!isOk) {
					bw.write(txtid.getText() + "/");
					bw.write(txtpw.getText() + "/");
					bw.write(txtname.getText() + "/");
					bw.write(txtemail.getText() + "/");
					bw.close();

					JOptionPane.showMessageDialog(null, "회원가입을 축하합니다.");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.");
				}

			} else if (e.getSource() == b2) {
				txtid.setText("");
				txtpw.setText("");
				txtname.setText("");
				txtemail.setText("");
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "실패");
		}
	}

	public static void main(String[] args) {
		new SignUp();
	}
}