import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.MorganHolbart.TVReminderAPI.API;


public class TVReminder {

	Map<Integer, Show> KnownShows = new HashMap<Integer, Show>();
	
	public static void main(String[] args) throws IOException {

		

		System.out.println(API.GetNextEpisode(172));
		
	}

}
