package dto;

import java.util.Objects;

public class Member {
	private static int num = 1000;
	private int memberNum;
	private String id;
	private String pw;
	private String memberName;
	public Member(int memberNum, String id, String pw, String memberName) {
		this.memberNum = memberNum;
		this.id = id;
		this.pw = pw;
		this.memberName = memberName;
	}
	public static int getNum() {
		return num;
	}
	public static void setNum(int num) {
		Member.num = num;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	@Override
	public String toString() {
		return "%d %s %s %s".formatted(this.memberNum,this.id,this.pw,this.memberName);
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, memberName, memberNum, pw);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(id, other.id) && Objects.equals(memberName, other.memberName)
				&& memberNum == other.memberNum && Objects.equals(pw, other.pw);
	}
	
	
}
