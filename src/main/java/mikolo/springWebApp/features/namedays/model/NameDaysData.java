package mikolo.springWebApp.features.namedays.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class NameDaysData {
	@SerializedName("status")
	@Expose
	public String status;
	@SerializedName("data")
	@Expose
	public DataNames data;

	}

