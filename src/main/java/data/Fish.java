package data;


public class Fish {
	private int id;
	private String breed;
	private float weight;
	private float length;
	private String city;
	private String water;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public void setWeight(String s) {
		try {
			this.weight = Float.parseFloat(s);
		}
		catch(NumberFormatException e) {
			weight=-1;
		}
		
	}
	public float getLength() {
		return length;
	}
	public void setLength(float length) {
		this.length = length;
	}
	
	public void setLength(String s) {
		try {
			this.length = Float.parseFloat(s);
		}
		catch(NumberFormatException e) {
			length=0;
		}
		
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWater() {
		return water;
	}
	public void setWater(String water) {
		this.water = water;
	}
	public String toString() {
		return id+" "+breed+" "+weight+""+length+ ""+city+""+water+"\n";
	}
}

