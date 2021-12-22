package com.example.jwt;

import com.example.jwt.model.AuthRequest;
import com.example.jwt.model.AuthResponse;
import com.example.jwt.services.MyUserDetailsService;
import com.example.jwt.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloResource {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    public HelloResource(AuthenticationManager authenticationManager,
                         MyUserDetailsService userDetailsService,
                         JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping("/")
    public String index() {
        return "Hello world";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {

        try {
            System.out.println("log from rest api");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Invalid username or password");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));

    }
}
