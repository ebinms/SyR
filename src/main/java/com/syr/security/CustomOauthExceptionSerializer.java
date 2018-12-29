package com.syr.security;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.syr.app_config.ApiResponse;

/**
 * @author apple
 * @on 29-Sep-2018
 * @version 
 */
public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException>{

	private static final long serialVersionUID = -2910908515781896583L;

	CustomOauthExceptionSerializer(){
		super(CustomOauthException.class);
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.ser.std.StdSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(CustomOauthException value, JsonGenerator jGen, SerializerProvider provider)
			throws IOException {
		/*jGen.writeStartObject();
		jGen.writeNumberField("responseCode",0);
		jGen.writeStringField("error", value.getOAuth2ErrorCode());
		jGen.writeStringField("message",value.getMessage());
		
		if(value.getAdditionalInformation()!=null){
			for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jGen.writeStringField(key, add);
            }
		}
		jGen.writeEndObject();*/
		
		ApiResponse apiResponse	= new ApiResponse(-1, value.getOAuth2ErrorCode(), value.getMessage(), value.getHttpErrorCode(), "en");
		
		jGen.writeObject(apiResponse);
		jGen.writeEndObject();
	}


}
