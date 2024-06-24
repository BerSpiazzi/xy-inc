package br.com.xyinc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"br.com.xyinc"})
public class XyIncApplication {

    public static void main(String[] args) {

        SpringApplication.run(XyIncApplication.class, args);
    }

}
