package netTools;

/**
 * Created by mike on 17-1-10.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.io.Util;
import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.mail.*;

public class netTools {
    public static int sendEmail(String sender, String receiver, String subject, String content, String server, String filename) {
        try {
            Writer writer;
            SimpleSMTPHeader header;
            SMTPClient client;
            BufferedReader stdin;
            FileReader fileReader = null;
            try
            {
                fileReader = new FileReader(filename);
            }
            catch (FileNotFoundException e)
            {
                System.err.println("File not found. " + e.getMessage());
                return 1;
            }
            header = new SimpleSMTPHeader(sender, receiver, subject);
            client = new SMTPClient();
            client.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

//            client.connect(server);
            client.connect(server);

            if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                client.disconnect();

                System.err.println("SMTP server refused connection.");
                return 1;
            }
            client.login();
            client.setSender(sender);
            client.addRecipient(receiver);

            writer = client.sendMessageData();

            if (writer != null) {
                writer.write(header.toString());
                Util.copyReader(fileReader, writer);
                writer.close();
                client.completePendingCommand();
            }
            fileReader.close();

            client.logout();

            client.disconnect();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static int send163(){
        // 这个类主要是设置邮件
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("25");
        mailInfo.setValidate(true);
        mailInfo.setUserName("allmy163mail@163.com"); // 实际发送者
        mailInfo.setPassword("poiuHJKL");// 您的邮箱密码
        mailInfo.setFromAddress("allmy163mail@163.com"); // 设置发送人邮箱地址
        mailInfo.setToAddress("allmy163mail2@163.com"); // 设置接受者邮箱地址
        mailInfo.setSubject("咱们开会吧");
        mailInfo.setContent("今天下午16点，毕见不散");
        // 这个类主要来发送邮件
        SimpleMailSender sms = new SimpleMailSender();
        sms.sendTextMail(mailInfo); // 发送文体格式
//        sms.sendHtmlMail(mailInfo); // 发送html格式
        return 1;
    }
}
