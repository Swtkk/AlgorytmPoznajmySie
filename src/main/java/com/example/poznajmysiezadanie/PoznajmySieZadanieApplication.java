package com.example.poznajmysiezadanie;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class PoznajmySieZadanieApplication extends CalendarMethods{

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PoznajmySieZadanieApplication.class, args);

		try {

		String calendar1 = "kalendarz1.txt"; //pobieranie kalendarz 1 z pliku
		String calendar2 = "kalendarz2.txt"; //pobieranie kalendarz 2 z pliku
		String jsonCalendar1 = new String(Files.readAllBytes(Paths.get(calendar1))); // czytanie zawartosci
		String jsonCalendar2 = new String(Files.readAllBytes(Paths.get(calendar2))); // czytanie zawartosci
		int meetingDuration =20;

		Gson gson = new Gson();

		Calendar calendar1obj = gson.fromJson(jsonCalendar1, Calendar.class);
		Calendar calendar2obj = gson.fromJson(jsonCalendar2, Calendar.class);

		List<TimeSchedule> slot1 = CalendarMethods.findAllAvailableSlots(calendar1obj,meetingDuration);
		List<TimeSchedule> slot2 = CalendarMethods.findAllAvailableSlots(calendar2obj,meetingDuration);
		List<TimeSchedule> meetingSlots = CalendarMethods.findMeetingSlot(slot1,slot2,meetingDuration);

		for(TimeSchedule slot : meetingSlots){
			System.out.println("[" + slot.getStart() + "," + slot.getEnd() + "]");
		}

		}
		catch (IOException e) {
			System.out.println("Blad pobierania pliku");
		}


	}

}
