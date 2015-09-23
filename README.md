I have started this application as to fill in the need of having a piece of paper to doodle on while having a flash of idea and frantically searching for a piece of paper, napkin and pen to explain it to the person in front of you enjoying his food/coffee on a trouble free evening.
Also, since the various apps of this kind in the google play store ask for stupid permissions like location, call details and stuff... this seemed to me the best way to find my own way to the napkin and pen.

Following are few ideas I currently have. I have also tried to catogriese them into various version goal.
This is my first attempt at any opensource application, so please be patient with me.

##What all is available already?
White Canvas
	- Only white background, no option to change background color

Immersive Mode
	- Works in true fullscreen, only available for 4.2.2 and above

Black Pen
	- Only a Black pen with predefined stroke attributes.

Clear the screen for new doodling.
	- Pressing the back button clears the screen.
	- Long press the back button to exit application.

Observe Mode/Edit Mode
	- Pressing the vol-down button toggles between the two modes.
	- Currently Observe Mode does nothing but to prohibit the user from making any changes (accidental or otherwise).

Saving the image?
	- Damn you!! just take a screenshot for now.

##Various other Ideas I plan to implement (not in specific order) 
Modes of usage, just like vim. Modes should be interchangable by using hardware keys (vol-up or vol-down)
	"paint mode" where you can "edit" the drawing.
	"observe mode" where you can zoom, shrink, move to different parts of the painting.
	"Visual mode"to select, cut, copy, paste and do similar stuff.
Add eraser tool
Add undo tool
basic drawable shapes like line, rectangle, circles etc.
Selectable draw colour.
Selectable background colour.
Saving the current "flash of idea" as an image.
Extendable canvas
Some drawing tools like brush, airbrush etc.

## Developer
### How to compile the source code?
Before starting, you need the following prerequisites:
-   Android SDK: http://developer.android.com/sdk/index.html
-   Apache ant: http://ant.apache.org/

    (Use `sudo apt-get install ant` on a proper OS)

-   Git: http://git-scm.com/

    (Use `sudo apt-get install git-core` on a proper OS)

Get the source code:

    git clone https://github.com/flide/TouchBoard.git && cd TouchBoard

You're ready to compile it!

    ant debug
