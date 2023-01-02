package com.java.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.java.model.MemberDao;
import com.java.stopwatch.StopWatch;
import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

@SuppressWarnings("serial")
public class LoginScreen extends JFrame {

	MemberDao dao = new MemberDao();

	Connection con = null;
	Statement stmt = null;

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306";
	static String uid = "root";
	static String pwd = "0000";

	JPanel title, jp1, idPanel, idPanel2, pwdPanel, pwdPanel2, loginPanel, joinPanel, joindelete;
	JLabel login, jlb1, jlb2;
	JTextField jtf1 = null;
	JTextField jtf2 = null;
	JButton jLogin, join;

	Color lightColor = new Color(252, 221, 176);

	public void db() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, pwd);
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println("te : " + e.toString());
		}
	}

	public LoginScreen() {
		init();
		setVisible(true);
		setResizable(false);
	}

	public void init() {
		// �����̳� ����
		Container ct = getContentPane();
		ct.setLayout(new FlowLayout());

		setTitle("���� ��ž��ġ");
		this.setBackground(lightColor);

		// ���� panel ����
		title = new JPanel();

		// title panel�� �� label ������Ʈ
		login = new JLabel("�α��� ȭ��");
		// �����̳ʿ� ���� ��ġ ����
		ct.add(title, BorderLayout.NORTH);

		// title panel�� label�÷��ֱ�
		title.add(login);

		// �г�1 ������ ���̾ƿ� ����
		jp1 = new JPanel();
		jp1.setLayout(new GridLayout(5, 5, 5, 5));

		// id panel1 ������ �� �־��ֱ�
		idPanel = new JPanel();
		jlb1 = new JLabel("���̵� : ", JLabel.CENTER);

		idPanel.add(jlb1);

		// id pannel2 ������ textfiel �־��ֱ�
		idPanel2 = new JPanel();
		jtf1 = new JTextField(10);

		idPanel2.add(jtf1);

		// gride layout���� ���� �гο� id �г� �߰�
		jp1.add(idPanel);
		jp1.add(idPanel2);

		// pwd �г� ������ ��и�ȣ �� �־��ֱ�
		pwdPanel = new JPanel();
		jlb2 = new JLabel("��й�ȣ : ", JLabel.CENTER);

		// pwd2 �г� ������ �ؽ�Ʈ�ʵ� �־��ֱ�
		pwdPanel2 = new JPanel();
		JTextField jtf2 = new JTextField(10);

		// �гο� �� �־��ֱ�
		pwdPanel.add(jlb2);
		pwdPanel2.add(jtf2);

		// id �� �����ϰ� �г�1�� pwd �г� �־��ֱ�
		jp1.add(pwdPanel);
		jp1.add(pwdPanel2);

		// ��ư�� ���� �г� �����ϱ�
		loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jLogin = new JButton("�α���");

		joinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		join = new JButton("ȸ������");

		joindelete = new JPanel(new FlowLayout());

		loginPanel.add(jLogin);
		joinPanel.add(join);

		// jp1 �гο� �α��ΰ� �����ϱ� ��ư �߰�
		jp1.add(loginPanel);
		jp1.add(joinPanel);
		// ȭ�� ũ�� ����
		setBounds(200, 200, 300, 250);

		// ȭ�� ũ�� �����ϱ�
		setResizable(true);

		// ������ x ��ư �����ϴ� �޼���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// �����̳ʿ� �г� �߰�
		ct.add(jp1);

		// ��������

		// �̺�Ʈ ó��
		join.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new JoinScreen();
				dispose(); // ������ frame�� �����Ű�� �޼���.

			}

		});
		jLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "�α���") {
					boolean result = dao.login(jtf1.getText(), jtf2.getText());
					if (result == true) {
						JOptionPane.showMessageDialog(null, "�α��� ����");

						new StopWatch(jtf1.getText());
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "�α��� ����");

					}

				}

			}
		});

	}
}
