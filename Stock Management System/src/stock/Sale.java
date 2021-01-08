package stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

class Sale {
	
	Scanner sc = new Scanner(System.in);
	int choice = 0;
	String itemName = new String();
	DatabaseConnect dc = new DatabaseConnect();
	
	void sale() {

		while (choice != 2) {
			System.out.println("---------Sale Product---------");
			System.out.println();
			System.out.println("Enter Product Name");
			itemName = sc.next();
			int stock;

			// check whether product exists in database
			try {
				PreparedStatement ps1 = dc.conn.prepareStatement("select * from products where name = ?");
				ps1.setString(1, itemName);
				ResultSet rs = ps1.executeQuery();

				if (rs.next()) {
					System.out.println("How many quantity you want to sell?");
					stock = sc.nextInt();

					// product stock updated in database
					PreparedStatement ps = dc.conn.prepareStatement("update products set stock = stock - ? where name = ?");
					ps.setString(2, itemName);
					ps.setInt(1, stock);
					int x = ps.executeUpdate();

					if (x > 0) {
						System.out.println("Product Sold successfully!");
					} else {
						System.out.println("Product Selling failed!");
					}
				} else {
					System.out.println("The Product Doesn't Exists!");
				}

				System.out.println("Do you want to sell more product?");
				System.out.println("1) Yes");
				System.out.println("2) No");

				choice = sc.nextInt();

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
