Author: Jackson Duke
https://github.com/JacksonBDuke/TTS-DeckBuilder

This application comes "as-is". There are no guarantees of any kind when using this application. Use at your own risk.
YOU MUST HAVE JAVA INSTALLED TO USE THIS APPLICATION. For the most recent version of Java, please go to https://java.com/en/download/

This application is recommended for use with Tabletop Simulator http://berserk-games.com/tabletop-simulator/

RECENTLY ADDED FEATURES
SETTINGS: A button to show a settings window.

	MAIN WINDOW
		 ______________________________________________________________________________
		| TTS Deck Builder                                                        - □ X|
		|------------------------------------------------------------------------------|
		|                                [Card Counter]                                |
		|                               [Status Message]                               |
		|   ______________________   ______________________   ______________________   |
		|  |                      | |                      | |                      |  |
		|  |                      | |                      | |                      |  |
		|  |                      | |                      | |                      |  |
		|  |                      | |                      | |                      |  |
		|  |                      | |                      | |                      |  |
		|  |                      | |                      | |                      |  |
		|  |       SPACE  A       | |       SPACE  B       | |       SPACE  C       |  |
		|  |                      | |                      | |                      |  |
		|  |                      | |                      | |                      |  |
		|  |                      | |                      | |                      |  |
		|  |                      | |                      | |                      |  |
		|  |                      | |                      | |                      |  |
		|  |______________________| |______________________| |______________________|  |
		|       Previous Added             Last Added              Current Card        |
		|            _______________________________________________________           |
		|           |          |                     |             |        |          |
		|           | Settings | Grab from Clipboard | Add to Deck | Export |          |
		|___________|__________|_____________________|_____________|________|__________|

OVERVIEW
Card Counter: Displays the number of cards currently in memory. If the deck is empty it will display a message saying so.
SPACE A: Displays the second-to-last card added to deck.
SPACE B: Displays most recent card added to the deck.
SPACE C: Displays the card currently waiting to be added to the deck.
Grab from Clipboard: Reads any image currently in the system clipboard and displays the image in SPACE C.
Add to Deck: Saves the image from SPACE C to the deck, also changes images shown in SPACE A, SPACE B, and resets SPACE C to blank.
Export: Saves deck image to application directory.

	SETTINGS WINDOW
		 ____________________________________________
		| Settings                              - □ X|
		|--------------------------------------------|
		|              [Status Message]              |
		|     Canvas Width: [Resolution Variable]    |
		|    Canvas Height: [Resolution Variable]    |
		|       Card Width: [Resolution Variable]    |
		|      Card Height: [Resolution Variable]    |
		|     Card Padding: [Resolution Variable]    |
		|    ____________________________________    |
		|   |       |                   |        |   |
		|   | Apply | Restore  Defaults | Cancel |   |
		|___|_______|___________________|________|___|

INSTRUCTIONS
Place the application in a folder of your choosing. Please note that the application saves deck images in whichever folder the application is in.
Copy images to the system clipboard.
Use the "Grab from Clipboard" button to place the copied image into the application.
Press "Add to Deck" to confirm the choice of image.
Repeat until all cards needed are added.
Press "Export" to save the deck-image.