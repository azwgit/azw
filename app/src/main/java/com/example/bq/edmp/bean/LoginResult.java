package com.example.bq.edmp.bean;

/**
 * Created by ljh on 2017/3/27.
 */

public class LoginResult {
    private Data data;
    private Results result;

    public LoginResult(Data data, Results result) {
        this.data = data;
        this.result = result;
    }
    public LoginResult() {
        this.data = new Data();
        this.result = new Results();
    }


    public class Data {
        private String JSESSIONID;
        private String accessToken;
        private String name;
        private String position;
        private String region;
        private String regionName;
        private String userId;

        public Data(String JSESSIONID, String accessToken, String name, String position, String region, String regionName, String userId) {
            this.JSESSIONID = JSESSIONID;
            this.accessToken = accessToken;
            this.name = name;
            this.position = position;
            this.region = region;
            this.regionName = regionName;
            this.userId = userId;
        }
        public Data() {
            this.JSESSIONID = "";
            this.accessToken = "";
            this.name = "";
            this.position = "";
            this.region = "";
            this.regionName = "";
            this.userId = "";
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getJSESSIONID() {
            return JSESSIONID;
        }

        public void setJSESSIONID(String JSESSIONID) {
            this.JSESSIONID = JSESSIONID;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getRegionName() {
            return regionName;
        }

        public void setRegionName(String regionName) {
            this.regionName = regionName;
        }
    }

    public class Results {
      private String code;
      private String message;

        public Results(String code, String message) {
            this.code = code;
            this.message = message;
        }
        public Results() {
            this.code = "";
            this.message = "";
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Results getResult() {
        return result;
    }

    public void setResult(Results result) {
        this.result = result;
    }
}
