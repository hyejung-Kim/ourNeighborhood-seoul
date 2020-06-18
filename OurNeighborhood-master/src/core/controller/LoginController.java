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

			if (loginResult[0] == true && loginResult[1] == false) { // �Ϲ�ȸ��
				JOptionPane.showMessageDialog(null, "ȸ���Դϴ�.");
				win.change("ListView");
				win.setId(id);
				win.setTitle("�츮���׾!! ::" +id+"��");

			} else if (loginResult[0] == true && loginResult[1] == true) {
				JOptionPane.showMessageDialog(null, "�������Դϴ�.");
				win.change("ListView");
				win.setId(id);
				win.setTitle("�츮���׾!! ::" +id+"��");
				
			} else if (loginResult[0] == false && loginResult[1] == true) {
				JOptionPane.showMessageDialog(null, "�ߺ� �α��� �Դϴ�.");
			} else if (loginResult[0] == false && loginResult[1] == false) {
				JOptionPane.showMessageDialog(null, "���̵� Ȥ�� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			}

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
