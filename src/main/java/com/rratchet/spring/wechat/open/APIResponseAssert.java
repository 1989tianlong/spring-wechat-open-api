package com.rratchet.spring.wechat.open;

import java.text.MessageFormat;

import com.rratchet.spring.wechat.open.token.accesstoken.AccessTokenExpiredException;

public class APIResponseAssert {

	public void assertOK(String errorMessage, Integer errorCode){
		if(errorCode != null && errorCode != 0) {
			if(errorCode.equals(AccessTokenExpiredException.INVALID_ACCESS_TOKEN_ERROR)) {
				throw new AccessTokenExpiredException(errorMessage);
			}
			String formatedErrorMessage = MessageFormat.format("wechat response errcode:{0}, errmsg:{1}", errorCode.toString(), errorMessage);
			throw new ErrorResponseException(errorCode, formatedErrorMessage);
		}
	}
	
	public void assertOK(CommonResponse response) {
		if(response == null) {
			throw new IllegalArgumentException("CommonResponse argument must not be null.");
		}
		assertOK(response.getErrmsg(), response.getErrcode());
	}
}
