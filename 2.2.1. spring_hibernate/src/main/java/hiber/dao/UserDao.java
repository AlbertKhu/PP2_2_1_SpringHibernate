package hiber.dao;

import hiber.model.User;
import java.util.List;

public interface UserDao {
   void addUser(User user);
   List<User> listUser();
   User getVehicle(String model, int series);
}
