# Eisen
Per CH onderdeel kun je 0 of de aangegeven punten krijgen.

| ID   | Gedaan | Omschrijving                                                                                                                                                                                                                                                                         | Prio   | Punten | Competentie VT       |
| ---- | ---- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------ | ------ | -------------------- |
| CH00 | ✔ | Minimaal vier van onderstaande checks **moeten** zijn geïmplementeerd                                                                                                                                                                                                                | Must   | 0      | APP-2, APP-6, APP-7, |
| CH01 | ✔ | Controleer of er geen variabelen worden gebruikt die niet gedefinieerd zijn.                                                                                                                                                                                                         | Should | 5      |
| CH02 | ✔ | Controleer of de operanden van de operaties plus en min van gelijk type zijn. Je mag geen pixels bij percentages optellen bijvoorbeeld. Controleer dat bij vermenigvuldigen minimaal een operand een scalaire waarde is. Zo mag `20% * 3` en `4 * 5` wel, maar mag `2px * 3px` niet. | Should | 5      |
| CH03 | ✔ | Controleer of er geen kleuren worden gebruikt in operaties (plus, min en keer).                                                                                                                                                                                                      | Should | 5      |
| CH04 | ✔ | Controleer of bij declaraties het type van de value klopt met de property. Declaraties zoals `width: #ff0000` of `color: 12px` zijn natuurlijk onzin.                                                                                                                                | Should | 5      |
| CH05 | ✔ | Controleer of de conditie bij een if-statement van het type boolean is (zowel bij een variabele-referentie als een boolean literal)                                                                                                                                                  | Should | 5      |
| CH06 | ✔ | Controleer of variabelen enkel binnen hun scope gebruikt worden                                                                                                                                                                                                                      | Must   | 5      |                       |


| ID | Gedaan | Omschrijving | Punten |
| --- | --- | ---|---|
| 1 | --- | Implementeren van booleaanse expressies zoals 3<5, Value==5, !AdjustWidth (deze kunnen worden gebruikt in if statement) en && | |
| 2 | --- | Checken van booleaanse expressies. Bij > dat de waardes van hetzelfde type zijn en alleen 20% 20px 22. Hiernaast dat ! en &&  alleen op booleanse waardes (of expressies) kan | |
| 3 | --- | Line comments // en block comments  /*  */. Line comments worden omgezet naar block comments. Deze worden aan het einde generate | |
| 4 | --- | Controleer of er geen booleans worden gebruikt in de operaties (plus, min en keer). | 2

footnote voor 3: However, it’s strongly recommended that you avoid this method in your code. The double-slash commenting method is not standardized in CSS (whereas the /* ... */ method is), meaning that it’s not guaranteed to work on all browsers.
# Test voorbeelden

##CH01 en CH06

##CH02
    Plusslagen := 20px + 20px;
    Minfalen := 20px + 10%; 
    Keerhalen := 20% * 2;
    Keerfalen := 10px * 10px;

    ERROR: Add operation values must be off same type
    ERROR: One of the values of a multiply operation must be scalar

##CH03
    Plusmetkleuren := #808080 + #808080;

    ERROR: Don't use colours in operations

## Eigen 1

## Eigen 3
Start 11:07

## Eigen 4
    Keermetbool := TRUE + FALSE;

    Bool := TRUE;
    
    Test2 := Bool * Bool;
    
    Test3 := 2 * 2 * FALSE * FALSE;
    ERROR: Don't use booleans in operations
    ERROR: Don't use booleans in operations
    ERROR: Don't use booleans in operations
    ERROR: Don't use booleans in operations