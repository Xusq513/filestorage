package com.refutrue.filestorage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.refutrue.filestorage.mapper")
public class FilestorageApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilestorageApplication.class, args);
	}
}
