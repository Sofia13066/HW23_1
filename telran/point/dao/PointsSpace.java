package telran.point.dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import telran.point.model.Point;

public class PointsSpace {
	Point relPoint;
	Point[] points;
	Comparator<Point> comp;

	public PointsSpace(Point relPoint, Point[] points) {
		this.relPoint = relPoint;
		this.comp = (c1, c2) -> Double.compare(distance(c1), distance(c2));
		this.points = new Point[points.length];
		System.arraycopy(points, 0, this.points, 0, points.length);
		Arrays.sort(this.points, this.comp);

	}

	private double distance(Point point) {
		double d = Math.sqrt((point.getX() - relPoint.getX()) * (point.getX() - relPoint.getX()) + (point.getY() - relPoint.getY()) * (point.getY() - relPoint.getY()));
		return d;
	}

	public Point[] getPoints() {
		return this.points;
	
	}

	
	
	public Comparator<Point> getComp() {
		return this.comp;
	}

	public void addPoint(Point point) {
		Point[] added = new Point[this.points.length + 1];
		int indexAddedPoint = Arrays.binarySearch(points, point, comp);
		indexAddedPoint = indexAddedPoint<0 ? -indexAddedPoint-1 : indexAddedPoint;
		System.arraycopy(this.points, 0, added, 0, indexAddedPoint);
		added[indexAddedPoint] = point;
		System.arraycopy(this.points, indexAddedPoint, added, indexAddedPoint + 1, points.length-indexAddedPoint);
		this.points = added;

		//TODO keep sort of this.points
		//to apply method binarySearch of the class Arrays
		//to apply method arrayCopy of the class System
		//method arrayCopy will be called twice
	}
}