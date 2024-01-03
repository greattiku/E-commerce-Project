package Ecommerce.Web.Ecommerce.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRequest {
    @NotEmpty(message = "Firstname can't be blank")
    private String firstName;

    @NotEmpty(message = "Lastname can't be blank")
    private String lastName;

    @NotEmpty(message = "Email can't be blank")
    private String email;

    @NotEmpty(message = "PhoneNumber can't be blank")
    private String phoneNumber;//do regex for users, phone number and admin


    @NotEmpty(message = "Password can't be blank")
    private String password;



}