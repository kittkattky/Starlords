package controllers;

/**
 * This is a universal controller meant to be used by any class in the project that needs it.
 * The sole purpose of this controller is to get and set a UUID.
 * The idea is to make it easy to pass a UUID from class to class using instances of this universal controller.
 * It also reduces redundancies across classes that need a get/set method to handle a UUID.
 * @author Diego Rodriguez
 * @date 4/25/20
 */
public class UUIDController {
    
    private String uuid;
    
    public void setUUID(String _uuid) {
        this.uuid = _uuid;
    }
    
    public String getUUID() {
        return this.uuid;
    }
}
