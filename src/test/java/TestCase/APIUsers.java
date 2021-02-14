package TestCase;

import java.io.UnsupportedEncodingException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.relevantcodes.extentreports.LogStatus;

import PageProp.UserAPI;
import Utility.APIUtility;

public class APIUsers extends APIUtility {

	UserAPI api = new UserAPI();
	public boolean Test1(String user_payload) throws UnsupportedEncodingException, JsonProcessingException {
		UserAPI api = new UserAPI();
		boolean post_user = post_request(api.post_user(), user_payload);
		if (!post_user) {
			test.log(LogStatus.FAIL, "Post request was not successfully");
			return true;
		}
		test.log(LogStatus.PASS, "Post request was successfully");
		return false;
	}

	public boolean Test2() {

		String get_user = getRequest(api.get_one_user("3"));
		int i =countElementsInJsonObject();
		if (!(i == 1 & !get_user.isEmpty())) {
			test.log(LogStatus.FAIL, "request did not return a user");
		}
		test.log(LogStatus.PASS, "User returned successfully");	
		return true;

	}

	public boolean Test3() {
		String get_user = getRequest(api.get_ten_users());
		int i =countElementsInJsonObject();
		if (!(i == 10 & !get_user.isEmpty())) {
			test.log(LogStatus.FAIL, "request did not return 10 user");
		}
		test.log(LogStatus.PASS, "10 User returned successfully");	
		return true;

	}

}
