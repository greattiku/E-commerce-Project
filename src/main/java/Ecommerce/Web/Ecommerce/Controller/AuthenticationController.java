package Ecommerce.Web.Ecommerce.Controller;

import Ecommerce.Web.Ecommerce.Dto.AppResponse;
import Ecommerce.Web.Ecommerce.Dto.AuthenticationRequest;
import Ecommerce.Web.Ecommerce.Dto.UserRequest;
import Ecommerce.Web.Ecommerce.Dto.UserResetPassword;
import Ecommerce.Web.Ecommerce.Service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/Admin/createAdmin")
    public AppResponse<String> createAdmin(@RequestBody @Valid UserRequest userRequest) {
        return authenticationService.createAdmin(userRequest);
    }


    @PostMapping("/User/createUser")
    public AppResponse<String> createUser(@RequestBody @Valid UserRequest userRequest) {
        return authenticationService.createUser(userRequest);
    }

    @PostMapping("/login")
    public AppResponse<String> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }


    @PatchMapping("/User/resetPassword")
    public AppResponse<String> resetPassword(@RequestBody @Valid UserResetPassword userResetPassword,
                                             @RequestParam String email) {
        return authenticationService.resetPassword(userResetPassword, email);
    }
}