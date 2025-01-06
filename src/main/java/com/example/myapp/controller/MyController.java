package com.example.myapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {

    @GetMapping("/greeting")
    @Operation(summary = "Get Greeting", description = "Returns a greeting message (e.g.: Hello, World!).")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Hello, World!");
    }

    @PostMapping("/submit")
    @Operation(summary = "Submit Data", description = "Receives data and returns a confirmation message + the data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Data successfully received."),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid data.")
    })
    public ResponseEntity<String> submitData(@RequestBody(required = false) String data) {
        if (data == null || data.isBlank()) {
            return ResponseEntity.badRequest().body("Data cannot be null or empty.");
        }
        return ResponseEntity.ok("Data received: " + data);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Gets User Id", description = "Receives an Id and returns a message with the User and his Id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User ID retrieved successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid user ID provided.")
    })
    public ResponseEntity<String> getUserById(@PathVariable("id") Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().body("Invalid user ID, it must be a positive number.");
        }
        return ResponseEntity.ok("User ID: " + id);
    }

    @GetMapping("/search")
    @Operation(summary = "Search Users", description = "Searches for users or data based on the query parameter.")
    public ResponseEntity<String> search(@RequestParam(name = "query", defaultValue = "") String query) {
        if (query.isBlank()) {
            return ResponseEntity.ok("No query provided.");
        }
        return ResponseEntity.ok("Search results for: " + query);
    }
}
