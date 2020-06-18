package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import core.Network;
import core.DTO.AdminDistrict;
import core.DTO.DistrictInfra;
import core.controller.SignUpController.addDongListener;
import core.controller.SignUpController.addGuListener;
import core.view.*;

public class ListController {
	private ListView view;
	private Network network;
	private MainFrame win;

	public ListController(ListView view) {
		this.view = view;
		this.win = this.view.getWin();
		this.network = this.win.getNetwork();
		this.view.callGuListener(new addGuListener());
		this.view.searchDistrictListener(new searchDistrictListener());
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

				guList[cnt]=districtsResult[0].getAutoRegion();
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
	
	
	class searchDistrictListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			DistrictInfra[] DistrictInfraResult;
			try {
				System.out.println(view.getGu() + "   " + view.getCondition());
				DistrictInfraResult = new DistrictInfra[network.getDistrictInfra(view.getGu(), view.getCondition()).length];
				DistrictInfraResult = network.getDistrictInfra(view.getGu(), view.getCondition());
				
				System.out.println(DistrictInfraResult.length);
				Object[][] rowDatas = new Object[DistrictInfraResult.length][8];
				for (int i = 0; i < DistrictInfraResult.length; i++) {
					rowDatas[i] = new Object[] { 
							DistrictInfraResult[i].getAutoRegion(),
							DistrictInfraResult[i].getAdminDong(),
							DistrictInfraResult[i].getRatingAverage(),
							DistrictInfraResult[i].getLeisureRating(),
							DistrictInfraResult[i].getTransportRating(),
							DistrictInfraResult[i].getCommercialRating(),
							DistrictInfraResult[i].getHousePriceRating(),
							DistrictInfraResult[i].getMost(),
					};
				}
				view.setTableModel(rowDatas);
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
}
