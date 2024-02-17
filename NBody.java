import java.lang.management.PlatformManagedObject;

public class NBody {

	public static double readRadius(String path) {
		In in = new In(path);

		int numOfPlanets = in.readInt();
		double radius = in.readDouble();

		return radius;
	}


	public static Planet[] readPlanets(String path){
		In in = new In(path);
		int numOfPlanets = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[numOfPlanets];

		for(int i = 0; i < numOfPlanets; i++){
			planets[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
		}

		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);
		double time = 0.0;

		StdDraw.enableDoubleBuffering();
		for(; time < T; time += dt){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];
			int i = 0;
			for(Planet p : planets){
				xForces[i] = p.calcNetForceExertedByX(planets);
				yForces[i] = p.calcNetForceExertedByY(planets);
				i ++;
			}
			i = 0;
			for(Planet p : planets){
				p.update(dt,xForces[i],yForces[i]);
				i ++;
			}
			StdDraw.clear();
			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0,0,"images/starfield.jpg");
			for(Planet p : planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
					planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
					planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}

	}
}
