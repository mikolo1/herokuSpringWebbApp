package mikolo.springWebApp.features.namedays.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class NameData {
	
	@SerializedName("data")
	@Expose
	public Datas datas;

}
