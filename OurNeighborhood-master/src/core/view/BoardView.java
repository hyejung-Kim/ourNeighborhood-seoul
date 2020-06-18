package core.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import core.DTO.Post;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BoardView extends JPanel {

	Color bgColor = new Color(255, 216, 225);
	BufferedImage img;
	private MainFrame win;
	private JTable table;
	private JComboBox si_comboBox;
	private JComboBox gu_comboBox;
	private JComboBox dong_comboBox;
	private JButton callPostList_btn;
	private String[] colNames;
	private DefaultTableModel model;
	private Post[] postList = new Post[30];
	private JButton myPost_btn;

	/**
	 * Create the panel.
	 */
	public BoardView(MainFrame win) {
		this.win = win;
		setLayout(null);
		setBounds(0, 0, 980, 760);
		setBackground(bgColor);

		try {
			img = ImageIO.read(new File("로고.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
			}
		};
		panel.setBackground(bgColor);
		panel.setBounds(12, 10, 188, 146);
		add(panel);

		JButton listView_btn = new JButton("\uB3D9\uB124\uCC3E\uAE30");
		listView_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 30));
		listView_btn.setBackground(bgColor);
		listView_btn.setBorderPainted(false);
		listView_btn.setBounds(0, 299, 211, 42);
		add(listView_btn);

		JButton reviewView_btn = new JButton("\uB3D9\uB124\uB9AC\uBDF0");
		reviewView_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 30));
		reviewView_btn.setBorderPainted(false);
		reviewView_btn.setBackground(new Color(255, 216, 225));
		reviewView_btn.setBounds(0, 400, 211, 42);
		add(reviewView_btn);

		JButton boardView_btn = new JButton("\uCEE4\uBBA4\uB2C8\uD2F0");
		boardView_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 30));
		boardView_btn.setBorderPainted(false);
		boardView_btn.setBackground(new Color(255, 216, 225));
		boardView_btn.setBounds(0, 499, 211, 42);
		add(boardView_btn);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(212, 10, 760, 740);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel label = new JLabel("\uC870\uAC74");
		label.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		label.setBounds(35, 72, 57, 32);
		panel_1.add(label);

		si_comboBox = new JComboBox();
		si_comboBox.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 14));
		si_comboBox.addItem("서울특별시");
		si_comboBox.setBounds(91, 72, 105, 32);
		si_comboBox.setBackground(Color.WHITE);
		panel_1.add(si_comboBox);

		gu_comboBox = new JComboBox();
		gu_comboBox.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 14));
		gu_comboBox.setBounds(208, 72, 105, 32);
		gu_comboBox.setBackground(Color.WHITE);
		panel_1.add(gu_comboBox);
		gu_comboBox.addItem("전체");

		dong_comboBox = new JComboBox();
		dong_comboBox.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 14));
		dong_comboBox.setBounds(325, 72, 105, 32);
		dong_comboBox.setBackground(Color.WHITE);
		panel_1.add(dong_comboBox);
		dong_comboBox.addItem("전체");

		JLabel label_2 = new JLabel("\uCEE4\uBBA4\uB2C8\uD2F0");
		label_2.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 24));
		label_2.setBounds(35, 20, 130, 42);
		panel_1.add(label_2);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(25, 60, 723, 2);
		panel_1.add(separator);

		JComboBox sort_comboBox = new JComboBox();
		sort_comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uCD5C\uC2E0\uC21C" }));
		sort_comboBox.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 14));
		sort_comboBox.setBackground(Color.WHITE);
		sort_comboBox.setBounds(618, 72, 119, 32);
		panel_1.add(sort_comboBox);

		JButton write_btn = new JButton("\uAE00\uC4F0\uAE30");
		write_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		write_btn.setBorderPainted(false);
		write_btn.setBackground(new Color(255, 216, 225));
		write_btn.setBounds(649, 688, 99, 42);
		panel_1.add(write_btn);

		myPost_btn = new JButton("\uB0B4 \uAE00 \uBCF4\uAE30");
		myPost_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		myPost_btn.setBorderPainted(false);
		myPost_btn.setBackground(new Color(255, 216, 225));
		myPost_btn.setBounds(631, 20, 105, 32);
		panel_1.add(myPost_btn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 139, 712, 532);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.getViewport().setBackground(Color.WHITE);
		panel_1.add(scrollPane);

		colNames = new String[] { "글번호", "제목","작성자", "작성일시"};
		model = new DefaultTableModel(colNames, 0);
		table = new JTable(model);
		table.setBackground(Color.WHITE);
		table.getColumnModel().getColumn(0).setWidth(20);
		table.getColumnModel().getColumn(1).setWidth(300);
		table.getColumnModel().getColumn(2).setWidth(45);
		table.setRowHeight(25);
		table.setShowVerticalLines(false);
		scrollPane.setViewportView(table);



		callPostList_btn = new JButton("\uC870\uD68C");
		callPostList_btn.setBounds(442, 72, 70, 30);
		panel_1.add(callPostList_btn);

		listView_btn.addActionListener(new getListViewListener());
		reviewView_btn.addActionListener(new getReviewListViewListener());
		write_btn.addActionListener(new getPostWritingViewListener());
		

	}

	public void setTableModel(Object[][] rowDatas) {
		table.setModel(new DefaultTableModel(rowDatas, colNames) {
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.setRowHeight(30);

	}

	public MainFrame getWin() {
		return win;
	}
	
	public JTable getTable() {
		return table;
	}

	public JButton getCallPostListBtn() {
		return callPostList_btn;
	}

	public String getGu() {
		return gu_comboBox.getSelectedItem().toString();
	}

	public String getDong() {
		return dong_comboBox.getSelectedItem().toString();
	}

	public void setGu(String[] guList) {
		for (int i = 1; i < gu_comboBox.getItemCount(); i++) {
			gu_comboBox.removeItemAt(i);
		}
		for (int i = 0; i < guList.length; i++) {
			gu_comboBox.addItem(guList[i]);
		}
	}

	public void setDong(String[] guList) {
		dong_comboBox.removeAllItems();
		for (int i = 0; i < guList.length; i++) {
			dong_comboBox.addItem(guList[i]);
		}
	}

	public void callDistrictListener(ActionListener listener) {
		si_comboBox.addActionListener(listener);
	}

	public void callDongListener(ActionListener listener) {
		gu_comboBox.addActionListener(listener);
	}

	public void callPostListListener(ActionListener listener) {
		callPostList_btn.addActionListener(listener);
	}
	
	public void callPostViewListener(MouseListener listener) {
		table.addMouseListener(listener);
	}
	
	public void callMyPostListListener(ActionListener listener) {
		myPost_btn.addActionListener(listener);
	}


	class getReviewListViewListener implements ActionListener { // 버튼 키 눌리면 리뷰뷰 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("ReviewListView");
		}
	}

	class getListViewListener implements ActionListener { // 버튼 키 눌리면 리스트뷰 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("ListView");
		}
	}

	class getPostWritingViewListener implements ActionListener { // 버튼 키 눌리면 글쓰기뷰 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			win.getPostWritingView().changePostBtn();
			win.change("PostWritingView");
		}
	}


}
