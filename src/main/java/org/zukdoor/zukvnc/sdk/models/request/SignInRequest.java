package org.zukdoor.zukvnc.sdk.models.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SignInRequest {
	private final String username;
	private final String password;
}
