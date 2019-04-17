package com.infy.visitor.management.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/image")
public interface ImageController {
	ResponseEntity<?> getQr(String qrCode) throws Exception;

	ResponseEntity<byte[]> getQrImage(HttpServletRequest request, String imageName) throws IOException;
}
