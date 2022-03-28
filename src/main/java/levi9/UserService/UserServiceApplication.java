package levi9.UserService;

import levi9.UserService.model.User;
import levi9.UserService.model.enums.UserRole;
import levi9.UserService.repository.UserRepository;
import levi9.VehicleService.api.VehicleServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(clients = VehicleServiceApi.class)
@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	private UserRepository userRepository;

	@Autowired
	public UserServiceApplication(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin = new User("admin", "admin", UserRole.ADMIN, "admin", "admin", "admin@mail.com");
		userRepository.save(admin);
	}
}
