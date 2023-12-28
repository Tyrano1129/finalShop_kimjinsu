package dao;

import java.util.*;
import java.util.stream.Collectors;

import dto.Cart;
import dto.Item;

public class CartDAO {
	private List<Cart> cartList;
	
	private static CartDAO instance = new CartDAO();

	public static CartDAO getInstance() {
		return instance;
	}
	private CartDAO() {
		cartList = new LinkedList<>();
	}
	// 구매내역
	public int cartMemberOnePrint(ItemDAO item,String id) {
		List<Cart> temp = new ArrayList<>();
		temp = cartList.stream()
						.filter(ids -> ids.getId().equals(id))
						.collect(Collectors.toList());
		int cnt = temp.size();
		if(cnt == 0) {
			return cnt;
		}
		for(int i = 0; i < temp.size(); i+=1) {
			int sum = 0;
			sum = item.cartNamePrint(temp.get(i).getItemNum()) * temp.get(i).getItemCnt();
			System.out.printf(" %d개 총 %d원%n", temp.get(i).getItemCnt(),sum);
			cnt += 1;
		}
		return cnt;
	}
	//회원 카트 구입
	public void insertCart(String id,int itemNum,int cnt) {
		boolean check = true;
		System.out.println(id + " " + cnt + " " + itemNum);
		for(int i = 0; i < cartList.size(); i+=1) {
			if(cartList.get(i).getItemNum() == itemNum && cartList.get(i).getId().equals(id)) {
				cartList.get(i).setItemCnt(cartList.get(i).getItemCnt() + cnt);
				check = false;
				break;
			}
		}
		if(!check) {
			return;
		}
		Cart.setNum(Cart.getNum()+1);
		cartList.add(new Cart(Cart.getNum(),id,itemNum,cnt));
		cartList.stream()
				.forEach(System.out::println);
	}
	
	// item 카운트 숫자 출력용
	public int cartCountItemNum(Item item) {
		int sum = 0;
		for(Cart cart : cartList) {
			if(item.getItemNum() == cart.getItemNum()) {
				sum += cart.getItemCnt();
				System.out.println(cart.getCartNum());
			}
		}
		return sum;
	}
	// 맴버 탈퇴후 카트 다지우기
	public void cartOneMemberDelete(String id) {
		for(int i = 0; i < cartList.size(); i+=1) {
			if(id.equals(cartList.get(i).getId())) {
				cartList.remove(i);
				i-=1;
			}
		}
		cartList.stream()
		.forEach(System.out::println);
	}
	// item 삭제후 카트에있는모든것 지우기
	public void cartItemOneDelete(int num) {
		System.out.println(num);
		for(int i = 0; i < cartList.size(); i+=1) {
			if(num == cartList.get(i).getItemNum()) {
				cartList.remove(i);
				i-=1;
			}
		}
		cartList.stream()
		.forEach(System.out::println);
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
