package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.MemberDAO;

public class MemberQuit implements MenuCommand {

	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		MemberDAO dao = MemberDAO.getInstance();
		CartDAO cartdao = CartDAO.getInstance();
		dao.delectMember(cont.getLoginId(), cartdao);
		cont.setLoginId(null);
		cont.setNext("MallMain");
		return false;
	}

}
