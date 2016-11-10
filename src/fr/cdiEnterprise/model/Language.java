package fr.cdiEnterprise.model;

/**
 * Class for language creation for company and user
 * 
 * @author Anaïs
 * @version 23-10-2016
 *
 */

public class Language {

	private static int languageId; // Auto-generated language's id
	private int id;
	private String languageName;
	public static final String[] LANGUAGES = { "JAVA", "PhP", "CSS", "C#", "C++", "Ruby" };

	/**
	 * Default constructor
	 */
	public Language(String languageName) {
		languageId++;
		this.id = languageId;
		this.languageName = languageName;
	}

	public Language(String languageSelect, int number) {
		this.id = number;
		this.languageName = languageSelect;
	}

	public String getLanguageName() {
		return languageName;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return languageName;

	}

}
