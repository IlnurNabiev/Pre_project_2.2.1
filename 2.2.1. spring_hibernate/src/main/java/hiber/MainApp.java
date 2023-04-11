package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cascade;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.OneToOne;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car = new Car("Volvo", 123123);
      Car car2 = new Car("VW", 32423);
      Car car3 = new Car("Skoda", 9912);
      Car car4 = new Car("Kia", 345346);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

      User userOne = userService.getUser("Skoda",9912 );
      User userTwo = userService.getUser("Kia", 345346);
      System.out.println(userOne.toString());
      System.out.println(userTwo.toString());

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
