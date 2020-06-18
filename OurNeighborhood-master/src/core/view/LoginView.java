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

import core.Network;

import javax.swing.JButton;
import javax.swing.JLabel;

public class LoginView extends JPanel {
	Color bgColor = new Color(255,216,225);
	BufferedImage img;
	
	private MainFrame win;
	
	private JTextField id_textField;
	private JPasswordField pwd_textField;
	private JButton login_btn;
	/**
	 * Create the panel.
	 */
	public LoginView(MainFrame win) {
		this.win = win;
        setLayout(null);
		setBounds(0, 0, 980, 760);
		setBackground(bgColor);
		
		id_textField = new JTextField();
		id_textField.setBounds(445, 451, 152, 32);
		add(id_textField);
		id_textField.setColumns(10);
		
		pwd_textField = new JPasswordField();
		pwd_textField.setBounds(445, 514, 152, 32);
		add(pwd_textField);
		pwd_textField.setColumns(10);
		
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
		logo.setBounds(317, 136, 333, 257);
		add(logo);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		lblId.setBounds(386, 451, 57, 32);
		add(lblId);
		
		login_btn = new JButton("\uB85C\uADF8\uC778");
		login_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		login_btn.setBackground(bgColor);
		login_btn.setBorderPainted(false);
		login_btn.setBounds(351, 613, 128, 42);
		add(login_btn);
		
		JButton signUp_btn = new JButton("\uD68C\uC6D0\uAC00\uC785");
		signUp_btn.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));;
		signUp_btn.setBorderPainted(false);
		signUp_btn.setBackground(new Color(255, 216, 225));
		signUp_btn.setBounds(495, 613, 143, 42);
		add(signUp_btn);
		
		JLabel lblPwd = new JLabel("PWD");
		lblPwd.setFont(new Font("나눔스퀘어라운드 ExtraBold", Font.PLAIN, 20));
		lblPwd.setBounds(386, 514, 57, 32);
		add(lblPwd);
		
		
		signUp_btn.addActionListener(new getSignUpViewListener());

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

	public void setId(String id) {
		id_textField.setText(id);
	}

	public void setPwd(String pwd) {
		pwd_textField.setText(pwd);
	}
	
	public void setLoginListener(ActionListener listener) {
		login_btn.addActionListener(listener);
	}



	class getSignUpViewListener implements ActionListener { // 버튼 키 눌리면 회원가입뷰 호출
        @Override
        public void actionPerformed(ActionEvent e) {
            win.change("SignUpView");
        }
    }
	
}







