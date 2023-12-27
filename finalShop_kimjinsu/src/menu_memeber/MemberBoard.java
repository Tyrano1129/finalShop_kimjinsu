package menu_memeber;

import java.util.List;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import dto.Board;
import util.Util;

public class MemberBoard implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		BoardDAO dao = BoardDAO.getInstance();
		while (true) {
			System.out.println("[1] 게시글보기");
			System.out.println("[2] 게시글추가");
			System.out.println("[3] 내게시글(삭제)");
			System.out.println("[4] 뒤로가기");
			System.out.println("[0] 종료");

			int sel = Util.getValue("메뉴선택 : ", 0, 4);
			if (sel == 1) {
				int start = 0;
				int end = 5;
				while (true) {
					dao.boardListPrint(start, end);
					System.out.println("[1] 이전");
					System.out.println("[2] 이후");
					System.out.println("[3] 게시글보기");
					System.out.println("[0] 뒤로가기");
					sel = Util.getValue("메뉴 선택 : ", 0, 3);
					if (sel == 1) {
						if (start == 0) {
							System.out.println("더이상 이전 할수없습니다.");
							continue;
						}
						end -= start;
						start -= 5;
					} else if (sel == 2) {
						if (end >= dao.boardListSize()) {
							System.out.println("더이상 글이 없습니다.");
							continue;
						}
						start += 5;
						end = start + 5;
					} else if (sel == 3) {
						int num = Util.getValue("번호 선택 : ", 1, dao.boardListSize()) - 1;
						System.out.println(dao.boardContents(num));
					} else if (sel == 0) {
						System.out.println("뒤로가기....");
						break;
					}
				}
			} else if (sel == 2) {
				String title = Util.getValueString("제목 입력 : ");
				String contents = Util.getValueString("게시 내용 입력 :");
				dao.boardMemberOneInsert(cont.getLoginId(), title, contents);
			} else if (sel == 3) {
				List<Board> temp = dao.memberOneboardPrint(cont.getLoginId());
				if (temp.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				int idx = Util.getValue("번호 입력 : ", 1, temp.size()) - 1;
				dao.boardMemberOneDelete(temp.get(idx));
			} else if (sel == 4) {
				System.out.println("뒤로가기");
				cont.setNext("MemberMain");
				break;
			} else if (sel == 0) {
				System.out.println("종료...");
				cont.setNext(null);
				break;
			}
		}
		return false;
	}

}
