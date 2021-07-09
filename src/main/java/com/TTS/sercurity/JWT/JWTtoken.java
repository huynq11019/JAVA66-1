package com.TTS.sercurity.JWT;

import com.TTS.DTO.AccountDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTtoken {
	private String idToken;
	private String tokenType = "bruh";
	private String name;
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
