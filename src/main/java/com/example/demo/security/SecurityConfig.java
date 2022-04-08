//package com.example.demo.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.provisioning.UserDetailsManager;
//
//import java.nio.file.AccessDeniedException;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public UserDetailsRepository userDetailsRepository() {
//        UserDetailsRepository userDetailsRepository = new UserDetailsRepository();
//        // 为了让我们的登录能够运行 这里我们初始化一个用户Felordcn 密码采用明文 当你在密码12345上使用了前缀{noop} 意味着你的密码不使用加密，authorities 一定不能为空 这代表用户的角色权限集合
//        UserDetails userDetails = User.withUsername("picong").password("{noop}123456").authorities(AuthorityUtils.NO_AUTHORITIES).build();
//        userDetailsRepository.createUser(userDetails);
//        return userDetailsRepository;
//    }
//
//    @Bean
//    public UserDetailsManager userDetailsManager(UserDetailsRepository userDetailsRepository) {
//        return new UserDetailsManager() {
//            @Override
//            public void createUser(UserDetails userDetails) {
//                userDetailsRepository.createUser(userDetails);
//            }
//
//            @Override
//            public void updateUser(UserDetails userDetails) {
//                userDetailsRepository.updateUser(userDetails);
//            }
//
//            @Override
//            public void deleteUser(String s) {
//                userDetailsRepository.deleteUser(s);
//            }
//
//            @Override
//            public void changePassword(String s, String s1) {
//                try {
//                    userDetailsRepository.changePassword(s, s1);
//                } catch (AccessDeniedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public boolean userExists(String s) {
//                return userDetailsRepository.userExists(s);
//            }
//
//            @Override
//            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//                return userDetailsRepository.loadUserByUsername(s);
//            }
//        };
//    }
//}
