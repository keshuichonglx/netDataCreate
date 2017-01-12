package com.mike.FtpClient;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class FtpClientUtil {
    public static String ftpTest(){

        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect("127.0.0.1",21);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SocketAddress addr = new InetSocketAddress("localhost", 21);
        int reply = ftpClient.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            log.warn("FTP server refused connection.");
            System.out.println("FTP server refused connection.");
            return "error";
        }

        boolean bok = false;
        try {
            bok = ftpClient.login("ftpstock", "123");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!bok)  {
            try {
                ftpClient.disconnect() ;
                ftpClient = null ;
            } catch (Exception e) { }
            try {
                throw new Exception("can not login ftp server") ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        ftpClient.setBufferSize(1024);
        ftpClient.setControlEncoding("GBK");
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ftpClient.setDataTimeout(120000);
        ftpClient.enterLocalPassiveMode();
        ftpClient.setUseEPSVwithIPv4(false);

        return "111";
    }
}

