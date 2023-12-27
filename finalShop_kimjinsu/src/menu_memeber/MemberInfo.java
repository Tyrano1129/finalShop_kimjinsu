package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MemberInfo implements MenuCommand {

	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
	}


	@Override
	public boolean update() {
		MemberDAO dao = MemberDAO.getInstance();
		while(true) {
			System.out.println("[1] 비밀번호 변경");
			System.out.println("[2] 뒤로가기");
			System.out.println("[0] 종료");
			int sel = Util.getValue("메뉴선택 : ", 0, 2);
			
			if(sel == 1) {
				String pw = Util.getValueString("비밀번호 입력 : ");
				String setPw = Util.getValueString("신규 비빌번호 입력 : ");
				if(dao.isValidMember(cont.getLoginId(), pw) == null) {
					System.out.println("비밀번호를 틀렸습니다.");
					continue;
				}
				dao.memberOnePwSet(cont.getLoginId(),setPw);
			}else if(sel == 2) {
				System.out.println("뒤로가기...");
				cont.setNext("MemberMain");
				break;
			}else if(sel == 0) {
				System.out.println("종료...");
				cont.setNext(null);
				break;
			}
		}
		return false;
	}

}
