package Ecommerce.Web.Ecommerce.Dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserResetPassword {


   private String newPassword;

    private String confirmNewPassword;
}

