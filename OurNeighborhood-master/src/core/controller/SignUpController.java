package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import core.Network;
import core.DTO.AdminDistrict;
import core.view.*;

public class SignUpController {
	private SignUpView view;
	private Network network;
	private MainFrame win;

	public SignUpController(SignUpView view) {
		this.view = view;
		this.win = this.view.getWin();
		this.network = this.win.getNetwork();
		this.view.callDistrictListener(new addGuListener());
		this.view.callDongListener(new addDongListener());
		this.view.SignUpListener(new signUpListener());
	}

	class addGuListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			AdminDistrict[] districtsResult;
			try {
				districtsResult = new AdminDistrict[network.getAdminDistricts().length];
				districtsResult = network.getAdminDistricts();
				String[] guList = new String[25];
				int cnt = 0;

				guList[cnt] = districtsResult[0].getAutoRegion();
				for (int i = 1; i < districtsResult.length; i++) {
					if (!guList[cnt].equals(districtsResult[i].getAutoRegion())) {
						cnt++;
						guList[cnt] = districtsResult[i].getAutoRegion();
						
					}
				}

				view.setGu(guList);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	
	class addDongListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			AdminDistrict[] districtsResult;
			String d = view.getGu();
			try {
				districtsResult = new AdminDistrict[network.getAdminDistrict(d).length];
				districtsResult = network.getAdminDistrict(d);
				String[] dongList = new String[districtsResult.length];
				for (int i = 0; i < districtsResult.length; i++) {
					dongList[i] = districtsResult[i].getAdminDong();
				}

				view.setDong(dongList);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	class signUpListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = view.getId();
			String pwd = view.getPwd();
			String name = view.getName();
			String gu = view.getGu();
			String dong = view.getDong();
			String phoneNum = view.getPhoneNum();

			boolean signUpResult;
			try {
				signUpResult = network.signUp(id, pwd, name, gu, dong, phoneNum);

				if (signUpResult == true) { // 가입성공
					JOptionPane.showMessageDialog(null, "가입성공");
					win.change("LoginView");
				} else { // 가입실패
					JOptionPane.showMessageDialog(null, "정보를 제대로 입력하세요.");
				}

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}
