# FileMan

FileMan is a File Manager to view files and folders

## Usage
Compile:

`mvn compile`

Run:

`mvn exec:java`

will print the files and folders in the home directory ($HOME) to Standard Output.

The user can then input a command:
- `.` will toggle hidden files
- `~` will move to the user's home directory
- `b` or `p` will move to the parent directory
- `c` will copy a file to another directory
- `d` will create a new directory
- `f` will create a new file
- `h` will display these input commands
- `m` will move a file to another directory
- `q` will end the program
- `r` will rename a file
- `u` will unzip a zip archive (TODO)
- `x` will WARNING: PERMANENTLY delete a file
- `z` will zip files to a zip archive (TODO)
- `1` will move to the subdirectory at index 1 (or any number entered) given it is a directory

An optional command line argument can be given to view (READ-ONLY) a user's directories on a web server at http://localhost:8080

`mvn exec:java -Dexec.args=-s`

To clean up artifacts created by a maven build:

`mvn clean`

## Logs
Log4j logs are saved to logs/fileman.log

## Todo
- [x] Toggling hidden files
- [x] Changing directories
- [x] Creating files
- [x] Deleting files
- [ ] Compressing and uncompressing files
- [x] Renaming files
- [x] Moving files
- [x] Copying files
- [x] Creating folders
- [x] Displaying files in sorted order
- [x] Implement a server to allow for (read-only) access
- [ ] Unit tests
- [x] Logging
- [ ] Cover edge causes causing program to crash
