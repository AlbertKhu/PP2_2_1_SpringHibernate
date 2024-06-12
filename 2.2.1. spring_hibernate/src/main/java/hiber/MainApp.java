package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User Alan = new User("Alan", "Alanov", "user1@mail.ru");
      User Gama = new User("Gama", "Gamov", "user2@mail.ru");
      User Rama = new User("Rama", "Ramov", "user3@mail.ru");
      User Sama = new User("Sama", "Samov", "user4@mail.ru");

      Car Vaz = new Car("Vaz", 21074);
      Car Ford = new Car("Ford", 150);
      Car Toyota = new Car("Toyota", 340);
      Car Nissan = new Car("Nissan", 280);

      userService.add(Alan.setCar(Vaz).setUser(Alan));
      userService.add(Gama.setCar(Ford).setUser(Gama));
      userService.add(Rama.setCar(Toyota).setUser(Rama));
      userService.add(Sama.setCar(Nissan).setUser(Sama));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }

      System.out.println(userService.getUserByCar("Ford", 150));

      try {
         User notFoundUser = userService.getUserByCar("Moscvich", 412);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
