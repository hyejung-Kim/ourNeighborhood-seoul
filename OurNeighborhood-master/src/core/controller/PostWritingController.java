package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import core.Network;
import core.DTO.AdminDistrict;
import core.DTO.Post;
import core.view.*;

public class PostWritingController {
	private PostWritingView view;
	private Network network;
	private MainFrame win;

	public PostWritingController(PostWritingView view) {
		this.view = view;
		this.win = this.view.getWin();
		this.network = this.win.getNetwork();
		this.view.callDistrictListener(new addGuListener());
		this.view.callDongListener(new addDongListener());
		this.view.postListener(new postPostListener());
		this.view.updateListener(new postUpdateListener());
	}
	
	
	class deletePostListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				network.deletePost(win.getPostView().getPostNum());
				win.change("BoardView");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	class postPostListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				network.postPost(win.getId(), view.getGu(), view.getDong(), view.getTitle(), view.getContent());
				win.change("BoardView");
				win.getBoardView().getCallPostListBtn().doClick();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class postUpdateListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				network.updatePost(win.getPostView().getPostNum(),  view.getGu(), view.getDong(), view.getTitle(), view.getContent());
				Post post = network.getPost(win.getPostView().getPostNum());
				win.getPostView().setPostNum(win.getPostView().getPostNum());
				win.getPostView().setTitle(post.getTitle());
				win.getPostView().setContent(post.getContent());
				win.getPostView().setWriter(post.getWriter());
				
				if(post.getWriter().equals(win.getId())) {
					win.getPostView().imWriter();
				} else {
					win.getPostView().imNotWriter();
				}
				win.change("PostView");
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

	
}
