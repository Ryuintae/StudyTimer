package com.java.todo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

class List extends JPanel {

	Color lightColor = new Color(252, 221, 176);

	List() {

		GridLayout layout = new GridLayout(10, 1);
		layout.setVgap(5); // 수직으로 간격주기

		this.setLayout(layout);
		this.setPreferredSize(new Dimension(400, 560));
		this.setBackground(lightColor);
	}

	public void updateNumbers() {
		Component[] listItems = this.getComponents();

		for (int i = 0; i < listItems.length; i++) {
			if (listItems[i] instanceof Task) {
				((Task) listItems[i]).changeIndex(i + 1);
			}
		}

	}

	public void removeCompletedTasks(java.util.List<Task> taskList) {

		for (Task task : taskList) {
			if (task.doneState) {
				this.remove(task);
			}
		}
		this.revalidate();
		this.repaint();

	}
}