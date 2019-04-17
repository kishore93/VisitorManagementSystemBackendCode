package com.infy.visitor.management.service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.google.zxing.WriterException;
import com.infy.visitor.management.domin.CreateAccountRequest;

public interface QrService {
	String writeQR(CreateAccountRequest request) throws WriterException, IOException;
	String readQR(String qrImage) throws Exception;
	String htmlQR(String qrId ) throws UnsupportedEncodingException, IOException;
	String qrEncodeed(String qrId ) throws UnsupportedEncodingException, IOException;
}
