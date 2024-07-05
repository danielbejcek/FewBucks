package com.RedRobot.Daniel.FewBucks.repositories;

import com.RedRobot.Daniel.FewBucks.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Long> {
}
