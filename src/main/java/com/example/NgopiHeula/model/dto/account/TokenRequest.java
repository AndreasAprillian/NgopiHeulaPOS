package com.example.NgopiHeula.model.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenRequest {

    @Schema(description = "Username maximum 20 characters.")
    private String username;

    @Schema(description = "Password maximum 20 characters.")
    private String password;

    @Schema(description = "Username, Email atau topic dari requester.")
    private String subject;

    @Schema(description = "Secret key dari API")
    private String secretKey;

    @Schema(description = "Pengguna API")
    private String audience;
}
