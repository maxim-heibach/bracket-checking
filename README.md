# Aufgabe 3: Klammerprüfung (Java)

Einsendeaufgabencode: B-GOPB01-XX2-K04  
Bearbeiter: Maxim Heibach  
Matrikelnummer: 909442

## Aufgabe
Teilaufgaben:
1. In dieser Aufgabe sollen Sie ein Programm implementieren, das einen übergebenen Quelltext auf eine korrekte Klammerung überprüft.  
Wenn der Quelltext korrekt geklammert ist, soll die Ausgabe lauten:  
Der Quelltext ist korrekt geklammert  
Andernfalls geben Sie aus:  
Der Quelltext ist nicht korrekt geklammert  

    Sie können zur Lösung der Aufgabe den gesamten Dateiinhalt zunächst in einen String einlesen. Gehen Sie dann wie folgt vor: Iterieren Sie zeichenweise über den String. Wenn Sie auf eine öffnende Klammer, d. h. eines der Zeichen (, { oder [, treffen, legen Sie die Klammer auf einen Stack. Wenn Sie dagegen auf eine schließende Klammer treffen, d. h. eines der
Zeichen ), } oder ], überprüfen Sie, ob oben auf dem Stack das entsprechende Gegenstück liegt. Wenn ja, entfernen Sie das oberste Zeichen vom Stack. Andernfalls brechen Sie ab – der Quelltext ist in diesem Fall nicht korrekt geklammert. Der Quelltext ist dann korrekt geklammert, wenn der Stack am Ende leer ist. Testen Sie Ihre Lösung an einigen einfachen Quelltexten, z. B. den Codebeispielen aus Kapitel 1 in JAPR01.

2. Klammern können in einem Quelltext auch als Character (z. B. '(') oder in einem String vorkommen. Diese Klammern müssen für eine robustere Lösung von der Prüfung auf korrekte Klammerung eines Quelltexts ausgeschlossen werden (Klammern in Kommentaren müssen Sie nicht berücksichtigen). Ergänzen Sie Ihr Programm aus Aufgabenteil 1, sodass Sie auch den Quelltext Ihres Programms überprüfen können: 
    ```sh
    java Klammerpruefung Klammerpruefung.java
    ```
    
    Hinweis: Prüfen Sie, ob das aktuelle Zeichen ein einfaches oder doppeltes Anführungszeichen ist (beim Vergleich auf einfaches oder doppeltes Anführungszeichen müssen Sie eine
Escape-Sequenz verwenden, z. B. ' \ ' '). Wenn ja, ignorieren Sie alle Klammern, bis Sie wieder auf ein einfaches oder doppeltes Anführungszeichen stoßen. 