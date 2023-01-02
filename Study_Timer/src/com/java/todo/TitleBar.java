package com.java.todo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

class TitleBar extends JPanel {

	Color lightColor = new Color(252, 221, 176);

	TitleBar() {
		this.setPreferredSize(new Dimension(400, 80)); // 제목 표시줄의 크기
		this.setBackground(lightColor); 
		JLabel titleText = new JLabel("To Do List"); 
		titleText.setPreferredSize(new Dimension(200, 60)); // 텍스트 크기
		titleText.setFont(new Font("Sans-serif", Font.BOLD, 20)); 
		titleText.setHorizontalAlignment(JLabel.CENTER); // 가운데로 정
		this.add(titleText);
	}
}
