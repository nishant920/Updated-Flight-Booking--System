package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.dto.LoginDto;
import com.fbs.central_api.dto.LoginResponseDto;
import com.fbs.central_api.models.AppUser;
import com.fbs.central_api.utility.AuthUtility;
import com.fbs.central_api.utility.Mapper;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fbs.central_api.exceptions.InvalidCredentials;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class UserServiceTest {
    @Test
    void throwInvalidCredentialsIsWhenUserDoesNotExist(){
        DBApiConnector dbApiConnector = mock(DBApiConnector.class);
        AuthUtility authUtility = mock(AuthUtility.class);
        Mapper mapper = mock(Mapper.class);
        MailService mailService = mock(MailService.class);

        UserService userService = new UserService(
                dbApiConnector,
                authUtility,
                mapper,
                mailService
        );

        when(dbApiConnector.callGetUserByEmailEndpoint("wrong@gmail.com"))
            .thenReturn(null);

        assertThrows(InvalidCredentials.class, () -> {
            userService.isValidCredentials("wrong@gmail.com", "wrongPassword");
        });
    }
    @Test
    void shouldReturnLoginResponseWhenCredentialsAreValid(){
        DBApiConnector dbApiConnector = mock(DBApiConnector.class);
        AuthUtility authUtility = mock(AuthUtility.class);
        Mapper mapper = mock(Mapper.class);
        MailService mailService = mock(MailService.class);

        UserService userService = new UserService(
                dbApiConnector,
                authUtility,
                mapper,
                mailService
        );

        AppUser appUser = new AppUser();
        appUser.setName("Nishant");
        appUser.setEmail("nishant@gmail.com");
        appUser.setPassword("correctPass");
        appUser.setUserType("CUSTOMER");

        when(dbApiConnector.callGetUserByEmailEndpoint("nishant@gmail.com")).thenReturn(appUser);

        when(authUtility.generateToken(
                appUser.getEmail(),
                appUser.getPassword(),
                appUser.getUserType()
        )).thenReturn("fake-jwt-token");

        LoginResponseDto loginResponseDto = new LoginResponseDto(
                "fake-jwt-token",
                "Nishant",
                "nishant@gmail.com",
                "Login successful"
        );

        when(mapper.mapAppUserToLoginResponseDto(appUser, "fake-jwt-token"))
                .thenReturn(loginResponseDto);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("nishant@gmail.com");
        loginDto.setPassword("correctPass");

        LoginResponseDto actualRes = userService.login(loginDto);

        assertEquals("fake-jwt-token", actualRes.getToken());
        assertEquals("Nishant", actualRes.getName());
        assertEquals("nishant@gmail.com", actualRes.getEmail());
        assertEquals("Login successful", actualRes.getMessage());
    }
}
