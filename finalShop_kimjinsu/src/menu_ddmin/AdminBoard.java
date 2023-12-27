package menu_ddmin;
import _mall.*;
import controller.MallController;
import dao.BoardDAO;
import dao.MemberDAO;
import util.Util;

public class AdminBoard implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		while (true) {
			BoardDAO dao = BoardDAO.getInstance();
			System.out.println("[1] 게시글목록");
			System.out.println("[2] 게시글삭제");
			System.out.println("[3] 뒤로가기");
			System.out.println("[0] 종료");

			int sel = Util.getValue("메뉴선택 : ", 0, 3);

			if (sel == 1) {
				int start = 0;
				int end = 5;
				while(true) {
					dao.boardListPrint(start,end);
					System.out.println("[1] 이전");
					System.out.println("[2] 이후");
					System.out.println("[3] 게시글보기");
					System.out.println("[0] 뒤로가기");
					sel = Util.getValue("메뉴 선택 : ",0,3);
					if(sel == 1) {
						if(start == 0) {
							System.out.println("더이상 이전 할수없습니다.");
							continue;
						}
						end -= start;
						start -= 5;
					}else if(sel == 2) {
						if(end >= dao.boardListSize()) {
							System.out.println("더이상 글이 없습니다.");
							continue;
						}
						start += 5;
						end = start + 5;
					}else if(sel == 3) {
						int num = Util.getValue("번호 선택 : ",1,dao.boardListSize())-1;
						System.out.println(dao.boardContents(num));
					}else if(sel == 0) {
						System.out.println("뒤로가기....");
						break;
					}
				}
			} else if (sel == 2) {
				int num = Util.getValue("번호 선택 : ",1,dao.boardListSize())-1;
				dao.boardDelete(num);
			} else if (sel == 3) {
				cont.setNext("AdminMain");
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
