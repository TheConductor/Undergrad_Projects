
public class Pumpkin implements Comparable{
	private int weight;
	
	public Pumpkin(int w) {
		weight = w;
	}

	public boolean equals(Object obj){
		boolean returnValue = false;
		Pumpkin p = (Pumpkin) obj;
		if(this.weight == p.weight){
			returnValue = true;
		}
		return returnValue;
	}// End equals

	@Override
	public int compareTo(Object obj) {
		Pumpkin p = (Pumpkin) obj;
		return (this.weight-p.weight);
	}
}
