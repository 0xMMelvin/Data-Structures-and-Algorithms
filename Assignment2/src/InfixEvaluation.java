
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This program evaluates and produces the value of a given arithmetic expression
 * using a Stack data structure.
 *
 * @author Michael Melvin
 */
public class InfixEvaluation {
    // TODO: check the name of the file
    /**
     * Main Method
     * @throws IOException 'sample_expression_in.txt' must exist.
     */
	public static void main(String[] args) throws IOException {
		// get the data from the file
		Scanner fileInput = new Scanner (new File("sample_expression_in.txt"));
		Scanner tokens;
		// create Stacks for the operands and operators
		Stack<Integer> operands = new LinkedStack<>();
		Stack<Character> operators = new LinkedStack<>();
		// variable to store the result of calculation
		int result;

		// loop through the file
		while (fileInput.hasNext()){
			tokens = new Scanner(fileInput.nextLine());
			while (tokens.hasNext()){
			    // if there is an integer, add it to the operand Stack
				if (tokens.hasNextInt()){
					int operand = tokens.nextInt();
					operands.push(operand);
				}
				else {
					String s = tokens.next();
					// skip '('
					if (s.toCharArray()[0] == '(') continue;
					// calculate expression and push it back on the operand Stack
					else if (s.toCharArray()[0] == ')') {
					    int second = operands.pop();
					    int first = operands.pop();
					    char op = operators.pop();
					    result = calculateExpression(first, second, op);
					    operands.push(result);
                    }
                    // push operators onto operators Stack
					else if (s.toCharArray()[0] == '+') operators.push(s.toCharArray()[0]);
					else if (s.toCharArray()[0] == '*') operators.push(s.toCharArray()[0]);
					else if (s.toCharArray()[0] == '-') operators.push(s.toCharArray()[0]);
					else if (s.toCharArray()[0] == '/') operators.push(s.toCharArray()[0]);
					else continue;
				}
			}
		}
		// display results of the calculation
        System.out.println(displayResults(operands));
	}

    /**
     * Returns the result of the expression.
     *
     * @param firstOperand  The first operand read from the file.
     * @param secondOperand The second operand read from the file.
     * @param operator      The operation in between firstOperand and secondOperand.
     * @return              The result of the expression.
     */
	private static int calculateExpression(int firstOperand, int secondOperand, char operator) {
	    // declare a variable to hold the result
	    int result;
	    // preform calculations based on the type of operator
	    if (operator == '+') result = firstOperand + secondOperand;
	    else if (operator == '-') result = firstOperand - secondOperand;
	    else if (operator == '*') result = firstOperand * secondOperand;
	    else result = firstOperand / secondOperand;
	    return result;
    }

    /**
     * Outputs the expression and the result of the expression.
     *
     * @param operands      The operands from the expression.
     * @return              The result of the expression.
     * @throws IOException  Throws error if file does not exist.
     */
    private static String displayResults(Stack<Integer> operands) throws IOException {
        // get the data from the file
        Scanner fileInput = new Scanner(new File("sample_expression_in.txt"));
        // declare a variable to store the result
        String result = "";
        // reverse the Stack so it outputs results in the order of the file
        operands = reverseStack(operands);
        // generate result String
        while(fileInput.hasNextLine()) {
            result += "The value of " + fileInput.nextLine() + " is " + operands.pop() + "\n";
        }
        return result;
    }

    /**
     * Returns the reverse of the passed in Stack.
     *
     * @param operands  The operands from the expression.
     * @return          The reversed Stack of operands.
     */
    private static Stack<Integer> reverseStack(Stack<Integer> operands) {
        // create a new stack to hold the reversed Stack
	    Stack<Integer> reversedStack = new LinkedStack<>();
	    // pop items off of the Stack until it is empty, storing them in the reversedStack
	    while (!operands.isEmpty()) {
	        reversedStack.push(operands.pop());
        }
        return reversedStack;
    }
}
