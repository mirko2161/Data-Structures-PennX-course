
import java.util.Queue;
import java.util.Stack;

/**
 * Note: Homework tests require that the class being tested is in the default package, and also that
 * the test files and .jar are in the root directory.
 *
 * In this assignment, you will use the stack and queue implementations from the Java Collections
 * API in order to build a program that determines whether an HTML page is well formatted.
 *
 * In theory (though not often in practice), well formatted HTML requires that the tags are
 * “balanced,” i.e. that open tags are matched by their corresponding close tag in the correct
 * order.
 *
 * For instance, if we ignore whitespace and the text between the tags, we end up with this:
 *
 * <html><head><title></title></head><body><b></b></body></html>
 *
 * In this assignment, you will write a method that determines whether an HTML file is well
 * formatted using a stack. Every time your code encounters an open tag, it should push it onto the
 * stack; when it encounters a close tag, it should pop the tag off the top of the stack, and if
 * they don’t match, then you’ll know the file is not well formatted.
 *
 * In HtmlValidator.java, implement the isValidHtml method. isValidHtml should take as input a Queue
 * of HtmlTags and return a Stack of HtmlTags that verifies the correctness of the tag structure,
 * according to the specification described below.
 *
 * The method should be implemented as follows:
 *
 * If the HTML file is well formatted, the method should return an empty Stack.
 *
 * If the HTML file is not well formatted, the method should return the Stack in its current state
 * (i.e., with its current values) at the time the code determined that the tags were not balanced.
 *
 * Additional examples and edge cases available at the assignment page.
 *
 * Keep in mind that that your HtmlValidator.isValidHtml method should only use methods in the Queue
 * interface, even though the Queue is implemented using a LinkedList.
 *
 * It is okay if your isValidHtml method modifies the contents of the Queue that is passed as input,
 * e.g. by removing elements.
 *
 * SD2x Homework #2 Implement the method below according to the specification in the assignment
 * description. Please be sure not to change the method signature!
 */
public class HtmlValidator {

    public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
        Stack<HtmlTag> stack = new Stack<>();

        for (HtmlTag tag : tags) {
            if (tag.isOpenTag()) {
                stack.push(tag);
            } else if (tag.isSelfClosing()) {
                // self-closing and should not be placed on the Stack
            } else {
                if (!stack.empty() && stack.peek().matches(tag)) {
                    stack.pop(); // tag correctly closed
                } else {
                    if (stack.empty()) { // HTML file containing only closing tag
                        return null;
                    }
                    break; // HTML file not well formatted - return current stack
                }
            }
        } // if all tags correct, an empty stack is returned
        return stack;
    }

}
