package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import dto.*;

public class BoardDAO {
	private List<Board> boardList;
	private static BoardDAO instance = new BoardDAO();
	private	LocalDateTime now = LocalDateTime.now();
	public static BoardDAO getInstance() {
		return instance;
	}
	private BoardDAO() {
		boardList = new LinkedList<>();
	}
	public int boardListSize() {
		return boardList.size();
	}
	// 게시글 보기
	public String boardContents(int idx) {
		boardList.get(idx).setHits(boardList.get(idx).getHits() + 1);
		return boardList.get(idx).getContents();
	}
	// 자신 게시글 삭제
	public void boardMemberOneDelete(Board board) {
		boardList.remove(board);
	}
	//게시글 가지고오기
	public List<Board> memberOneboardPrint(String id) {
		List<Board> temp = new ArrayList<>();
		temp = boardList.stream()
						.filter(board -> board.getId().equals(id))
						.collect(Collectors.toList());
		for(Board board : temp) {
			System.out.println(board);
			System.out.println(board.getContents());
			System.out.println("-----------------");
		}
		
		return temp;
	}
	// 게시글 추가
	public void boardMemberOneInsert(String id,String title,String contents) {
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = now.format(formatter1);
		Board.setNum(Board.getNum()+1);
		boardList.add(new Board(Board.getNum(),title,id,date,contents,1));
	}
	// 게시글 삭제
	public void boardDelete(int idx) {
		boardList.remove(idx);
	}
	// 5페이지 보여주기
	public void boardListPrint(int start, int end) {
		List<Board> temp = new ArrayList<>();
		temp.addAll(boardList);
		if(end >= boardList.size()) end = temp.size(); 
		for(int i = start; i < end; i+=1) {
			System.out.printf("(%d) %s%n",i+1,temp.get(i));
		}
	}
	// 로드
	public void init(String data) {
		String[] temp = data.split("\n");
		System.out.println(data);
		System.out.println(Arrays.toString(temp));
		for(String boa : temp) {
			String[] info = boa.split("/");
			Board b = new Board(Integer.parseInt(info[0])
								,info[1]
								,info[3]
								,info[4]
								,info[2]
								,Integer.parseInt(info[5]));
			System.out.println(b);
			boardList.add(b);
		}
		Board.setNum(boradNumMax());
		boardList.stream().forEach(System.out::println);
	}
	private int boradNumMax() {
		int max = 0;
		for(Board b : boardList) {
			if(max < b.getBoradNum()) {
				max = b.getBoradNum();
			}
		}
		return max;
	}
	// 저장
	public String dataSave() {
		String data = "";
		for(Board board : boardList) {
			data += String.format("%d/%s/%s/%s/%s/%d%n",board.getBoradNum(),board.getTitle(),board.getContents(),board.getId(),board.getDate(),board.getHits());
		}
		return data;
	}
}
