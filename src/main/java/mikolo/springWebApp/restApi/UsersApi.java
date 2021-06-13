package mikolo.springWebApp.restApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mikolo.springWebApp.user.User;
import mikolo.springWebApp.user.UserService;

@RestController
@RequestMapping("/api/users")
public class UsersApi {

	private UserService userService;
	
	@Autowired
	public UsersApi(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/getall")
	private List<User>getAll(){
		return userService.findAll();
	}
}
