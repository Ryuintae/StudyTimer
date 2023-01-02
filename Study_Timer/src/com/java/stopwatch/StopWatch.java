package com.java.stopwatch;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.java.todo.AppFrame;

import com.java.stopwatch.*;

import javax.swing.BoxLayout;
import java.awt.Font;
import com.java.model.MemberDao;
import com.java.myInfo.MyInfo;
import java.awt.Color;

@SuppressWarnings("serial")
public class StopWatch extends JFrame {

	MemberDao dao = new MemberDao();
	String Result_ID;

	JButton start, reset, save, stop;
	Thread p_display, t_dispaly;
	JLabel w1, w2, w3, w4;
	int hour, mm, ss, ms, t = 0;
	private JPanel panel;
	private JPanel panel_1;
	private JButton todo, myinfo;
	private JButton delete;
	private JButton btnSwapButton;

	public StopWatch(String id) {
		super("½ºÅ¾¿öÄ¡");
		Result_ID = id;
		buildGUI();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(506, 450);
		this.setVisible(true);
		this.setResizable(false);
		// this.pack();
	}

	private void buildGUI() {
		JPanel p = new JPanel(new BorderLayout());
		p.setBounds(0, 0, 380, 410);
		JPanel bp = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel wp = new JPanel();

		JLabel c1 = new JLabel(" : ");
		c1.setFont(new Font("±¼¸²", Font.PLAIN, 50));
		JLabel c2 = new JLabel(" : ");
		c2.setFont(new Font("±¼¸²", Font.PLAIN, 50));
		JLabel c3 = new JLabel(" : ");
		c3.setFont(new Font("±¼¸²", Font.PLAIN, 50));
		w1 = new JLabel("00");
		w1.setFont(new Font("±¼¸²", Font.PLAIN, 50));
		w2 = new JLabel("00");
		w2.setFont(new Font("±¼¸²", Font.PLAIN, 50));
		w3 = new JLabel("00");
		w3.setFont(new Font("±¼¸²", Font.PLAIN, 50));
		w4 = new JLabel("00");
		w4.setFont(new Font("±¼¸²", Font.PLAIN, 50));

		start = new JButton("START");
		save = new JButton("SAVE");
		reset = new JButton("RESET");
		stop = new JButton("STOP");

		bp.add(start);
		bp.add(save);
		bp.add(reset);
		bp.add(stop);
		wp.setLayout(new BoxLayout(wp, BoxLayout.X_AXIS));

		wp.add(w4);
		wp.add(c3);
		wp.add(w1);
		wp.add(c1);
		wp.add(w2);
		wp.add(c2);
		wp.add(w3);

		getContentPane().setLayout(null);

		p.add(wp, BorderLayout.CENTER);
		p.add(bp, BorderLayout.SOUTH);
		getContentPane().add(p);

		save.setEnabled(false);
		reset.setEnabled(false);

		panel = new JPanel();
		panel.setBounds(404, 95, 83, 51);
		getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		myinfo = new JButton("\uB0B4\uC815\uBCF4");
		panel.add(myinfo);

		panel_1 = new JPanel();
		panel_1.setBounds(394, 52, 83, 33);
		getContentPane().add(panel_1);

		todo = new JButton("toDo!");
		panel_1.add(todo);

		delete = new JButton("°èÁ¤Å»Åð");
		delete.setForeground(Color.RED);
		delete.setBounds(380, 380, 110, 20);
		getContentPane().add(delete);
		delete.setForeground(Color.RED);

		btnSwapButton = new JButton("ºñ¹Ð¹øÈ£º¯°æ");
		btnSwapButton.setBounds(380, 350, 110, 20);
		getContentPane().add(btnSwapButton);

		btnSwapButton.addActionListener(new ButtonListener());
		delete.addActionListener(new ButtonListener());
		start.addActionListener(new ButtonListener());
		save.addActionListener(new ButtonListener());
		reset.addActionListener(new ButtonListener());
		stop.addActionListener(new ButtonListener());
		todo.addActionListener(new ButtonListener());
		myinfo.addActionListener(new ButtonListener());
	}

	class ButtonListener implements ActionListener {
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			String s = e.getActionCommand();

			if (s.equals("START")) {
				start.setEnabled(false);
				save.setEnabled(true);
				reset.setEnabled(false);
				stop.setEnabled(true);

				p_display = new Thread(new Runnable() {

					@Override
					public void run() {
						mm = Integer.parseInt(w1.getText());
						ss = Integer.parseInt(w2.getText());
						ms = Integer.parseInt(w3.getText());
						hour = Integer.parseInt(w4.getText());

						while (p_display == Thread.currentThread()) {

							mm = t % (1000 * 60) / 100 / 60;
							ss = t % (1000 * 60) / 100 % 60;
							ms = t % 100;
							hour = t % (1000 * 60) / 100 / 60 / 60;

							try {
								Thread.sleep(10);

								w1.setText(String.format("%02d", mm));
								w2.setText(String.format("%02d", ss));
								w3.setText(String.format("%02d", ms));
								w4.setText(String.format("%02d", hour));
								t++;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					}
				});

				p_display.start();

			} else if (s.equals("SAVE")) {
				start.setEnabled(true);
				save.setEnabled(false);
				reset.setEnabled(true);
				stop.setEnabled(false);

				dao.insertTime(Result_ID.toString(), hour, mm, ss);

				p_display = null;

			} else if (s.equals("RESET")) {
				start.setEnabled(true);
				save.setEnabled(false);
				reset.setEnabled(false);
				stop.setEnabled(false);

				w3.setText("00");
				w2.setText("00");
				w1.setText("00");

				t = 0;
			} else if (s.equals("STOP")) {
				start.setEnabled(true);
				save.setEnabled(true);
				reset.setEnabled(false);

				p_display.stop();

			} else if (s.equals("toDo!")) {
				new AppFrame(Result_ID);
			} else if (s.equals("³»Á¤º¸")) {
				new MyInfo(Result_ID);
			} else if (s.equals("°èÁ¤Å»Åð")) {
				dao.delete(Result_ID);
				JOptionPane.showMessageDialog(null, "°èÁ¤ÀÌ »èÁ¦µÇ¾ú½À´Ï´Ù.");
				System.exit(0);
			} else if (s.equals("ºñ¹Ð¹øÈ£º¯°æ")) {
				new PwdSwap(Result_ID);
			}

		}

	}
}