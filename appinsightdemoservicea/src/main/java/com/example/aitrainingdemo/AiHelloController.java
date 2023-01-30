package com.example.aitrainingdemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.common.io.CharStreams;

//import org.apache.commons.codec.Charsets;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AiHelloController {

	private static final Logger logger = LogManager.getLogger();
	
	@GetMapping("/")
	public String landingPageServiceA(){
		//return get("http://localhost:8082/api2/hello");
		//return get("http://localhost:8080/swagger-ui/index.html#/ai-hello-controller");
		return "Open Swagger API via http://localhost:8081/swagger-ui/index.html#/ai-hello-controller";
	}

	@GetMapping("/hello")
	public String helloServiceA() {
		return "Hello from service A";
	}

	@GetMapping("/hello/World")
	public String helloWorldServiceA() {
		return "Hello World from Service A";
	}

	/*
	 * @RequestMapping("/hello/12a34b/kd/23") public String index3(@PathVariable
	 * String from, @PathVariable String to) { return "Hello Kuldeep A!"; }
	 */

	@GetMapping("/http-dependency/success")
	public String httpDependencyServiceA() throws IOException {
		return get("https://httpstat.us/200");
	}

	@GetMapping("/http-dependency/failure")
	public String httpDependencyFailureServiceA() throws IOException {
		return get("https://httpstat.us/500");
	}

	@GetMapping("/jdbc-dependency/success")
	public String jdbcDependencySuccessServiceA() throws SQLException {
		try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test")) {
			try (Statement statement = connection.createStatement()) {
				statement.execute("select 1");
			}
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "SQL Call from ServiceA sent success";
	}

	@GetMapping("/jdbc-dependency/failure")
	public String jdbcDependencyFailureServiceA() throws SQLException {
		try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test")) {
			try (Statement statement = connection.createStatement()) {
				statement.execute("select OOPS");
			}
		} catch (SQLException e) {
			return e.getMessage();
		}
		return "SQL Call from ServiceA sent Failure";
	}

	@GetMapping("/service-b")
	public String helloCallToServiceB() throws IOException {
		return get("http://localhost:8082/hello");
	}
	
	@GetMapping("/service-b-sql")
	public String jdbcSuccessCallToServiceB() throws IOException {
		return get("http://localhost:8082/jdbc-dependency/success");
	}

	private static String get(String url) throws IOException {
		return getWithApacheHttpClient4(url);
	}

	private static String getWithApacheHttpClient4(String url) throws IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		HttpResponse response = httpClient.execute(httpGet);
		InputStream content = response.getEntity().getContent();
		InputStreamReader reader = new InputStreamReader(content);
		String body = CharStreams.toString(reader);
		// String body = "test";
		content.close();
		httpClient.close();
		return "Response from " + url + " was: " + body;
	}

	@GetMapping("/log/fatal")
	public String logFatalServiceA() throws IOException {
		logger.error("this is a fatal message from service A");
		return "fatal message was logged from service A";
	}

	@GetMapping("/log/error")
	public String logErrorServiceA() throws IOException {
		logger.error("this is an error message from service A");
		return "error message was logged from service A";
	}

	@GetMapping("/log/warn")
	public String logWarnServiceA() throws IOException {
		logger.warn("this is a warn message from service A");
		return "warn message was logged from service A";
	}

	@GetMapping("/log/info")
	public String logInfoServiceA() throws IOException {
		logger.info("this is an info message from service A");
		return "info message was logged from service A";
	}

	@GetMapping("/log/debug")
	public String logDebugServiceA() throws IOException {
		logger.debug("this is a debug message from service A");
		return "debug message was logged from service A";
	}

	@GetMapping("/log/trace")
	public String logTraceServiceA() throws IOException {
		logger.trace("this is a trace message from service A1");
		return "trace message was logged from service A";
	}
}
