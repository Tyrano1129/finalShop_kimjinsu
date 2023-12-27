package menu_ddmin;
import _mall.*;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class AdminBoard implements MenuCommand {
	private MallController cont;

	@Override
	public void init() {
		cont = MallController.getInstance();
		update();
	}

	@Override
	public boolean update() {
		while (true) {
			System.out.println("[1] 회원목록");
			System.out.println("[2] 회원삭제");
			System.out.println("[3] 뒤로가기");
			System.out.println("[0] 종료");

			int sel = Util.getValue("메뉴선택 : ", 0, 3);

			if (sel == 1) {

			} else if (sel == 2) {
				MemberDAO.getInstance();
			} else if (sel == 3) {
				cont.setNext("AdminMain");
				return false;
			} else if (sel == 0) {
				System.out.println("종료...");
				cont.setNext(null);
				return true;
			}
		}
	}

}
