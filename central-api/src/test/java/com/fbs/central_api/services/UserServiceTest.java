package com.fbs.central_api.services;

import com.fbs.central_api.connectors.DBApiConnector;
import com.fbs.central_api.utility.AuthUtility;
import com.fbs.central_api.utility.Mapper;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fbs.central_api.exceptions.InvalidCredentials;

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
}
