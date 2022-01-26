package com.expeed.fileuploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;	
import java.io.File;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;

@SpringBootApplication
public class DemoApplication {
	

	public static void main(String[] args) {
		try {
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   			LocalDateTime now = LocalDateTime.now();  
   			System.out.println(dtf.format(now));

			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAXTG5QVVVNKQDGRES", "jjWhFl2qDpdM9O6Fyj/VrSyC2wX2ETwnjYNpHtwU");
			
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-1").withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
		
			//Upload file to S3 bucket
			String bucketName = "datastore.xyz.com";
			String FileNameInS3 = "SECURITY_ROLE_CLIENT_HIER_SITE_MAP.csv";
			String filename = "C:\\Users\\abc\\Downloads\\SECURITY_ROLE_CLIENT_HIER_SITE_MAP_Old\\SECURITY_ROLE_CLIENT_HIER_SITE_MAP_Old.csv";
			
			System.out.println("--Uploading file started");
			PutObjectRequest request = new PutObjectRequest(bucketName,FileNameInS3, new File(filename));
			s3Client.putObject(request);

			System.out.println("--Uploading file done");
			System.out.println(dtf.format(now));
		} 
		catch (AmazonServiceException e) {
			e.printStackTrace();
		}
		catch (SdkClientException  e) {
			e.printStackTrace();
		}
		SpringApplication.run(DemoApplication.class, args);
	}

}
