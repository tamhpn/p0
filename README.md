# FileMan

FileMan is a File Manager to view files and folders

## Usage
Compile:

`mvn compile`

Run:

`mvn exec:java`

will print the files and folders in the home directory ($HOME) to Standard Output.

The program will then wait for user input:
- `.` will toggle hidden folder view
- `h` or `b` will move to the parent directory
- `~` will move to the user's home directory
- `q` will end the program
- `1` will move to the subfolder at index 1 (or any number entered) given it is a folder

To clean up artifacts created by a maven build:

`mvn clean`

## Todo
- [x] Toggling hidden files
- [x] Changing directories
- [ ] Compressing files
- [ ] Renaming files
- [ ] Moving files
- [ ] Copying files
- [ ] Deleting files
- [ ] Creating files
- [ ] Opening files
- [x] Displaying files in sorted order
- [ ] Unit tests
- [ ] Logging
- [ ] Cover edge causes causing program to crash
- [x] Implement a server to allow for (read-only) access