package ca.mcgill.cs.swevo.necsis.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This is a wrapper for the config.properties file.
 * 
 * @author ctreude
 * 
 */
public final class Configuration
{

	private static Configuration instance = null;

	private String aDatabaseName;
	private String aDatabaseUser;
	private String aDatabasePassword;
	private String aStackOverflowAPIKey;
	private int aStackOverflowThreads;
	private String[] aExceptionalContains;
	private String[] aExceptionalEndings;
	private String[] aExceptionalEquals;
	private String[] aExceptionalMatches;
	private String[] aExceptionalStarts;
	private String[] aCodeTags;
	private String[] aLinebreakTags;
	private String[] aEmphasisTags;
	private String aLinkTag;
	private List<SimpleEntry<String, Integer>> aRegexes;
	private int aCrawlerPause;
	private int aCrawlerYear;

	private Configuration()
	{
		Properties lProperties = new Properties();
		File lFile = new File("config.properties");
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
		setDatabaseName(lProperties.getProperty("DATABASE_NAME"));
		setDatabaseUser(lProperties.getProperty("DATABASE_USER"));
		setDatabasePassword(lProperties.getProperty("DATABASE_PASSWORD"));
	}

	private void setDatabasePassword(String pDatabasePassword)
	{
		aDatabasePassword = pDatabasePassword;
	}

	private void setDatabaseUser(String pDatabaseUser)
	{
		aDatabaseUser = pDatabaseUser;
	}

	/**
	 * @return singleton
	 */
	public static Configuration getInstance()
	{
		if (instance == null)
		{
			instance = new Configuration();
		}
		return instance;
	}

	/**
	 * @return database name
	 */
	public String getDatabaseName()
	{
		return aDatabaseName;
	}

	/**
	 * @param pDatabaseName
	 *            database name
	 */
	public void setDatabaseName(String pDatabaseName)
	{
		this.aDatabaseName = pDatabaseName;
	}

	/**
	 * @return Stack Overflow API key
	 */
	public String getStackOverflowAPIKey()
	{
		return aStackOverflowAPIKey;
	}

	/**
	 * @param pStackOverflowAPIKey
	 *            Stack Overflow API key
	 */
	public void setStackOverflowAPIKey(String pStackOverflowAPIKey)
	{
		this.aStackOverflowAPIKey = pStackOverflowAPIKey;
	}

	/**
	 * @return Stack Overflow threads
	 */
	public int getStackOverflowThreads()
	{
		return aStackOverflowThreads;
	}

	/**
	 * @param pStackOverflowThreads
	 *            Stack Overflow threads
	 */
	public void setStackOverflowThreads(String pStackOverflowThreads)
	{
		this.aStackOverflowThreads = Integer.parseInt(pStackOverflowThreads);
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
	 * @param pCodeTags
	 *            code tags
	 */
	public void setCodeTags(String[] pCodeTags)
	{
		aCodeTags = pCodeTags;
	}

	/**
	 * @return code tags
	 */
	public String[] getCodeTags()
	{
		return aCodeTags;
	}

	/**
	 * @param pLinebreakTags
	 *            line break tags
	 */
	public void setLinebreakTags(String[] pLinebreakTags)
	{
		aLinebreakTags = pLinebreakTags;
	}

	/**
	 * @return line break tags
	 */
	public String[] getLinebreakTags()
	{
		return aLinebreakTags;
	}

	/**
	 * @param pEmphasisTags
	 *            emphasis tags
	 */
	public void setEmphasisTags(String[] pEmphasisTags)
	{
		aEmphasisTags = pEmphasisTags;
	}

	/**
	 * @return emphasis tag
	 */
	public String[] getEmphasisTags()
	{
		return aEmphasisTags;
	}

	/**
	 * @param pLinkTag
	 *            link tag
	 */
	public void setLinkTag(String pLinkTag)
	{
		aLinkTag = pLinkTag;
	}

	/**
	 * @return link tag
	 */
	public String getLinkTag()
	{
		return aLinkTag;
	}

	/**
	 * @param pRegexes
	 *            regexes
	 */
	public void setRegexes(String[] pRegexes)
	{
		aRegexes = new ArrayList<SimpleEntry<String, Integer>>();
		for (String lRegex : pRegexes)
		{
			aRegexes.add(new SimpleEntry<String, Integer>(lRegex.substring(2), new Integer(lRegex.substring(0, 1))));
		}
	}

	/**
	 * This method creates all regexes if they haven't been created yet.
	 * 
	 * @return list of regex entries (regex + group number)
	 */
	public List<SimpleEntry<String, Integer>> getRegexes()
	{
		return aRegexes;
	}

	/**
	 * gets the database username.
	 * 
	 * @return the database username
	 */
	public String getDatabaseUser()
	{
		return aDatabaseUser;
	}

	/**
	 * Gets the database password.
	 * 
	 * @return the database password
	 */
	public String getDatabasePassword()
	{
		return aDatabasePassword;
	}

	/**
	 * Gets pause between each request.
	 * 
	 * @return pause between each request
	 */
	public int getCrawlerPause()
	{
		return aCrawlerPause;
	}

	/**
	 * Sets pause between each request.
	 * 
	 * @param pCrawlerPause
	 *            pause between each request
	 */
	public void setCrawlerPause(String pCrawlerPause)
	{
		this.aCrawlerPause = Integer.parseInt(pCrawlerPause);
	}

	/**
	 * Gets first year for crawler.
	 * 
	 * @return first year for crawler
	 */
	public int getCrawlerYear()
	{
		return aCrawlerYear;
	}

	/**
	 * Sets first year for crawler.
	 * 
	 * @param pCrawlerYear
	 *            first year for crawler
	 */
	public void setCrawlerYear(String pCrawlerYear)
	{
		aCrawlerYear = Integer.parseInt(pCrawlerYear);
	}

}
