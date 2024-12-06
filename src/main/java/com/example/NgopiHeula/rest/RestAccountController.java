package com.example.NgopiHeula.rest;

import com.example.NgopiHeula.model.dto.account.TokenRequest;
import com.example.NgopiHeula.model.dto.account.TokenResponse;
import com.example.NgopiHeula.service.abstraction.AccountService;
import com.example.NgopiHeula.utility.JwtToken;
import com.example.NgopiHeula.utility.ResponsesHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RestAccountController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResponsesHelper responsesHelper;

    @PostMapping("/authenticate")
    public ResponseEntity<Object> tokenResponse(@RequestBody TokenRequest tokenRequest){
        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    tokenRequest.getUsername(), tokenRequest.getPassword());
            authenticationManager.authenticate(authenticationToken);
        }catch (Exception ex){}
        UserDetails userDetails = userDetailsService.loadUserByUsername(tokenRequest.getUsername());
        String role = accountService.getAccountRole(userDetails.getUsername());
        String token = jwtToken.generateToken(tokenRequest.getSubject(), userDetails.getUsername(), tokenRequest.getSecretKey(), role, tokenRequest.getAudience());

        TokenResponse response = new TokenResponse(tokenRequest.getUsername(),role,token);

        return responsesHelper.statusOk(response);
    }
}
