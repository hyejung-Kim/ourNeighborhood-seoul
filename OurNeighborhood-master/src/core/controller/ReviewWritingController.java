package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import core.Network;
import core.DTO.AdminDistrict;
import core.DTO.ResidentReview;
import core.view.*;

public class ReviewWritingController {
	private ReviewWritingView view;
	private Network network;
	private MainFrame win;

	public ReviewWritingController(ReviewWritingView view) {
		this.view = view;
		this.win = this.view.getWin();
		this.network = this.win.getNetwork();
		this.view.postListener(new ReviewPostListener());
		this.win.getReviewView().deleteReviewListener(new deleteReviewListener());
		this.win.getReviewView().updateReviewListener(new updateReviewListener());
		this.view.updateListener(new ReviewUpdateListener());
	}
	
	
	class deleteReviewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				network.deleteReview(win.getId(), win.getReviewView().getDong());
				win.getReviewListView().getCallReviewListBtn().doClick();
				win.change("ReviewListView");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class updateReviewListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			win.getReviewListView().getWriteReviewBtn().doClick();
			win.getReviewWritingView().changeUpdateBtn();
			win.getReviewWritingView().setComment_textArea(win.getReviewView().getComment());
			win.change("ReviewtWritingView");  
		}
	}

	
	class ReviewPostListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				network.postReview(win.getId(), view.getDong(), view.getLeisureRating(), view.getTransportRating(), view.getCommercialRating(), view.getHousePriceRating(), view.getComment());
				win.getReviewListView().getCallReviewListBtn().doClick();
				win.change("ReviewListView");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class ReviewUpdateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				network.updateReview(win.getId(), view.getDong(), view.getLeisureRating(), view.getTransportRating(), view.getCommercialRating(), view.getHousePriceRating(), view.getComment());
				ResidentReview review = network.getReview(win.getReviewView().getReviewNum());
				win.getReviewView().setLeisure(Integer.toString(view.getLeisureRating()));
				win.getReviewView().setCommercial(Integer.toString(view.getCommercialRating()));
				win.getReviewView().setTransport(Integer.toString(view.getTransportRating()));
				win.getReviewView().setHousePrice(Integer.toString(view.getHousePriceRating()));
				win.getReviewView().setComment(view.getComment());
				

				if(review.getWriter().equals(win.getId())) {
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
