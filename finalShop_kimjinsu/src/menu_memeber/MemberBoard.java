package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;

public class MemberBoard implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

}
