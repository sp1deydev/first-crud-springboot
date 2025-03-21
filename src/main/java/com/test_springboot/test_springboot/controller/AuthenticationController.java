package com.test_springboot.test_springboot.controller;
import com.nimbusds.jose.JOSEException;
import com.test_springboot.test_springboot.dto.request.*;
import com.test_springboot.test_springboot.dto.response.AuthenticationResponse;
import com.test_springboot.test_springboot.dto.response.IntrospectResponse;
import com.test_springboot.test_springboot.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/access-token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        IntrospectResponse result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }
    @PostMapping("/logout")
    ApiResponse<Void> introspect(@RequestBody LogoutRequest request) throws ParseException, JOSEException  {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
//                .result(result)
                .build();
    }
    @PostMapping("/refresh-token")
    ApiResponse<AuthenticationResponse> introspect(@RequestBody RefreshRequest request) throws ParseException, JOSEException  {
        var result = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }
}
