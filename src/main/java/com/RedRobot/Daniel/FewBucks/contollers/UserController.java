package com.RedRobot.Daniel.FewBucks.contollers;

import com.RedRobot.Daniel.FewBucks.DTO.ShoppingCartDTO;
import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.repositories.UsersRepo;
import com.RedRobot.Daniel.FewBucks.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/auth")
public class UserController {

    @Autowired
    CartService cartService;

    @Autowired
    UsersRepo usersRepo;

    @GetMapping("/listBasket")
    public ResponseEntity<ShoppingCartDTO> listBasket(Authentication auth){
        String username = auth.getName();
        return ResponseEntity.ok(cartService.getCartByUser(username));
    }

    @PostMapping("/addItemToBasket")
    public ResponseEntity<ShoppingCartDTO> addItemToBasket(@RequestParam Long itemId, @RequestParam int quantity, Authentication auth){
        String username = auth.getName();
//        Users user = usersRepo.findByUserName(username).orElseThrow(()-> new IllegalArgumentException("User not found"));
//        System.out.println(user.getId());
        return ResponseEntity.ok(cartService.addItemToCart(username,itemId,quantity));
    }

    @DeleteMapping("/removeItemFromBasket")
    public ResponseEntity<ShoppingCartDTO> removeItemFromBasket(@RequestParam Long itemId, Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(cartService.removeItemFromCart(username, itemId));
    }

//    Implement methods for adding, removing and viewing items in the shopping cart.

}
