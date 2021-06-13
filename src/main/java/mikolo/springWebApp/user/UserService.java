package mikolo.springWebApp.user;

import java.util.List;

public interface UserService {
	
	public User findByEmail(String email);
	public void saveUser(User user);
	public void saveAdmin(User admin);
	public void updatePassword(String newPassword, String email);
	public void updateUser(String newEmail, String newName, String newLastName, long id);
	public void updateUserActivation(int active, String activationCode);
	public User findUserByActivationCode(String activationcode);
	public List<User> findAll();
	
}
