package ee.carlrobert.chatgpt.ide.toolwindow.components;

import icons.Icons;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class GenerateButton extends JButton {

  public GenerateButton() {
    initialize();
  }

  public void setMode(Mode mode, Runnable onClick) {
    var isStopMode = mode == Mode.STOP;
    setIcon(isStopMode ? Icons.SquareIcon : Icons.RefreshIcon);
    setText(isStopMode ? "Stop generating" : "Regenerate response");
    for (var listener : getActionListeners()) {
      removeActionListener(listener);
    }
    addActionListener(e -> onClick.run());
  }

  private void initialize() {
    setVerticalTextPosition(SwingConstants.CENTER);
    setHorizontalTextPosition(SwingConstants.RIGHT);
    setVerticalAlignment(SwingConstants.CENTER);
    setAlignmentY(0.5f);
    setVisible(false);
  }

  public enum Mode {
    STOP,
    REFRESH
  }
}
