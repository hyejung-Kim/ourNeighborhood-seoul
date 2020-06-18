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

import core.DTO.Comment;
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
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class CommentView extends JPanel {

	Color bgColor = new Color(255, 216, 225);
	BufferedImage img;
	private MainFrame win;
	private JTable table;
	private String[] colNames;
	private DefaultTableModel model;
	private Comment[] commentList = new Comment[30];
	private JButton returnPost_btn;
	private JButton comment_btn;
	private JButton update_btn;
	private JButton delete_btn;
	private JTextPane comment_textPane;
	private String writer;
	private String time;

	/**
	 * Create the panel.
	 */
	public CommentView(MainFrame win) {
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

		JLabel label_2 = new JLabel("\uB313\uAE00");
		label_2.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 24));
		label_2.setBounds(35, 20, 130, 42);
		panel_1.add(label_2);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(25, 60, 723, 2);
		panel_1.add(separator);

		returnPost_btn = new JButton("\uC6D0\uBB38 \uBCF4\uAE30");
		returnPost_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 16));
		returnPost_btn.setBorderPainted(false);
		returnPost_btn.setBackground(new Color(255, 216, 225));
		returnPost_btn.setBounds(631, 20, 105, 32);
		panel_1.add(returnPost_btn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 89, 712, 441);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.getViewport().setBackground(Color.WHITE);
		panel_1.add(scrollPane);

		colNames = new String[] { "작성자", "내용", "작성일시" };
		model = new DefaultTableModel(colNames, 0);
		table = new JTable(model);
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);


		table.setRowHeight(35);
		table.setShowVerticalLines(false);
		
		comment_textPane = new JTextPane();
		comment_textPane.setBounds(25, 540, 712, 138);
		comment_textPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.add(comment_textPane);
		
		comment_btn = new JButton("\uB313\uAE00 \uC791\uC131");
		comment_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		comment_btn.setBorderPainted(false);
		comment_btn.setBackground(new Color(255, 216, 225));
		comment_btn.setBounds(615, 688, 121, 42);
		panel_1.add(comment_btn);
		
		update_btn = new JButton("\uC218\uC815");
		update_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		update_btn.setBorderPainted(false);
		update_btn.setBackground(new Color(255, 216, 225));
		update_btn.setBounds(658, 688, 90, 42);
		panel_1.add(update_btn);
		update_btn.setVisible(false);
		
		delete_btn = new JButton("\uC0AD\uC81C");
		delete_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		delete_btn.setBorderPainted(false);
		delete_btn.setBackground(new Color(255, 216, 225));
		delete_btn.setBounds(560, 688, 90, 42);
		panel_1.add(delete_btn);
		delete_btn.setVisible(false);
		



		listView_btn.addActionListener(new getListViewListener());
		reviewView_btn.addActionListener(new getReviewListViewListener());
		boardView_btn.addActionListener(new getBoardViewListener());
		returnPost_btn.addActionListener(new getPostViewListener());
		

	}

	public void setTableModel(Object[][] rowDatas) {
		table.setModel(new DefaultTableModel(rowDatas, colNames) {
			boolean[] columnEditables = new boolean[] { false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

	}

	public MainFrame getWin() {
		return win;
	}
	
	public JTable getTable() {
		return table;
	}
	
	
	public String getContent() {
		return comment_textPane.getText();
	}
	
	public void setContent(String comment) {
		comment_textPane.setText(comment);
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String w) {
		writer = w;
	} 
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String t) {
		time = t;
	} 
	
	public void imWriter() {
		comment_btn.setVisible(false);
		update_btn.setVisible(true);
		delete_btn.setVisible(true);
	}
	public void imNotWriter() {
		comment_btn.setVisible(true);
		update_btn.setVisible(false);
		delete_btn.setVisible(false);
	}
	
	public void resetTextpane() {
		imNotWriter();
		setContent("");
		setWriter("");
		setTime("");
	}
	
	public void getCommentListener(MouseListener listener) {
		table.addMouseListener(listener);
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
	
	class getBoardViewListener implements ActionListener { // 버튼 키 눌리면 리스트뷰 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("BoardView");
		}
	}
	
	class getPostViewListener implements ActionListener { // 버튼 키 눌리면 포스트뷰 호출
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("PostView");
		}
	}

	public void postCommentListener(ActionListener listener) {
		// TODO Auto-generated method stub
		comment_btn.addActionListener(listener);	
	}
	
	public void deleteCommentListener(ActionListener listener) {
		// TODO Auto-generated method stub
		delete_btn.addActionListener(listener);	
	}
	
	public void updateCommentListener(ActionListener listener) {
		// TODO Auto-generated method stub
		update_btn.addActionListener(listener);	
	}
}
