package menu_memeber;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class MemberShopping implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
	}

	@Override
	public boolean update() {
		while (true) {
			ItemDAO itemdao = ItemDAO.getInstance();
			CartDAO cartdao = CartDAO.getInstance();
			System.out.println("[1] 쇼핑하기");
			System.out.println("[2] 뒤로가기");
			System.out.println("[0] 종료");
			
			int sel = Util.getValue("메뉴선택 : ", 0, 2);
			
			if(sel == 1) {
				itemdao.itemcategoryPrint1();
				String category = itemdao.getCategory(Util.getValue("번호선택 : ",1,itemdao.categorySize())-1);
				itemdao.itemPrint(category);
				while(true) {
					String itemName = Util.getValueString("아이템 입력 :");
					int idx = itemdao.itemNameCheck(itemName);
					if(idx == -1) {
						System.out.println("다시 입력해주세요.");
						continue;
					}
					int cnt = Util.getValue("구매갯수 입력하세요",1,100);
					System.out.println(cnt);
					itemdao.itemPurchase(cartdao,idx,cnt,cont.getLoginId());
					break;
				}
			}else if(sel == 2) {
				System.out.println("뒤로가기....");
				cont.setNext("MemberMain");
				break;
			}else if(sel == 0) {
				System.out.println("종료....");
				cont.setNext(null);
				break;
			}
		}
		return false;
	}

}
