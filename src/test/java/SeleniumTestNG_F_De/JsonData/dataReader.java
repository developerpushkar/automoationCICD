package SeleniumTestNG_F_De.JsonData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class dataReader {

	//We are Not Using this class -> we have pasted this method in base test and using it from there
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "//src//test//java//SeleniumTestNG_F_De//JsonData//purchaseData.json"),
				StandardCharsets.UTF_8);

		//C:\Users\Pushkar Singh\eclipse-workspace\18_SeleniumTestNG_F_De\src\test\java\SeleniumTestNG_F_De\JsonData\purchaseData.json
		// String to HashMap- Jackson Datbind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

		// {map, map}

	}
}
