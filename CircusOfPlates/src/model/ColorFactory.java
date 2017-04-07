package model;

import javafx.scene.paint.Color;

public class ColorFactory {

  public static Color getColor(String color) {

    switch (color.toLowerCase()) {
      case "red":
        return Color.RED;
      case "green":
        return Color.GREEN;
      case "blue":
        return Color.BLUE;
      case "brown":
        return Color.BROWN;
      case "magneta":
        return Color.MAGENTA;
      case "purple":
        return Color.PURPLE;
      case "yellow":
        return Color.YELLOW;
      default:
        return null;

    }
  }
}
