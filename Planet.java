public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	static final double G = 6.67e-11;
	static final double timeUnit = 1.00;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	};

	// 计算两个planet之间的距离
	public double calcDistance(Planet p){
		double square = (p.xxPos - this.xxPos) * (p.xxPos - this.xxPos) + (p.yyPos - this.yyPos) * (p.yyPos - this.yyPos);
		return Math.sqrt(square);
	}

	// 计算两个planet之间的引力
	public double calcForceExertedBy(Planet p){
		double distance = calcDistance(p);
		return G * this.mass * p.mass / ( distance * distance );
	}

	// 计算引力在x方向上的分量
	public double calcForceExertedByX(Planet p){
		double force = calcForceExertedBy(p);
		double distance = calcDistance(p);

		return force * (p.xxPos - this.xxPos) / distance;
	}

	// 计算引力在y方向上的分量
	public double calcForceExertedByY(Planet p){
		double force = calcForceExertedBy(p);
		double distance = calcDistance(p);

		return force * (p.yyPos - this.yyPos) / distance;
	}

	// 计算在x方向上引力分量的总和
	public double calcNetForceExertedByX(Planet[] planets){
		double netForceByX = 0.0;

		// 如果列表中有这个planet本身，注意排除
		for(Planet p : planets){
			if(p.equals(this)) continue;
			netForceByX += calcForceExertedByX(p);
		}

		return netForceByX;
	}

	// 计算在y方向上引力分量的总和
	public double calcNetForceExertedByY(Planet[] planets){
		double netForceByY = 0.0;

		// 如果列表中有这个planet本身，注意排除
		for(Planet p : planets){
			if(p.equals(this)) continue;
			netForceByY += calcForceExertedByY(p);
		}

		return netForceByY;
	}

	//更新参数
	public void update(double dt, double xForce, double yForce){
		double xxA;
		double yyA;

		xxA = xForce / this.mass;
		yyA = yForce / this.mass;

		this.xxVel += dt / timeUnit * xxA;
		this.yyVel += dt / timeUnit * yyA;

		this.xxPos += this.xxVel * dt / timeUnit;
		this.yyPos += this.yyVel * dt / timeUnit;
	}

	// 绘画方法
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}
}
