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
- `~` will move to the user's home directory
- `b` or `p` will move to the parent directory
- `c` will copy a file to another directory
- `d` will create a new directory with the given name
- `f` will create a new file with the given name
- `h` will display these tips (TODO)
- `m` will move a file to another directory
- `q` will end the program
- `r` will rename a file
- `u` will unzip a zip archive (TODO)
- `x` will PERMANENTLY delete a file WARNING (TODO)
- `z` will zip files to a zip archive (TODO)
- `1` will move to the subfolder at index 1 (or any number entered) given it is a folder

To clean up artifacts created by a maven build:

`mvn clean`

## Todo
- [x] Toggling hidden files
- [x] Changing directories
- [x] Creating files
- [ ] Deleting files
- [ ] Compressing and uncompressing files
- [x] Renaming files
- [x] Moving files
- [ ] Copying files
- [x] Creating folders
- [x] Displaying files in sorted order
- [ ] Unit tests
- [ ] Logging
- [ ] Cover edge causes causing program to crash
- [x] Implement a server to allow for (read-only) access