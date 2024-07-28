package com.RedRobot.Daniel.FewBucks.repositories;

import com.RedRobot.Daniel.FewBucks.entities.ShoppingCart;
import com.RedRobot.Daniel.FewBucks.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepo extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findByUser(Users user);
}
