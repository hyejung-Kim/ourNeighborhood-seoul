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

public class ReviewView extends JPanel {
	Color bgColor = new Color(255,216,225);
	BufferedImage img;
	private MainFrame win;
	private JTextField dong_textField;
	private JButton update_btn;
	private JButton delete_btn;
	private JTextField reviewNum_textField;
	private JTextField writer_textField;
	private JTextArea comment_textArea;
	private JTextField leisure_textField;
	private JTextField transport_textField;
	private JTextField commercial_textField;
	private JTextField housePrice_textField;
	private JTextField average_textField;

	/**
	 * Create the panel.
	 */
	public ReviewView(MainFrame win) {
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
		
		JLabel label = new JLabel("\uC9C0\uC5ED");
		label.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label.setBounds(35, 75, 57, 32);
		panel_1.add(label);
		
		JLabel label_2 = new JLabel("\uB9AC\uBDF0");
		label_2.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 24));
		label_2.setBounds(35, 20, 130, 42);
		panel_1.add(label_2);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(25, 60, 723, 2);
		panel_1.add(separator);
		
		JLabel lblWpahr = new JLabel("\uD55C\uC904\uD3C9");
		lblWpahr.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		lblWpahr.setBounds(35, 219, 57, 32);
		panel_1.add(lblWpahr);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 261, 702, 384);
		panel_1.add(scrollPane);
		
		comment_textArea = new JTextArea();
		scrollPane.setViewportView(comment_textArea);
		
		
		update_btn = new JButton("\uC218\uC815");
		update_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		update_btn.setBorderPainted(false);
		update_btn.setBackground(new Color(255, 216, 225));
		update_btn.setBounds(658, 688, 90, 42);
		panel_1.add(update_btn);
		update_btn.setVisible(false);
		
		delete_btn = new JButton("\uC0AD\uC81C");
		delete_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		delete_btn.setBorderPainted(false);
		delete_btn.setBackground(new Color(255, 216, 225));
		delete_btn.setBounds(560, 688, 90, 42);
		panel_1.add(delete_btn);
		delete_btn.setVisible(false);
		
		dong_textField = new JTextField();
		dong_textField.setEditable(false);
		dong_textField.setText("\uAC1C\uBD091\uB3D9");
		dong_textField.setColumns(10);
		dong_textField.setBounds(115, 75, 105, 32);
		panel_1.add(dong_textField);
		
		reviewNum_textField = new JTextField();
		reviewNum_textField.setEditable(false);
		reviewNum_textField.setColumns(10);
		reviewNum_textField.setBounds(506, 20, 69, 32);
		panel_1.add(reviewNum_textField);
		
		writer_textField = new JTextField();
		writer_textField.setEditable(false);
		writer_textField.setColumns(10);
		writer_textField.setBounds(653, 20, 95, 32);
		panel_1.add(writer_textField);
		
		JLabel label_3 = new JLabel("\uC5EC\uAC00\uC810\uC218");
		label_3.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_3.setBounds(35, 134, 78, 32);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("\uAD50\uD1B5\uC810\uC218");
		label_4.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_4.setBounds(182, 134, 78, 32);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("\uC0C1\uAD8C\uC810\uC218");
		label_5.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_5.setBounds(324, 134, 78, 32);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("\uC9D1\uAC12\uC810\uC218");
		label_6.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_6.setBounds(466, 133, 78, 32);
		panel_1.add(label_6);
		
		JLabel label_1 = new JLabel("\uB9AC\uBDF0\uBC88\uD638");
		label_1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_1.setBounds(416, 20, 78, 32);
		panel_1.add(label_1);
		
		JLabel label_7 = new JLabel("\uC791\uC131\uC790");
		label_7.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_7.setBounds(586, 20, 78, 32);
		panel_1.add(label_7);
		
		leisure_textField = new JTextField();
		leisure_textField.setEditable(false);
		leisure_textField.setColumns(10);
		leisure_textField.setBounds(115, 134, 50, 32);
		panel_1.add(leisure_textField);
		
		transport_textField = new JTextField();
		transport_textField.setEditable(false);
		transport_textField.setColumns(10);
		transport_textField.setBounds(262, 134, 50, 32);
		panel_1.add(transport_textField);
		
		commercial_textField = new JTextField();
		commercial_textField.setEditable(false);
		commercial_textField.setColumns(10);
		commercial_textField.setBounds(404, 134, 50, 32);
		panel_1.add(commercial_textField);
		
		housePrice_textField = new JTextField();
		housePrice_textField.setEditable(false);
		housePrice_textField.setColumns(10);
		housePrice_textField.setBounds(544, 134, 50, 32);
		panel_1.add(housePrice_textField);
		
		JLabel label_8 = new JLabel("\uD3C9\uC810\uD3C9\uADE0");
		label_8.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_8.setBounds(272, 75, 78, 32);
		panel_1.add(label_8);
		
		average_textField = new JTextField();
		average_textField.setEditable(false);
		average_textField.setColumns(10);
		average_textField.setBounds(352, 75, 50, 32);
		panel_1.add(average_textField);
		
		update_btn.addActionListener(new getReviewWritingViewListener());

	}
	


	public String getReviewNum() {
		return reviewNum_textField.getText();
	}
	
	public String getDong() {
		return dong_textField.getText();
	}
	
	public void setReviewNum(String s) {
		reviewNum_textField.setText(s);
	}

	public void setWriter(String s) {
		writer_textField.setText(s);
	}
	
	public void setComment(String s) {
		comment_textArea.setText(s);
	}
	
	public void imWriter() {
		update_btn.setVisible(true);
		delete_btn.setVisible(true);
	}
	public void imNotWriter() {
		update_btn.setVisible(false);
		delete_btn.setVisible(false);
	}
	
	
	public MainFrame getWin() {
		return win;
	}

	
	public String getComment() {
		return comment_textArea.getText();
	}
	
	
	
	
	public String getWriter() {
		return writer_textField.getText();
	}



	public String getLeisure() {
		return leisure_textField.getText();
	}



	public String getTransport() {
		return transport_textField.getText();
	}



	public String getCommercial() {
		return commercial_textField.getText();
	}



	public String getHousePrice() {
		return housePrice_textField.getText();
	}



	public void setLeisure(String rating) {
		this.leisure_textField.setText(rating);
	}



	public void setTransport(String rating) {
		this.transport_textField.setText(rating);
	}



	public void setCommercial(String rating) {
		this.commercial_textField.setText(rating);
	}



	public void setHousePrice(String rating) {
		this.housePrice_textField.setText(rating);
	}

	
	
	
	public String getAverage() {
		return average_textField.getText();
	}



	public void setAverage(String average) {
		this.average_textField.setText(average);
	}



	public void setDong(String dong) {
		dong_textField.setText(dong);
	}
	
	public void callDongListener(ActionListener listener) {
		dong_textField.addActionListener(listener);
	}
	public void deleteReviewListener(ActionListener listener) {
		delete_btn.addActionListener(listener);
	}
	public void updateReviewListener(ActionListener listener) {
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
	
	class getReviewWritingViewListener implements ActionListener { // ¹öÆ° Å° ´­¸®¸é ±Û¾²±âºä È£Ãâ
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("ReviewWritingView");
		}
	}




}
