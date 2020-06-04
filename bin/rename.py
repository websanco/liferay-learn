import colorama
from colorama import Fore, Back, Style
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

    colorama.init() # supposedly init is needed for windows

    oldName = sys.argv[1]
    newName = sys.argv[2]

    dest_folder = "."
    if (len(sys.argv)) > 2:
        dest_folder = sys.argv[2]

    # Find article and dir names matching the pattern passed, then replace the
    # text passed in the first arg with the text from the second arg.

    types = ('*.md', '*.rst', '*.html')
    files_grabbed = []
    for filetype in types:
        files_grabbed.extend(glob.glob("**/" + filetype, recursive=True))

    matchingfiles = []

    for filename in files_grabbed:

        f = open(filename, "r")
        content = f.read()
        f.close()

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

        newlines = []

        for origline in origlines:
            newline = origline.replace(oldName,newName)
            if re.search(newName,newline):
                print(Fore.LIGHTBLUE_EX + f.name + Fore.RESET + Fore.LIGHTYELLOW_EX + "\n" + newline)
            newlines.append(newline)

        # d = difflib.Differ()
        # result = list(difflib.context_diff(origlines, newlines,n=0))
        # pprint(result)

        f = open(filepath, "w")
        f.writelines(newlines)
        f.close

    matchingfilepatterns = glob.glob("**/*"+oldName+"*", recursive=True)

    choices = [
    inquirer.Checkbox('filepatterns',
                      message="De-select the files and folders you wish to skip (spacebar) renaming. For all selected, the string '" + oldName + "' will be replaced with '" + newName,
                      choices=matchingfilepatterns,
                      default=matchingfilepatterns),
    ]

    chosenfilepatterns = inquirer.prompt(choices).values()

    renamelist = list(chosenfilepatterns)

    for renamefile in renamelist[0]:

        newfilename = re.sub(oldName, newName, renamefile, 1, 0)

        os.rename(renamefile, newfilename)

        print(Fore.BLUE + renamefile + "\n" + " was renamed to " + "\n" + newfilename)

        if newfilename.endswith('.md'):

            f = open(newfilename)
            titleline = f.readline()
            f.close()

            print(Fore.YELLOW + "<<<Make sure your first level heading matches the new file name>>>")
            print(Fore.YELLOW + titleline + "------------")

        else:
            print(Fore.YELLOW + "------------")
