package application;

public class Bill {
	
	public String invoiceno;
	public String custname;
	public String custcont;
	public String custemail;
	public double totamnt;
	public Bill(String invoiceno, String custname, String custcont, String custemail, double totamnt) {
		super();
		this.invoiceno = invoiceno;
		this.custname = custname;
		this.custcont = custcont;
		this.custemail = custemail;
		this.totamnt = totamnt;
	}
	
}
