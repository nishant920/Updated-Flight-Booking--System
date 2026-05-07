package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.CustomerRegistrationDto;
import com.fbs.central_api.dto.CustomerRegistrationResponseDto;
import com.fbs.central_api.enums.UserType;
import com.fbs.central_api.exceptions.InvalidCredentials;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utility.AuthUtility;
import com.fbs.central_api.utility.Mapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/*
This class is going contain all the user related logics
 */
@Slf4j
@Service
public class UserService {
    DBApiConnector dbApiConnector;
    AuthUtility authUtility;
    Mapper mapper;
    MailService mailService;

    public UserService(DBApiConnector dbApiConnector, AuthUtility authUtility, Mapper mapper, MailService mailService){
    this.dbApiConnector=dbApiConnector;
    this.authUtility=authUtility;
    this.mapper=mapper;
    this.mailService=mailService;
    }

    public List<AppUser> getAllSystemAdmins(){
        return dbApiConnector.callGetAllUsersByUserType(UserType.SYSTEM_ADMIN.toString());
    }

    public AppUser updateUserDetails(AppUser user){
        return dbApiConnector.callUpdateUserEndpoint(user);
    }

    /**
     * Work of this method is to bring user by email
     * @param email
     * @return
     */
    public AppUser getUserByEmail(String email){
        // Internally this method will bring user on the basis of email
        // For now lets check database api connector are we having any method which will bring user by email
        return dbApiConnector.callGetUserByEmailEndpoint(email);
    }
    /*
    This function should be able to validate the credentials of the user.
    1. This function will first get the user record from the user table on the basis of email
    2. After getting the user this function will compare the password that password is correct or not.
    3. If correct, we will return true else we will return false.
     */
    public String isValidCredentials(String email, String password){
        // 1. We need to develop one method which will bring user from the user table on the basis of email -> Done
        AppUser user = this.getUserByEmail(email);
        // 2. Validate we got the user object as null? Is Yes throw exception else Move forward
        if(user == null){
            throw new InvalidCredentials("Email or password is wrong");
        }
        if(user.getPassword().equals(password)){
            // if password is correct I am going to return token
            // If incorrect, I am going to return null value.
            return authUtility.generateToken(user.getEmail(), user.getPassword(), user.getUserType());
        }
        throw new InvalidCredentials("Email or password is wrong");
    }

    public boolean validateToken(String token){
        String payload = authUtility.decryptJwtToken(token);
        String [] details = payload.split(":");
        String email = details[0];
        String password = details[1];
        // I want to validate email & password is it belonging to correct user or not
        // Auth Utility is going to call UserService to validate email and password belongs to correct user or not
        try{
            isValidCredentials(email, password);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public AppUser getUserFromToken(String token){
        String payload = authUtility.decryptJwtToken(token);
        String email = payload.split(":")[0];
        return this.getUserByEmail(email);
    }
    public CustomerRegistrationResponseDto registerUser(CustomerRegistrationDto customerRegistrationDto){
       AppUser appUser = getUserByEmail(customerRegistrationDto.getEmail());
       if (appUser!=null){
          throw new RuntimeException("User is already present");
       }
        AppUser appUser1 = new AppUser();
        appUser1 = mapper.mapCustomerRegistrationDtoToAppUser(customerRegistrationDto,appUser1);
        appUser1.setVerified(false);
        AppUser savedUser = dbApiConnector.callCreateUserEndpoint(appUser1);
        log.info("Customer saved successfully with email: {}", savedUser.getEmail());
        log.info("Calling mail service for customer registration email: {}", savedUser.getEmail());
        mailService.notifyCustomerAboutRegistration(savedUser.getEmail(), savedUser.getName());
        return mapper.mapCustomerToResponceDto(savedUser);
    }
}
