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
		// 컨테이너 생성
		Container ct = getContentPane();
		ct.setLayout(new FlowLayout());

		setTitle("공부 스탑워치");
		this.setBackground(lightColor);

		// 제목 panel 생성
		title = new JPanel();

		// title panel에 들어갈 label 컴포넌트
		login = new JLabel("로그인 화면");
		// 컨테이너에 제목 위치 설정
		ct.add(title, BorderLayout.NORTH);

		// title panel에 label올려주기
		title.add(login);

		// 패널1 생성후 레이아웃 설정
		jp1 = new JPanel();
		jp1.setLayout(new GridLayout(5, 5, 5, 5));

		// id panel1 생성후 라벨 넣어주기
		idPanel = new JPanel();
		jlb1 = new JLabel("아이디 : ", JLabel.CENTER);

		idPanel.add(jlb1);

		// id pannel2 생성후 textfiel 넣어주기
		idPanel2 = new JPanel();
		jtf1 = new JTextField(10);

		idPanel2.add(jtf1);

		// gride layout으로 만든 패널에 id 패널 추가
		jp1.add(idPanel);
		jp1.add(idPanel2);

		// pwd 패널 생성후 비밀먼호 라벨 넣어주기
		pwdPanel = new JPanel();
		jlb2 = new JLabel("비밀번호 : ", JLabel.CENTER);

		// pwd2 패널 생성후 텍스트필드 넣어주기
		pwdPanel2 = new JPanel();
		JTextField jtf2 = new JTextField(10);

		// 패널에 라벨 넣어주기
		pwdPanel.add(jlb2);
		pwdPanel2.add(jtf2);

		// id 와 동일하게 패널1에 pwd 패널 넣어주기
		jp1.add(pwdPanel);
		jp1.add(pwdPanel2);

		// 버튼에 넣을 패널 생성하기
		loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		jLogin = new JButton("로그인");

		joinPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		join = new JButton("회원가입");

		joindelete = new JPanel(new FlowLayout());

		loginPanel.add(jLogin);
		joinPanel.add(join);

		// jp1 패널에 로그인과 가입하기 버튼 추가
		jp1.add(loginPanel);
		jp1.add(joinPanel);
		// 화면 크기 설정
		setBounds(200, 200, 300, 250);

		// 화면 크기 고정하기
		setResizable(true);

		// 윈도우 x 버튼 생성하는 메서드
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 컨테이너에 패널 추가
		ct.add(jp1);

		// 보여지기

		// 이벤트 처리
		join.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new JoinScreen();
				dispose(); // 현재의 frame을 종료시키는 메서드.

			}

		});
		jLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand() == "로그인") {
					boolean result = dao.login(jtf1.getText(), jtf2.getText());
					if (result == true) {
						JOptionPane.showMessageDialog(null, "로그인 성공");

						new StopWatch(jtf1.getText());
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "로그인 실패");

					}

				}

			}
		});

	}
}
