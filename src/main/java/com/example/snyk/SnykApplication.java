// ===========Original Code ======================
// package com.example.snyk;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class SnykApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(SnykApplication.class, args);
// 	}

// }
// ======================================
package com.example.snyk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class SnykApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnykApplication.class, args);
	}
}

@RestController
class FileController {

    @GetMapping("/readFile")
    public String readFile(@RequestParam String fileName) {
        // Vulnerability: File path is directly taken from user input
        File file = new File("/var/data/" + fileName);
        try {
            // Reads the file contents and returns them
            return new String(java.nio.file.Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            return "File not found or error reading file";
        }
    }
}
