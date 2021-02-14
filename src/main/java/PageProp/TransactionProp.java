package PageProp;

public class TransactionProp {

	
	
	public String start_date_name() {
		return "start";
	}
	
	public String end_date_name() {
		return "end";
	}
	
	public String button_transaction_xpath() {
		return "//button[@class='btn btn-lg tab' and contains(text(),'Transactions')]";
	}
	
	
	public String new_transaction_xpath(String time, String amount, String type) {
		return "//tr[td[contains(text(),'"+amount+"')] and td[contains(text(),'"+type+"')]]";
	}
	
	
	public String button_back_xpath() {
		return "//button[@ng-click='back()' and text()='Back']";
	}
	
}
