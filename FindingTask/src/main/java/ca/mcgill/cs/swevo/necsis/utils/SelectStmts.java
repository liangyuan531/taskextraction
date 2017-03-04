package ca.mcgill.cs.swevo.necsis.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectStmts
{

	private static Connection aConnection;
//	private Statement aStatement;

	public static String init(String pDBName) throws ClassNotFoundException, SQLException
	{
		aConnection = SQLUtils.getConnection(pDBName);

		return pDBName;

	}

	public static ResultSet selectSentences() throws  SQLException
	{
		String lSelectSentence = "SELECT * FROM necsis.sentences WHERE necsis.sentences.lemma IS NULL  ";
		PreparedStatement lPreparedStatement;
		lPreparedStatement = aConnection.prepareStatement(lSelectSentence);
		ResultSet lResultSet = lPreparedStatement.executeQuery();
		return lResultSet;
	}
}
