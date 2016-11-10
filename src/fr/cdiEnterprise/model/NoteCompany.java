/**
 * 
 */
package fr.cdiEnterprise.model;

/**
 *24 oct. 2016
 * @author Ismael
 *ecf-16035-a
 *10:28:26
 */
public class NoteCompany 
{

	/**
	 * 
	 */
	
	private int 	idNote;
	private String 	lblNote;
	
	public NoteCompany(int idNote, String lblNote) 
	{
		this.idNote	 =idNote;
		this.lblNote = lblNote;
		// TODO Parameter to note's user
	}
	
	public NoteCompany(String lblNote)
	{
		this.idNote= 0;
		this.lblNote = lblNote;
	}

	
	
	//Getters
	
	/**
	 * @return the idNote
	 */
	public int getIdNote() {
		return idNote;
	}

	/**
	 * @return the lblNote
	 */
	public String getLblNote() {
		return lblNote;
	}
	
	//Setters
	
	/**
	 * @param idNote the idNote to set
	 */
	public void setIdNote(int idNote) {
		this.idNote = idNote;
	}

	/**
	 * @param lblNote the lblNote to set
	 */
	public void setLblNote(String lblNote) {
		this.lblNote = lblNote;
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NoteCompany [idNote=" + idNote + ", lblNote=" + lblNote + "]";
	}

}
