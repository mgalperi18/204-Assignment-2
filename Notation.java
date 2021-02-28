
public class Notation {

	/**
	 * 
	 * @param <T>
	 * @param postfix
	 * @return
	 * @throws InvalidNotationFormatException
	 */
	public static <T> String convertPostfixToInfix(String postfix ) throws InvalidNotationFormatException{
		NotationStack<String> postStack = new NotationStack<String>(50);
		
		for(int i = 0; i < postfix.length(); i++) {
			switch(postfix.charAt(i)) {
			
			case '1' : case '2' :  case '3' : case '4' : case '5' : case '6': case '7': case '8': case '9' : case '0': {
				try {
					postStack.push(String.valueOf(postfix.charAt(i)));
				}
				catch(StackOverflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				break;
			}
			
			case '+': case '-': case '*': case '/': {
				try {
					String popString = postStack.pop();
					String popString2 = postStack.pop();
					String popFinal = "";
					popFinal = "(" + popFinal + popString2 + postfix.charAt(i) + popString + ")";
					
					postStack.push(popFinal);
					break;
				}
				catch(StackOverflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				catch(StackUnderflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				
			}
			
			}//switch
		}
		
		return postStack.toString();		
		
	}
	
	/**
	 * 
	 * @param <T>
	 * @param infix
	 * @return
	 * @throws InvalidNotationFormatException
	 */
	public static <T> String convertInfixToPostfix(String infix ) throws InvalidNotationFormatException{
		NotationStack<Character> inStack = new NotationStack<Character>(50);
		NotationQueue<Character> inQueue = new NotationQueue<Character>(50);
		
		char [] infixC = infix.toCharArray();
		for(char c: infixC) {
			switch(c) {
			
			case '1' : case '2' :  case '3' : case '4' : case '5' : case '6': case '7': case '8': case '9' : case '0': {
				try {
					inQueue.enqueue(c);
				}
				catch(QueueOverflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				break;
			}
			
			case '(':{
				try {
					inStack.push(c);
				}
				catch(StackOverflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				break;
			}
			
			case '+': case '-': case '*': case '/': {
				try {
					if(inStack.top() >= c) {
						inStack.pop();
					}
				}
				catch(StackUnderflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				
				try {
					inStack.push(c);
				}
				catch(StackOverflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				break;
			}
			
			case ')':{
				try {
					while(inStack.top() != '(') {
					try {
						inQueue.enqueue(inStack.top());
					}
					catch(QueueOverflowException e) {
						throw new InvalidNotationFormatException("Notation error occured");
					}
					catch(StackUnderflowException e) {
						throw new InvalidNotationFormatException("Notation error occured");
					}
					
					try {
						inStack.pop();
					}
					catch(StackUnderflowException e) {
						throw new InvalidNotationFormatException("Notation error occured");
					}
					
					}//while
				}
				catch(StackUnderflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				
				try {
					inStack.pop();
				}
				catch(StackUnderflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				break;		
			}
			
			}//switch
		}
		
		return inQueue.toString();
	}
		
	/**
	 * 
	 * @param postfixExpr
	 * @return
	 * @throws InvalidNotationFormatException
	 */
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException{
		NotationStack<Double> exprStack = new NotationStack<Double>(50);
		
		for(int i = 0; i < postfixExpr.length(); i++) {
			switch(postfixExpr.charAt(i)) {
			
			case '1' : case '2' :  case '3' : case '4' : case '5' : case '6': case '7': case '8': case '9' : case '0': {
				try {
					exprStack.push(Double.parseDouble(String.valueOf(postfixExpr.charAt(i))));
				}
				catch(StackOverflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				break;
			}
			
			case '+': case '-': case '*': case '/': {
				try {
					Double popS = exprStack.pop();
					Double popSS = exprStack.pop();
					Double popFinal = 0.0;
					
					if(postfixExpr.charAt(i) == '+') {
						popFinal = popSS + popS;
					}
					
					else if(postfixExpr.charAt(i) == '-') {
						popFinal = popSS - popS;
					}
					
					else if(postfixExpr.charAt(i) == '*') {
						popFinal = popSS * popS;
					}
					
					else if(postfixExpr.charAt(i) == '/') {
						popFinal = popSS / popS;
					}
					
					exprStack.push(popFinal);
				}
				catch(StackUnderflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				catch(StackOverflowException e) {
					throw new InvalidNotationFormatException("Notation error occured");
				}
				break;
			}
			
			}//switch
		}
		
		try {
			Double d = exprStack.top();
			return d;
		}
		catch(StackUnderflowException e) {
			throw new InvalidNotationFormatException("Notation error occured");
		}
	}
	
}
	
