package com.infy.visitor.management.utils;
import java.util.Collection;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author neeraj
 *
 */
@Slf4j
@Component
public class Utility {



  public Boolean validateCsvOrElsxType(String originalFilename) {

    Boolean validateCsvOrElsxType=Boolean.FALSE;

    if(originalFilename.endsWith(".csv")){
      validateCsvOrElsxType=Boolean.TRUE;}
    else  if(originalFilename.endsWith(".xlsx")){
    validateCsvOrElsxType=Boolean.TRUE;}
  return validateCsvOrElsxType;
  }



  @SuppressWarnings("rawtypes")
public static boolean isNotEmpty(final Object obj) {
    boolean returnVal = false;
    try {
      if (obj != null) {
        if (obj instanceof String) {
          String str = (String) obj;
          if (str.trim().length() > 0) {
            returnVal = true;
          }
        } else if (obj instanceof Collection) {
          Collection col = (Collection) obj;
          if (!col.isEmpty()) {
            returnVal = true;
          }
        }
      }
    } catch (Exception e) {
      log.error("=== Error from Utility.isNotEmpty() " + e.getMessage());
    }

    return returnVal;
  }


 public static boolean isValidEmailAddress(String emailId) {
    boolean result = false;
    InternetAddress emailAddress = null;
    try {
      emailAddress = new InternetAddress(emailId);
      emailAddress.validate();
      result = true;

    } catch (AddressException | NullPointerException e) {
      log.error("Enter Valid Email Address", e.getMessage());
    }
    return result;
 }


}
