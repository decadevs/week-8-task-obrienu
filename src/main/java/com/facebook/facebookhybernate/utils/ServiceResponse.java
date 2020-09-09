package com.facebook.facebookhybernate.utils;


/**
 * Class serves as a return class for DAO method operations
 * the status field tells if the operation was successful or not
 * the code field is the status code of the operation
 * the data serves as the returned object for read operations
 * @author decagon
 *
 */
public class ServiceResponse {
    private boolean status;
    private String message;
    private int code;
    private Object data;

    public ServiceResponse(boolean status, String message, int code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public ServiceResponse(boolean status, String message, int code, Object data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }



    public String getMessage() {
        return message;
    }



    public int getCode() {
        return code;
    }



    public Object getData() {
        return data;
    }

    public static class ServiceResponseBuilder{
        private boolean status;
        private String message;
        private int code;
        private Object data;

        public ServiceResponseBuilder setStatus(boolean status) {
            this.status = status;
            return this;
        }

        public ServiceResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ServiceResponseBuilder setCode(int code) {
            this.code = code;
            return this;
        }

        public ServiceResponseBuilder setData(Object data) {
            this.data = data;
            return this;
        }

        public ServiceResponseBuilder serviceResponse(ServiceResponse serviceResponse) {
            this.data = serviceResponse.getData();
            this.code = serviceResponse.getCode();
            this.message = serviceResponse.getMessage();
            this.status = serviceResponse.isStatus();
            return this;
        }

        public ServiceResponse build() {
            this.data = data;
            return new ServiceResponse( status, message, code, data);
        }
    }
}



