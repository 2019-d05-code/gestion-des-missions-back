package dev.service;

import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import dev.domain.LigneDeFrais;
import dev.domain.Mission;
import dev.domain.Nature;
import dev.domain.Statut;
import dev.repository.MissionRepo;
import dev.repository.NatureRepository;

//@Service
@Configuration
@EnableScheduling
public class TraitementDeNuit
{
	@Autowired
	private MissionRepo missionRepo;
	@Autowired
	private NatureRepository natureRepo;

	@Autowired
	public TraitementDeNuit(MissionRepo missionRepo, NatureRepository natureRepo) {
		this.missionRepo = missionRepo;
		this.natureRepo = natureRepo;
	}
	
	// - changement de statut - 
	//@Scheduled(cron="0 0 23 * * *") //pour le deploiement
	@Scheduled(initialDelay=10000, fixedDelay=50000) //pour les tests locaux
	public void changerStatut()
	{
		List<Mission> liste = missionRepo.findAll();
		for(Mission miss:liste)
		{
			if(miss.getStatut().equals(Statut.INITIALE))
			{
				miss.setStatut(Statut.EN_ATTENTE_VALIDATION);
				missionRepo.save(miss);
				System.out.println("set statut");
				//code pour envoyer un email au manager
				//envoyerEmail()
			}
		}
	}
	
	//@Scheduled(cron="0 0 23 * * *") //pour le deploiement
	@Scheduled(initialDelay=10000, fixedDelay=50000) //pour les tests locaux
	public void calculPrime()
	{
		List<Mission> liste = missionRepo.findAll();
		for(Mission miss:liste)
		{
			if(miss.getStatut().equals(Statut.VALIDEE))
			{
				// calcul du nombre de jours travaille
				int c = 0;
				for(LocalDate d = miss.getDateDebut(); d.isBefore(miss.getDateFin()); d = d.plusDays(1) )
				{
					if (d.getDayOfWeek().equals("SATURDAY") || d.getDayOfWeek().equals("SUNDAY") ) {}
					else {c++;}
				}

				// avec nature (classe) dans la mission
				//double prime = c* miss.getNature().getTauxJournalierMoyen() * (miss.getNature().getPourcentPrime())/100 - calculerDeduction(miss, miss.getNature(), c)
				
				// avec nature(enum) dans la mission
				String nat = miss.getNature().toString();
				List<Nature> natures = natureRepo.findAll();
				for(Nature n:natures)
				{
					if(nat.equals(n.getNomNature()) )
					{
						double prime = c * n.getTauxJournalierMoyen() * (n.getPourcentPrime())/100 - calculerDeduction(miss, n, c); //deduction
						miss.setPrime(prime);
						missionRepo.save(miss);
						System.out.println("set prime");
					}
				}

			}
			
		}
	}
	
	public double calculerDeduction(Mission miss, Nature nat, int c)
	{
		if (nat.isDepassementFrais()==false)
		{return 0.0;}
		else
		{
			double somme = 0;
			for (LigneDeFrais f : miss.getNotesFrais() )
			{
				somme += f.getMontant();
			}
			
			return somme - nat.getPlafondQuotidien() * c;
		}
	}
	
	// - envoie d'un email - 
	/*
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
	    
	}*/


}
