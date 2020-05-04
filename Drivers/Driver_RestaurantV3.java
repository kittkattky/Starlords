package Drivers;

import views.*;
import models.RestaurantModel;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Basic test driver to show that methods work, and information flow is correct.
 *
 * @author Diego Rodriguez
 */
public class Driver_RestaurantV3 {
    public static void main(String[] args) throws Exception {
        double lat = 36.066984;
        double lon = -79.800178;
        int cuisineID = 1;
        
        
//       
//        RestaurantModel modelA = new RestaurantModel();
//        RestaurantModel modelB = new RestaurantModel();
//
//        //modelA = modelA.loadCuisinesByLocation();
//        System.out.println(modelA.getCuisineMap());
//
//        //modelB = modelA.loadRestaurantsByID(1);
//        System.out.println(modelB.getRestaurantNameMap());
//        System.out.println(modelB.getRestaurantUrlMap());
//        System.out.println(modelB.getRestaurantAddressMap());
//        System.out.println(modelB.getRestaurantRatingMap());
        
        CuisineView tester = new CuisineView();
        Class view = tester.getClass();
        Field field = view.getDeclaredField("restaurantController");
        Class clazz = field.getType();
        Method[] methods = clazz.getMethods();
        Method methodCall = clazz.getDeclaredMethod("setUUID", String.class);
        methodCall.invoke(view.newInstance(), "69");
        System.out.println(tester.restaurantController.getUUID());
        for(Method i: methods){
            System.out.println(i.getName());
        }

    }

}
