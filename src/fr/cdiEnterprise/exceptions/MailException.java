/**
 * 
 */
package fr.cdiEnterprise.exceptions;

import fr.cdiEnterprise.model.Item;

/**
 * @author Afpa
 *
 */
public class MailException extends Exception {

	/**
	 * Generated Serial
	 */
	private static final long serialVersionUID = 4230595565989332626L;

	/**
	 * Default constructor.
	 */
	public MailException(String ERREUR) {
		
		super(ERREUR);
		
	}
	
	/**
	 * Controle si la boite mail de reception n'est pas la meme que la boite mail d'envoie.
	 * 
	 * @param item
	 * @return 
	 */
	public static boolean senderNotEqualsReceiver(Item item){
		
		boolean senderNotEqualsReceiver = false;
		try{
			if(item.getSender().equalsIgnoreCase(item.getReceiver())){
				
				final String ERREUR = "Vous ne pouvez pas vous envoyez un mail a vous même!";
				throw new MailException(ERREUR);
				
			}else{
				
				senderNotEqualsReceiver = true;
				
			}	
		}catch (MailException Me){
			
			senderNotEqualsReceiver = false;
			
		}
		
		
		return senderNotEqualsReceiver;
	}
	
	/**
	 * Controle si le mail ne contient pas de champs a null
	 * @param item
	 * @return
	 */
	public static boolean notNull(Item item) {
		final String ERREUR = "Erreur un des champs est a null";
		boolean notNull = true;
		
		try{
			
			if(item.getBody() == null){
				
				throw new MailException(ERREUR);
				
			}else if(item.getObject() == null){
			
				throw new MailException(ERREUR);
			}else if(item.getReceiver() == null){
				
				throw new MailException(ERREUR);
			}
			
			
		}catch (MailException Me){
			
			notNull = false;
			
		}
		
		
		return notNull;
	}

}
