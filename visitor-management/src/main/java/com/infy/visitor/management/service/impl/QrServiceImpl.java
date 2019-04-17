package com.infy.visitor.management.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.infy.visitor.management.domin.CreateAccountRequest;
import com.infy.visitor.management.service.QrService;
import com.infy.visitor.management.utils.ImageUtils;

@Service
public class QrServiceImpl implements QrService {
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private ImageUtils imageUtils;

	public String writeQR(CreateAccountRequest request) throws WriterException, IOException {
		String qcodePath = "src/main/resources/static/qr/" + request.getQrId();
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(request.getContent(), BarcodeFormat.QR_CODE, 350, 350);
		Path path = FileSystems.getDefault().getPath(qcodePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		return request.getQrId();
	}

	public String readQR(String qrId) throws Exception {
		final Resource fileResource = resourceLoader.getResource("classpath:static/qr/" + qrId);
		File QRfile = fileResource.getFile();
		BufferedImage bufferedImg = ImageIO.read(QRfile);
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImg);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = new MultiFormatReader().decode(bitmap);
		return result.getText();

	}

	public String htmlQR(String qrId) throws UnsupportedEncodingException, IOException {

		return "<img src=data:image/png;base64," + qrEncodeed(qrId) + ">";

	}

	public String qrEncodeed(String qrId) throws UnsupportedEncodingException, IOException {

		return new String(org.apache.tomcat.util.codec.binary.Base64.encodeBase64(imageUtils.getImage(qrId)),
				"UTF-8");

	}
}