# FileMan

FileMan is a File Manager to view files and folders

## Usage
Compile:

`mvn compile`

Run:

`mvn exec:java -Dexec.mainClass="FileMan"`

will print the files and folders in the home directory ($HOME) to Standard Output.

The program will then wait for user input:
- `.` will toggle hidden folder view
- `h` will move to the parent directory
- `~` will move to the user's home directory
- `q` will end the program
- `1` will move to the subfolder at index 1 (or any number entered) given it is a folder

To clean up artifacts created by a maven build:

`mvn clean`
