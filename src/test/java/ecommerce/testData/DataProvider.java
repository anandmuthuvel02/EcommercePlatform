package ecommerce.testData;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProvider {

	
	public List<HashMap<String, String>> getJsonData() throws IOException {
		
		Path path = Paths.get(System.getProperty("user.dir")+"//src//test//java//ecommerce//testData//PurchseData.json");
		String JsonContent = Files.readString(path);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(JsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		
		return data;
	}
}
