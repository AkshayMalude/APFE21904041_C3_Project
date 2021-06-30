import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;


class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    //REFACTOR ALL THE REPEATED LINES OF CODE
    public void addNewRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("23:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {           //Will fail when restaurant timings are out of bounds.

        Assertions.assertTrue(restaurant.isRestaurantOpen());

    }


    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {             //Will pass when restaurant timings are out of bounds.

        Assertions.assertFalse(restaurant.isRestaurantOpen());


    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);

        Assertions.assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        Assertions.assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        Assertions.assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    @Test
    public void checking_price_if_no_items_are_added() {
        restaurant = Mockito.spy(restaurant);
        List<Item> selectedItems = new ArrayList<>();
        Assertions.assertEquals(0, restaurant.getPrice(selectedItems));
        System.out.println("No items have been selected");
    }

    @Test
    public void checking_total_price_when_one_item_is_added() {
        restaurant = Mockito.spy(restaurant);
        Item i1 = new Item("Pasta", 200);
        List<Item> selectedItems = new ArrayList<>();
        selectedItems.add(i1);
        Assertions.assertEquals(200, restaurant.getPrice(selectedItems));
        System.out.println("Total order cost is: Rs." +restaurant.getPrice(selectedItems));
    }

    @Test
    public void checking_total_price_when_two_items_is_added() {
        restaurant = Mockito.spy(restaurant);
        Item i1 = new Item("Pasta", 200);
        Item i2 = new Item("Pizza", 300);
        List<Item> selectedItems = new ArrayList<>();
        selectedItems.add(i1);
        selectedItems.add(i2);
        Assertions.assertEquals(500, restaurant.getPrice(selectedItems));
        System.out.println("Total order cost is: Rs."+restaurant.getPrice(selectedItems));


    }
}