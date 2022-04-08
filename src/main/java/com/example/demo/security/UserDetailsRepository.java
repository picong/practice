//package com.example.demo.security;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.nio.file.AccessDeniedException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 代理 {@link org.springframework.security.provisioning.UserDetailsManager} 所有功能
// */
//public class UserDetailsRepository {
//
//    private Map<String, UserDetails> users = new HashMap<>();
//
//
//    public void createUser(UserDetails user) {
//        users.put(user.getUsername(), user);
//    }
//
//    public void updateUser(UserDetails user) {
//        users.put(user.getUsername(), user);
//    }
//
//    public void deleteUser(String userName) {
//        users.remove(userName);
//    }
//
//    public void changePassword(String oldPassword, String newPassword) throws AccessDeniedException {
//        Authentication currentUser = SecurityContextHolder.getContext()
//                .getAuthentication();
//
//        if (currentUser == null) {
//            // This would indicate bad coding somewhere
//            throw new AccessDeniedException(
//                    "Can't change password as no Authentication object found in context "
//                            + "for current user.");
//        }
//
//        String username = currentUser.getName();
//
//        UserDetails user = users.get(username);
//
//
//        if (user == null) {
//            throw new IllegalStateException("Current user doesn't exist in database.");
//        }
//
//    }
//
//    public boolean userExists(String username) {
//
//        return users.containsKey(username);
//    }
//
//
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return users.get(username);
//    }
//
//    public static void main(String[] args) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encode = encoder.encode("123456");
//        System.out.println(encode);
//    }
//
//}
