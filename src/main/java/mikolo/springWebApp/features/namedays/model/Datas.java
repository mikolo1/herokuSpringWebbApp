package mikolo.springWebApp.features.namedays.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;


@Data
public class Datas {
	
	@SerializedName("dates")
	@Expose
	public Dates dates;
	@SerializedName("namedays")
	@Expose
	public NameDays nameDays;

}
