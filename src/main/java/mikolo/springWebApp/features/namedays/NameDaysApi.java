package mikolo.springWebApp.features.namedays;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import mikolo.springWebApp.features.namedays.model.NameData;
import mikolo.springWebApp.features.namedays.model.NameDaysData;

public class NameDaysApi {

	private RestTemplate restTemplate = new RestTemplate();
	
	private NameDaysUtils utilities = new NameDaysUtils();

	public String getDateAndNameDays() {
		String returnString = "";

		try {
			URL url = new URL(NameDaysStatics.URL + NameDaysStatics.COUNTRY);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Chrome");
			try (Scanner s = new Scanner(connection.getInputStream())) {
				String text = s.nextLine();
				Gson gson = new Gson();

				NameData data = gson.fromJson(text, NameData.class);
				returnString = "Dziś jest " + utilities.getDate() + ". Imieniny obchodzą: "
						+ data.getDatas().getNameDays().getPl() + ".";
			}

		} catch (Exception e) {
			e.printStackTrace();
			returnString = "";
			return returnString;
		}
		return returnString;
	}

	public List<String> getNameDays() {
		List<String> namesList = new ArrayList<>();

		try {
			URL url = new URL(NameDaysStatics.URL + NameDaysStatics.COUNTRY);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Chrome");
			try (Scanner s = new Scanner(connection.getInputStream())) {

				String text = s.nextLine();
				Gson gson = new Gson();

				NameData data = gson.fromJson(text, NameData.class);
				String allNames = data.getDatas().getNameDays().getPl();
				namesList = Arrays.asList(allNames.split(", "));
				namesList.forEach(String::trim);
			}
		} catch (Exception e) {
			e.printStackTrace();
			namesList = Collections.emptyList();
			return namesList;
		}

		return namesList;
	}
	
	public String getDateAndNameDays1() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("User-Agent", "Chrome");
		String requestJson = "{\"country\":\"poland\", \"timezone\" : \"Europe\\/Warsaw\"}";
		
		NameDaysData nameDaysEntity = restTemplate.postForObject("https://nameday.abalin.net/today",
				new HttpEntity<String>(requestJson, headers), NameDaysData.class);
		return "Dziś jest " + utilities.getDate() + ". Imieniny obchodzą: " + nameDaysEntity.getData().getNamedays().getPl() + ".";
	}
	
	public List<String> getNameDays1(){
		List<String> namesList = new ArrayList<>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("User-Agent", "Chrome");
		String requestJson = "{\"country\":\"poland\", \"timezone\" : \"Europe\\/Warsaw\"}";
		
		NameDaysData nameDaysEntity = restTemplate.postForObject("https://nameday.abalin.net/today",
				new HttpEntity<String>(requestJson, headers), NameDaysData.class);
		String allNames = nameDaysEntity.getData().getNamedays().getPl();
		namesList = Arrays.asList(allNames.split(", "));
		namesList.forEach(String::trim);
		return namesList;
	}
	
}
