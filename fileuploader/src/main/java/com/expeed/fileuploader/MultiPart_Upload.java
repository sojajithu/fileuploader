package com.expeed.fileuploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;	
import java.io.File; 

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;

@SpringBootApplication
public class MultiPart_Upload {
	

	public static void main(String[] args) throws Exception{
        String bucketName = "datastore.xyz.com";
		String FileNameInS3 = "SECURITY_ROLE_CLIENT_HIER_SITE_MAP_MPart.csv";
        String filename = "C:\\Users\\abc\\Downloads\\SECURITY_ROLE_CLIENT_HIER_SITE_MAP_Zip\\SECURITY_ROLE_CLIENT_HIER_SITE_MAP_Old.csv";
            
		try {
			
			BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAXTG5QVVVNKQDGRES", "jjWhFl2qDpdM9O6Fyj/VrSyC2wX2ETwnjYNpHtwU");
			
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion("us-east-1").withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
            
            TransferManager tm = TransferManagerBuilder.standard().withS3Client(s3Client).build();
			
			Upload upload = tm.upload(bucketName, FileNameInS3, new File(filename));
            System.out.println("Uploading file started");
            
            upload.waitForCompletion();
            System.out.println("Uploading file completed");

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