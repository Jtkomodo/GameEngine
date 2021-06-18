package physics;

public class Collision {

	public AABB aabb1,aabb2;

	public Collision(AABB aabb1, AABB aabb2) {
		this.aabb1 = aabb1;
		this.aabb2 = aabb2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aabb1 == null) ? 0 : aabb1.hashCode());
		result = prime * result + ((aabb2 == null) ? 0 : aabb2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collision other = (Collision) obj;
		if (aabb1 == null) {
			if (other.aabb1 != null)
				return false;
		} else if (!aabb1.equals(other.aabb1))
			return false;
		if (aabb2 == null) {
			if (other.aabb2 != null)
				return false;
		} else if (!aabb2.equals(other.aabb2))
			return false;
		return true;
	}
 
	
	
	
}
