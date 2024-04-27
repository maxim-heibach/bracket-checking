// Einsendeaufgabencode: B-GOPB01-XX2-K04
// Bearbeiter: Maxim Heibach
// Matrikelnummer: 909442
//
// Aufgabe 3, Klammerprüfung (Java):
// Das Tool überprüft einen übergebenen Quelltext auf eine korrekte Klammerung. Klammern in Kommentaren werden
// nicht berücksichtigt und müssten vor Verwendung für ein verlässliches Ergebnis entfernt werden. 
//
// Anleitung:
// In Konsole Klammerpruefung.java mit javac kompilieren und mit Hilfe von 
// java Klammerpruefung Klammerpruefung.java aufrufen. 
// Alternativ kann jede andere Datei mit Hilfe von
// java Klammerpruefung "Pfad zu Datei, z.B. C:\Users\..." geprüft werden. 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Klammerpruefung {

    // Übergebene Datei in String umwandeln
    private static String readFile(String filename) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("Fehler beim Lesen der Datei: " + ex.getMessage());
            System.exit(1);
        }
        return builder.toString();
    }

    // Klammerprüfung
    private static boolean checkBracketing(String sourceText) {
        Stack<Character> bracketStack = new Stack<>();
        Stack<Character> quoteStack = new Stack<>();
        boolean inString = false;
        char lastChar = '\0';
        char beforeLastChar = '\0';

        for (int i = 0; i < sourceText.length(); i++) {
            char currentChar = sourceText.charAt(i); 

            // Aufgabenteil b) Klammern in Charactern oder Strings
            // Legt fest, ob nachfolgende Character sich innerhalb eines Strings oder Characters befinden 
            // oder nicht. 
            if (isQuote(currentChar)) {
                if (quoteStack.isEmpty()) {
                    quoteStack.push(currentChar);
                    inString = true;
                } else {
                    char previousQuoteChar = quoteStack.pop();
                    if (isMatchingQuote(currentChar, previousQuoteChar, lastChar, beforeLastChar)) {
                        inString = false;
                    } else {
                        quoteStack.push(previousQuoteChar);
                    }
                }
            }
            
            // Prüft Klammern nur, wenn außerhalb eines Strings.
            if (isOpeningBracket(currentChar) && inString == false) { 
                bracketStack.push(currentChar); 
            } else if (isClosingBracket(currentChar) && inString == false) {
                if (bracketStack.isEmpty()) {
                    return false; 
                }
                char previousBracketChar = bracketStack.pop(); 
                if (!isMatchingBracket(currentChar, previousBracketChar)) {
                    return false; 
                }
            }
            beforeLastChar = lastChar;
            lastChar = currentChar;
        } 
        return bracketStack.isEmpty(); // true wenn bracketStack leer ist -> jede öffnende Klammer hat eine Schließende
    }

    public static boolean isQuote(char currentChar) {
        return currentChar == '"' || currentChar == '\'';
    }
    
    private static boolean isOpeningBracket(char currentChar) {
        return currentChar == '(' || currentChar == '[' || currentChar == '{'; 
    }
    
    private static boolean isClosingBracket(char currentChar) {
        return currentChar == ')' || currentChar == ']' || currentChar == '}'; 
    }

    // Die Methode isMatchingBracket prüft ob die aktuelle, schließende Klammer zur auf dem Stack "bracketStack"
    // liegenden, öffnenden Klammer passt.
    private static boolean isMatchingBracket(char currentChar, char previousBracketChar) {
        boolean result = true;
        switch(currentChar) {
            case ')': 
                if (previousBracketChar != '(') { result = false; } 
                break;
            case ']': 
                if (previousBracketChar != '[') { result = false; } 
                break;
            case '}': 
                if (previousBracketChar != '{') { result = false; } 
                break;
        }
        return result;
    }
    
    // Die Methode isMatchingQuote prüft, ob das aktuelle Anführungszeichen mit dem Anführungszeichen auf dem 
    // Stack "quoteStack" übereinstimmt und keine Escape-Sequenz verwendet wurde.
    private static boolean isMatchingQuote(char currentChar, char previousQuoteChar, char lastChar, char beforeLastChar) {
        boolean result = false;
        switch(currentChar) {
            case '"': 
                if (previousQuoteChar == '"'  && lastChar == '\\' && beforeLastChar == '\\') { 
                    result = true; 
                } else if (previousQuoteChar == '"' && lastChar != '\\') {
                    result = true;
                }
                break;      
            case '\'': 
                if (previousQuoteChar == '\''  && lastChar == '\\' && beforeLastChar == '\\') { 
                    result = true; 
                } else if (previousQuoteChar == '\'' && lastChar != '\\') {
                    result = true;
                }
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        String sourceText = readFile(args[0]);
        if (checkBracketing(sourceText)) { // wenn true
            System.out.println("Der Quelltext ist korrekt geklammert."); 
        } else {
            System.out.println("Der Quelltext ist nicht korrekt geklammert."); 
        }
    }
}