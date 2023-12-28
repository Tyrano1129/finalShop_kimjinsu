package menu_ddmin;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class AdminMember implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		while (true) {
			MemberDAO dao = MemberDAO.getInstance();
			CartDAO cartdao = CartDAO.getInstance();
			System.out.println("[1] 회원목록");
			System.out.println("[2] 회원삭제");
			System.out.println("[3] 뒤로가기");
			System.out.println("[0] 종료");

			int sel = Util.getValue("메뉴선택 : ", 0, 3);

			if (sel == 1) {
				dao.memberPrint();
			} else if (sel == 2) {
				String id = Util.getValueString("삭제 아이디 입력 : ");
				int idx = dao.getMemberIdIndex(id);
				if(idx == -1) {
					System.out.println("입력한 아이디는 없습니다.");
					continue;
				}
				dao.deleteMember(idx,cartdao);
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
