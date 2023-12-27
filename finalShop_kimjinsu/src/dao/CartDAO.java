package dao;

import java.util.*;

import dto.Cart;

public class CartDAO {
	private List<Cart> cartList;
	
	private static CartDAO instance = new CartDAO();

	public static CartDAO getInstance() {
		return instance;
	}
	
	private CartDAO() {
		cartList = new LinkedList<>();
	}
	//로드
	public void init(String data) {
		String[] temp = data.split("\n");
		for(String cart : temp) {
			String[] info = cart.split("/");
			
			Cart c = new Cart(Integer.parseInt(info[0]),info[1],Integer.parseInt(info[2]),Integer.parseInt(info[3]));
			
			cartList.add(c);
		}
		
		int max = 0;
		
		for(Cart cart : cartList) {
			if(max < cart.getCartNum()) {
				max = cart.getCartNum();
			}
		}
		
		Cart.setNum(max);
		cartList.stream().forEach(System.out::println);
	}
	//저장
	public String dataSave() {
		String data = "";
		for(Cart cart : cartList) {
			data += String.format("%d/%s/%d/%d%n",cart.getCartNum(),cart.getId(),cart.getItemCnt(),cart.getItemNum());
		}
		return data;
	}
}
