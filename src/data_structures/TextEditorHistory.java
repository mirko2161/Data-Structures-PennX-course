package data_structures;

import java.util.Stack;

/**
 * A simple use case of a stack - the undo functionality of a text editor.
 *
 * @author mirko
 */
public class TextEditorHistory {

    protected Stack<String> history;

    public TextEditorHistory() {
        this.history = new Stack<>();
    }

    public void addToHistory(String currentVersion) {
        history.push(currentVersion);
    }

    public boolean canUndo() {
        return !history.empty();
    }

    public String undo() {
        if (!canUndo()) {
            return null;
        }
        return history.pop();
    }

}
