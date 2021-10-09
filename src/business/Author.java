package business;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	private String id;
	private String bio;
	public String getBio() {
		return bio;
	}
	
	public Author(String id, String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.id = id;
		this.bio = bio;
	}
	public Author(String f, String l) {
		super(f,l);
		this.bio = null;
	}
	
	public String getAuthorId() {
		return id;
	}
	@Override
	public String toString() {
		return id+" "+super.getFirstName();
	}

	private static final long serialVersionUID = 7508481940058530471L;
}
