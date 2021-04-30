package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("q",1);
      Car car2 = new Car("w",2);
      Car car3 = new Car("e",3);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user1@mail.ru");
      User user3 = new User("User3", "Lastname3", "user1@mail.ru");
      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      User x = userService.getUserCar("e",3);
      System.out.println(x.getFirstName());

      for (User u:userService.listUsers()){
         System.out.println(u+" - "+u.getCar());
      }

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car" + user.getCar().getModel());
         System.out.println("Car_series" + user.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
