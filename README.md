# XCTU-Parser
Simple utility that can be used to parse raw data from XCTU into a CSV file.

## So what does it do?
It's simple, it scans the file line per line. If it sees a SENT entry with 10 hexadecimal numbers (_shown in orange_), it takes the timestamp (_shown in red_) and last 4 hexadecimal digits (_shown in green_) converted to decimal of the next line and adds it to the ouput file as an entry.

If the data is not complete (_i.e. 2 digits in one packet and 4 in the next one_), the parser will try to finish it by looking at the next received message.

![alt text](https://github.com/McGillRocketTeam/XCTU-Parser/blob/master/parsing.png "Elements parsed by the parser.")

## Notes
- It needs a filename (for the input data) parameter to run. If you run it in eclipse, you might want to specify the filename of your test data in your run configuration.
- Don't forget to comment out the debugging print statements if you are going to parse a large file. Well, at least if you don't want to fill your console with redundant messages.

## Thanks
- **Ibrahim Miraj**: For his initial work on the parser logic and for providing detailed information on the logfiles' structure.
