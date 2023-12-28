package dao;

import java.util.*;

import dto.Member;

public class MemberDAO {
	private List<Member> memberList;
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}
	
	private MemberDAO() {
		memberList = new LinkedList<>();
	}
	//로드
	public void init(String data) {
		String[] temp = data.split("\n");
		
		for(String member : temp) {
			String[] info = member.split("/");
			
			
			Member m = new Member(Integer.parseInt(info[0]),info[1],info[2],info[3]);
			
			memberList.add(m);
		}
		
		int max = 0;
		for(Member member : memberList) {
			if(max < member.getMemberNum()) {
				max = member.getMemberNum();
			}
		}
		
		Member.setNum(max);
		
		memberList.stream().forEach(System.out::println);
	}
	// 회원 전체출력
	public void memberPrint() {
		List<Member> temp = new ArrayList<>();
		temp.addAll(memberList);
		temp.stream().forEach(System.out::println);
	}
	//아이디 인덱스 찾기
	public int getMemberIdIndex(String id) {
		List<Member> temp = new ArrayList<>();
		temp.addAll(memberList);
		for(int i =0; i < temp.size(); i+=1) {
			if(id.equals(temp.get(i).getId())) {
				return i;
			}
		}
		return -1;
	}
	//아이디 중복체크
	public String getMemberById(String id) {
		List<Member> temp = new ArrayList<>();
		temp.addAll(memberList);
		for(Member member : temp) {
			if(member.getId().equals(id)) {
				return member.getId();
			}
		}
		return null;
	}
	//로그인 중복체크
	public String isValidMember(String id,String pw) {
		List<Member> temp = new ArrayList<>();
		temp.addAll(memberList);
		for(Member member : temp) {
			if(member.getId().equals(id) && member.getPw().equals(pw)) {
				return member.getId();
			}
		}
		return null;
	}
	//추가
	public boolean insertMember(String id,String pw,String name) {
		Member.setNum(Member.getNum()+1);
		memberList.add(new Member(Member.getNum(),id,pw,name));
		memberList.stream().forEach(System.out::println);
		return true;
	}
	// 비밀번호 수정
	public void memberOnePwSet(String id,String pw) {
		int idx = getMemberIdIndex(id);
		memberList.get(idx).setPw(pw);
	}
	// 본인 회원탈퇴
	public void delectMember(String id,CartDAO cart) {
		for(int i = 0; i < memberList.size(); i+=1) {
			if(memberList.get(i).getId().equals(id)) {
				memberList.remove(i);
				break;
			}
		}
		cart.cartOneMemberDelete(id);
	}
	// admin 삭제
	public void deleteMember(int idx,CartDAO cart) {
		if(memberList.get(idx).getId().equals("admin")) {
			System.out.println("관리자는 지울수없습니다.");
			return;
		}
		memberList.remove(idx);
		cart.cartOneMemberDelete(memberList.get(idx).getId());
	}
	
	//세이브
	public String dataSave() {
		String data = "";
		for(Member member : memberList) {
			data += String.format("%d/%s/%s/%s%n",member.getMemberNum(),member.getId(),member.getPw(),member.getMemberName());			
		}
		return data;
	}
	
	
}
