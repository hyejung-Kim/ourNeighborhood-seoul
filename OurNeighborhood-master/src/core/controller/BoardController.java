package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import core.Network;
import core.DTO.AdminDistrict;
import core.DTO.Post;
import core.view.*;

public class BoardController {
	private BoardView view;
	private Network network;
	private MainFrame win;

	public BoardController(BoardView view) {
		this.view = view;
		this.win = this.view.getWin();
		this.network = this.win.getNetwork();
		this.view.callDistrictListener(new addGuListener());
		this.view.callDongListener(new addDongListener());
		this.view.callPostListListener(new callPostListListener());
		this.view.callPostViewListener(new getPostViewListener());
		this.view.callMyPostListListener(new callMyPostListListener());
		this.win.getPostView().deletePostListener(new deletePostListener());
		this.win.getPostView().updatePostListener(new updatePostListener());
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
	
	class updatePostListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			win.getPostWritingView().changeUpdateBtn();
			win.getPostWritingView().setTitle(win.getPostView().getTitle());
			win.getPostWritingView().setContent(win.getPostView().getContent());
			win.change("PostWritingView");  
		}
	}

	class getPostViewListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) { // 더블클릭
				String postNum = (String) view.getTable().getValueAt(view.getTable().getSelectedRow(), 0);
				Post post;
				try {
					post = network.getPost(postNum);
					win.getPostView().setPostNum(postNum);
					win.getPostView().setTitle(post.getTitle());
					win.getPostView().setContent(post.getContent());
					win.getPostView().setWriter(post.getWriter());

					if (post.getWriter().equals(win.getId()) || win.getId().equals("admin")) {
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
	}

	class callMyPostListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Post[] postResult = null;
			try {

				postResult = new Post[network.getMyPosts().length];
				postResult = network.getMyPosts();

				Object[][] rowDatas = new Object[postResult.length][4];
				for (int i = 0; i < postResult.length; i++) {
					rowDatas[i] = new Object[] { postResult[i].getPostNumber(), postResult[i].getTitle(),
							postResult[i].getWriter(), postResult[i].getTime(), };
				}
				view.setTableModel(rowDatas);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	class callPostListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Post[] postResult = null;
			try {
				if (view.getGu().equals("전체")) {
					postResult = new Post[network.getPosts().length];
					postResult = network.getPosts();
				} else {
					postResult = new Post[network.getPostsPartially(view.getGu(), view.getDong()).length];
					postResult = network.getPostsPartially(view.getGu(), view.getDong());
				}

				Object[][] rowDatas = new Object[postResult.length][4];
				for (int i = 0; i < postResult.length; i++) {
					rowDatas[i] = new Object[] { postResult[i].getPostNumber(), postResult[i].getTitle(),
							postResult[i].getWriter(), postResult[i].getTime(), };
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
