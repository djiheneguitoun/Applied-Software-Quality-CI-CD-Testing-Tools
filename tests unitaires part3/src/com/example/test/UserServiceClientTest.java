package com.example.test;


import com.example.model.GitHubResponse;
import com.example.service.Endpoint;
import com.example.service.UserServiceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import static org.mockito.Mockito.when;



    class UserServiceClientTest {

        @Test
        void getUser_success() throws URISyntaxException, IOException, InterruptedException {
            Endpoint endpoint = Mockito.mock(Endpoint.class);
            UserServiceClient userServiceClient = new UserServiceClient();


            HttpResponse<String> response = Mockito.mock(HttpResponse.class);

            when(response.statusCode()).thenReturn(200);
            when(response.body()).thenReturn("{\"name\":\"username\"}");

            when(endpoint.getResponse("https://api.github.com/users/{username}")).thenReturn(response);


            userServiceClient.setEndpoint(endpoint);
            GitHubResponse gitHubResponse = userServiceClient.getUser("https://api.github.com/users/{username}");
            Assertions.assertEquals(200, gitHubResponse.getStatusCode());
            Assertions.assertEquals("username",gitHubResponse.getGitHubUser().getName());
        }

        @Test
        void getUser_fail() throws URISyntaxException, IOException, InterruptedException {
            Endpoint endpoint = Mockito.mock(Endpoint.class);
            UserServiceClient userServiceClient = new UserServiceClient();
            HttpResponse<String> response = Mockito.mock(HttpResponse.class);
            when(response.statusCode()).thenReturn(404);
            when(endpoint.getResponse("https://api.github.com/users/{username}")).thenReturn(response);
            userServiceClient.setEndpoint(endpoint);
            GitHubResponse gitHubResponse = userServiceClient.getUser("https://api.github.com/users/{username}");
            Assertions.assertEquals(404, gitHubResponse.getStatusCode());
        }

        @Test
        void getUser_integration_success() throws URISyntaxException, IOException, InterruptedException {
            Endpoint endpoint = new Endpoint();//on mock ici 
            HttpResponse<String> response ;
            //when(endpoint.getResponse("https://api.github.com/users/{username}")).thenReturn(response);

            UserServiceClient userServiceClient = new UserServiceClient();
            userServiceClient.setEndpoint(endpoint);
            GitHubResponse gitHubResponse = userServiceClient.getUser("https://api.github.com/users/dia-na-oct");
            Assertions.assertEquals(200, gitHubResponse.getStatusCode());
            Assertions.assertEquals("dia_ne__",gitHubResponse.getGitHubUser().getName());
        }

        @Test
        void getUser_integration_fail() throws URISyntaxException, IOException, InterruptedException {
            Endpoint endpoint = new Endpoint();
            UserServiceClient userServiceClient = new UserServiceClient();
            userServiceClient.setEndpoint(endpoint);
            GitHubResponse gitHubResponse = userServiceClient.getUser("https://api.github.com/users/username");
            Assertions.assertEquals(404, gitHubResponse.getStatusCode());
        }

    }
