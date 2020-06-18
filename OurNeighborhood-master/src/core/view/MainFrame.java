package core.view;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import core.Network;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private String id = "";
	private Network network;

	private LoginView loginView = null;
	private SignUpView signUpView = null;
	private ListView listView = null;
	private ReviewListView reviewListView = null;
	private ReviewWritingView reviewWritingView = null;
	private ReviewView reviewView = null;
	private BoardView boardView = null;
	private PostWritingView postWritingView = null;
	private PostView postView = null;
	private CommentView commentView = null;

	public String getId() {
		return id;
	}

	public Network getNetwork() {
		return network;
	}

	public LoginView getLoginView() {
		return loginView;
	}

	public SignUpView getSignUpView() {
		return signUpView;
	}

	public ListView getListView() {
		return listView;
	}

	public ReviewListView getReviewListView() {
		return reviewListView;
	}

	public BoardView getBoardView() {
		return boardView;
	}

	public PostWritingView getPostWritingView() {
		return postWritingView;
	}

	public PostView getPostView() {
		return postView;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public void setLoginView(LoginView loginView) {
		this.loginView = loginView;
	}

	public void setSignUpView(SignUpView signUpView) {
		this.signUpView = signUpView;
	}

	public void setListView(ListView listView) {
		this.listView = listView;
	}

	public void setReviewListView(ReviewListView reviewListView) {
		this.reviewListView = reviewListView;
	}

	public void setBoardView(BoardView boardView) {
		this.boardView = boardView;
	}

	public void setPostWritingView(PostWritingView postWritingView) {
		this.postWritingView = postWritingView;
	}

	public void setPostView(PostView postView) {
		this.postView = postView;
	}
	

	public CommentView getCommentView() {
		return commentView;
	}

	public void setCommentView(CommentView commentView) {
		this.commentView = commentView;
	}
	
	

	public ReviewView getReviewView() {
		return reviewView;
	}

	public void setReviewView(ReviewView reviewView) {
		this.reviewView = reviewView;
	}

	public ReviewWritingView getReviewWritingView() {
		return reviewWritingView;
	}

	public void setReviewWritingView(ReviewWritingView reviewWritingView) {
		this.reviewWritingView = reviewWritingView;
	}

	public MainFrame(Network network) {
		this.network = network;

		// 창닫기버튼클릭시 서버에 종료메세지보내고 종료
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				try {
					System.out.println("[클라이언트정상종료]");
					network.exit();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});

	}

	public void change(String viewName) { // 패널 1번과 2번 변경 후 재설정

		if (viewName.equals("LoginView")) {
			getContentPane().removeAll();
			getContentPane().add(loginView);
			revalidate();
			repaint();
		} else if (viewName.equals("SignUpView")) {
			getContentPane().removeAll();
			getContentPane().add(signUpView);
			revalidate();
			repaint();
		} else if (viewName.equals("ListView")) {
			getContentPane().removeAll();
			getContentPane().add(listView);
			revalidate();
			repaint();
		} else if (viewName.equals("ReviewListView")) {
			getContentPane().removeAll();
			getContentPane().add(reviewListView);
			revalidate();
			repaint();
			reviewListView.getCallReviewListBtn().doClick();
		} else if (viewName.equals("ReviewWritingView")) {
			getContentPane().removeAll();
			getContentPane().add(reviewWritingView);
			revalidate();
			repaint();
		} else if (viewName.equals("ReviewView")) {
			getContentPane().removeAll();
			getContentPane().add(reviewView);
			revalidate();
			repaint();
		} else if (viewName.equals("BoardView")) {
			getContentPane().removeAll();
			getContentPane().add(boardView);
			revalidate();
			repaint();
			boardView.getCallPostListBtn().doClick();
		} else if (viewName.equals("PostWritingView")) {
			getContentPane().removeAll();
			getContentPane().add(postWritingView);
			revalidate();
			repaint();
			
		} else if (viewName.equals("PostView")) {
			getContentPane().removeAll();
			getContentPane().add(postView);
			revalidate();
			repaint();

		} else if (viewName.equals("CommentView")){
			getContentPane().removeAll();
			getContentPane().add(commentView);
			revalidate();
			repaint();
		}

	}


}