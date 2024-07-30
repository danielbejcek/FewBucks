package com.RedRobot.Daniel.FewBucks.services;

import com.RedRobot.Daniel.FewBucks.DTO.CartItemDTO;
import com.RedRobot.Daniel.FewBucks.DTO.ShoppingCartDTO;
import com.RedRobot.Daniel.FewBucks.entities.CartItem;
import com.RedRobot.Daniel.FewBucks.entities.Inventory;
import com.RedRobot.Daniel.FewBucks.entities.ShoppingCart;
import com.RedRobot.Daniel.FewBucks.entities.Users;
import com.RedRobot.Daniel.FewBucks.repositories.CartItemRepo;
import com.RedRobot.Daniel.FewBucks.repositories.InventoryRepo;
import com.RedRobot.Daniel.FewBucks.repositories.ShoppingCartRepo;
import com.RedRobot.Daniel.FewBucks.repositories.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    ShoppingCartRepo shoppingCartRepo;

    @Autowired
    CartItemRepo cartItemRepo;

    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    UsersRepo usersRepo;

    /*Method that returns a shopping cart object converted to a DTO object based on a username*/
    public ShoppingCartDTO getCartByUser(String username){
        Users user = usersRepo.findByUserName(username).orElseThrow(() -> new IllegalArgumentException("User not found."));
        ShoppingCart shoppingCart = shoppingCartRepo.findByUser(user);

        return convertToShoppingCartDTO(shoppingCart); /*Converts a ShoppingCart object into a ShoppingCartDTO*/

    }

    public ShoppingCartDTO addItemToCart(String username, Long itemId, int quantity) {
        Users user = usersRepo.findByUserName(username).orElseThrow(() -> new IllegalArgumentException("User not found."));
        /*Creates a new ShoppingCart object if there isn't one created already. Then proceeds to assign said shoppingCart to the user*/
        ShoppingCart shoppingCart = shoppingCartRepo.findByUser(user);

        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
        }
        /*Fetches the item from the inventoryRepo according to the provided ID in the method's parameter*/
        Inventory inventoryItem = inventoryRepo.findById(itemId).orElseThrow(() -> new IllegalArgumentException("Item not found."));

        /*Check if the item already exists in the shopping cart by filtering the cart items based on the item ID.*/
        Optional<CartItem> duplicate = shoppingCart // This object represents an existing item in the shopping cart that matches the itemId
                .getItems()
                .stream()
                .filter(item -> item.getItem().getId().equals(itemId)).findFirst();

        /*If a duplicate item is found in the cart, update its quantity and total price.*/
        if (duplicate.isPresent()) {
            CartItem duplicateItem = duplicate.get(); // When a duplicate item is found, we retrieve it using duplicate.get()
            duplicateItem.setQuantity(duplicateItem.getQuantity() + quantity);
            duplicateItem.setItemPrice(duplicateItem.getItemPrice() + (inventoryItem.getItemPrice() * quantity));

        } else {
            CartItem cartItem = new CartItem(); // If no duplicate item is found, we create a new CartItem object
            cartItem.setItem(inventoryItem);
            cartItem.setQuantity(quantity);
            cartItem.setItemPrice(inventoryItem.getItemPrice());
            cartItem.setCart(shoppingCart);
            shoppingCart.getItems().add(cartItem); // Using .getItems() which returns a list of CartItem objects, only then we can use an .add(cartItem) method
        }

        if (shoppingCart.getItems() == null) {
            shoppingCart.setItems(new ArrayList<>());

        }

        shoppingCartRepo.save(shoppingCart);

        return convertToShoppingCartDTO(shoppingCart);
    }

    public ShoppingCartDTO removeItemFromCart(String username, long cartItemId){
        Users user = usersRepo.findByUserName(username).orElseThrow(()-> new IllegalArgumentException("Username not found"));
        ShoppingCart shoppingCart = shoppingCartRepo.findByUser(user); // Find the shopping cart associated with the user
        if (shoppingCart == null){
            throw new IllegalArgumentException("Cart not found");
        }
        Optional<CartItem> cartItem = shoppingCart
                .getItems()
                .stream()
                .filter(item -> item.getId().equals(cartItemId)) // Filters the stream to include only items with an ID matching cartItemId
                .findFirst();
        if (cartItem.isEmpty()){ // Check to ensure that the Optional contains a value
            throw new IllegalArgumentException("Cart item not found");
        }
        shoppingCart.getItems().remove(cartItem.get()); //Using .get() to retrieve the actual value contained within the Optional
        cartItemRepo.delete(cartItem.get()); // Delete the cart item from the repository (database)

        return convertToShoppingCartDTO(shoppingCart); // Converts the shoppingCart to a DTO and returns the updated version of ShoppingCartDTO to the client
    }

    /*Method that converts ShoppingCart object into a ShoppingCartDTO*/
    private ShoppingCartDTO convertToShoppingCartDTO(ShoppingCart shoppingCart){
        if (shoppingCart == null){
            return null; // Method will exit if parameter shoppingCart is null
        }
        /* Creating a new shoppingCartDTO object. This will hold all the object we retrieve from the ShoppingCart entity*/
        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
        shoppingCartDTO.setId(shoppingCart.getId()); // Set the ID of the ShoppingCartDTO to the ID of the ShoppingCart
        shoppingCartDTO.setUserId(shoppingCart.getUser().getId()); // Set the User ID of the ShoppingCartDTO to the ID of the User associated with the ShoppingCart.

        /* Convert the list of CartItem entities to a list of CartItemDTOs */
        List<CartItemDTO> cartItemDTO = shoppingCart
                .getItems()
                .stream() // Convert the list of CartItem entities to a stream

                /* Apply the convertToCartItemDTO method to each CartItem entity.
                Using ".map" to transform each element in the stream with the use of lambda expression*/
                .map(this::convertToCartItemDTO)

                .collect(Collectors.toList()); // Collect the results into a list
        shoppingCartDTO.setItems(cartItemDTO); // Sets the converted CartItemDTO list in the ShoppingCartDTO

        return shoppingCartDTO;
    }

    /*Converts individual CartItem entity to CartItemDTO*/
    private CartItemDTO convertToCartItemDTO(CartItem cartItem){
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartItem.getId());
        cartItemDTO.setItemId(cartItem.getItem().getId());
        cartItemDTO.setItemName(cartItem.getItem().getItemName());
        cartItemDTO.setQuantity(cartItem.getQuantity());
        cartItemDTO.setItemPrice(cartItem.getItemPrice());
        return cartItemDTO;
    }
}
