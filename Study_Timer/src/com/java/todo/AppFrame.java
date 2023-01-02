package com.java.todo;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class AppFrame extends JFrame {

	private TitleBar title;
	private Footer footer;
	private List list;

	private JButton newTask;
	private JButton clear;

	String Result_ID;

	public AppFrame(String id) {
		Result_ID = id;
		this.setSize(400, 600);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setVisible(true);
		setResizable(false);
		title = new TitleBar();
		footer = new Footer();
		list = new List();

		this.add(title, BorderLayout.NORTH);
		this.add(footer, BorderLayout.SOUTH);
		this.add(list, BorderLayout.CENTER);

		newTask = footer.getNewTask();
		clear = footer.getClear();

		addListeners();
	}

	// 변수 list 가 존재해서 컬렉션 list 를 지정해주기 위해 java.util을 붙여서 사용함
	java.util.List<Task> taskList = new ArrayList<Task>();

	public void addListeners() {
		newTask.addMouseListener(new MouseAdapter() {
			@override
			public void mousePressed(MouseEvent e) {
				Task task = new Task();
				taskList.add(task);
				list.add(task);

				list.updateNumbers();

				task.getDone().addMouseListener(new MouseAdapter() {
					@override
					public void mousePressed(MouseEvent e) {

						task.changeState();
						list.updateNumbers();
						revalidate();

					}
				});
			}

		});

		clear.addMouseListener(new MouseAdapter() {
			@override
			public void mousePressed(MouseEvent e) {
				list.removeCompletedTasks(taskList); 
				repaint(); 
			}
		});
	}

}
