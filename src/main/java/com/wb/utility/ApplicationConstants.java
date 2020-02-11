package com.wb.utility;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class ApplicationConstants {
	
    
    public static List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("pl"));
    
    //Message Key
    public static final String NOT_FOUND_KEY = "NotFoundKey";
    public static final String SERVER_ERROR_KEY = "ServerErrorKey";
    public static final String VALIDATION_FAILED_KEY = "ValidationFailedKey";
    public static final String GET_KEY = "GetKey";
    public static final String PROPERTY_NOTFOUND_KEY = "PropertyNotFoundKey";
    
    //API
    public static final String WB_API_PRIFIX = "/api/v1";
    
    //Swagger Document 
    public static final String GROUP_WB = "WEATHER";
    public static final String WB_API_BASEPACKAGR = "com.wb.controller";
    
}
