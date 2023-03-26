package com.ercan.enums;

public enum BucketNameEnum {

    PROFILE_IMAGE("quizportal-image-upload");

    private final String bucketName;

    BucketNameEnum(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
