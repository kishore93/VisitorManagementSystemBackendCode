package com.infy.visitor.management.controller.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.infy.visitor.management.controller.ImageController;
import com.infy.visitor.management.service.QrService;
import com.infy.visitor.management.utils.ImageUtils;

import io.swagger.annotations.Api;

@RestController
@Api(description="Qr Uitilitiy")
public class ImageControllerImpl implements ImageController {
	@Autowired
	private QrService qrService;
	@Autowired
	private ImageUtils imageUtils;

	@GetMapping("/html/{qrCode}")
	public ResponseEntity<String> getQr(@PathVariable String qrCode) throws Exception {
		return new ResponseEntity<String>(qrService.htmlQR(qrCode), HttpStatus.OK);

	}

	@Override
	@GetMapping("/bytes/{qrCode}")
	public ResponseEntity<byte[]> getQrImage(HttpServletRequest request, @PathVariable String qrCode)
			throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<>(imageUtils.getImage(qrCode), headers, HttpStatus.OK);

	}

}
