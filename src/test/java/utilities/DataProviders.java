package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
// Dataprovider=1
	@DataProvider(name="Login Data")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_Loggingdata.xlsx"; // taking xl file from testdata folder
		
		Excelutils xlutil= new Excelutils(path); // creating object for xl util file
		int totalrows= xlutil.getRowCount("Sheet1");
		int totalcols= xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]= new String[totalrows][totalcols];// created for 2 dimensional array
		for(int i=1; i<=totalrows; i++) {
			for (int j=0; j<totalcols; j++) {
				logindata[i-1][j]= xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}
	// data provider=2
}
