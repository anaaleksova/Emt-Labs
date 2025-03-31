package com.example.demo.web;


import com.example.demo.dto.CreateUserDto;
import com.example.demo.dto.DisplayUserDto;
import com.example.demo.dto.LoginUserDto;
import com.example.demo.model.exceptions.InvalidArgumentsException;
import com.example.demo.model.exceptions.InvalidUserCredentialsException;
import com.example.demo.model.exceptions.PasswordsDoNotMatchException;
import com.example.demo.service.application.UserApplicationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDto> login(HttpServletRequest request) {
        try {
            DisplayUserDto displayUserDto = userApplicationService.login(
                    new LoginUserDto(request.getParameter("username"), request.getParameter("password"))
            ).orElseThrow(InvalidUserCredentialsException::new);

            request.getSession().setAttribute("user", displayUserDto.toUser());
            return ResponseEntity.ok(displayUserDto);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/current")
    public ResponseEntity<DisplayUserDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.notFound().build();
        }

        return userApplicationService.findByUsername(authentication.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
