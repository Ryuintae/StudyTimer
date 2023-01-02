package com.java.myInfo;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.java.model.MemberDao;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class MyInfo extends JFrame {
	MemberDao dao = new MemberDao();
	String Result_ID;

	private JTextField myHourResult;
	private JTextField otherHourResult;

	Color lightColor = new Color(252, 221, 176);
	Color pink = new Color(255, 161, 161);
	Color doneColor = new Color(233, 119, 119);

	public MyInfo(String id) {
		setResizable(false);
		Result_ID = id;
		String re = dao.selectTime(Result_ID);
		String win = dao.rankTime();
		setTitle("내 정보");

		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		Container c = getContentPane();
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(lightColor);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel myTotHour = new JLabel("나의 총 공부시간은?");
		myTotHour.setBounds(100, 10, 120, 35);
		panel.add(myTotHour);
		myTotHour.setBackground(pink);

		JLabel otherTotHour = new JLabel("현재 공부시간 1등은?");
		otherTotHour.setBounds(100, 130, 120, 35);
		panel.add(otherTotHour);

		myHourResult = new JTextField();
		myHourResult.setEnabled(false);
		myHourResult.setBounds(60, 50, 200, 40);
		panel.add(myHourResult);
		myHourResult.setColumns(10);

		myHourResult.setText(re);

		myHourResult.setBackground(doneColor);
		myHourResult.setFont(new Font("돋움", Font.PLAIN, 15));

		otherHourResult = new JTextField();
		otherHourResult.setEnabled(false);
		otherHourResult.setBounds(60, 170, 200, 40);
		panel.add(otherHourResult);
		otherHourResult.setColumns(10);

		otherHourResult.setText(win);

		otherHourResult.setBackground(doneColor);
		otherHourResult.setFont(new Font("돋움", Font.PLAIN, 15));

		setSize(345, 300);
		setVisible(true);

	}
}
