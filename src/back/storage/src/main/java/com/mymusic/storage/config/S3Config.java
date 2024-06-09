package com.mymusic.storage.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${S3_ACCESS}")
    private String accessKey;

    @Value("${S3_SECRET}")
    private String secretKey;

    @Value("${s3.region}")
    private String region;

    @Bean
    public AmazonS3 s3Client() {
        BasicAWSCredentials credentials = new BasicAWSCredentials(
                "uf26847", "sAILOR-343"
        );

        var s3Builder = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(
                                "https://s3.timeweb.com",
                        region))
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .enablePathStyleAccess();

        return s3Builder.build();
    }
}
