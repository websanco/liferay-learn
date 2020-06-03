import difflib
import glob
import os
import re
import shutil
import sys
from pprint import pprint
from pathlib import Path
# pip install inquirer
import inquirer

if __name__ == "__main__":

    if (len(sys.argv) < 3):
        pprint("Usage: migrate.py oldName newName\nIMPORTANT: Run this script in the folder containing the article to rename.")
        sys.exit()

    oldName = sys.argv[1]
    newName = sys.argv[2]

    dest_folder = "."
    if (len(sys.argv)) > 2:
        dest_folder = sys.argv[2]

    # Find article and dir names matching the pattern passed, then replace the
    # text passed in the first arg with the text from the second arg.

    types = ('*.md', '*.rst')
    files_grabbed = []
    for filetype in types:
        files_grabbed.extend(glob.glob("**/" + filetype, recursive=True))

    matchingfiles = []

    for filename in files_grabbed:

        f = open(filename, "r")
        content = f.read()
        f.close()

        # for each line of each file, look for the matching oldName arg,.
        # i think i still need to rewrite the line, see jim's code for
        # inspiration
        if re.search(oldName,content):

            matchingfiles.append(filename)

    choices = [
        inquirer.Checkbox('files',
                          message="De-select the files you wish to skip (spacebar). For all selected, the string '" + oldName + "' will be replaced with '" + newName + "', and the file will be re-written:",
                          choices=matchingfiles,
                          default=matchingfiles),
    ]

    filtermatches = inquirer.prompt(choices).values()

    matcheslist = list(filtermatches)

            # moar logic: test for absolute links, like if there's a http
            # don't replace because it's not a relative link (elasticesearch
            # and github links, for example, must not be changed).

    for each in matcheslist[0]:

        filepath = Path(each)
        f = open(filepath, "r")
        # contents = f.read()
        # newcontents = contents.replace(oldName, newName)
        origlines = f.readlines()
        f.close()
        # not working, prints a single char on each line right now because it
        # wants a list of strings--probably use readlines to get it the list.


        newlines = []

        for origline in origlines:
            newline = origline.replace(oldName,newName)
            newlines.append(newline)

        d = difflib.Differ()
        result = list(difflib.ndiff(origlines, newlines))
        pprint(result)

        f = open(filepath, "w")
        f.writelines(newlines)
        f.close

    # Warning, below we're working with a potentially different list of files
    # than just the list from the inqurirer stuff, because that's looking at
    # file content not file names. Also we should adopt the approach for only
    # renaming the .md and .rst files here as well, so we don't go renaming
    # anything in a src.zip folder.

    types = ('*.md', '*.rst')
    files_caught = []
    for filetype in types:
        files_caught.extend(glob.glob("**/*"+oldName+"*", recursive=True))

    for filename in files_caught:

        newfilename = re.sub(oldName, newName, filename, 1, 0)

        os.rename(filename, newfilename)

        pprint(filename + " was renamed to " + newfilename)

