package mikolo.springWebApp.features.namedays.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Dates {

@SerializedName("day")
@Expose
public Integer day;
@SerializedName("month")
@Expose
public Integer month;

}
