package dao;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import dto.*;

public class ItemDAO {
	private List<Item> itemList;
	private List<String> categoryList;
	private static ItemDAO instance = new ItemDAO();

	public static ItemDAO getInstance() {
		return instance;
	}
	
	private ItemDAO() {
		itemList = new LinkedList<>();
		categoryList = new LinkedList<>();
	}
	// 아이템이름 체크
	public int itemNameCheck(String name) {
		for(int i = 0; i < itemList.size(); i+=1) {
			if(name.equals(itemList.get(i).getItemName())) {
				return i;
			}
		}
		return -1;
	}
	// cart에 있는 같은넘버 가격과 이름
	public int cartNamePrint(int number) {
		for(int i = 0; i < itemList.size(); i+=1) {
			if(number == itemList.get(i).getItemNum()) {
				System.out.printf("%s (%d원) ",itemList.get(i).getItemName(),itemList.get(i).getPrice());
				return itemList.get(i).getPrice();
			}
		}
		return 0;
	}
	// 구매아이템 총 판매 갯수 순
	public void itemCountPrint(CartDAO cart) {
		List<Item> itemList = new ArrayList<>();
		itemList.addAll(this.itemList);
		List<Integer> cnt = new ArrayList<>();
		// 갯수 합산
		for (int i = 0; i < itemList.size(); i += 1) {
			int count = cart.cartCountItemNum(itemList.get(i));
			cnt.add(count);
		}
		//정렬
		for(int i = 0; i < itemList.size(); i+=1) {
			int max = cnt.get(i);
			for(int k = i; k < itemList.size(); k+=1) {
				if(max < cnt.get(k)) {
					max = cnt.get(k);
					
					Item temp = itemList.get(i);
					itemList.set(i,itemList.get(k));
					itemList.set(k, temp);
					
					Integer temps = cnt.get(i);
					cnt.set(i,cnt.get(k));
					cnt.set(k, temps);
				}
			}
			if(cnt.get(i) != 0) {
				System.out.println(itemList.get(i) + " " + cnt.get(i)+ "개");
			}
		}
		System.out.println("====================");
	}
	
	// 카테고리순 출력
	public void printItemNumber() {
		List<Item> temp = new ArrayList<>();
		temp.addAll(itemList);
		temp.stream()
			.sorted((item1,item2)->{
				if(item1.getCategoryName().compareTo(item2.getCategoryName()) > 0) {
					return 1;
				}else if(item1.getCategoryName().compareTo(item2.getCategoryName()) < 0){
					return -1;
				}
				return 0;
			})
			.forEach(System.out::println);
		System.out.println("====================");
	}
	// 아이템&&카테고리 중복값 체크
	public boolean isValueItem(String name) {
		List<Item> temp = new ArrayList<>();
		temp.addAll(itemList);
		for(Item item : temp) {
			if(item.getItemName().equals(name) || item.getCategoryName().equals(name))
			{
				return true;
			}
		}
		return false;
	}
	public int categorySize() {
		return categoryList.size();
	}
	//추가
	public void itemAdd(String name,String category,int price) {
		Item.setNum(Item.getNum()+1);
		itemList.add(new Item(Item.getNum(),category,name,price));
	}
	//삭제
	public void itemDelete(int idx,CartDAO cart) {
		itemList.remove(idx);
	}
	//카테고리 출력
	public void itemcategoryPrint1() {
		List<String> temp = new ArrayList<>();
		temp.addAll(categoryList);
		for(int i =0; i < categoryList.size(); i+=1) {
			System.out.printf("[%d] %s%n",i+1,categoryList.get(i));
		}
	}
	//카테고리 값 가지고오기
	public String getCategory(int idx) {
		return categoryList.get(idx);
	}
	//카테고리에 맞는 아이템 출력
	public void itemPrint(String category) {
		List<Item> temp = new ArrayList<>();
		temp.addAll(itemList);
		int num = 1;
		for(Item item : temp) {
			if(item.getCategoryName().equals(category)) {
				System.out.printf("[%d] %s%n",num++,item);
			}
		}
	}
	// 카트에다가 채우기
	public void itemPurchase(CartDAO cart,int idx,int cnt,String id) {
		cart.insertCart(id,itemList.get(idx).getItemNum(),cnt);
	}
	//로드
	public void init(String data) {
		String[] temp = data.split("\n");
		
		for(String item : temp) {
			String[] info = item.split("/");
			
			Item i = new Item(Integer.parseInt(info[0]),info[1],info[2],Integer.parseInt(info[3]));
			
			itemList.add(i);
		}
		
		categoryList = itemList.stream()
										.map(name -> name.getCategoryName())
										.distinct()
										.collect(Collectors.toList());
		Item.setNum(itemNumMax());
		itemList.stream().forEach(System.out::println);
		categoryList.stream().forEach(System.out::println);
	}
	private int itemNumMax() {
		int max = 0;
		for(Item item : itemList) {
			if(max < item.getItemNum()) {
				max = item.getItemNum();
			}
		}
		return max;
	}
	//저장
	public String dataSave() {
		String data = "";
		for(Item item : itemList) {
			data += String.format("%d/%s/%s/%d%n",item.getItemNum(),item.getCategoryName(),item.getItemName(),item.getPrice());
		}
		return data;
	}
}
