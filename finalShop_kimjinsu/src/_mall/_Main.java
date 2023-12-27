package _mall;

import controller.*;

public class _Main {

	public static void main(String[] args) {
		MallController con = MallController.getInstance();
		con.init();
	}

}