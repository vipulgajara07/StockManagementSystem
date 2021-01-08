package stock;

import java.util.Scanner;

class ProductMenu {
	void menu() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		CRUDProduct proOperate = new CRUDProduct();

		while (choice != 5) {
			System.out.println("---------Products---------");
			System.out.println();
			System.out.println("1) Add");
			System.out.println("2) Update");
			System.out.println("3) View");
			System.out.println("4) Delete");
			System.out.println("5) Exit");
			System.out.println("Enter your choice :-");

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				proOperate.addProduct();
				break;
			case 2:
				proOperate.updateProduct();
				break;
			case 3:
				proOperate.viewProduct();
				break;
			case 4:
				proOperate.deleteProduct();
				break;
			case 5:
				break;
			default:
				System.out.println("Please enter valid choice!");
			}
		}
	}
}
