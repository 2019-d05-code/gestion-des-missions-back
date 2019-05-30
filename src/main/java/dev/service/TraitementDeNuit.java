package dev.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.domain.Mission;
import dev.domain.Statut;
import dev.repository.FraisRepo;
import dev.repository.MissionRepo;

@Service
public class TraitementDeNuit
{
	@Autowired
	private MissionRepo missionRepo;

	@Autowired
	public TraitementDeNuit(MissionRepo missionRepo) {
		this.missionRepo = missionRepo;
	}
	
	// - changement de statut - 
	public void changerStatut()
	{
		List<Mission> liste = missionRepo.findAll();
		for(Mission miss:liste)
		{
			if(miss.getStatut().equals(Statut.INITIALE))
			{
				miss.setStatut(Statut.EN_ATTENTE_VALIDATION);
				//code pour envoyer un email au manager
				envoyerEmail()
			}
		}
	}
	
	public void calculPrime()
	{
		List<Mission> liste = missionRepo.findAll();
		for(Mission miss:liste)
		{
			if(miss.getStatut().equals(Statut.VALIDEE))
			{
				Days.daysBetween(date1, date2).getDays();
				//a revoir
				double prime = (nombre de jours travaillés)* TJM * %Prime/100 - déduction
				miss.setPrime(prime);
			}
		}
	}
	
	// - envoie d'un email
	public static void envoyerEmail(String subject, String text, String destinataire, String copyDest) {
	    // 1 -> Création de la session
	    Properties properties = new Properties();
	    properties.setProperty("mail.transport.protocol", "smtp");
	    properties.setProperty("mail.smtp.host", SMTP_HOST1);
	    properties.setProperty("mail.smtp.user", LOGIN_SMTP1);
	    properties.setProperty("mail.from", IMAP_ACCOUNT1);
	    Session session = Session.getInstance(properties);
	    
	 // 2 -> Création du message
	    MimeMessage message = new MimeMessage(session);
	    try {
	        message.setText(text);
	        message.setSubject(subject);
	        message.addRecipients(Message.RecipientType.TO, destinataire);
	        message.addRecipients(Message.RecipientType.CC, copyDest);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	    
	    // 3 -> Envoi du message
	    Transport transport;
	    try {
	        transport = session.getTransport("smtp");
	        transport.connect(LOGIN_SMTP1, PASSWORD_SMTP1);
	        transport.sendMessage(message, new Address[] { new InternetAddress(destinataire), new InternetAddress(copyDest) });
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (transport != null) {
	                transport.close();
	            }
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	    
	}


}
