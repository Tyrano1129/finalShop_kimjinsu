package dto;

import java.util.Objects;

public class Board {
	private static int num;
	private int boradNum;
	private String title;
	private String id;
	private String date;
	private String contents;
	private int hits;
	public Board(int boradNum, String title, String id, String date, String contents, int hits) {
		super();
		this.boradNum = boradNum;
		this.title = title;
		this.id = id;
		this.date = date;
		this.contents = contents;
		this.hits = hits;
	}
	public static int getNum() {
		return num;
	}
	public static void setNum(int num) {
		Board.num = num;
	}
	public int getBoradNum() {
		return boradNum;
	}
	public void setBoradNum(int boradNum) {
		this.boradNum = boradNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	@Override
	public String toString() {
		return "[제목 : %s \t작성자 : %s 날짜 : %s 조회수 : %d".formatted(this.title,this.id,this.date,this.hits);
	}
	@Override
	public int hashCode() {
		return Objects.hash(boradNum, contents, date, hits, id, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		return boradNum == other.boradNum && Objects.equals(contents, other.contents)
				&& Objects.equals(date, other.date) && hits == other.hits && Objects.equals(id, other.id)
				&& Objects.equals(title, other.title);
	}
	
}
