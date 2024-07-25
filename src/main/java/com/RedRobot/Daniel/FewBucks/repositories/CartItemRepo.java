package com.RedRobot.Daniel.FewBucks.repositories;

import com.RedRobot.Daniel.FewBucks.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
}
