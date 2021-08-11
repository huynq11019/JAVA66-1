package com.TTS.sercurity.JWT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTtoken {
	private String idToken;
	private String reFreshToken;
	private String tokenType = "bruh";
	private String name;
	private List<String> role;
//	private AccountDTO accountDTO;
//	public JWTtoken(String idToken) {
//
//		this.idToken = idToken;
//	}
//
//	@JsonProperty("id_token")
//	public String getIdtoken() {
//		return this.idToken;
//
//	}
//	
//	public String getTokenType() {
//		return this.tokenType;
//	}
//
//	public void setIdToken(String idToken) {
//		this.idToken = idToken;
//	}
}
