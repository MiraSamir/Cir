package view;

import javafx.stage.Screen;

public final class Util {
  public static final double SCREEN_HEIGHT;
  public static final double SCREEN_WIDTH;
  static {
    SCREEN_WIDTH = Screen.getScreens().get(0).getBounds().getWidth();
    SCREEN_HEIGHT = Screen.getScreens().get(0).getBounds().getHeight();
  }

  private Util() {}
}
