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


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ListView extends JPanel {
	Color bgColor = new Color(255,216,225);
	BufferedImage img;
	private MainFrame win;
	
	private JButton search_btn;
	private JComboBox si_comboBox;
	private JComboBox gu_comboBox;
	private JComboBox sort_comboBox;
	private JTable table;
	private String[] colNames;
	private DefaultTableModel model;
	

	/**
	 * Create the panel.
	 */
	public ListView(MainFrame win)  {
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
		

		JPanel logo = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
			}			
		};
		logo.setBackground(bgColor);
		logo.setBounds(12, 10, 188, 146);
		add(logo);
		
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
		si_comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC11C\uC6B8\uD2B9\uBCC4\uC2DC"}));
		si_comboBox.setBounds(91, 72, 105, 32);
		si_comboBox.setBackground(Color.WHITE);
		panel_1.add(si_comboBox);
		
		gu_comboBox = new JComboBox();
		gu_comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC120\uD0DD"}));
		gu_comboBox.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 14));
		gu_comboBox.setBounds(208, 72, 105, 32);
		gu_comboBox.setBackground(Color.WHITE);
		panel_1.add(gu_comboBox);
		
		JLabel label_2 = new JLabel("\uB3D9\uB124\uCC3E\uAE30");
		label_2.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 24));
		label_2.setBounds(35, 20, 130, 42);
		panel_1.add(label_2);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(25, 60, 723, 2);
		panel_1.add(separator);
		
		sort_comboBox = new JComboBox();
		sort_comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uD3C9\uC810\uD3C9\uADE0\uC21C", "\uC5EC\uAC00\uD3C9\uC810\uC21C", "\uAD50\uD1B5\uD3C9\uC810\uC21C", "\uC0C1\uAD8C\uD3C9\uC810\uC21C", "\uC9D1\uAC12\uD3C9\uC810\uC21C"}));
		sort_comboBox.setFont(new Font("나눔스퀘어라운드 Regular", Font.PLAIN, 14));
		sort_comboBox.setBackground(Color.WHITE);
		sort_comboBox.setBounds(618, 72, 119, 32);
		panel_1.add(sort_comboBox);
		
		search_btn = new JButton("\uC870\uD68C");
		search_btn.setBounds(325, 72, 72, 32);
		panel_1.add(search_btn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 126, 712, 544);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.getViewport().setBackground(Color.WHITE);
		panel_1.add(scrollPane);
		
		colNames = new String[] { "자치구", "행정동","평균평점", "여가평점", "교통평점", "상권평점", "집값평점", "최다가구형태" };
		model = new DefaultTableModel(colNames, 0);
		table = new JTable(model);
		table.setRowHeight(30);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);

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
		
		reviewView_btn.addActionListener(new getReviewListViewListener());
		boardView_btn.addActionListener(new getBoardViewListener());
	}
	
	public void setTableModel(Object[][] rowDatas) {
		table.setModel(new DefaultTableModel(rowDatas, colNames) {
			boolean[] columnEditables = new boolean[] { false, false, false, false,false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

	}
	
	
	public MainFrame getWin() {
		return win;
	}

	public void setWin(MainFrame win) {
		this.win = win;
	}
	
	public String getGu() {
		return gu_comboBox.getSelectedItem().toString();
	}
	
	public void setGu(String[] guList) {
		gu_comboBox.removeAllItems();
		for (int i = 0; i < guList.length; i++) {
			gu_comboBox.addItem(guList[i]);
		}
	}
	
	public String getCondition() {
		return sort_comboBox.getSelectedItem().toString();
	}

	public void callGuListener(ActionListener listener) {
		si_comboBox.addActionListener(listener);
	}
		
	
	public void searchDistrictListener(ActionListener listener) {
		search_btn.addActionListener(listener);
		sort_comboBox.addActionListener(listener);
	}


	class getReviewListViewListener implements ActionListener { // 버튼 키 눌리면 리뷰뷰 호출
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("ReviewListView");
        }
    }
	
	class getBoardViewListener implements ActionListener { // 버튼 키 눌리면 게시판뷰 호출
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("BoardView");
        }
    }

}