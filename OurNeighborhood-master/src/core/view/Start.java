package core.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import core.Network;
import core.controller.*;

public class Start {
	public static void main(String[] args) {
		
		Network network = new Network();
		MainFrame win = new MainFrame(network);

		win.setTitle("우리동네어때!!");
		win.setLoginView(new LoginView(win));
		win.setSignUpView(new SignUpView(win));
		win.setListView(new ListView(win));
		win.setReviewListView(new ReviewListView(win));
		win.setReviewWritingView(new ReviewWritingView(win));
		win.setBoardView(new BoardView(win));
		win.setPostWritingView(new PostWritingView(win));
		win.setPostView(new PostView(win));
		win.setCommentView(new CommentView(win));
		win.setReviewView(new ReviewView(win));
		

		win.add(win.getLoginView());
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setSize(1000, 800);
		
		LoginController lgc = new LoginController(win.getLoginView());
		SignUpController sc = new SignUpController(win.getSignUpView());
		ListController ltc = new ListController(win.getListView());
		BoardController bc = new BoardController(win.getBoardView());
		PostWritingController pc = new PostWritingController(win.getPostWritingView());
		CommentController cc = new CommentController(win.getCommentView());
		ListController lc = new ListController(win.getListView());
		ReviewController rc = new ReviewController(win.getReviewListView());
		ReviewWritingController rwc = new ReviewWritingController(win.getReviewWritingView());
		
		win.setVisible(true);
	}
}
