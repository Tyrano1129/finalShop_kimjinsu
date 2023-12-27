package dao;

import java.util.*;

import dto.Item;

public class ItemDAO {
	private List<Item> itemList;
	private static ItemDAO instance = new ItemDAO();

	public static ItemDAO getInstance() {
		return instance;
	}
	
	private ItemDAO() {
		itemList = new LinkedList<>();
	}
	//로드
	public void init(String data) {
		String[] temp = data.split("\n");
		
		for(String item : temp) {
			String[] info = item.split("/");
			
			Item i = new Item(Integer.parseInt(info[0]),info[1],info[2],Integer.parseInt(info[3]));
			
			itemList.add(i);
		}
		
		int max = 0;
		for(Item item : itemList) {
			if(max < item.getItemNum()) {
				max = item.getItemNum();
			}
		}
		
		Item.setNum(max);
		itemList.stream().forEach(System.out::println);
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
