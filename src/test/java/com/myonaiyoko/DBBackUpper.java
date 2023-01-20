package com.myonaiyoko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "/test.properties")
public abstract class DBBackUpper {

	public static String[] testTables;
	protected static String backXml = "./src/test/resources/dbunit/backup.xml";

	private static Connection con;
	private static IDatabaseConnection dbCon;

	protected static void dbBackup() throws SQLException, DatabaseUnitException, FileNotFoundException, IOException {

		ResourceBundle rb = ResourceBundle.getBundle("test");
		con = DriverManager.getConnection(
				rb.getString("spring.datasource.url"),
				rb.getString("spring.datasource.username"),
				rb.getString("spring.datasource.password"));
		dbCon = new DatabaseConnection(con);
		QueryDataSet backupDataSet = new QueryDataSet(dbCon);

		for (String table : testTables) {
			backupDataSet.addTable(table);
		}

		FlatXmlDataSet.write(backupDataSet, new FileOutputStream(backXml));
	}

	protected static void dbRestore() throws DatabaseUnitException, SQLException, IOException {
		IDataSet dataset = new FlatXmlDataSetBuilder().build(new File(backXml));
		DatabaseOperation.CLEAN_INSERT.execute(dbCon, dataset);
	}

}
