package ca.mcgill.cs.swevo.necsis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author ctreude
 * 
 */
public final class SQLUtils
{

	public static final String SHORT_TEXT = "VARCHAR(2500)";
	public static final String LONG_TEXT = "VARCHAR(10000)";
	public static final String BOOLEAN = "BOOLEAN";
	public static final String TIMESTAMP = "TIMESTAMP";
	public static final String INT = "INT";

	private SQLUtils()
	{

	}

	/**
	 * @param pPreparedStatement
	 *            prepared statement
	 * @param pForeignKey
	 *            foreign key
	 * @param pValues
	 *            values
	 * @return generated key
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static int insertIntoTable(PreparedStatement pPreparedStatement, int pForeignKey, Object[] pValues)
			throws SQLException
	{
		pPreparedStatement.setInt(1, pForeignKey);
		for (int lCount = 0; lCount < pValues.length; lCount++)
		{
			Object lValue = pValues[lCount];
			if (lValue instanceof String)
			{
				lValue = ((String) lValue).replace("'", "\\'");
			}
			pPreparedStatement.setObject(lCount + 2, lValue);
		}
		pPreparedStatement.executeUpdate();
		ResultSet lGeneratedKeys = pPreparedStatement.getGeneratedKeys();
		lGeneratedKeys.next();
		int lGeneratedKey = lGeneratedKeys.getInt("GENERATED_KEY");
		return lGeneratedKey;
	}

	/**
	 * @param pPreparedStatement
	 *            prepared statement
	 * @param pForeignKey1
	 *            first foreign key
	 * @param pForeignKey2
	 *            second foreign key
	 * @param pValues
	 *            values
	 * @return generated key
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static int insertIntoTable(PreparedStatement pPreparedStatement, int pForeignKey1, int pForeignKey2,
			Object[] pValues) throws SQLException
	{
		pPreparedStatement.setInt(1, pForeignKey1);
		pPreparedStatement.setInt(2, pForeignKey2);
		for (int lCount = 0; lCount < pValues.length; lCount++)
		{
			Object lValue = pValues[lCount];
			if (lValue instanceof String)
			{
				lValue = ((String) lValue).replace("'", "\\'");
			}
			pPreparedStatement.setObject(lCount + 3, lValue);
		}
		pPreparedStatement.executeUpdate();
		ResultSet lGeneratedKeys = pPreparedStatement.getGeneratedKeys();
		lGeneratedKeys.next();
		int lGeneratedKey = lGeneratedKeys.getInt("GENERATED_KEY");
		return lGeneratedKey;
	}

	/**
	 * @param pPreparedStatement
	 *            prepared statement
	 * @param pForeignKey1
	 *            first foreign key
	 * @param pForeignKey2
	 *            second foreign key
	 * @param pForeignKey3
	 *            third foreign key
	 * @param pValues
	 *            values
	 * @return generated key
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static int insertIntoTable(PreparedStatement pPreparedStatement, int pForeignKey1, int pForeignKey2,
			int pForeignKey3, Object[] pValues) throws SQLException
	{
		pPreparedStatement.setInt(1, pForeignKey1);
		pPreparedStatement.setInt(2, pForeignKey2);
		pPreparedStatement.setInt(3, pForeignKey3);
		for (int lCount = 0; lCount < pValues.length; lCount++)
		{
			Object lValue = pValues[lCount];
			if (lValue instanceof String)
			{
				lValue = ((String) lValue).replace("'", "\\'");
			}
			pPreparedStatement.setObject(lCount + 4, lValue);
		}
		pPreparedStatement.executeUpdate();
		ResultSet lGeneratedKeys = pPreparedStatement.getGeneratedKeys();
		lGeneratedKeys.next();
		int lGeneratedKey = lGeneratedKeys.getInt("GENERATED_KEY");
		return lGeneratedKey;
	}

	/**
	 * @param pPreparedStatement
	 *            prepared statement
	 * @param pValues
	 *            values
	 * @return generated key
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static int insertIntoTable(PreparedStatement pPreparedStatement, Object[] pValues) throws SQLException
	{
		for (int lCount = 0; lCount < pValues.length; lCount++)
		{
			Object lValue = pValues[lCount];
			if (lValue instanceof String)
			{
				lValue = ((String) lValue).replace("'", "\\'");
			}
			pPreparedStatement.setObject(lCount + 1, lValue);
		}
		pPreparedStatement.executeUpdate();
		ResultSet lGeneratedKeys = pPreparedStatement.getGeneratedKeys();
		lGeneratedKeys.next();
		int lGeneratedKey = lGeneratedKeys.getInt("GENERATED_KEY");
		return lGeneratedKey;
	}

	/**
	 * @param pConnection
	 *            connection
	 * @param pStatement
	 *            statement
	 * @param pCreateTable
	 *            whether to create table
	 * @param pTableName
	 *            table name
	 * @param pColumns
	 *            columns
	 * @return PreparedStatement to insert into table
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static PreparedStatement createTable(Connection pConnection, Statement pStatement, boolean pCreateTable,
			String pTableName, String[][] pColumns) throws SQLException
	{
		String lSQL;
		if (pCreateTable)
		{
			lSQL = "CREATE TABLE " + pTableName + " (id INTEGER not NULL AUTO_INCREMENT, "
					+ SQLUtils.getCreateColumnString(pColumns) + ", PRIMARY KEY ( id ))";
			pStatement.executeUpdate(lSQL);
		}
		lSQL = "INSERT INTO " + pTableName + " (" + SQLUtils.getInsertColumnString(pColumns) + ") VALUES ("
				+ SQLUtils.getQuestionMarkString(pColumns.length) + ")";
		return pConnection.prepareStatement(lSQL, Statement.RETURN_GENERATED_KEYS);
	}

	/**
	 * @param pConnection
	 *            connection
	 * @param pStatement
	 *            statement
	 * @param pCreateTable
	 *            whether to create table
	 * @param pTableName
	 *            table name
	 * @param pColumns
	 *            columns
	 * @param pForeignKey
	 *            foreign key
	 * @param pReference
	 *            table referenced by foreign key
	 * @return PreparedStatement to insert into table
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static PreparedStatement createTable(Connection pConnection, Statement pStatement, boolean pCreateTable,
			String pTableName, String[][] pColumns, String pForeignKey, String pReference) throws SQLException
	{
		String lSQL;
		if (pCreateTable)
		{
			lSQL = "CREATE TABLE " + pTableName + " (id INTEGER not NULL AUTO_INCREMENT, " + pForeignKey
					+ " INTEGER not NULL, " + SQLUtils.getCreateColumnString(pColumns) + ", PRIMARY KEY ( id ), "
					+ "FOREIGN KEY ( " + pForeignKey + " ) REFERENCES " + pReference + "(id))";
			pStatement.executeUpdate(lSQL);
		}
		lSQL = "INSERT INTO " + pTableName + " (" + pForeignKey + "," + SQLUtils.getInsertColumnString(pColumns)
				+ ") VALUES (" + SQLUtils.getQuestionMarkString(pColumns.length + 1) + ")";
		return pConnection.prepareStatement(lSQL, Statement.RETURN_GENERATED_KEYS);
	}

	/**
	 * @param pConnection
	 *            connection
	 * @param pStatement
	 *            statement
	 * @param pCreateTable
	 *            whether to create table
	 * @param pTableName
	 *            table name
	 * @param pColumns
	 *            columns
	 * @param pForeignKey1
	 *            first foreign key
	 * @param pReference1
	 *            table referenced by first foreign key
	 * @param pForeignKey2
	 *            second foreign key
	 * @param pReference2
	 *            table referenced by second foreign key
	 * @return PreparedStatement to insert into table
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static PreparedStatement createTable(Connection pConnection, Statement pStatement, boolean pCreateTable,
			String pTableName, String[][] pColumns, String pForeignKey1, String pReference1, String pForeignKey2,
			String pReference2) throws SQLException
	{
		String lSQL;
		if (pCreateTable)
		{

			lSQL = "CREATE TABLE " + pTableName + " (id INTEGER not NULL AUTO_INCREMENT, " + pForeignKey1
					+ " INTEGER not NULL, " + pForeignKey2 + " INTEGER not NULL, "
					+ SQLUtils.getCreateColumnString(pColumns) + ", PRIMARY KEY ( id ), " + "FOREIGN KEY ( "
					+ pForeignKey1 + " ) REFERENCES " + pReference1 + "(id)," + "FOREIGN KEY ( " + pForeignKey2
					+ " ) REFERENCES " + pReference2 + "(id))";

			pStatement.executeUpdate(lSQL);
		}
		lSQL = "INSERT INTO " + pTableName + " (" + pForeignKey1 + "," + pForeignKey2 + ","
				+ SQLUtils.getInsertColumnString(pColumns) + ") VALUES ("
				+ SQLUtils.getQuestionMarkString(pColumns.length + 2) + ")";
		return pConnection.prepareStatement(lSQL, Statement.RETURN_GENERATED_KEYS);
	}

	/**
	 * @param pConnection
	 *            connection
	 * @param pStatement
	 *            statement
	 * @param pCreateTable
	 *            whether to create table
	 * @param pTableName
	 *            table name
	 * @param pColumns
	 *            columns
	 * @param pForeignKey1
	 *            first foreign key
	 * @param pReference1
	 *            table referenced by first foreign key
	 * @param pForeignKey2
	 *            second foreign key
	 * @param pReference2
	 *            table referenced by second foreign key
	 * @param pForeignKey3
	 *            third foreign key
	 * @param pReference3
	 *            table referenced by third foreign key
	 * @return PreparedStatement to insert into table
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static PreparedStatement createTable(Connection pConnection, Statement pStatement, boolean pCreateTable,
			String pTableName, String[][] pColumns, String pForeignKey1, String pReference1, String pForeignKey2,
			String pReference2, String pForeignKey3, String pReference3) throws SQLException
	{
		String lSQL;
		if (pCreateTable)
		{
			lSQL = "CREATE TABLE " + pTableName + " (id INTEGER not NULL AUTO_INCREMENT, " + pForeignKey1
					+ " INTEGER not NULL, " + pForeignKey2 + " INTEGER not NULL, " + pForeignKey3
					+ " INTEGER not NULL, " + SQLUtils.getCreateColumnString(pColumns) + ", PRIMARY KEY ( id ), "
					+ "FOREIGN KEY ( " + pForeignKey1 + " ) REFERENCES " + pReference1 + "(id)," + "FOREIGN KEY ( "
					+ pForeignKey2 + " ) REFERENCES " + pReference2 + "(id)," + "FOREIGN KEY ( " + pForeignKey3
					+ " ) REFERENCES " + pReference3 + "(id))";

			pStatement.executeUpdate(lSQL);
		}
		lSQL = "INSERT INTO " + pTableName + " (" + pForeignKey1 + "," + pForeignKey2 + "," + pForeignKey3 + ","
				+ SQLUtils.getInsertColumnString(pColumns) + ") VALUES ("
				+ SQLUtils.getQuestionMarkString(pColumns.length + 3) + ")";
		return pConnection.prepareStatement(lSQL, Statement.RETURN_GENERATED_KEYS);
	}

	/**
	 * @param pConnection
	 *            connection
	 * @param pStatement
	 *            statement
	 * @param pCreateTable
	 *            whether to create table
	 * @param pTableName
	 *            table name
	 * @param pForeignKey1
	 *            first foreign key
	 * @param pReference1
	 *            table referenced by first foreign key
	 * @param pForeignKey2
	 *            second foreign key
	 * @param pReference2
	 *            table referenced by second foreign key
	 * @return PreparedStatement to insert into table
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static PreparedStatement createTable(Connection pConnection, Statement pStatement, boolean pCreateTable,
			String pTableName, String pForeignKey1, String pReference1, String pForeignKey2, String pReference2)
			throws SQLException
	{
		String lSQL;
		if (pCreateTable)
		{
			lSQL = "CREATE TABLE " + pTableName + " (id INTEGER not NULL AUTO_INCREMENT, " + pForeignKey1
					+ " INTEGER not NULL, " + pForeignKey2 + " INTEGER not NULL, PRIMARY KEY ( id ), "
					+ "FOREIGN KEY ( " + pForeignKey1 + " ) REFERENCES " + pReference1 + "(id)," + "FOREIGN KEY ( "
					+ pForeignKey2 + " ) REFERENCES " + pReference2 + "(id))";

			pStatement.executeUpdate(lSQL);
		}
		lSQL = "INSERT INTO " + pTableName + " (" + pForeignKey1 + "," + pForeignKey2 + ") VALUES ("
				+ SQLUtils.getQuestionMarkString(2) + ")";
		return pConnection.prepareStatement(lSQL, Statement.RETURN_GENERATED_KEYS);
	}

	/**
	 * @param pStatement
	 *            statement
	 * @param pTableName
	 *            table name
	 */
	public static void dropTable(Statement pStatement, String pTableName)
	{
		try
		{
			String lDropTable = "DROP TABLE " + pTableName;
			pStatement.executeUpdate(lDropTable);
		}
		catch (SQLException e)
		{
			// System.out.println(e);
		}
	}

	/**
	 * @param pStatement
	 *            statement
	 * @param pTableNames
	 *            table name
	 */
	public static void dropTables(Statement pStatement, String... pTableNames)
	{
		for (String lTableName : pTableNames)
		{
			dropTable(pStatement, lTableName);
		}
	}

	/**
	 * @param pDBName
	 *            database name
	 * @return connection
	 * @throws ClassNotFoundException
	 *             if SQL driver isn't found
	 * @throws SQLException
	 *             if SQL goes wrong
	 */
	public static Connection getConnection(String pDBName) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection lConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + pDBName + "?user="
				+ Configuration.getInstance().getDatabaseUser() + "&password="
				+ Configuration.getInstance().getDatabasePassword());
		return lConnection;
	}

	private static String getQuestionMarkString(int pLength)
	{
		String lQuestionMarkString = "";
		for (int lCount = 0; lCount < pLength; lCount++)
		{
			lQuestionMarkString += "?,";
		}
		return lQuestionMarkString.replaceAll(",$", "");
	}

	private static String getInsertColumnString(String[][] pColumns)
	{
		String lColumnString = "";
		for (String[] lColumn : pColumns)
		{
			lColumnString += lColumn[0] + ",";
		}
		return lColumnString.replaceAll(",$", "");
	}

	private static String getCreateColumnString(String[][] pColumns)
	{
		String lColumnString = "";
		for (String[] lColumn : pColumns)
		{
			lColumnString += lColumn[0] + " " + lColumn[1] + ",";
		}
		return lColumnString.replaceAll(",$", "");
	}
}
