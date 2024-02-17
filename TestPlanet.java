public class TestPlanet {
	public static void main(String[] args) {
		Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
		Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");

		double distance = p1.calcDistance(p2);

		if(distance == 1.0) {
			System.out.println("Pass");
		}else{
			System.out.println("Not pass");
		}
	}
}
