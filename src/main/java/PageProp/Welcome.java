package PageProp;

public class Welcome {
	
	
	public String welcome_message_xpath() {
		return "//strong[contains(text(),'Welcome')]/span[text()]";
	}
	
	public String select_account_name() {
		return "accountSelect";
	}

	public String balance_field_xpath() {
		return "//div[@class='center'][1]/strong[2]";
	}
	
	
	public String button_deposit_xpath() {
		return "//button[@ng-click='deposit()' and contains(text(),'Deposit')]";
	}
	
	public String button_withdrawl_xpath() {
		return "//button[@class='btn btn-lg tab' and contains(text(),'Withdrawl')]";
	} 
	
	public String input_field_deposit_xpath() {
		return "//input[@type='number']";
	}
	
	public String button_complete_deposit_xpath() {
		return "//button[@type='submit' and text()='Deposit']";
	}
	 
	public String button_complete_withdraw_xpath() {
		return "//button[@type='submit' and text()='Withdraw']";
	}
	
	public String success_text_xpath() {
		return "//span[@class='error ng-binding']";
	}
	
}
