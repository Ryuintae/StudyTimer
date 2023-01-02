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
		this.setPreferredSize(new Dimension(400, 20)); // task 사이즈 지정하기
		this.setBackground(pink); // 배경색 지정

		this.setLayout(new BorderLayout());

		checked = false;

		index = new JLabel(""); // 인덱스 라벨 생성
		index.setPreferredSize(new Dimension(20, 20)); // 인덱스 레이블 크기 설정
		index.setHorizontalAlignment(JLabel.CENTER); // 인덱스 레이블의 정렬
		this.add(index, BorderLayout.WEST); // 태스크에 인덱스 레이블 추가

		taskName = new JTextField("Write something.."); // task 이름 텍스트 필드 생성
		taskName.setBorder(BorderFactory.createEmptyBorder()); // 텍스트 필드의 보더 제거
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
		this.index.setText(num + ""); // 숫자를 문자열로바꾸기
		this.revalidate(); // 초기화
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
					remove(c); // 컴포넌트 삭제
					updateNumbers(); // 인덱싱 업데이트하기
				}
			}
		}

	}

	private void updateNumbers() {

	}
}

@interface override {

}