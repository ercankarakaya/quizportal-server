package com.ercan.configurations;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    public AmazonS3 s3(){
        AWSCredentials awsCredentials = new BasicAWSCredentials("accesskey","secretkey");
        return AmazonS3ClientBuilder
                .standard()
                .withRegion(Regions.US_EAST_2) //US_EAST_1
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
