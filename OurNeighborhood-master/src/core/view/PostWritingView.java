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
import javax.swing.JScrollPane;
import javax.swing.JTextField;


import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class PostWritingView extends JPanel {
	Color bgColor = new Color(255,216,225);
	BufferedImage img;
	private MainFrame win;
	private JTextField title_textField;
	
	private JComboBox si_comboBox;
	private JComboBox gu_comboBox;
	private JComboBox dong_comboBox;
	
	private JTextArea content_textArea;
	private JButton post_btn;
	private JButton update_btn;

	/**
	 * Create the panel.
	 */
	public PostWritingView(MainFrame win) {
		this.win=win;
		setLayout(null);
		setBounds(0, 0, 980, 760);
		setBackground(bgColor);
		
		try {
			img = ImageIO.read(new File("·Î°í.png"));
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		JPanel panel = new JPanel(){
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
			}			
		};
		panel.setBackground(bgColor);
		panel.setBounds(12, 10, 188, 146);
		add(panel);
		
		
		JButton listView_btn = new JButton("\uB3D9\uB124\uCC3E\uAE30");
		listView_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 30));
		listView_btn.setBackground(bgColor);
		listView_btn.setBorderPainted(false);
		listView_btn.setBounds(0, 299, 211, 42);
		add(listView_btn);
		
		JButton reviewView_btn = new JButton("\uB3D9\uB124\uB9AC\uBDF0");
		reviewView_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 30));
		reviewView_btn.setBorderPainted(false);
		reviewView_btn.setBackground(new Color(255, 216, 225));
		reviewView_btn.setBounds(0, 400, 211, 42);
		add(reviewView_btn);
		
		JButton boardView_btn = new JButton("\uCEE4\uBBA4\uB2C8\uD2F0");
		boardView_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 30));
		boardView_btn.setBorderPainted(false);
		boardView_btn.setBackground(new Color(255, 216, 225));
		boardView_btn.setBounds(0, 499, 211, 42);
		add(boardView_btn);
		
		
		listView_btn.addActionListener(new getListViewListener());
		reviewView_btn.addActionListener(new getReviewListViewListener());
		boardView_btn.addActionListener(new getBoardViewListener());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(212, 10, 760, 740);
		add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("\uAC8C\uC2DC\uD310");
		label.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label.setBounds(35, 75, 57, 32);
		panel_1.add(label);
		
		si_comboBox = new JComboBox();
		si_comboBox.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));
		si_comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC11C\uC6B8\uD2B9\uBCC4\uC2DC"}));
		si_comboBox.setBounds(101, 75, 105, 32);
		si_comboBox.setBackground(Color.WHITE);
		panel_1.add(si_comboBox);
		
		gu_comboBox = new JComboBox();
		gu_comboBox.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));
		gu_comboBox.setBounds(218, 75, 105, 32);
		gu_comboBox.setBackground(Color.WHITE);
		panel_1.add(gu_comboBox);
		
		dong_comboBox = new JComboBox();
		dong_comboBox.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));
		dong_comboBox.setBounds(335, 75, 105, 32);
		dong_comboBox.setBackground(Color.WHITE);
		panel_1.add(dong_comboBox);
		
		JLabel label_2 = new JLabel("\uAC8C\uC2DC\uAE00\uC4F0\uAE30");
		label_2.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 24));
		label_2.setBounds(35, 20, 130, 42);
		panel_1.add(label_2);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(25, 60, 723, 2);
		panel_1.add(separator);
		
		JLabel lblWpahr = new JLabel("\uC81C\uBAA9");
		lblWpahr.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		lblWpahr.setBounds(35, 117, 57, 32);
		panel_1.add(lblWpahr);
		
		title_textField = new JTextField();
		title_textField.setBounds(101, 117, 492, 32);
		panel_1.add(title_textField);
		title_textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\uB0B4\uC6A9");
		label_1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_1.setBounds(35, 171, 57, 32);
		panel_1.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 208, 702, 460);
		panel_1.add(scrollPane);
		
		content_textArea = new JTextArea();
		content_textArea.setLineWrap(true);
		scrollPane.setViewportView(content_textArea);
		
		post_btn = new JButton("\uB4F1\uB85D");
		post_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		post_btn.setBorderPainted(false);
		post_btn.setBackground(new Color(255, 216, 225));
		post_btn.setBounds(658, 688, 90, 42);
		panel_1.add(post_btn);
		
		update_btn = new JButton("\uC218\uC815");
		update_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		update_btn.setBorderPainted(false);
		update_btn.setBackground(new Color(255, 216, 225));
		update_btn.setBounds(658, 688, 90, 42);
		panel_1.add(update_btn);
		update_btn.setVisible(false);

	}
	
	
	public MainFrame getWin() {
		return win;
	}

	public String getTitle() {
		return title_textField.getText();
	}
	
	public void setTitle(String title)  {
		title_textField.setText(title);
	}
	
	public void setContent(String content)  {
		content_textArea.setText(content);
	}
	
	public String getContent() {
		return content_textArea.getText();
	}
	
	public String getGu() {
		return gu_comboBox.getSelectedItem().toString();
	}

	public String getDong() {
		return dong_comboBox.getSelectedItem().toString();
	}
	
	public void changeUpdateBtn() {
		post_btn.setVisible(false);
		update_btn.setVisible(true);
	}
	public void changePostBtn() {
		post_btn.setVisible(true);
		update_btn.setVisible(false);
	}

	
	public void setGu(String[] guList) {
		gu_comboBox.removeAllItems();
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
	public void postListener(ActionListener listener) {
		post_btn.addActionListener(listener);
	}
	public void updateListener(ActionListener listener) {
		update_btn.addActionListener(listener);
	}
	
	
	class getReviewListViewListener implements ActionListener { // ¹öÆ° Å° ´­¸®¸é ¸®ºäºä È£Ãâ
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("ReviewListView");
        }
    }
	
	class getListViewListener implements ActionListener { // ¹öÆ° Å° ´­¸®¸é ¸®½ºÆ®ºä È£Ãâ
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("ListView");
        }
    }
	
	class getBoardViewListener implements ActionListener { // ¹öÆ° Å° ´­¸®¸é ¸®½ºÆ®ºä È£Ãâ
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("BoardView");
        }
    }
}
