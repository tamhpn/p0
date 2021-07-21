# FileMan

FileMan is a File Manager to view files and folders

## Usage
Compile:

`javac FileManager.java`

Run:

`java FileManager (directory path)`

will print the files and folders in the given directory to Standard Output. If no directory is given, the home directory is used ($HOME).

The program will then wait for user input:
- `.` will toggle hidden folder view
- `h` will move to the parent directory
- `~` will move to the user's home directory