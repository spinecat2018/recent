package com.nightmare.recent;

public class Point {

	long moment;
	int colorId;
	String description;
	
	public Point(long moment,int colorId,String description) {
		this.moment = moment;
		this.colorId = colorId;
		this.description = description;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			if (this.moment== ( (Point)obj ).moment ) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

	
}
