package core.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import core.Network;
import core.DTO.AdminDistrict;
import core.DTO.Comment;
import core.DTO.Post;
import core.view.*;

public class CommentController {
	private CommentView view;
	private Network network;
	private MainFrame win;

	public CommentController(CommentView view) {
		this.view = view;
		this.win = this.view.getWin();
		this.network = this.win.getNetwork();
		this.view.deleteCommentListener(new deleteCommentListener());
		this.view.postCommentListener(new postCommentListener());
		this.view.getCommentListener(new getCommentListener());
		this.view.updateCommentListener(new updateCommentListener());
		this.win.getPostView().getCommentListener(new callCommentListListener());
	}

	class postCommentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				network.postComment(win.getId(), view.getContent(), win.getPostView().getPostNum());
				view.resetTextpane();
				win.getPostView().getCommentBtn().doClick();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class updateCommentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				network.updateComment(view.getTime(), view.getWriter(), view.getContent());
				view.resetTextpane();
				win.getPostView().getCommentBtn().doClick();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class deleteCommentListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				network.deleteComment(view.getTime(), view.getWriter());
				view.resetTextpane();
				win.getPostView().getCommentBtn().doClick();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	class getCommentListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {

			if (e.getClickCount() == 2) { // 더블클릭
				String text = (String) view.getTable().getValueAt(view.getTable().getSelectedRow(), 1);
				String writer = (String) view.getTable().getValueAt(view.getTable().getSelectedRow(), 0);
				String time = (String) view.getTable().getValueAt(view.getTable().getSelectedRow(), 2);

				if (writer.equals(win.getId())) {
					try {
						view.imWriter();
						view.setContent(text);
						view.setWriter(writer);
						view.setTime(time);

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else view.resetTextpane();
			
			}
		}
	}

	class callCommentListListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Comment[] commentResult = null;
			try {
				commentResult = new Comment[network.getComment(win.getPostView().getPostNum()).length];
				commentResult = network.getComment(win.getPostView().getPostNum());

				Object[][] rowDatas = new Object[commentResult.length][3];
				for (int i = 0; i < commentResult.length; i++) {
					rowDatas[i] = new Object[] { commentResult[i].getWriter(), commentResult[i].getContent(),
							commentResult[i].getTime() };
				}
				view.setTableModel(rowDatas);

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}
}
