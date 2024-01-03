package Ecommerce.Web.Ecommerce.Service;


import Ecommerce.Web.Ecommerce.Configuration.JwtAuthenticationFilter;
import Ecommerce.Web.Ecommerce.Dto.AppResponse;
import Ecommerce.Web.Ecommerce.Dto.AuthenticationRequest;
import Ecommerce.Web.Ecommerce.Dto.UserRequest;
import Ecommerce.Web.Ecommerce.Dto.UserResetPassword;
import Ecommerce.Web.Ecommerce.Entity.Role;
import Ecommerce.Web.Ecommerce.Entity.User;
import Ecommerce.Web.Ecommerce.Exception.ApiException;

import Ecommerce.Web.Ecommerce.Repository.RoleRepository;
import Ecommerce.Web.Ecommerce.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final MyUserDetailsService myUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    public AppResponse<String> createAdmin(UserRequest userRequest) {
        boolean check = userRepository.existsByEmail(userRequest.getEmail());//

        if (check) throw new ApiException("User already exists, login to continue");

        User addingAdmin = new User();
        addingAdmin.setFirstName(userRequest.getFirstName());
        addingAdmin.setLastName(userRequest.getLastName());
        addingAdmin.setEmail(userRequest.getEmail());
        addingAdmin.setPhoneNumber(userRequest.getPhoneNumber());
        addingAdmin.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        addingAdmin.setRoles(adminRole());


        userRepository.save(addingAdmin);

        return new AppResponse<>(200, "Admin created successfully");
    }

    public Role adminRole() {
        Role role = roleRepository.findByName("Admin");

        if (role == null) {
            Role adminrole = new Role();
            adminrole.setName("Admin");
            return adminrole;
        }
        return role;
    }

    public AppResponse<String> createUser(UserRequest userRequest) {
        boolean check = userRepository.existsByEmail(userRequest.getEmail());//

        if (check) throw new ApiException("User already exists, login to continue");

        User addingUser = new User();
        addingUser.setFirstName(userRequest.getFirstName());
        addingUser.setLastName(userRequest.getLastName());
        addingUser.setEmail(userRequest.getEmail());
        addingUser.setPhoneNumber(userRequest.getPhoneNumber());
        addingUser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        addingUser.setRoles(userRole());

        userRepository.save(addingUser);

        return new AppResponse<>(200, "User created successfully");
    }

    public Role userRole() {
        Role role1 = roleRepository.findByName("User");

        if (role1 == null) {
            Role userRole = new Role();
            userRole.setName("User");
            return userRole;
        }
        return role1;
    }

    public AppResponse<String> login(AuthenticationRequest authenticationRequest) {

        var user = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            return new AppResponse<>(204, "wrong gmail/password");
        }


        var jwtToken = jwtService.generateToken(user);
        return new AppResponse<>(200, "Successfully logged in", jwtToken);
    }


    // email and new password. do a verification that must match with the email
    public AppResponse<String> resetPassword(UserResetPassword userResetPassword, String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ApiException("Invalid User"));
        user.setNewPassword(userResetPassword.getNewPassword());
        user.setConfirmNewPassword(userResetPassword.getConfirmNewPassword());
        if (!userResetPassword.getNewPassword().equals(userResetPassword.getConfirmNewPassword())) {
            return new AppResponse<>(400, "Passwords do not match. Please try again");
        }

            user.setPassword(passwordEncoder.encode(userResetPassword.getNewPassword()));
            userRepository.save(user);
            return new AppResponse<>(200, "Password Changed Successfully");

    }

}