package com.icarusfrog.redirect_demo.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequestMapping("/")
@Controller
public class RedirectController {

    /**
     * Simple home page
     * @return
     */
    @GetMapping
    public ModelAndView home () {
        return  new ModelAndView("home");
    }

    /**
     * Redirect endpoint vulnerable to host header tampering
     * @param httpServletRequest
     * @param response
     * @throws IOException
     */
    @GetMapping("/redirect")
    public void redirect(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        response.sendRedirect("bob");
    }

    public static List<String> validHosts = List.of("localhost:8080", "www.icarusfrog.com");

    /**
     * Host header endpoint that is not vulnerable to host header tampering.
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("/redirecth")
    public String redirectWithHostProtection(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(validHosts.contains(request.getHeader("Host"))) {
            response.sendRedirect("bob");
        } else {
            log.error("Invalid Host Header detected: {}", request.getHeader("Host"));
            return "redirect-error";
        }
        return "redirect-error";
    }

    @GetMapping("/bob")
    @ResponseBody
    public String getBob() {
        return "bob";
    }
}
