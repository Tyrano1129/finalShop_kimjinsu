package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;

public class MemberCart implements MenuCommand {

	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		CartDAO cartdao = CartDAO.getInstance();
		ItemDAO itemdao = ItemDAO.getInstance();
		if(cartdao.cartMemberOnePrint(itemdao,cont.getLoginId()) == 0) {
			System.out.println("구매하신 내역이 없습니다.");
		}
		cont.setNext("MemberMain");
		return false;
	}

}
