package semester1.practice02.homework;

public class Task4HeronGeometry {
    public static void main(String[] args) {
        double a = 7.0;
        double b = 8.0;
        double c = 9.0;

        double perimeter = a + b + c;

        double p = perimeter / 2.0;

        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));

        double r = s / p;

        double inscribedCircleArea = Math.PI * Math.pow(r, 2);

        double R = (a * b * c) / (4.0 * s);

        double circumscribedCircleArea = Math.PI * Math.pow(R, 2);

        System.out.println("================ ГЕОМЕТРИЧНИЙ ЗВІТ ===============");
        System.out.println("Сторони: a = " + a + ", b = " + b + ", c = " + c);
        System.out.println("Периметр (P):               " + perimeter);
        System.out.println("Напівпериметр (p):          " + p);
        System.out.println("Площа трикутника (S):       " + s + " кв. од.");
        System.out.println("Радіус вписаного кола (r):  " + r);
        System.out.println("Площа вписаного круга:      " + inscribedCircleArea + " кв. од.");
        System.out.println("Радіус описаного кола (R):  " + R);
        System.out.println("Площа описаного круга:      " + circumscribedCircleArea + " кв. од.");
        System.out.println("==================================================");
    }
}
