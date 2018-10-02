package ru.stqa.ptf.sandbox;

public class MyFirst {

public static void main(String[] args) {
	Point p1 = new Point(1, 5);
	Point p2 = new Point(2, 10);
	System.out.println("Расстояние между точкой с координатами (" + p1.x + " и " + p1.y + ") и точкой с координатами ("+ p2.x + " и " + p2.y + ") равно " + p2.distance(p1));
}

}		


