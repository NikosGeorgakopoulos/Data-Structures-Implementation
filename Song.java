
public class Song {
	
	private int id;
	private String name;
	private int likes;
	
	public Song() {
		
		this(1,"Test",10);
	}
	
	
	public Song(int id,String name, int likes) {
		this.id = id;
		this.name = name;
		this.likes = likes;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getLikes() {
		return likes;
	}


	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
	
	@Override
	public String toString() {
	    return id + " || " + name + "|| " + likes;
	}

}
