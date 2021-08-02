package com;

import com.menu.MainMenu;

public class App {
	
	public static void main(String[] args) {
		System.out.println("Pure banking nothing else; with you all the way");
		String type = null;
		System.out.println("Welocme To pritam banking App");
		System.out.println("----------------------------------------------------------");
		MainMenu main = new MainMenu();
		try {
			type = main.cheakType();
			
			main.Menu(type);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
