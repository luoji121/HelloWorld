package mail.test;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendEmail2
{
   public static void main(String [] args)
   {
      // 收件人电子邮箱
      String to = "luo_ji@founder.com";
 
      // 发件人电子邮箱
      String from = "luoji121@163.com";
 
      // 指定发送邮件的主机为 smtp.qq.com
      String host = "smtp.163.com";  //QQ 邮件服务器
 
      // 获取系统属性
      Properties properties = System.getProperties();
 
      // 设置邮件服务器
      properties.setProperty("mail.smtp.host", host);
      properties.setProperty("mail.smtp.port", "25");
 
      properties.put("mail.smtp.auth", "true");//这个指的是需要进行认证，前面的参数不需要更改,默認是False
      // 获取默认session对象
      Session session = Session.getDefaultInstance(properties,new Authenticator(){
        public PasswordAuthentication getPasswordAuthentication()
        {
         return new PasswordAuthentication("luoji121@163.com", "Lxxxxxxxx"); //发件人邮件用户名、密码
        }
       });
 
      try{
         // 创建默认的 MimeMessage 对象
         MimeMessage message = new MimeMessage(session);
 
         // Set From: 头部头字段
         message.setFrom(new InternetAddress(from));
 
         // Set To: 头部头字段
         message.addRecipient(Message.RecipientType.TO,
                                  new InternetAddress(to));
 
         // Set Subject: 头部头字段
         message.setSubject("This is the Subject Line!");
 
         // 设置消息体
         message.setText("This is actual message");
         message.saveChanges();
         // 发送消息
         Transport.send(message);
         System.out.println("Sent message successfully....from"+host);
      }catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}