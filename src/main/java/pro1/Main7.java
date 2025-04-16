package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.DataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main7 {
    public static String specializationDeadlines(int year)
    {
        Set<String> uniqueDates = new HashSet<>();
        String json = Api.getSpecializations(year);
        DataModel data = new Gson().fromJson(json, DataModel.class);
        for(var val : data.prijimaciObor)
        {
            uniqueDates.add(val.eprDeadlinePrihlaska.value);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
        List<String> sortedDates = new ArrayList<>(uniqueDates);
        sortedDates.sort(Comparator.comparing(dateStr -> LocalDate.parse(dateStr, formatter)));
        return String.join(",", sortedDates);
    }
}
