package ee.carlrobert.chatgpt.ide.action;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SwingUtilities;

public class CustomPromptAction extends BaseAction {

  private static String previousUserPrompt = "";

  protected void initToolWindow(ToolWindow toolWindow) {
    toolWindow.setTitle("Custom Prompt");
    toolWindow.show();
  }

  protected void actionPerformed(Project project, Editor editor, String selectedText) {
    if (selectedText != null && !selectedText.isEmpty()) {
      var fileExtension = getFileExtension(((EditorImpl) editor).getVirtualFile().getName());
      var dialog = new CustomPromptDialog(selectedText, fileExtension, previousUserPrompt);
      if (dialog.showAndGet()) {
        previousUserPrompt = dialog.getUserPrompt();
        SwingUtilities.invokeLater(() -> sendMessage(project, dialog.getFullPrompt()));
      }
    }
  }

  private String getFileExtension(String filename) {
    Pattern pattern = Pattern.compile("[^.]+$");
    Matcher matcher = pattern.matcher(filename);

    if (matcher.find()) {
      return matcher.group();
    }
    return null;
  }
}
