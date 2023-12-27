package menu_ddmin;

import _mall.MenuCommand;
import controller.MallController;
import dao.*;
import util.Util;

public class _AdminMain implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("[1] 회원관리");
		System.out.println("[2] 상품관리");
		System.out.println("[3] 게시판관리");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 파일저장");
		System.out.println("[0] 종료");
		
		int sel = Util.getValue("메뉴선택 : ",0,5);
		
		if(sel == 1) {
			cont.setNext("AdminMember");
			System.out.println("회원관리");
		}else if(sel == 2) {
			cont.setNext("AdminItem");
			System.out.println("상품관리");
		}else if(sel == 3) {
			cont.setNext("AdminBoard");
			System.out.println("게시판관리");
		}else if(sel == 4) {
			cont.setNext("MallMain");
			cont.setLoginId(null);
			System.out.println("로그아웃");
		}else if(sel == 5) {
			FileDAO.getInstance().fileAllSave(MemberDAO.getInstance(),CartDAO.getInstance()
					,ItemDAO.getInstance(),BoardDAO.getInstance());
		}else if(sel == 0) {
			cont.setNext(null);
		}
		return false;
	}

}
