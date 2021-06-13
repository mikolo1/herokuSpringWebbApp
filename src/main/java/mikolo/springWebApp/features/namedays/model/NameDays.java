package mikolo.springWebApp.features.namedays.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class NameDays {
	
@SerializedName("pl")
@Expose
public String pl;

}
