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

public class ReviewWritingView extends JPanel {
	Color bgColor = new Color(255,216,225);
	BufferedImage img;
	private MainFrame win;
	
	private JTextArea comment_textArea;
	private JButton post_btn;
	private JButton update_btn;
	private JTextField si_textField;
	private JTextField gu_textField;
	private JTextField dong_textField;
	private JComboBox leisure_comboBox;
	private JComboBox transport_comboBox;
	private JComboBox commercial_comboBox;
	private JComboBox housePrice_comboBox; 

	/**
	 * Create the panel.
	 */
	public ReviewWritingView(MainFrame win) {
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
		label.setBounds(35, 73, 57, 32);
		panel_1.add(label);
		
		JLabel label_2 = new JLabel("\uB9AC\uBDF0 \uC4F0\uAE30");
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
		comment_textArea.setLineWrap(true);
		scrollPane.setViewportView(comment_textArea);
		
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
		
		si_textField = new JTextField();
		si_textField.setText("\uC11C\uC6B8\uD2B9\uBCC4\uC2DC\r\n");
		si_textField.setColumns(10);
		si_textField.setBounds(101, 72, 105, 32);
		panel_1.add(si_textField);
		
		gu_textField = new JTextField();
		gu_textField.setColumns(10);
		gu_textField.setBounds(207, 72, 105, 32);
		panel_1.add(gu_textField);
		
		dong_textField = new JTextField();
		dong_textField.setColumns(10);
		dong_textField.setBounds(313, 72, 105, 32);
		panel_1.add(dong_textField);
		
		JLabel label_3 = new JLabel("\uC5EC\uAC00\uC810\uC218");
		label_3.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_3.setBounds(35, 134, 78, 32);
		panel_1.add(label_3);
		
		leisure_comboBox = new JComboBox();
		leisure_comboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "4", "3", "2", "1"}));
		leisure_comboBox.setBounds(115, 134, 50, 32);
		panel_1.add(leisure_comboBox);
		
		JLabel label_4 = new JLabel("\uAD50\uD1B5\uC810\uC218");
		label_4.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_4.setBounds(182, 134, 78, 32);
		panel_1.add(label_4);
		
		transport_comboBox = new JComboBox();
		transport_comboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "4", "3", "2", "1"}));
		transport_comboBox.setBounds(262, 134, 50, 32);
		panel_1.add(transport_comboBox);
		
		JLabel label_5 = new JLabel("\uC0C1\uAD8C\uC810\uC218");
		label_5.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_5.setBounds(324, 134, 78, 32);
		panel_1.add(label_5);
		
		commercial_comboBox = new JComboBox();
		commercial_comboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "4", "3", "2", "1"}));
		commercial_comboBox.setBounds(404, 134, 50, 32);
		panel_1.add(commercial_comboBox);
		
		JLabel label_6 = new JLabel("\uC9D1\uAC12\uC810\uC218");
		label_6.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_6.setBounds(466, 134, 78, 32);
		panel_1.add(label_6);
		
		housePrice_comboBox = new JComboBox();
		housePrice_comboBox.setModel(new DefaultComboBoxModel(new String[] {"5", "4", "3", "2", "1"}));
		housePrice_comboBox.setBounds(546, 134, 50, 32);
		panel_1.add(housePrice_comboBox);
		update_btn.setVisible(false);

	}
	
	
	public MainFrame getWin() {
		return win;
	}

	
	public String getComment() {
		return comment_textArea.getText();
	}
	
	
	public void changeUpdateBtn() {
		post_btn.setVisible(false);
		update_btn.setVisible(true);
	}
	public void changePostBtn() {
		post_btn.setVisible(true);
		update_btn.setVisible(false);
	}

	public void setGu(String gu) {
		gu_textField.setText(gu);
	}
	
	public void setDong(String dong) {
		dong_textField.setText(dong);
	}
	
	public String getDong() {
		return dong_textField.getText();
	}
	
	
	public void setComment_textArea(String comment) {
		this.comment_textArea.setText(comment);
	}


	public int getLeisureRating() {
		return Integer.parseInt(leisure_comboBox.getSelectedItem().toString());
	}


	public int getTransportRating() {
		return Integer.parseInt(transport_comboBox.getSelectedItem().toString());
	}


	public int getCommercialRating() {
		return Integer.parseInt(commercial_comboBox.getSelectedItem().toString());
	}


	public int getHousePriceRating() {
		return Integer.parseInt(housePrice_comboBox.getSelectedItem().toString());
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
	
	class getBoardViewListener implements ActionListener { // ¹öÆ° Å° ´­¸®¸é °Ô½ÃÆÇºä È£Ãâ
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("BoardView");
        }
    }
}
