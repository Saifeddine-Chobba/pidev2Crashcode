package tn.esprit.pidevcrashcode.Services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component

public class QrCodeGenerator {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    public static byte[] generateQrCodeImage(String reservationId) throws WriterException, IOException {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
        String filePath = "C:/Users/Hannachi/Desktop/Git/pidev2Crashcode/src/main/resources/static/";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(reservationId, BarcodeFormat.QR_CODE,WIDTH,HEIGHT,hints);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",outputStream);

        byte[] bytes = outputStream.toByteArray();
      //  BufferedImage qrCodeImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
     //   qrCodeImage.createGraphics();


        return bytes;
    }
}
