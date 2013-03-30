/**
 * Copyright (C) 2013, Claus Nielsen, cn@cn-consult.dk
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package dk.clanie.bitcoin.client.response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import dk.clanie.bitcoin.json.JsonExtra;
import dk.clanie.io.IOUtil;

/**
 * Tests json serialization and deserialization of response objects.
 * 
 * @author Claus Nielsen
 */
public class ResponseSerializationTest {

	private static final Logger log = LoggerFactory.getLogger(ResponseSerializationTest.class);

	private ObjectMapper objectMapper = new ObjectMapper();


	@Test
	public void testDoubleUnwrapped() throws Exception {
		File file = new File("src/test/resources/sampleResponse/_DOUBLE_UNWRAP.json");
		String jsonSample = IOUtils.toString(file.toURI());
		ListUnspentResult obj = objectMapper.readValue(jsonSample, ListUnspentResult.class);
		String roundtrippedJson = objectMapper.writeValueAsString(obj);
		System.out.println(roundtrippedJson);
		assertThat("json -> obj -> json roundtrip serialization failed for " + file.getName() + ".", roundtrippedJson, equalTo(jsonSample));
	}


	@Test
	@Ignore
	public void test() throws IOException, ClassNotFoundException {
		List<File> files = IOUtil.listFilesRecursively(new File("src/test/resources/sampleResponse"));
		for (File file : files) {
			testSerialization(file);
		}
	}


	/**
	 * Tests that the given json can be deserialized to the given type and serialized back to 
	 * the original. Also checks that all fields are mapped explicitly (ie. that "otherFilds"
	 * isn't used). 
	 * 
	 * 
	 * @param jsonSample
	 * @param responseType
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	private void testSerialization(File file) throws IOException, ClassNotFoundException {
		
		log.debug("Testing deserialization of sample file " + file.getName() + ".");

		String className = extractResponseClassName(file);
		String jsonSample = IOUtils.toString(file.toURI());
		@SuppressWarnings("unchecked")
		Class<? extends BitcoindJsonRpcResponse<?>> responseType = (Class<? extends BitcoindJsonRpcResponse<?>>) Class.forName("dk.clanie.bitcoin.client.response." + className);

		// Deserialize and re-serialize
		log.debug("Deserializing " + jsonSample);
		BitcoindJsonRpcResponse<?> response = objectMapper.readValue(jsonSample, responseType);
		String roundtrippedJson = objectMapper.writeValueAsString(response);
		assertThat("json -> obj -> json roundtrip serialization failed for " + file.getName() + ".", roundtrippedJson, equalTo(jsonSample));

		// Check that all fields are explicitly mapped.
		assertThat("Some fields in response not explicitly mapped (see otherFields): " + response.toString(), response.getOtherFields().size(), equalTo(0));
		
		Object resultObject = response.getResult();
		if (resultObject instanceof JsonExtra) {
			JsonExtra result = (JsonExtra) resultObject;
			assertThat("Some fields in result not explicitly mapped (see otherFields): " + result.toString(), result.getOtherFields().size(), equalTo(0));
		}
	}


	/**
	 * Extracts the response class name from the name of the sample file.
	 * 
	 * Sample files must be names &lt;ResponseClassName&gt;_&lt;test sequence or description&gt;.json,
	 * where the _&lt;test sequence or description&gt; is optional (it is used when there are more
	 * than one sample response of the same response class).
	 * 
	 * @param file
	 * @return String - response class name
	 */
	private String extractResponseClassName(File file) {
		String fileName = file.getName();
		int endIndex = fileName.indexOf("_");
		if (endIndex == -1) endIndex = fileName.indexOf(".");
		return fileName.substring(0, endIndex);
	}


}
