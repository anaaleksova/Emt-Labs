//package com.example.demo.dto;
//
//import com.example.demo.model.domain.User;
//import com.example.demo.model.enumerations.Role;
//
//public record CreateUserDto(
//        String username,
//        String password,
//        String repeatPassword,
//        String name,
//        String surname,
//        Role role
//) {
//
//    /*
//        todo: add repeat password logic
//     */
//    public User toUser() {
//        return new User(username, password, name, surname, role);
//    }
//}
//
