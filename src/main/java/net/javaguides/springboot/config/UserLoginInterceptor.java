package net.javaguides.springboot.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    private static final Map<String, Pair<String, Byte>> authenticatedUsers = new ConcurrentHashMap<>();
    private static final ThreadLocal<Pair<String,Byte>> currentUser = ThreadLocal.withInitial(() -> null);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<Cookie> userCookie = Arrays.stream(request.getCookies() != null ? request.getCookies() : new Cookie[0])
                .filter(cookie -> "userHash".equals(cookie.getName()))
                .findAny();

        if (userCookie.isEmpty() || authenticatedUsers.get(userCookie.get().getValue()) == null) {
            response.sendRedirect("/login");
            return false;
        }
        Pair<String, Byte> authUser = authenticatedUsers.get(userCookie.get().getValue());
        currentUser.set(authUser);
        return true;
    }

    public static String addAuthenticatedUser(int userId, byte isAdmin) {
        String id = String.valueOf(userId);
        String userHash = generateUserHash(id);
        authenticatedUsers.put(userHash, Pair.of(id, isAdmin));
        return userHash;
    }

    private static String generateUserHash(String userId) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        String saltedUser = userId + Base64.getEncoder().encodeToString(salt);

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] encodedHash = digest.digest(saltedUser.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
    }

}
