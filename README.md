# FileMan

FileMan is a File Manager to view files and folders

## Usage
Compile:

`mvn compile`

Run:

`mvn exec:java -Dexec.mainClass="com.github.tamhpn.app.FileMan"`

will print the files and folders in the given directory to Standard Output. If no directory is given, the home directory is used ($HOME).

A command line argument can be given using:

`mvn exec:java -Dexec.mainClass="com.github.tamhpn.app.FileMan" -Dexec.args="arg0"`

The program will then wait for user input:
- `.` will toggle hidden folder view
- `h` will move to the parent directory
- `~` will move to the user's home directory
- `q` will end the program

To clean up artifacts created by a maven build:

`mvn clean`