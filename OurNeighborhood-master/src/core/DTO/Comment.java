package core.DTO;

import java.io.Serializable;

public class Comment implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String time; // 작성시간
	private String writer; // 작성자
	private String content; // 내용
	private int postNumber; // 게시글번호

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

}
