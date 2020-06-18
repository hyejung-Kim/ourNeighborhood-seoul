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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import core.DTO.AdminDistrict;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.DefaultComboBoxModel;

public class SignUpView extends JPanel {
	Color bgColor = new Color(255, 216, 225);
	BufferedImage img;

	private MainFrame win;

	private JPanel panel_1;
	private JButton signUp_btn;
	private JTextField id_textField;
	private JPasswordField pwd_textField;
	private JTextField name_textField;
	private JComboBox si_comboBox;
	private JComboBox gu_comboBox;
	private JComboBox dong_comboBox;
	private JTextField phoneNum_textField;

	/**
	 * Create the panel.
	 */
	public SignUpView(MainFrame win) {
		this.win = win;
		setLayout(null);
		setBounds(0, 0, 980, 760);
		setBackground(bgColor);

		try {
			img = ImageIO.read(new File("·Î°í.png"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel logo = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(img, 0, 0, getWidth(), getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
			}
		};
		logo.setBackground(bgColor);
		logo.setBounds(12, 10, 188, 146);
		add(logo);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(212, 10, 760, 740);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(108, 224, 57, 32);
		panel_1.add(lblId);
		lblId.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));

		id_textField = new JTextField();
		id_textField.setBounds(183, 224, 207, 32);
		panel_1.add(id_textField);
		id_textField.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));

		JLabel lblPwd = new JLabel("PWD");
		lblPwd.setBounds(108, 288, 57, 32);
		panel_1.add(lblPwd);
		lblPwd.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));

		pwd_textField = new JPasswordField();
		pwd_textField.setBounds(183, 288, 207, 32);
		panel_1.add(pwd_textField);
		pwd_textField.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));

		signUp_btn = new JButton("\uAC00\uC785\uD558\uAE30");
		signUp_btn.setBounds(605, 688, 143, 42);
		panel_1.add(signUp_btn);
		signUp_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		signUp_btn.setBorderPainted(false);
		signUp_btn.setBackground(new Color(255, 216, 225));

		JLabel label = new JLabel("\uC8FC\uC18C");
		label.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label.setBounds(108, 418, 57, 32);
		panel_1.add(label);

		JLabel label_1 = new JLabel("\uC774\uB984");
		label_1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_1.setBounds(108, 354, 57, 32);
		panel_1.add(label_1);

		name_textField = new JTextField();
		name_textField.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));
		name_textField.setBounds(183, 354, 207, 32);
		panel_1.add(name_textField);

		si_comboBox = new JComboBox();
		si_comboBox.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));
		si_comboBox.setModel(new DefaultComboBoxModel(new String[] { "\uC11C\uC6B8\uD2B9\uBCC4\uC2DC" }));
		si_comboBox.setBounds(183, 418, 136, 32);
		si_comboBox.setBackground(Color.WHITE);
		panel_1.add(si_comboBox);

		gu_comboBox = new JComboBox();
		gu_comboBox.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));
		gu_comboBox.setBounds(331, 418, 136, 32);
		gu_comboBox.setBackground(Color.WHITE);
		panel_1.add(gu_comboBox);

		dong_comboBox = new JComboBox();
		dong_comboBox.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));
		dong_comboBox.setBounds(479, 418, 136, 32);
		dong_comboBox.setBackground(Color.WHITE);
		panel_1.add(dong_comboBox);

		JLabel label_2 = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		label_2.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 24));
		label_2.setBounds(35, 20, 130, 42);
		panel_1.add(label_2);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(25, 60, 723, 2);
		panel_1.add(separator);

		JLabel label_3 = new JLabel("\uC804\uD654\uBC88\uD638");
		label_3.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		label_3.setBounds(93, 479, 72, 32);
		panel_1.add(label_3);

		phoneNum_textField = new JTextField();
		phoneNum_textField.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Regular", Font.PLAIN, 14));
		phoneNum_textField.setBounds(183, 479, 207, 32);
		panel_1.add(phoneNum_textField);
		
		JLabel label_4 = new JLabel("000-0000-0000 \uD615\uC2DD\uC73C\uB85C \uC785\uB825\uD558\uC138\uC694");
		label_4.setForeground(Color.BLUE);
		label_4.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Light", Font.PLAIN, 13));
		label_4.setBounds(402, 479, 213, 32);
		panel_1.add(label_4);

		JButton login_btn = new JButton("\uB85C\uADF8\uC778");
		login_btn.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå ExtraBold", Font.PLAIN, 20));
		login_btn.setBorderPainted(false);
		login_btn.setBackground(new Color(255, 216, 225));
		login_btn.setBounds(0, 307, 213, 42);
		add(login_btn);

		login_btn.addActionListener(new getLoginViewListener());
	}

	public MainFrame getWin() {
		return win;
	}

	public String getId() {
		return id_textField.getText();
	}

	public String getPwd() {
		return pwd_textField.getText();
	}

	public String getName() {
		return name_textField.getText();
	}

	public String getPhoneNum() {
		return phoneNum_textField.getText();
	}

	public String getGu() {
		return gu_comboBox.getSelectedItem().toString();
	}

	public String getDong() {
		return dong_comboBox.getSelectedItem().toString();
	}



	public void setId(String id) {
		id_textField.setText(id);
	}

	public void setPwd(String pwd) {
		pwd_textField.setText(pwd);
	}

	public void setName(String name) {
		name_textField.setText(name);
	}

	public void getPhoneNum(String num) {
		phoneNum_textField.setText(num);
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
	


	
	
	public void SignUpListener(ActionListener listener) {
		signUp_btn.addActionListener(listener);
	}

	class getLoginViewListener implements ActionListener { // ¹öÆ° Å° ´­¸®¸é ·Î±×ÀÎºä È£Ãâ
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("LoginView");
		}
	}
}