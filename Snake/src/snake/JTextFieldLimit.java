package snake;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
//This class limits the number of characters in which can be entered into the JTextField
class JTextFieldLimit extends PlainDocument {

	//This integer called limit defines what the limit shall be
	private int limit;

	//The constructor which uses the parameter limit
	JTextFieldLimit(int limit) {
		//Invoking the super classes method PlainDocument, what this does is creates a plain text document, this is used with setDocument
		super();
		//the limit of this class is set to the limit specified
		this.limit = limit;
	}

	//Method insertString, what this method will do is allow for a maximum number of characters to be input.
	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		//If number of characters is less than the limit specified
		if ((getLength() + str.length()) <= limit) {
			//Inserts string if below
			super.insertString(offset, str, attr);
		}
	}
}