package com.ordermanagement.common.exception;

public class ResourceNotFoundException extends BusinessException{

   public ResourceNotFoundException(String resourcename, String fieldname, Object fieldvalue){
    
        super("RESOURCE_NOT_FOUND",String.format("%s not found with %s : '%s'",resourcename,fieldname,fieldvalue));
   }
    
}
