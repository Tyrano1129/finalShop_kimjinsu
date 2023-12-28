package menu_memeber;


import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;
import util.Util;

public class _MemberMain implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("---------------[ 회원 " + cont.getLoginId() + "님 ]--------------");
		System.out.println("[1] 상품구매");
		System.out.println("[2] 구매내역");
		System.out.println("[3] 게시판");
		System.out.println("[4] 나의 정보");
		System.out.println("[5] 회원 탈퇴");
		System.out.println("[6] 로그아웃");
		System.out.println("[0] 종료");
		int sel = Util.getValue("메뉴선택 : ", 0, 6);
		if (sel == 1) {
			cont.setNext("MemberShopping");
			System.out.println("상품구매");
		} else if (sel == 2) {
			cont.setNext("MemberCart");
			System.out.println("구매내역");
		} else if (sel == 3) {
			cont.setNext("MemberBoard");
			System.out.println("게시판");
		} else if (sel == 4) {
			cont.setNext("MemberInfo");
			System.out.println("나의 정보");
		} else if (sel == 5) {
			cont.setNext("MemberQuit");
			System.out.println("회원탈퇴");
		} else if (sel == 6) {
			cont.setNext("MallMain");
			cont.setLoginId(null);
			System.out.println("로그아웃...");
		} else if (sel == 0) {
			System.out.println("종료...");
			cont.setNext(null);
		}
		
		return false;
	}

}
