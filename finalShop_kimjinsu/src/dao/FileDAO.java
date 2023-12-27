package dao;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

public class FileDAO {
	private String curPath = "src/files/";
	private Charset charset = StandardCharsets.UTF_8;
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
		Path path = Paths.get(curPath + name.getName());
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
		Path path = Paths.get(curPath,name.getName());
		StringBuilder data = new StringBuilder();
		try (FileInputStream fis = new FileInputStream(path.toString());
			 InputStreamReader is = new InputStreamReader(fis,charset);
			BufferedReader br = new BufferedReader(is)){
			String read;
			while((read = br.readLine()) != null) {
				data.append(read).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data.toString().substring(0,data.length()-1);
	}
	//read 같은경우는 엔터값이 가져오는게 있고 안가지고오는게 있기에 변수가 많아 사용하기 힘들다.
	/*private String load2(FileName name) {
		Path path = Paths.get(curPath,name.getName());
		StringBuilder data = new StringBuilder();
		try (FileInputStream fis = new FileInputStream(path.toString());
				BufferedInputStream bis = new BufferedInputStream(fis)){
			byte[] buffer = new byte[1024];
			int readbyte = 0;
			while(true) {
				readbyte = bis.read(buffer);
				if(readbyte == -1) break;
				String read = new String(buffer,0,readbyte,charset);
				data.append(read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data.toString();
	}*/
	//파일 로드
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
	// 저장
	private void save(FileName name,String data) {
		Path path = Paths.get(curPath,name.getName());
		try(FileOutputStream fos = new FileOutputStream(path.toString());
			OutputStreamWriter ow = new OutputStreamWriter(fos,charset);
			BufferedWriter bw = new BufferedWriter(ow)){
			bw.write(data);
			System.out.printf("%s 저장 성공%n",name);
		} catch (IOException e) {
			System.out.printf("%s 저장 실패%n",name);
		}
	}
	// 파일 저장
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
