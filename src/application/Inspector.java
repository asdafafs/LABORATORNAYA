package application;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Inspector {
private IntegerProperty idInspector;
private StringProperty Name;
private StringProperty Surname;
private StringProperty Position;
private StringProperty Rank;
private StringProperty Conclusion;

public Inspector() {
	this.idInspector=new SimpleIntegerProperty(0);
	this.Name= new SimpleStringProperty("");
	this.Surname= new SimpleStringProperty("");
	this.Position=new SimpleStringProperty("");
	this.Rank=new SimpleStringProperty("");
	this.Conclusion=new SimpleStringProperty("");
	}
public Inspector(Integer idInspector, String Name, String Surname, String Position, String Rank, String Conclusion) {
	this.idInspector=new SimpleIntegerProperty(idInspector);
	this.Name= new SimpleStringProperty(Name);
	this.Surname= new SimpleStringProperty(Surname);
	this.Position=new SimpleStringProperty(Position);
	this.Rank=new SimpleStringProperty(Rank);
	this.Conclusion=new SimpleStringProperty(Conclusion);
	}


public Integer getidInspector() {
	return this.idInspector.get();
}
public String getName() {
	return this.Name.get();
}
public String getSurname() {
	return this.Surname.get();
}
public String getPosition() {
	return this.Position.get();
}
public String getRank() {
	return this.Rank.get();
}
public String getConclusion() {
	return this.Conclusion.get();
}

}
