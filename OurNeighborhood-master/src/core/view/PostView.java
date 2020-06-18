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
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class PostView extends JPanel {
	Color bgColor = new Color(255,216,225);
	BufferedImage img;
	private MainFrame win;
	private JTextField title_textField;
	private JTextArea content_textArea;
	private JTextField writer_textField;
	private JButton update_btn;
	private JButton delete_btn;
	private JButton comment_btn;
	private JTextField postNum_textField;

	/**
	 * Create the panel.
	 */
	public PostView(MainFrame win) {
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
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(25, 60, 723, 2);
		panel_1.add(separator);
		
		JLabel lblWpahr = new JLabel("\uC81C\uBAA9");
		lblWpahr.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		lblWpahr.setBounds(35, 20, 57, 32);
		panel_1.add(lblWpahr);
		
		title_textField = new JTextField();
		title_textField.setEditable(false);
		title_textField.setBounds(101, 20, 636, 32);
		panel_1.add(title_textField);
		title_textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\uB0B4\uC6A9");
		label_1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_1.setBounds(35, 125, 57, 32);
		panel_1.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 160, 702, 508);
		panel_1.add(scrollPane);
		
		content_textArea = new JTextArea();
		content_textArea.setEditable(false);
		content_textArea.setLineWrap(true);
		scrollPane.setViewportView(content_textArea);
		
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
		
		JLabel label = new JLabel("\uC791\uC131\uC790");
		label.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label.setBounds(310, 72, 57, 32);
		panel_1.add(label);
		
		writer_textField = new JTextField();
		writer_textField.setEditable(false);
		writer_textField.setColumns(10);
		writer_textField.setBounds(376, 72, 181, 32);
		panel_1.add(writer_textField);
		
		JLabel label_2 = new JLabel("\uAE00\uBC88\uD638");
		label_2.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_2.setBounds(35, 72, 57, 32);
		panel_1.add(label_2);
		
		postNum_textField = new JTextField();
		postNum_textField.setEditable(false);
		postNum_textField.setColumns(10);
		postNum_textField.setBounds(101, 72, 181, 32);
		panel_1.add(postNum_textField);
		
		comment_btn = new JButton("\uB313\uAE00 \uBCF4\uAE30");
		comment_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		comment_btn.setBorderPainted(false);
		comment_btn.setBackground(new Color(255, 216, 225));
		comment_btn.setBounds(25, 688, 113, 42);
		panel_1.add(comment_btn);
		
		comment_btn.addActionListener(new getCommentViewListener());

	}
	
	
	public MainFrame getWin() {
		return win;
	}
	
	public JButton getCommentBtn() {
		return comment_btn;
	}
	
	public String getPostNum() {
		return postNum_textField.getText();
	}
	
	public String getTitle() {
		return title_textField.getText();
	}
	
	public String getContent() {
		// TODO Auto-generated method stub
		return content_textArea.getText();
	}
	
	public void setPostNum(String s) {
		postNum_textField.setText(s);
	}

	public void setTitle(String s) {
		title_textField.setText(s);
	}
	
	public void setWriter(String s) {
		writer_textField.setText(s);
	}
	
	public void setContent(String s) {
		content_textArea.setText(s);
	}
	
	public void imWriter() {
		update_btn.setVisible(true);
		delete_btn.setVisible(true);
	}
	public void imNotWriter() {
		update_btn.setVisible(false);
		delete_btn.setVisible(false);
	}
	
	
	
	public void deletePostListener(ActionListener listener) {
		delete_btn.addActionListener(listener);
	}
	
	public void getCommentListener(ActionListener listener) {
		 comment_btn.addActionListener(listener);
	}
	
	public void updatePostListener(ActionListener listener) {
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
	
	
	class getCommentViewListener implements ActionListener { // ¹öÆ° Å° ´­¸®¸é ´ñ±Ûºä È£Ãâ
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("CommentView");
        }
    }




}
