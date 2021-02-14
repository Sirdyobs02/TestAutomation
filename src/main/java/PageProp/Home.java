package PageProp;

public class Home {
	
	
	
	public String page_title_xpath() {
		return "//strong[text()='XYZ Bank']";
	}
	
	public String home_button_xpath() {
		return "//button[@class='btn home' and text()='Home']";
	}
	
	public String entity_login_button_xpath(String entity) {
		return "//button[@class='btn btn-primary btn-lg' and text()='"+entity+"']";
	}

	public String bank_manager_login_xpath() {
		return "//button[@class='btn btn-primary btn-lg' and text()='Bank Manager Login']";
	}
	
}
