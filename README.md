# XCTU-Parser
Simple utility that can be used to parse raw data from XCTU into a CSV file.

## So what does it do?
It's simple, it scans the file line per line. If it sees a SENT entry with 10 hexadecimal numbers (_shown in orange_), it takes the timestamp (_shown in red_) and last 4 hexadecimal digits (_shown in green_) converted to decimal of the next line and adds it to the ouput file as an entry.

![alt text](https://github.com/McGillRocketTeam/XCTU-Parser/parsing.png "Elements parsed by the parser.")

## Notes
- It needs a filename (for the input data) parameter to run. If you run it in eclipse, you might want to specify the filename of your test data in your run configuration.
