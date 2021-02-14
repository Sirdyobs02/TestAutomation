package PageProp;

public class UserAPI {
	
	public String post_user() {
		return "https://jsonplaceholder.typicode.com/users";
	}

	public String get_one_user(String id) {
		return "https://jsonplaceholder.typicode.com/users?id="+id;
	}
	
	public String get_ten_users() {
		return "https://jsonplaceholder.typicode.com/users";
	}
	
}
