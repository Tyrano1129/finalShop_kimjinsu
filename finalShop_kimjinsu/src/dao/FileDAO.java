package dao;

import java.io.*;
import java.nio.file.*;

public class FileDAO {
	enum FileName {
		BOARD("board.txt"), MEMBER("member.txt"), ITEM("item.txt"), CART("cart.txt");
		private String name;
		FileName(String name) {
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}

	private FileDAO() {}

	private static FileDAO instance = new FileDAO();

	static public FileDAO getInstance() {
		return instance;
	}
	
	private  void createFile(FileName name) {
		Path path = Paths.get("src/files/" + name.getName());
		try {
			Files.createFile(path);
		} catch (IOException e) {
//			System.out.println("파일이 이미 있음");
		}
	}

	private  void init() {
		createFile(FileName.BOARD);
		createFile(FileName.MEMBER);
		createFile(FileName.ITEM);
		createFile(FileName.CART);
	}
	private String load(FileName name) {
		
		String data = "";
		try (FileReader fr = new FileReader("src/files/"+ name.getName());
			BufferedReader br = new BufferedReader(fr)){
			while(true) {
				String read = br.readLine();
				if(read == null) break;
				data += read + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(data.equals("")) {
			return null;
		}
		return data;
	}
	public void loadAllFiles(MemberDAO member,CartDAO cart,ItemDAO item,BoardDAO board) {
		init();
		String dataBoard = load(FileName.BOARD);
		String dataMember = load(FileName.MEMBER);
		String dataItem = load(FileName.ITEM);
		String dataCart = load(FileName.CART);
		if(dataBoard != null) {
			board.init(dataBoard);
		}
		if(dataMember != null) {
			member.init(dataMember);
		}
		if(dataItem != null) {
			item.init(dataItem);
		}
		if(dataCart != null) {
			cart.init(dataCart);
		}
	}
	private void save(FileName name,String data) {
		try(FileWriter fw = new FileWriter("src/files/"+ name.getName())){
			fw.write(data);
			System.out.printf("%s 저장 성공%n",name);
		} catch (IOException e) {
			System.out.printf("%s 저장 실패%n",name);
		}
	}
	public void fileAllSave(MemberDAO member,CartDAO cart,ItemDAO item,BoardDAO board) {
		String dataBoard = board.dataSave();
		String dataCart = cart.dataSave();
		String dataItem = item.dataSave();
		String dataMember = member.dataSave();

		save(FileName.BOARD,dataBoard);
		save(FileName.MEMBER,dataMember);
		save(FileName.ITEM,dataItem);
		save(FileName.CART,dataCart);
	}
}
