package braille;

import java.security.DrbgParameters.NextBytes;
import java.util.ArrayList;
import java.util.Queue;

/**
 * Contains methods to translate Braille to English and English to Braille using
 * a BST.
 * Reads encodings, adds characters, and traverses tree to find encodings.
 * 
 * @author Seth Kelley
 * @author Kal Pandit
 */
public class BrailleTranslator {

    private TreeNode treeRoot;

    /**
     * Default constructor, sets symbols to an empty ArrayList
     */
    public BrailleTranslator() {
        treeRoot = null;
    }

    /**
     * Reads encodings from an input file as follows:
     * - One line has the number of characters
     * - n lines with character (as char) and encoding (as string) space-separated
     * USE StdIn.readChar() to read character and StdIn.readLine() after reading
     * encoding
     * 
     * @param inputFile the input file name
     */
    public void createSymbolTree(String inputFile) {

        /* PROVIDED, DO NOT EDIT */

        StdIn.setFile(inputFile);
        int numberOfChars = Integer.parseInt(StdIn.readLine());
        for (int i = 0; i < numberOfChars; i++) {
            Symbol s = readSingleEncoding();
            addCharacter(s);
        }
    }

    /**
     * Reads one line from an input file and returns its corresponding
     * Symbol object
     * 
     * ONE line has a character and its encoding (space separated)
     * 
     * @return the symbol object
     */
    public Symbol readSingleEncoding() {
        char character = StdIn.readChar();
        String encoding = StdIn.readString();
        StdIn.readLine();

        Symbol symbol = new Symbol(character, encoding);
        // Symbol symbol = new Symbol();
        // symbol.setCharacter(character);
        // symbol.setEncoding(encoding);

        return symbol;
    }

    /**
     * Adds a character into the BST rooted at treeRoot.
     * Traces encoding path (0 = left, 1 = right), starting with an empty root.
     * Last digit of encoding indicates position (left or right) of character within
     * parent.
     * 
     * @param newSymbol the new symbol object to add
     */
    public void addCharacter(Symbol newSymbol) {
        String newEncoding = newSymbol.getEncoding();
        char[] partialEncodingArray = newEncoding.toCharArray();
        
        if (treeRoot == null){
            Symbol treeRootSymbol = new Symbol(""); //Only has string parameter (no character at this level)
            treeRoot = new TreeNode(treeRootSymbol, null, null);
        }

        Symbol firstSymbol = new Symbol(Character.toString(partialEncodingArray[0]));
        TreeNode firstTreeNode = new TreeNode(firstSymbol, null, null);

        if (Character.toString(partialEncodingArray[0]).equals("L")) {
            if (treeRoot.getLeft() == null){
                treeRoot.setLeft(firstTreeNode);
            }
            else{
                firstTreeNode = treeRoot.getLeft();
            }
        } else {
            if (Character.toString(partialEncodingArray[0]).equals("R")) {
                if (treeRoot.getRight() == null){
                    treeRoot.setRight(firstTreeNode);
                }
                else{
                    firstTreeNode = treeRoot.getRight();
                }
            }
        }

        Symbol secondSymbol = new Symbol(firstSymbol.getEncoding() + Character.toString(partialEncodingArray[1]));
        TreeNode secondTreeNode = new TreeNode(secondSymbol, null, null);

        if (Character.toString(partialEncodingArray[1]).equals("L")) {
            if (firstTreeNode.getLeft() == null){
                firstTreeNode.setLeft(secondTreeNode);
            }
            else{
                secondTreeNode = firstTreeNode.getLeft();
            }
        } else {
            if (Character.toString(partialEncodingArray[1]).equals("R")) {
                if (firstTreeNode.getRight() == null){
                    firstTreeNode.setRight(secondTreeNode);
                }
                else{
                    secondTreeNode = firstTreeNode.getRight();
                }
            }
        }

        Symbol thirdSymbol = new Symbol(secondSymbol.getEncoding() + Character.toString(partialEncodingArray[2]));
        TreeNode thirdTreeNode = new TreeNode(thirdSymbol, null, null);

        if (Character.toString(partialEncodingArray[2]).equals("L")) {
            if (secondTreeNode.getLeft() == null){
                secondTreeNode.setLeft(thirdTreeNode);
            }
            else{
                thirdTreeNode = secondTreeNode.getLeft();
            }
        } else {
            if (Character.toString(partialEncodingArray[2]).equals("R")) {
                if (secondTreeNode.getRight() == null){
                    secondTreeNode.setRight(thirdTreeNode);
                }
                else{
                    thirdTreeNode = secondTreeNode.getRight();
                }
            }
        }

        Symbol fourthSymbol = new Symbol(thirdSymbol.getEncoding() + Character.toString(partialEncodingArray[3]));
        TreeNode fourthTreeNode = new TreeNode(fourthSymbol, null, null);

        if (Character.toString(partialEncodingArray[3]).equals("L")) {
            if (thirdTreeNode.getLeft() == null){
                thirdTreeNode.setLeft(fourthTreeNode);
            }
            else{
                fourthTreeNode = thirdTreeNode.getLeft();
            }
        } else {
            if (Character.toString(partialEncodingArray[3]).equals("R")) {
                if (thirdTreeNode.getRight() == null){
                    thirdTreeNode.setRight(fourthTreeNode);
                }
                else{
                    fourthTreeNode = thirdTreeNode.getRight();
                }
            }
        }

        Symbol fifthSymbol = new Symbol(fourthSymbol.getEncoding() + Character.toString(partialEncodingArray[4]));
        TreeNode fifthTreeNode = new TreeNode(fifthSymbol, null, null);

        if (Character.toString(partialEncodingArray[4]).equals("L")) {
            if (fourthTreeNode.getLeft() == null){
                fourthTreeNode.setLeft(fifthTreeNode);
            }
            else{
                fifthTreeNode = fourthTreeNode.getLeft();
            }
        } else {
            if (Character.toString(partialEncodingArray[4]).equals("R")) {
                if (fourthTreeNode.getRight() == null){
                    fourthTreeNode.setRight(fifthTreeNode);
                }
                else{
                    fifthTreeNode = fourthTreeNode.getRight();
                }
            }
        }

        TreeNode lastTreeNode = new TreeNode(newSymbol, null, null);

        if (Character.toString(partialEncodingArray[5]).equals("L")) {
            if (fifthTreeNode.getLeft() == null){
                fifthTreeNode.setLeft(lastTreeNode);
            }
            else{
                lastTreeNode = fifthTreeNode.getLeft();
            }
        } else {
            if (Character.toString(partialEncodingArray[5]).equals("R")) {
                if (fifthTreeNode.getRight() == null){
                    fifthTreeNode.setRight(lastTreeNode);
                }
                else{
                    lastTreeNode = fifthTreeNode.getRight();
                }
            }
        }

    }

    /**
     * Given a sequence of characters, traverse the tree based on the characters
     * to find the TreeNode it leads to
     * 
     * @param encoding Sequence of braille (Ls and Rs)
     * @return Returns the TreeNode of where the characters lead to, or null if
     *         there is no path
     */
    public TreeNode getSymbolNode(String encoding) {        
        TreeNode currentNode = treeRoot;
        //TreeNode nextNode = null;

        int i = 0;
        while (i < encoding.length()){
            //TreeNode nextNode = new TreeNode(null, null, null);
            //String nextNodeEncoding = treeRoot.getSymbol().getEncoding() + encoding.charAt(i);
            
            if (Character.toString(encoding.charAt(i)).equals("R")){
                if (currentNode.getRight() == null){
                    //Symbol nextNodeSymbol = new Symbol(nextNodeEncoding);
                    //nextNode = new TreeNode(nextNodeSymbol, null, null);
                    //currentNode.setRight(nextNode);
                    return null;
                }
                else{
                    currentNode = currentNode.getRight();
                    //nextNode = currentNode.getRight();
                }
            }
            else{
                if (Character.toString(encoding.charAt(i)).equals("L")){
                    if (currentNode.getLeft() == null){
                        //Symbol nextNodeSymbol = new Symbol(nextNodeEncoding);
                        //nextNode = new TreeNode(nextNodeSymbol, null, null);
                        //currentNode.setRight(nextNode);
                        return null;
                    }
                    else{
                        currentNode = currentNode.getLeft();
                        //nextNode = currentNode.getLeft();
                    }
                }

            }

            /*if (nextNode == null){
                return null;
            }*/
            
            //currentNode = nextNode;

            i++;
        }

        return currentNode;
    }

    /**
     * Given a character to look for in the tree will return the encoding of the
     * character
     * 
     * @param character The character that is to be looked for in the tree
     * @return Returns the String encoding of the character
     */
    private String treeTraversal(char character, TreeNode node){

        if (node == null){
            return null;
        }

        else
        {
            if (node.getSymbol() != null && node.getSymbol().getCharacter() == character){
                return node.getSymbol().getEncoding();
            }
            
            if (treeTraversal(character, node.getLeft()) != null){
                return treeTraversal(character, node.getLeft());
            }
        }

        return treeTraversal(character, node.getRight());

    }

    public String findBrailleEncoding(char character) {

        if (treeRoot == null){
            return null;
        }        

        return treeTraversal(character, treeRoot);
    }

    /**
     * Given a prefix to a Braille encoding, return an ArrayList of all encodings
     * that start with
     * that prefix
     * 
     * @param start the prefix to search for
     * @return all Symbol nodes which have encodings starting with the given prefix
     */
        private void preOrderHelper(TreeNode currentNode, ArrayList<Symbol> list){

        if (currentNode == null){
            return;
        }

        if (currentNode.getSymbol().getCharacter() != Character.MIN_VALUE){
            list.add(currentNode.getSymbol());
        }

        preOrderHelper(currentNode.getLeft(), list);
        preOrderHelper(currentNode.getRight(), list);

    }
    
     public ArrayList<Symbol> encodingsStartWith(String start) {
        
        ArrayList<Symbol> symbolList = new ArrayList<>();
        TreeNode startNode = getSymbolNode(start);
        preOrderHelper(startNode, symbolList);
        return symbolList;

    }

    /**
     * Reads an input file and processes encodings six chars at a time.
     * Then, calls getSymbolNode on each six char chunk to get the
     * character.
     * 
     * Return the result of all translations, as a String.
     * 
     * @param input the input file
     * @return the translated output of the Braille input
     */
    public String translateBraille(String input) {
        StdIn.setFile(input);

        String translation = "";

        String output = StdIn.readString();

        int i = 0;
        int j = 6;

        while (i <= output.length() && j <= output.length()){
            String partialEncoding = output.substring(i, j);
            TreeNode partialEncodingNode = getSymbolNode(partialEncoding);
            char partialEncodingNodeCharacter = partialEncodingNode.getSymbol().getCharacter();

            translation += partialEncodingNodeCharacter;
            i += 6;
            j += 6;
        }

        return translation;
    }

    /**
     * Given a character, delete it from the tree and delete any encodings not
     * attached to a character (ie. no children).
     * 
     * @param symbol the symbol to delete
     */
    public void deleteSymbol(char symbol) {
        String correspondingEncoding = findBrailleEncoding(symbol);

        TreeNode targetNode = getSymbolNode(correspondingEncoding);

        String parentEncoding = correspondingEncoding.substring(0,correspondingEncoding.length()-1);
        
        TreeNode parentNode = getSymbolNode(parentEncoding);


        if (treeRoot.getSymbol().getCharacter() == symbol){
            treeRoot = null;
        }

        if (parentNode.getLeft() == targetNode){
            parentNode.setLeft(null);
        }

        else{
            if (parentNode.getRight() == targetNode){
                parentNode.setRight(null);
            }
        }

        while (parentEncoding.length() > 0){
            TreeNode currentNode = getSymbolNode(parentEncoding);

            if (currentNode.getLeft() == null && currentNode.getRight() == null){
                String currentEncoding = parentEncoding.substring(0, parentEncoding.length()-1);
                TreeNode newParent = getSymbolNode(currentEncoding);

                if (newParent.getLeft() == currentNode){
                    newParent.setLeft(null);
                }
                else{
                    if (newParent.getRight() == currentNode){
                        newParent.setRight(null);
                    }
                }
            }
            parentEncoding = parentEncoding.substring(0, parentEncoding.length()-1);
        }

    }

    public TreeNode getTreeRoot() {
        return this.treeRoot;
    }

    public void setTreeRoot(TreeNode treeRoot) {
        this.treeRoot = treeRoot;
    }

    public void printTree() {
        printTree(treeRoot, "", false, true);
    }

    private void printTree(TreeNode n, String indent, boolean isRight, boolean isRoot) {
        StdOut.print(indent);

        // Print out either a right connection or a left connection
        if (!isRoot)
            StdOut.print(isRight ? "|+R- " : "--L- ");

        // If we're at the root, we don't want a 1 or 0
        else
            StdOut.print("+--- ");

        if (n == null) {
            StdOut.println("null");
            return;
        }
        // If we have an associated character print it too
        if (n.getSymbol() != null && n.getSymbol().hasCharacter()) {
            StdOut.print(n.getSymbol().getCharacter() + " -> ");
            StdOut.print(n.getSymbol().getEncoding());
        } else if (n.getSymbol() != null) {
            StdOut.print(n.getSymbol().getEncoding() + " ");
            if (n.getSymbol().getEncoding().equals("")) {
                StdOut.print("\"\" ");
            }
        }
        StdOut.println();

        // If no more children we're done
        if (n.getSymbol() != null && n.getLeft() == null && n.getRight() == null)
            return;

        // Add to the indent based on whether we're branching left or right
        indent += isRight ? "|    " : "     ";

        printTree(n.getRight(), indent, true, false);
        printTree(n.getLeft(), indent, false, false);
    }

}
