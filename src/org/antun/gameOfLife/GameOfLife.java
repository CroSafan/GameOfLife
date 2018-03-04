package org.antun.gameOfLife;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GameOfLife extends JFrame {

	private static final long serialVersionUID = 7289601463897799724L;
	private JPanel contentPane;
	private static Timer timer;
	private JPanel universePanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameOfLife frame = new GameOfLife();
					frame.setVisible(true);
					frame.setTitle("Conway's game of life");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GameOfLife() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 915);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		initialize();
	}

	private void initialize() {
		universePanel = new JPanel();
		universePanel.setLayout(new GridLayout(1, 1));
		universePanel.setBounds(10, 11, 822, 823);
		Grid grid = new Grid();
		universePanel.add(grid);
		grid.setLayout(null);
		grid.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					grid.fillCell((e.getX() / 10) - 1, (e.getY() / 10) - 1);
				if (e.getButton() == MouseEvent.BUTTON3)
					grid.removeCell((e.getX() / 10) - 1, (e.getY() / 10) - 1);

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

		contentPane.add(universePanel);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.start();
			}
		});
		startButton.setBounds(110, 845, 89, 23);
		contentPane.add(startButton);

		JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
			}
		});
		stopButton.setBounds(239, 845, 89, 23);
		contentPane.add(stopButton);

		JButton randomButton = new JButton("Random");
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grid.random();
			}
		});
		randomButton.setBounds(358, 845, 89, 23);
		contentPane.add(randomButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grid.clearGrid();
			}
		});
		clearButton.setBounds(475, 845, 89, 23);
		contentPane.add(clearButton);

		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				grid.checkAndApplyRules();
				grid.repaint();

			}
		});
	}
}
