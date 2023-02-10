# Word Square challenge
This challenge is to produce a word square. In a word square you are given a grid with letters arranged that spell valid English language words when you read from left to right or from top to bottom, with the requirement that the words you spell in each column and row of the same number are the same word.
For example, the first row and the first column spell the same word, the second row and second column do, too, and so on. The challenge is that in arranging those letters that you spell valid words that meet those requirements.

For example, the input "4" and "eeeeddoonnnsssrv" would produce the solution:

rose
oven
send
ends


## Running the application

To run, use:

mvn spring-boot:run

You will then be prompted to enter a size for your word square (e.g. 4 for a 4x4 square) and the letters.
