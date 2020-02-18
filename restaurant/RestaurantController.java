package restaurant;


/**
 *Basic controller for facilitating data to and from the view file
 * @author Diego Rodriguez
 * @Updated: 18-FEB-2020
 */
public class RestaurantController {

    protected Restaurant restaurantModel = new Restaurant();
    private String usersRequest;

    //=================  GETTERS ===============
    
    //gets a list of cuisines from the restaurant model
    public void getCuisineList() {
        System.out.println(restaurantModel.cuisineList);
    }
    
    //gets a list of restaurants from the model
    public void getRestaurantList() {
        System.out.println(restaurantModel.restaurantList);
    }

    //=================  SETTERS ===============
    
    //sets user request within the restaurant model
    public void setRequestType() {
        restaurantModel.setGetRequestType(this.usersRequest);
        System.out.println(this.usersRequest);
    }



}
