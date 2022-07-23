package com.example.vehiclesharing.constants;

public interface IAppMessages {
    public static final String INVALID_CREDENTIALS = "Invalid credentials";
    public static final String USER_REGISTERED_SUCCESSFULLY= "User Registered Successfully";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String ERROR_REGISTERING_USER = "Error Registering User, Please try again!" ;
    public static final String NO_USER= "No such user exists";

    //Notification messages
    public static final String USER_REGISTERED_NOTIFY_SUBJECT="REGISTRATION SUCCESSFUL!";
    public static final String USER_REGISTERED_NOTIFY_MESSAGE="Welcome! You are registered successfully. You can start your journey with us by booking a ride";

    public static final String RIDE_BOOKED_NOTIFY_SUBJECT="RIDE BOOKED SUCCESSFUL!";
    public static final String RIDE_BOOKED_NOTIFY_MESSAGE="Yaaay! Your ride has been booked successfully! You will receive a notification soon from a driver. Hope you have a safe and exciting journey.";

    public static final String NOTIFY_NO_DRIVER= "No driver present";

}
