package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import core.Network;
import core.DTO.AdminDistrict;
import core.DTO.ResidentReview;
import core.view.*;

public class ReviewController {
	private ReviewListView view;
	private Network network;
	private MainFrame win;

	public ReviewController(ReviewListView view) {
		this.view = view;
		this.win = this.view.getWin();
		this.network = this.win.getNetwork();
		this.view.callDistrictListener(new addGuListener());
		this.view.callDongListener(new addDongListener());
		this.view.callReviewListListener(new callReviewListListener());
		this.view.callReviewViewListener(new getReviewViewListener());
		this.view.callMyReviewListListener(new callMyReviewListListener());
		this.view.clickWriteReviewBtnListener(new clickWriteReviewBtnListener());
	}
	
	
	class clickWriteReviewBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				win.getReviewWritingView().setGu(network.getUser().getResidenDistrict());
				win.getReviewWritingView().setDong(network.getUser().getResidenDong());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	


	class getReviewViewListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) { // 더블클릭
				String reviewNum = String.valueOf(view.getTable().getValueAt(view.getTable().getSelectedRow(), 0));
				ResidentReview review;
				try {
					review = network.getReview(reviewNum);
					win.getReviewView().setReviewNum(reviewNum);
					win.getReviewView().setWriter(review.getWriter());
					win.getReviewView().setDong(review.getAdminDong());
					win.getReviewView().setLeisure(Integer.toString(review.getLeisureRating()));
					win.getReviewView().setCommercial(Integer.toString(review.getCommercialRating()));
					win.getReviewView().setTransport(Integer.toString(review.getTransportRating()));
					win.getReviewView().setHousePrice(Integer.toString(review.getHousePriceRating()));
					win.getReviewView().setAverage(Float.toString(review.getRatingAverage()));
					win.getReviewView().setComment(review.getComment());
		
					if (review.getWriter().equals(win.getId())) {
						win.getReviewView().imWriter();
					} else {
						win.getReviewView().imNotWriter();
					}
					win.change("ReviewView");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	class callMyReviewListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ResidentReview[] reviewResult = null;
			try {

				reviewResult = new ResidentReview[network.getMyReviews().length];
				reviewResult = network.getMyReviews();

				Object[][] rowDatas = new Object[reviewResult.length][6];
				for (int i = 0; i < reviewResult.length; i++) {
					rowDatas[i] = new Object[] {
							reviewResult[i].getReviewNumber(),
							reviewResult[i].getAdminDong(),
							reviewResult[i].getRatingAverage(),
							reviewResult[i].getComment(),
							reviewResult[i].getWriter()
					};
				}
				view.setTableModel(rowDatas);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	class callReviewListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ResidentReview[] reviewResult = null;
			try {
				if (view.getGu().equals("전체")) {
					reviewResult = new ResidentReview[network.getReviews().length];
					reviewResult = network.getReviews();
				} else {
					reviewResult = new ResidentReview[network.getReviewsPartially(view.getDong()).length];
					reviewResult = network.getReviewsPartially(view.getDong());
				}

				Object[][] rowDatas = new Object[reviewResult.length][5];
				for (int i = 0; i < reviewResult.length; i++) {
					rowDatas[i] = new Object[] {
							reviewResult[i].getReviewNumber(),
							reviewResult[i].getAdminDong(),
							reviewResult[i].getRatingAverage(),
							reviewResult[i].getComment(),
							reviewResult[i].getWriter()
					};
				}
				view.setTableModel(rowDatas);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

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



}
