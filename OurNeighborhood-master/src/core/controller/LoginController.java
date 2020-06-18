package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import core.Network;
import core.view.LoginView;
import core.view.MainFrame;

public class LoginController implements ActionListener {
	private LoginView view;
	private Network network;
	private MainFrame win;

	public LoginController(LoginView view) {
		this.view = view;
		this.win = this.view.getWin();
		this.network = this.win.getNetwork();
		this.view.setLoginListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = view.getId();
		String pwd = view.getPwd();

		boolean[] loginResult;
		try {
			loginResult = network.login(id, pwd);

			if (loginResult[0] == true && loginResult[1] == false) { // 일반회원
				JOptionPane.showMessageDialog(null, "회원입니다.");
				win.change("ListView");
				win.setId(id);
				win.setTitle("우리동네어때!! ::" +id+"님");

			} else if (loginResult[0] == true && loginResult[1] == true) {
				JOptionPane.showMessageDialog(null, "관리자입니다.");
				win.change("ListView");
				win.setId(id);
				win.setTitle("우리동네어때!! ::" +id+"님");
				
			} else if (loginResult[0] == false && loginResult[1] == true) {
				JOptionPane.showMessageDialog(null, "중복 로그인 입니다.");
			} else if (loginResult[0] == false && loginResult[1] == false) {
				JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 틀렸습니다.");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
