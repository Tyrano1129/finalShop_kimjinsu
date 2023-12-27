package menu_ddmin;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import dto.Item;
import util.Util;

public class AdminItem implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
		
	}

	@Override
	public boolean update() {
		while(true) {
			ItemDAO itemdao = ItemDAO.getInstance();
			CartDAO cartdao = CartDAO.getInstance();
			System.out.println("[1] 아이템 추가");
			System.out.println("[2] 아이템 삭제");
			System.out.println("[3] 총 매출 아이템 갯수 출력(판매력 높은순으로)");
			System.out.println("[4] 뒤로가기");
			System.out.println("[0] 종료");
			System.out.println("[카테고리 별 순서]");
			int sel = Util.getValue("메뉴선택 : ", 0, 4);
			itemdao.printItemNumber();
			if (sel == 1) {
				String itemName = Util.getValueString("아이템 입력 : ");
				if(itemdao.isValueItem(itemName)) {
					System.out.println("이미 있는 카테고리/아이템 입니다.");
					continue;
				}
				String category = Util.getValueString("카테고리 입력 : ");
				int price = Util.getValue("가격 : ",100,1000000);
				itemdao.itemAdd(itemName,category,price);
			} else if (sel == 2) {
				itemdao.itemDelete(Util.getValue("메뉴 선택:",1,Item.getNum())-1,cartdao);
			} else if (sel == 3) {
				itemdao.itemCountPrint(cartdao);
			} else if (sel == 4) {
				System.out.println("뒤로가기...");
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
