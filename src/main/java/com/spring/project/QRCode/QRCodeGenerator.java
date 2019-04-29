package com.spring.project.QRCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public  class QRCodeGenerator{

    private static final String QRCODE_PATH = System.getProperty("user.dir")+"/src/main/resources/static/images/QRCodes/";

    public QRCodeGenerator()
    {
    }

    public static String writeQRCode(String qrCodeName) throws WriterException, IOException {

        String truePath = QRCODE_PATH +qrCodeName+"-QRCode.png";
        String returnPath = qrCodeName+"-QRCode.png";


        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(qrCodeName, BarcodeFormat.QR_CODE,200,200);

        Path path = FileSystems.getDefault().getPath(truePath);
        MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);

        return returnPath;
    }
}


/*

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeGenerator {

   // public static final String QRCodeDir = System.getProperty("user.dir") + "/src/main/resources/static/images/QRCodes/";


    private static void generateQRCodeImage(String QRCodeName, String qrCodePath)
            throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(QRCodeName, BarcodeFormat.QR_CODE, 200, 200);

        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", Paths.get(qrCodePath));
    }

    public void  makeQRCode(String qrCodeName, String qrPath) {

        try {
            generateQRCodeImage(qrCodeName, qrPath);
            System.out.println("Successfull !!!!");
        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
    }


    /*
    public static String makeQRCode(String QRCodeName, String profileUrl) throws IOException, WriterException {
        String qrCodeData = profileUrl;
        String filePath = QRCodeDir + QRCodeName+".png";
        String charset = "UTF-8"; // or "ISO-8859-1"

        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);

        return QRCodeName+".png";
    }

    public static void createQRCode(String qrCodeData, String filePath,
                                    String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
            throws WriterException, IOException {

        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
    }
    ////
    public static void main(String[] args) throws WriterException, IOException,
            NotFoundException {
        String qrCodeData = "Hello World!";
        String filePath = "QRCode.png";

        Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        createQRCode(qrCodeData, filePath, charset, hintMap, 200, 200);
        System.out.println("QR Code image created successfully!");

        System.out.println("Data read from QR Code: "
                + readQRCode(filePath, charset, hintMap));

    }

    public static void createQRCode(String qrCodeData, String filePath,
                                    String charset, Map hintMap, int qrCodeheight, int qrCodewidth)
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
                new String(qrCodeData.getBytes(charset), charset),
                BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight, hintMap);
        MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                .lastIndexOf('.') + 1), new File(filePath));
    }

    public static String readQRCode(String filePath, String charset, Map hintMap)
            throws FileNotFoundException, IOException, NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(
                new BufferedImageLuminanceSource(
                        ImageIO.read(new FileInputStream(filePath)))));
        Result qrCodeResult = new MultiFormatReader().decode(binaryBitmap,
                hintMap);
        return qrCodeResult.getText();
    }
*/



