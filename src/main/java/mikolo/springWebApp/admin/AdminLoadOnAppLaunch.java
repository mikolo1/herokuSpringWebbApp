package mikolo.springWebApp.admin;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.transaction.annotation.Transactional;

import mikolo.springWebApp.user.Role;
import mikolo.springWebApp.user.RoleRepository;
import mikolo.springWebApp.user.User;
import mikolo.springWebApp.user.UserService;

@Configuration
public class AdminLoadOnAppLaunch {

	private UserService userService;
	private JpaContext jpaContext;
	private RoleRepository roleRepository;

	@Autowired
	public AdminLoadOnAppLaunch(UserService userService, JpaContext jpaContext, RoleRepository roleRepository) {
		this.userService = userService;
		this.jpaContext = jpaContext;
		this.roleRepository = roleRepository;
	}

	@Transactional
	@EventListener(ApplicationReadyEvent.class)
	public void loadAdmin() {
		Role roleAdmin = roleRepository.findByRole("ROLE_ADMIN");
		Role roleUser = roleRepository.findByRole("ROLE_USER");
		if (roleAdmin == null && roleUser == null) {
			EntityManager em = jpaContext.getEntityManagerByManagedType(Role.class);
			roleAdmin = new Role("ROLE_ADMIN");
			roleUser = new Role("ROLE_USER");
			em.persist(roleAdmin);
			em.persist(roleUser);
			em.flush();
			em.close();
		}

		String adminEmail = "admin@admin.com";
		User admin = userService.findByEmail(adminEmail);
		if (admin == null) {
			admin = new User();
			admin.setName("Admin");
			admin.setLastName("Admin");
			admin.setEmail(adminEmail);
			admin.setPassword("Admin123!");
			userService.saveAdmin(admin);
		}
	}
}