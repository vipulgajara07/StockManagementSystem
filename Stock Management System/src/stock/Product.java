package stock;

class Product {
	String name;
	float price;
	int stock;
}

interface ProductCRUD { //TODO THis should not be here. create sepearate java file for this interface
	void addProduct();
	void updateProduct();
	void viewProduct();
	void deleteProduct();
}
