package dao;

import java.util.*;

import dto.*;

public class BoardDAO {
	private List<Board> boardList;
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}
	private BoardDAO() {
		boardList = new LinkedList<>();
	}
	
	// 로드
	public void init(String data) {
		String[] temp = data.split("\n");
		
		for(String boa : temp) {
			String[] info = boa.split("/");
			
			Board b = new Board(Integer.parseInt(info[0])
								,info[1]
								,info[3]
								,info[4]
								,info[2]
								,Integer.parseInt(info[5]));
			boardList.add(b);
		}
		int max = 0;
		for(Board b : boardList) {
			if(max < b.getBoradNum()) {
				max = b.getBoradNum();
			}
		}
		Board.setNum(max);
		boardList.stream().forEach(System.out::println);
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
