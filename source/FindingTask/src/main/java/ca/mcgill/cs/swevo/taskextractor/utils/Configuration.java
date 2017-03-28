package ca.mcgill.cs.swevo.taskextractor.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * This is a wrapper for the config.properties file.
 * 
 * @author ctreude
 * 
 */
public final class Configuration
{

	private static Configuration instance = null;
	private static String pro_option;
	private static String gen_option;
	private static String customise;

	private Set<String> aDomainTerms;
	private String[] aExceptionalContains;
	private String[] aExceptionalEndings;
	private String[] aExceptionalEquals;
	private String[] aExceptionalMatches;
	private String[] aExceptionalStarts;
	private Set<String> aFirstWordVbz;
	private Set<String> aGenericAccusatives;
	private Set<String> aProgrammingActions;
	private Set<String> aPunctuationLemmas;

	private Configuration()
	{
		Properties lProperties = new Properties();
		File lFile=null;
		if(pro_option.equals("yes") && gen_option.equals("yes")){
			lFile = new File("config.properties");
			//System.out.println(lFile.getAbsolutePath());
		}
		else if(pro_option.equals("no") && gen_option.equals("no")){
			lFile=new File("configwithoutboth.properties");
		}
		else if(pro_option.equals("yes") && gen_option.equals("no")){
			lFile=new File("configwithoutgeneric.properties");
		}
		else if(pro_option.equals("no") && gen_option.equals("yes")){
			lFile=new File("configwithoutprogramming.properties");
		}
		else if(gen_option.equals("yes") && customise.equals("yes")){
			lFile=new File("customizedconfigwithgeneric.properties");
			//System.out.println(lFile.getAbsolutePath());
		}
		else if(gen_option.equals("no") && customise.equals("yes")){
			lFile=new File("customizedconfigwithoutgeneric.properties");
		}
		BufferedReader lBufferedReader;
		try
		{
			lBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(lFile), "UTF8"));
			lProperties.load(lBufferedReader);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		setDomainTerms(new HashSet<String>(Arrays.asList(lProperties.getProperty("DOMAIN_TERMS").split(","))));
		setExceptionalContains(lProperties.getProperty("EXCEPTIONAL_CONTAINS").split(","));
		setExceptionalEndings(lProperties.getProperty("EXCEPTIONAL_ENDINGS").split(","));
		setExceptionalEquals(lProperties.getProperty("EXCEPTIONAL_EQUALS").split(","));
		setExceptionalMatches(lProperties.getProperty("EXCEPTIONAL_MATCHES").split(","));
		setExceptionalStarts(lProperties.getProperty("EXCEPTIONAL_STARTS").split(","));
		setFirstWordVbz(new HashSet<String>(Arrays.asList(lProperties.getProperty("FIRST_WORD_VBZ").split(","))));
		setGenericAccusatives(new HashSet<String>(Arrays.asList(lProperties.getProperty("GENERIC_ACCUSATIVES").split(
				","))));
		setProgrammingActions(new HashSet<String>(Arrays.asList(lProperties.getProperty("PROGRAMMING_ACTIONS").split(
				","))));
		setPunctuationLemmas(new HashSet<String>(
				Arrays.asList(lProperties.getProperty("PUNCTUATION_LEMMAS").split(","))));
		getPunctuationLemmas().add(",");
	}
	/**
	 * @return singleton
	 */
	public static Configuration getInstance()
	{
		instance = new Configuration();
		return instance;
	}

	/**
	 * @return the aPunctuationLemmas
	 */
	public Set<String> getPunctuationLemmas()
	{
		return aPunctuationLemmas;
	}

	/**
	 * @param pPunctuationLemmas
	 *            the aPunctuationLemmas to set
	 */
	public void setPunctuationLemmas(Set<String> pPunctuationLemmas)
	{
		this.aPunctuationLemmas = pPunctuationLemmas;
	}

	/**
	 * @return the aProgrammingActions
	 */
	public Set<String> getProgrammingActions()
	{
		return aProgrammingActions;
	}

	/**
	 * @param pProgrammingActions
	 *            the aProgrammingActions to set
	 */
	public void setProgrammingActions(Set<String> pProgrammingActions)
	{
		this.aProgrammingActions = pProgrammingActions;
	}

	/**
	 * @return the aGenericAccusatives
	 */
	public Set<String> getGenericAccusatives()
	{
		return aGenericAccusatives;
	}

	/**
	 * @param pGenericAccusatives
	 *            the aGenericAccusatives to set
	 */
	public void setGenericAccusatives(Set<String> pGenericAccusatives)
	{
		this.aGenericAccusatives = pGenericAccusatives;
	}

	/**
	 * @return the aFirstWordVbz
	 */
	public Set<String> getFirstWordVbz()
	{
		return aFirstWordVbz;
	}

	/**
	 * @param pFirstWordVbz
	 *            the aFirstWordVbz to set
	 */
	public void setFirstWordVbz(Set<String> pFirstWordVbz)
	{
		this.aFirstWordVbz = pFirstWordVbz;
	}

	/**
	 * @return the aExceptionalStarts
	 */
	public String[] getExceptionalStarts()
	{
		return aExceptionalStarts;
	}

	/**
	 * @param pExceptionalStarts
	 *            the aExceptionalStarts to set
	 */
	public void setExceptionalStarts(String[] pExceptionalStarts)
	{
		this.aExceptionalStarts = pExceptionalStarts;
	}

	/**
	 * @return the aExceptionalMatches
	 */
	public String[] getExceptionalMatches()
	{
		return aExceptionalMatches;
	}

	/**
	 * @param pExceptionalMatches
	 *            the aExceptionalMatches to set
	 */
	public void setExceptionalMatches(String[] pExceptionalMatches)
	{
		this.aExceptionalMatches = pExceptionalMatches;
	}

	/**
	 * @return the aExceptionalEquals
	 */
	public String[] getExceptionalEquals()
	{
		return aExceptionalEquals;
	}

	/**
	 * @param pExceptionalEquals
	 *            the aExceptionalEquals to set
	 */
	public void setExceptionalEquals(String[] pExceptionalEquals)
	{
		this.aExceptionalEquals = pExceptionalEquals;
	}

	/**
	 * @return the aExceptionalEndings
	 */
	public String[] getExceptionalEndings()
	{
		return aExceptionalEndings;
	}

	/**
	 * @param pExceptionalEndings
	 *            the aExceptionalEndings to set
	 */
	public void setExceptionalEndings(String[] pExceptionalEndings)
	{
		this.aExceptionalEndings = pExceptionalEndings;
	}

	/**
	 * @return the aExceptionalContains
	 */
	public String[] getExceptionalContains()
	{
		return aExceptionalContains;
	}

	/**
	 * @param pExceptionalContains
	 *            the aExceptionalContains to set
	 */
	public void setExceptionalContains(String[] pExceptionalContains)
	{
		this.aExceptionalContains = pExceptionalContains;
	}

	/**
	 * @return the aDomainTerms
	 */
	public Set<String> getDomainTerms()
	{
		return aDomainTerms;
	}

	/**
	 * @param pDomainTerms
	 *            the aDomainTerms to set
	 */
	public void setDomainTerms(Set<String> pDomainTerms)
	{
		this.aDomainTerms = pDomainTerms;
	}
	
	public static String getPro_option() {
		return pro_option;
	}
	public static void setPro_option(String pro_option) {
		Configuration.pro_option = pro_option;
	}
	public static String getGen_option() {
		return gen_option;
	}
	public static void setGen_option(String gen_option) {
		Configuration.gen_option = gen_option;
	}
	public static String getCustomise() {
		return customise;
	}
	public static void setCustomise(String customise) {
		Configuration.customise = customise;
	}
	

}
