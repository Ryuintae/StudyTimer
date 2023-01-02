package com.java.todo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

class TitleBar extends JPanel {

	Color lightColor = new Color(252, 221, 176);

	TitleBar() {
		this.setPreferredSize(new Dimension(400, 80)); // ���� ǥ������ ũ��
		this.setBackground(lightColor); 
		JLabel titleText = new JLabel("To Do List"); 
		titleText.setPreferredSize(new Dimension(200, 60)); // �ؽ�Ʈ ũ��
		titleText.setFont(new Font("Sans-serif", Font.BOLD, 20)); 
		titleText.setHorizontalAlignment(JLabel.CENTER); // ����� ��
		this.add(titleText);
	}
}
