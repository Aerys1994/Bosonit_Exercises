package com.bosonit.Filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String parameters = request.getParameterMap().entrySet().stream()
                .map(entry -> entry.getKey() + " " + String.join(", ", entry.getValue()))
                .collect(Collectors.joining(", "));

        request.setAttribute("parameters", parameters);

        filterChain.doFilter(request, response);
    }
}

