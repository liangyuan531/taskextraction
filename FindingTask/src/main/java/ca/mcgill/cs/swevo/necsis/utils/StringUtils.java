package ca.mcgill.cs.swevo.necsis.utils;

import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 * @author ctreude
 * 
 */
public final class StringUtils
{

	private static Whitelist aWhiteList = null;

	private StringUtils()
	{

	}

	private static Whitelist getWhitelist()
	{
		if (aWhiteList == null)
		{
			aWhiteList = Whitelist.none();
			for (String lCodeTag : Configuration.getInstance().getCodeTags())
			{
				aWhiteList.addTags(lCodeTag.trim());
			}
			for (String lLinebreakTag : Configuration.getInstance().getLinebreakTags())
			{
				aWhiteList.addTags(lLinebreakTag.trim());
			}
			for (String lEmphasisTag : Configuration.getInstance().getEmphasisTags())
			{
				aWhiteList.addTags(lEmphasisTag.trim());
			}
			String[] lLinkTagAttribute = Configuration.getInstance().getLinkTag().split("#");
			aWhiteList.addAttributes(lLinkTagAttribute[0].trim(), lLinkTagAttribute[1].trim());
		}
		return aWhiteList;
	}

	/**
	 * @param pElement
	 *            element
	 * @return string content
	 */
	public static String trimHTML(Element pElement)
	{
		return Jsoup.clean(pElement.html(), getWhitelist()).replaceAll("\\s+", " ").trim();
	}

	/**
	 * @param pElements
	 *            elements
	 * @return string content
	 */
	public static String trimHTML(Elements pElements)
	{
		return Jsoup.clean(pElements.html(), getWhitelist()).replaceAll("\\s+", " ").trim();
	}

	/**
	 * @param pString
	 *            string
	 * @param pPrefixes
	 *            set of prefixes
	 * @return whether string starts with any of the prefixes (case insensitive)
	 */
	public static boolean startsWithAnyIgnoreCase(String pString, Set<String> pPrefixes)
	{
		for (String lPrefix : pPrefixes)
		{
			if (pString.toLowerCase().startsWith(lPrefix.toLowerCase()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param pString
	 *            string
	 * @param pContains
	 *            set of substrings
	 * @return whether string contains any of the substrings
	 */
	public static boolean stringContainsAny(String pString, String[] pContains)
	{
		for (String lContains : pContains)
		{
			if (pString.contains(lContains))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param pString
	 *            string
	 * @param pEndings
	 *            set of suffixes
	 * @return whether string ends with any of the suffixes
	 */
	public static boolean stringEndsWithAny(String pString, String[] pEndings)
	{
		for (String lEnding : pEndings)
		{
			if (pString.endsWith(lEnding))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param pString
	 *            string
	 * @param pEquals
	 *            set of strings
	 * @return whether string equals any of the other strings
	 */
	public static boolean stringEqualsAny(String pString, String[] pEquals)
	{
		for (String lEqual : pEquals)
		{
			if (pString.equals(lEqual))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param pString
	 *            string
	 * @param pMatches
	 *            set of regexes
	 * @return whether string matches any of the regexes
	 */
	public static boolean stringMatchesAny(String pString, String[] pMatches)
	{
		for (String lMatch : pMatches)
		{
			if (pString.matches(lMatch))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param pString
	 *            string
	 * @param pStarts
	 *            set of prefixes
	 * @return whether string starts with any of the prefixes
	 * @see stringStartsWithAnyIgnoreCase
	 */
	public static boolean stringStartsWithAny(String pString, String[] pStarts)
	{
		for (String lStart : pStarts)
		{
			if (pString.startsWith(lStart))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * @param pStrings
	 *            list of strings
	 * @param pTerms
	 *            list of terms
	 * @return whether list of strings contains any of the terms
	 */
	public static boolean listContainsAny(List<String> pStrings, String[] pTerms)
	{
		for (String lString : pStrings)
		{
			if (stringEqualsAny(lString, pTerms))
			{
				return true;
			}
		}
		return false;
	}
}
