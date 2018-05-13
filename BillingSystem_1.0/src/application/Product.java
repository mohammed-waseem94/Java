package application;

public class Product {

	public String product_id;
	public String product_name;
	public String barcode;
	public String model_no;
	public String material;
	public String category;
	public int carat;
	public double  net_wt;
	public double stone_wt;
	public double gross_wt;
	public double making;
	public double wastage;
	public String stonedetails;
	public Product(String product_id, String product_name, String barcode, String model_no, String material,
			String category, int carat, double net_wt, double stone_wt, double gross_wt, double making,
			double wastage, String stonedetails) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.barcode = barcode;
		this.model_no = model_no;
		this.material = material;
		this.category = category;
		this.carat = carat;
		this.net_wt = net_wt;
		this.stone_wt = stone_wt;
		this.gross_wt = gross_wt;
		this.making = making;
		this.wastage = wastage;
		this.stonedetails=stonedetails;
		
		
	}


}
