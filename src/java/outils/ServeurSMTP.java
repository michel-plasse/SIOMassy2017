package outils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServeurSMTP {

  /** Crée un nouveau email (objet de type MimeMessage) à envoyer via
   * le serveur SMTP de google.<br/>
   * Exemple d'utilisation :<br/>
   * <pre> String from = "inscription@gmail.com";
   * String to = "titi@gros-minet.fr";
   * String subject = "Oh, z'ai cru voir un rros minet";
   * String body = "&lt;html>... (du HTML bien formé) &lt;/html>";
   * MimeMessage email = ServeurSMTP.newEmail(from, to, subject);
   * email.setContent(body, "text/html; charset=utf-8");
   * javax.mail.Transport.send(email);</pre> Il est possible bien
   * entendu de positionner des Cc (carbon copy) et Bcc (blank carbon
   * copy) et d'envoyer des pièces jointes (voir la javadoc de
   * MimeMesage)
   *
   * @param from adresse complete de l'expediteur. Ex :
   * @param to
   * @param subject
   * @return
   * @throws javax.mail.MessagingException
   */
  public static MimeMessage newEmail(String from, String to,
      String subject) throws MessagingException {
    MimeMessage result;
    Properties props = new Properties();

    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    final String username = "btssiomassy@gmail.com";
    final String password = "siomassy";
    javax.mail.Session sessionMail = javax.mail.Session.getInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
          }
        });
    result = new MimeMessage(sessionMail);
    result.setFrom(new InternetAddress(from));
    result.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    result.setSubject(subject);
    return result;
  }

}
