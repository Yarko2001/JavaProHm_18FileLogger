package ua.dehtiar.model;

import java.util.Objects;

/**
 * @author Yaroslav Dehtiar on 11.12.2022
 */
public class Vector {

  private final double x;
  private final double y;
  private final double z;

  public Vector(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double scalarProduct(Vector vector) {
    return x * vector.x + y * vector.y + z * vector.z;
  }

  public Vector crossProduct(Vector vector) {
    return new Vector(
        y * vector.z - z * vector.y,
        z * vector.x - x * vector.z,
        x * vector.y - y * vector.x);
  }


  public Vector add(Vector vector) {
    return new Vector(
        x + vector.x,
        y + vector.y,
        z + vector.z
    );
  }

  public Vector subtract(Vector vector) {
    return new Vector(
        x - vector.x,
        y - vector.y,
        z - vector.z
    );
  }

  public static Vector[] generate(int n) {
    Vector[] vectors = new Vector[n];
    for (int i = 0; i < n; i++) {
      vectors[i] = new Vector(getRandom(1, 10), getRandom(1, 10), getRandom(1, 10));
    }
    return vectors;
  }

  private static int getRandom(int min, int max) {
    return (int) (Math.random() * (max - min) + min);
  }

  @Override
  public String toString() {
    return "Vector{" +
        "x=" + x +
        ", y=" + y +
        ", z=" + z +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Vector vector = (Vector) o;
    return Double.compare(vector.x, x) == 0 && Double.compare(vector.y, y) == 0
        && Double.compare(vector.z, z) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, z);
  }
}
