package com.runtracker.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.runtracker.models.User;

import java.io.IOException;

@Component
public class CustomSessionAuthenticationFilter extends OncePerRequestFilter {

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {
      // Ambil sesi
      Object dataSession = request.getSession().getAttribute("dataSession");

      // Validasi sesi dan set autentikasi
      if (dataSession != null && SecurityContextHolder.getContext().getAuthentication() == null) {
         User user = (User) dataSession;

         // Atur autentikasi ke Spring Security
         UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
               user, null, null); // Tambahkan roles jika diperlukan
         authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

         SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      filterChain.doFilter(request, response);
   }
}
