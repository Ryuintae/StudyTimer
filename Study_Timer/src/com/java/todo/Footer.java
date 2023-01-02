package com.java.todo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

class Footer extends JPanel {

	JButton addTask;
	JButton clear;

	Color orange = new Color(233, 133, 128);
	Color lightColor = new Color(252, 221, 176);
	Border emptyBorder = BorderFactory.createEmptyBorder();

	Footer() {
		this.setPreferredSize(new Dimension(400, 60));
		this.setBackground(lightColor);

		addTask = new JButton("Add Task"); // �߰���ư
		addTask.setBorder(emptyBorder); // ���� �����ϱ�
		addTask.setFont(new Font("Sans-serif", Font.ITALIC, 20)); // ��Ʈ �������ֱ�
		addTask.setVerticalAlignment(JButton.BOTTOM); // �ؽ�Ʈ�� �� �Ʒ��� ���α�
		addTask.setBackground(orange);
		this.add(addTask);

		this.add(Box.createHorizontalStrut(20)); // ��ư ���� �����ֱ�

		clear = new JButton("Clear finished tasks"); // ��ư �ʱ�ȭ
		clear.setFont(new Font("Sans-serif", Font.ITALIC, 20));
		clear.setBorder(emptyBorder);
		clear.setBackground(orange);
		this.add(clear);
	}

	public JButton getNewTask() {
		return addTask;
	}

	public JButton getClear() {
		return clear;
	}
}
