package stock;

import java.util.Scanner;

class MainMenu {
	void menu() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;

		while (choice != 4) {
			System.out.println("---------STOCK MANAGEMENT SYSTEM---------");
			System.out.println();
			System.out.println("1) Products");
			System.out.println("2) Purchase");
			System.out.println("3) Sale");
			System.out.println("4) Exit");
			System.out.println("Enter your choice :-");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				ProductMenu pmenu = new ProductMenu();
				pmenu.menu();
				break;
			case 2:
				Purchase purObj = new Purchase();
				purObj.purchase();
				break;
			case 3:
				Sale saleObj = new Sale();
				saleObj.sale();
				break;
			case 4:
				System.out.println("Thanks you for using System!");
				break;
			default:
				System.out.println("Please enter valid choice!");
			}
		}
	}
}
