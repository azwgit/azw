package com.example.bq.edmp.bean;

/**
 * Created by tl on 2018/7/16.
 */

public class GetSessionResult {
    private Data data;
    private Result result;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Data {

        private String JSESSIONID;

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }
    }
    public class Result {

        private String code;
        private String message;

        public Result(String code, String message) {
            this.code = code;
            this.message = message;
        }
        public Result() {
            this.code = "";
            this.message = "";
        }

        public void setCode(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }

    }
}
