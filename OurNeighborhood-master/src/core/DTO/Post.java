package core.DTO;

import java.io.Serializable;

public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private String postNumber; // 리뷰번호
	private String writer; // 작성자
	private String autoRegion; // 자치구
	private String adminDong; // 행정동
	private String title; // 글제목
	private String content; // 글내용
	private String time; //작성시간

	public String getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getAutoRegion() {
		return autoRegion;
	}

	public void setAutoRegion(String autoRegion) {
		this.autoRegion = autoRegion;
	}

	public String getAdminDong() {
		return adminDong;
	}

	public void setAdminDong(String adminDong) {
		this.adminDong = adminDong;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}