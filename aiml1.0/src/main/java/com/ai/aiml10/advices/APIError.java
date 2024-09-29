package com.ai.aiml10.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;


public class APIError {

   private HttpStatus status ;
   private String error ;
   private List<String> subErrors ;

   public APIError() {
      this.subErrors = new ArrayList<>(5);
   }

   public HttpStatus getStatus() {
      return status;
   }

   public void setStatus(HttpStatus status) {
      this.status = status;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }

   public List<String> getSubErrors() {
      return subErrors;
   }

   public void setSubErrors(List<String> subErrors) {
      this.subErrors = subErrors;
   }
}
