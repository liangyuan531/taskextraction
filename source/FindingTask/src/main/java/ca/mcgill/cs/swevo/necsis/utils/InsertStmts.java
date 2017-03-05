package ca.mcgill.cs.swevo.necsis.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ctreude
 * 
 */
public final class InsertStmts
{
	private static Connection aConnection;
	private static Statement aStatement;
	private static PreparedStatement aDocumentationUnits;
	private static PreparedStatement aApiTypes;
	private static PreparedStatement aSentences;
	private static PreparedStatement aSentencesMap;
	private static PreparedStatement aTokens;
	private static PreparedStatement aTokensMap;
	private static PreparedStatement aApiMembers;
	private static PreparedStatement aApiMembersHeaders;
	private static PreparedStatement aApiMembersNames;
	private static PreparedStatement aQuestions;
	private static PreparedStatement aAnswers;
	private static PreparedStatement aUsers;
	private static PreparedStatement aQuestionsMap;
	private static PreparedStatement aComments;
	private static PreparedStatement aBugs;
	private static PreparedStatement aBugsMap;
	private static PreparedStatement aFourmMessages;
	private static PreparedStatement aFourmMessagesMap;
	private static PreparedStatement aTasksMap;
	private static PreparedStatement aTasks;

	private InsertStmts()
	{

	}

	/**
	 * @param pDBName
	 *            database name
	 * @param pDeleteAll
	 *            wipes database and creates new tables if true
	 * @throws SQLException
	 *             if SQL goes wrong
	 * @throws ClassNotFoundException
	 *             if DB driver isn't found
	 */
	public static void init(String pDBName, boolean pDeleteAll) throws SQLException, ClassNotFoundException
	{
		aConnection = SQLUtils.getConnection(pDBName);
		aStatement = aConnection.createStatement();

		if (pDeleteAll)
		{
			SQLUtils.dropTables(aStatement, "QUESTIONS_MAP", "BUGS_MAP", "FORUM_MESSAGES_MAP", "TASKS_MAP", "TASKS",
					"TOKENS_MAP", "SENTENCE_MAP", "TOKENS", "SENTENCES_MAP", "SENTENCES", "API_MEMBERS_NAMES",
					"API_MEMBERS_HEADERS", "API_MEMBERS", "FORUM_MESSAGES", "COMMENTS", "BUGS", "ANSWERS", "QUESTIONS",
					"USERS", "API_TYPES", "DOCUMENTATION_UNITS");
		}

		aDocumentationUnits = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "DOCUMENTATION_UNITS",
				new String[][] { { "type", SQLUtils.SHORT_TEXT } });
		aApiTypes = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "API_TYPES", new String[][] {
				{ "fqname", SQLUtils.SHORT_TEXT }, { "name", SQLUtils.SHORT_TEXT },
				{ "qualifiers", SQLUtils.SHORT_TEXT }, { "link", SQLUtils.SHORT_TEXT } }, "description",
				"DOCUMENTATION_UNITS");
		aSentences = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "SENTENCES", new String[][] { {
				"sentence", SQLUtils.LONG_TEXT } });
		aSentencesMap = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "SENTENCES_MAP", "unit",
				"DOCUMENTATION_UNITS", "sentence", "SENTENCES");
		aTokens = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "TOKENS", new String[][] {
				{ "text", SQLUtils.LONG_TEXT }, { "pos", SQLUtils.SHORT_TEXT }, { "lemma", SQLUtils.SHORT_TEXT } });
		aTokensMap = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "TOKENS_MAP", new String[][] {
				{ "position", SQLUtils.INT }, { "tag", SQLUtils.SHORT_TEXT }, { "value", SQLUtils.SHORT_TEXT } },
				"sentence", "SENTENCES", "token", "TOKENS");
		aTasks = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "TASKS", new String[][] { { "task",
				SQLUtils.LONG_TEXT } });
		aTasksMap = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "TASKS_MAP", "task", "TASKS", "sentence",
				"SENTENCES");
		aApiMembers = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "API_MEMBERS", new String[][] { {
				"qualifiers", SQLUtils.SHORT_TEXT } }, "description", "DOCUMENTATION_UNITS", "type", "API_TYPES");
		aApiMembersHeaders = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "API_MEMBERS_HEADERS",
				new String[][] { { "header", SQLUtils.SHORT_TEXT } }, "description", "DOCUMENTATION_UNITS", "member",
				"API_MEMBERS");
		aApiMembersNames = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "API_MEMBERS_NAMES",
				new String[][] { { "name", SQLUtils.SHORT_TEXT } }, "description", "DOCUMENTATION_UNITS", "header",
				"API_MEMBERS_HEADERS");
		aUsers = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "USERS", new String[][] {
				{ "userid", SQLUtils.SHORT_TEXT }, { "name", SQLUtils.SHORT_TEXT }, { "reputation", SQLUtils.INT },
				{ "type", SQLUtils.SHORT_TEXT }, { "link", SQLUtils.SHORT_TEXT }, { "acceptrate", SQLUtils.INT } });
		aQuestions = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "QUESTIONS", new String[][] {
				{ "questionid", SQLUtils.INT }, { "creation", SQLUtils.TIMESTAMP },
				{ "lastactivity", SQLUtils.TIMESTAMP }, { "score", SQLUtils.INT }, { "link", SQLUtils.SHORT_TEXT },
				{ "favorites", SQLUtils.INT }, { "views", SQLUtils.INT } }, "title", "DOCUMENTATION_UNITS", "body",
				"DOCUMENTATION_UNITS", "user", "USERS");
		aAnswers = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "ANSWERS", new String[][] {
				{ "answerid", SQLUtils.INT }, { "creation", SQLUtils.TIMESTAMP },
				{ "lastactivity", SQLUtils.TIMESTAMP }, { "score", SQLUtils.INT }, { "link", SQLUtils.SHORT_TEXT },
				{ "accepted", SQLUtils.BOOLEAN } }, "body", "DOCUMENTATION_UNITS", "question", "QUESTIONS", "user",
				"USERS");
		aQuestionsMap = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "QUESTIONS_MAP", "type", "API_TYPES",
				"question", "QUESTIONS");
		aBugs = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "BUGS", new String[][] {
				{ "bugid", SQLUtils.INT }, { "creation", SQLUtils.TIMESTAMP }, { "lastactivity", SQLUtils.TIMESTAMP },
				{ "link", SQLUtils.SHORT_TEXT } }, "title", "DOCUMENTATION_UNITS", "user", "USERS");
		aComments = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "COMMENTS", new String[][] {
				{ "commentid", SQLUtils.INT }, { "creation", SQLUtils.TIMESTAMP } }, "text", "DOCUMENTATION_UNITS",
				"bug", "BUGS", "user", "USERS");
		aBugsMap = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "BUGS_MAP", "type", "API_TYPES", "bug",
				"BUGS");
		aFourmMessages = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "FORUM_MESSAGES", new String[][] {
				{ "messageid", SQLUtils.INT }, { "threadid", SQLUtils.INT }, { "creation", SQLUtils.TIMESTAMP },
				{ "lastactivity", SQLUtils.TIMESTAMP }, { "link", SQLUtils.SHORT_TEXT } }, "description",
				"DOCUMENTATION_UNITS", "title", "DOCUMENTATION_UNITS", "user", "USERS");
		aFourmMessagesMap = SQLUtils.createTable(aConnection, aStatement, pDeleteAll, "FORUM_MESSAGES_MAP", "type",
				"API_TYPES", "thread", "FORUM_MESSAGES");

	}

	/**
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static void close() throws SQLException
	{
		aStatement.close();
		aConnection.close();

	}

	/**
	 * @return PreparedStatement for documentation units
	 */
	public static PreparedStatement documentationUnits()
	{
		return aDocumentationUnits;
	}

	/**
	 * @return PreparedStatement for api types
	 */
	public static PreparedStatement apiTypes()
	{
		return aApiTypes;
	}

	/**
	 * @return PreparedStatement for sentences
	 */
	public static PreparedStatement sentences()
	{
		return aSentences;
	}

	/**
	 * @return PreparedStatement for sentences map
	 */
	public static PreparedStatement sentencesMap()
	{
		return aSentencesMap;
	}

	/**
	 * @return PreparedStatement for tokens
	 */
	public static PreparedStatement tokens()
	{
		return aTokens;
	}

	/**
	 * @return PreparedStatement for tokens map
	 */
	public static PreparedStatement tokensMap()
	{
		return aTokensMap;
	}

	/**
	 * @return PreparedStatement for api members
	 */
	public static PreparedStatement apiMembers()
	{
		return aApiMembers;
	}

	/**
	 * @return PreparedStatement for api members headers
	 */
	public static PreparedStatement apiMembersHeaders()
	{
		return aApiMembersHeaders;
	}

	/**
	 * @return PreparedStatement for api members names
	 */
	public static PreparedStatement apiMembersNames()
	{
		return aApiMembersNames;
	}

	/**
	 * @return PreparedStatement for questions
	 */
	public static PreparedStatement questions()
	{
		return aQuestions;
	}

	/**
	 * @return PreparedStatement for answers
	 */
	public static PreparedStatement answers()
	{
		return aAnswers;
	}

	/**
	 * @return PreparedStatement for users
	 */
	public static PreparedStatement users()
	{
		return aUsers;
	}

	/**
	 * @return PreparedStatement for questions map
	 */
	public static PreparedStatement questionsMap()
	{
		return aQuestionsMap;
	}

	/**
	 * @return PreparedStatement for bugs map
	 */
	public static PreparedStatement bugsMap()
	{
		return aBugsMap;
	}

	/**
	 * @return PreparedStatement for bugs
	 */
	public static PreparedStatement bugs()
	{
		return aBugs;
	}

	/**
	 * @return PreparedStatement for comments
	 */
	public static PreparedStatement comments()
	{
		return aComments;
	}

	/**
	 * @return PreparedStatement for forum messages map
	 */
	public static PreparedStatement forumMessagesMap()
	{
		return aFourmMessagesMap;
	}

	/**
	 * @return PreparedStatement for forum messages
	 */
	public static PreparedStatement forumMessage()
	{
		return aFourmMessages;
	}

	/**
	 * @return PreparedStatement for task map
	 */
	public static PreparedStatement taskMap()
	{
		return aTasksMap;
	}

	/**
	 * @return PreparedStatement for tasks
	 */
	public static PreparedStatement tasks()
	{
		return aTasks;
	}
}
