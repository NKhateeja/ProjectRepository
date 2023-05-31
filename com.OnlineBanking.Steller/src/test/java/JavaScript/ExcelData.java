package JavaScript;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import com.OnlineBanking.Steller.Utilities.ExcelUtilities;

public class ExcelData {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		ExcelUtilities Eut=new ExcelUtilities();
		String data = Eut.getExcelData("Sheet1", 1, 1);
		System.out.println(data);
	}

}
