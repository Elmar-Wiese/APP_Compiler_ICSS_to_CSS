Per CH onderdeel kun je 0 of de aangegeven punten krijgen.

| ID   | Omschrijving                                                                                                                                                                                                                                                                         | Prio   | Punten | Competentie VT       |
| ---- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ------ | ------ | -------------------- |
| CH00 | Minimaal vier van onderstaande checks **moeten** zijn geïmplementeerd                                                                                                                                                                                                                | Must   | 0      | APP-2, APP-6, APP-7, |
| CH01 | Controleer of er geen variabelen worden gebruikt die niet gedefinieerd zijn.                                                                                                                                                                                                         | Should | 5      |
| CH02 | Controleer of de operanden van de operaties plus en min van gelijk type zijn. Je mag geen pixels bij percentages optellen bijvoorbeeld. Controleer dat bij vermenigvuldigen minimaal een operand een scalaire waarde is. Zo mag `20% * 3` en `4 * 5` wel, maar mag `2px * 3px` niet. | Should | 5      |
| CH03 | Controleer of er geen kleuren worden gebruikt in operaties (plus, min en keer).                                                                                                                                                                                                      | Should | 5      |
| CH04 | Controleer of bij declaraties het type van de value klopt met de property. Declaraties zoals `width: #ff0000` of `color: 12px` zijn natuurlijk onzin.                                                                                                                                | Should | 5      |
| CH05 | Controleer of de conditie bij een if-statement van het type boolean is (zowel bij een variabele-referentie als een boolean literal)                                                                                                                                                  | Should | 5      |
| CH06 | Controleer of variabelen enkel binnen hun scope gebruikt worden                                                                                                                                                                                                                      | Must   | 5      |


Ik kan in fase 0. CH04 doen.
|ID | Done |
|CH04|  |