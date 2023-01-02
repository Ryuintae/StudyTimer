package com.java.todo;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Component;

class Task extends JPanel {

	JLabel index;
	JTextField taskName;
	JButton done;

	public boolean doneState;

	Color pink = new Color(255, 161, 161);
	Color green = new Color(188, 226, 158);
	Color doneColor = new Color(233, 119, 119);

	private boolean checked;

	Task() {
		this.setPreferredSize(new Dimension(400, 20)); // task ������ �����ϱ�
		this.setBackground(pink); // ���� ����

		this.setLayout(new BorderLayout());

		checked = false;

		index = new JLabel(""); // �ε��� �� ����
		index.setPreferredSize(new Dimension(20, 20)); // �ε��� ���̺� ũ�� ����
		index.setHorizontalAlignment(JLabel.CENTER); // �ε��� ���̺��� ����
		this.add(index, BorderLayout.WEST); // �½�ũ�� �ε��� ���̺� �߰�

		taskName = new JTextField("Write something.."); // task �̸� �ؽ�Ʈ �ʵ� ����
		taskName.setBorder(BorderFactory.createEmptyBorder()); // �ؽ�Ʈ �ʵ��� ���� ����
		taskName.setBackground(pink);

		this.add(taskName, BorderLayout.CENTER);

		done = new JButton("Done");
		done.setPreferredSize(new Dimension(80, 20));
		done.setBorder(BorderFactory.createEmptyBorder());
		done.setBackground(doneColor);
		done.setFocusPainted(false);

		this.add(done, BorderLayout.EAST);

	}

	public void changeIndex(int num) {
		this.index.setText(num + ""); // ���ڸ� ���ڿ��ιٲٱ�
		this.revalidate(); // �ʱ�ȭ
	}

	public JButton getDone() {
		return done;
	}

	public boolean getState() {
		return checked;
	}

	public void changeState() {
		doneState = true;
		this.setBackground(green);
		taskName.setBackground(green);
		checked = true;
		revalidate();
	}

	public void removeCompletedTasks() {

		for (Component c : getComponents()) {
			if (c instanceof Task) {
				if (((Task) c).getState()) {
					remove(c); // ������Ʈ ����
					updateNumbers(); // �ε��� ������Ʈ�ϱ�
				}
			}
		}

	}

	private void updateNumbers() {

	}
}

@interface override {

}