package com.example.pandemic;

import android.content.Context;

public class TestCenter {

    private String testCenterName;

    public TestCenter() {
    }

    public TestCenter(String testCenterName) {
        this.testCenterName = testCenterName;
    }

    public String getTestCenterName() {
        return testCenterName;
    }

    public void setTestCenterName(String testCenterName) {
        this.testCenterName = testCenterName;
    }


    public void registerTestCenter(Context context, String testCenterName){

    }
}
