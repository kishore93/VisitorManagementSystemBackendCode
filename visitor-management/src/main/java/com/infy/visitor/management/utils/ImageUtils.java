package com.infy.visitor.management.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
@Component
public class ImageUtils {
	@Autowired HttpServletRequest request;
	@SuppressWarnings("deprecation")
	public void generateQrCode(String content, String qrFileName) {
		String fileLocation = new File(request.getRealPath("/")).getParent() + "/qr/";

		int size = 250;
		String fileType = "png";
		File dir = new File(fileLocation + File.separator);
		if (!dir.exists())
			dir.mkdirs();

		File serverFile = new File(dir.getAbsolutePath() + File.separator + qrFileName);

		try {
			createQRImage(serverFile, content, size, fileType);
			System.out.println("generatedQrCode");
		} catch (WriterException | IOException e) {
			e.printStackTrace();
		}

	}

	public byte[] getImage(String imagepath) {
		String fileLocation = new File(request.getRealPath("/")).getParent() + "/qr/" + imagepath;
		try {
			InputStream fileInputStream = new FileInputStream(new File(fileLocation));
			return IOUtils.toByteArray(fileInputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
			throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable hintMap = new Hashtable();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}

}