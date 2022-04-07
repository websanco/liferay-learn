import os
import sys

def handle_admonition_line(line):
    
    if (line.strip() == ""):
        md_file.write("\n")
        return line
    
    # Replace all double back-ticks with singles
    line = line.replace("``","`")

    gt_split = line.split(">`__")

    if (len(gt_split) > 1):
        # Process the line and the link(s)
        
        i = 0
        for i, gt_split_elem in enumerate(gt_split):
            
            lt_split = gt_split_elem.split("<")
            
            if (len(lt_split) > 1):
                # Found the start of the link path
                
                # Find the start of the link text. Search backwards from the character closest to the link path.
                index_last_bt = lt_split[0].rfind("`")

                if (index_last_bt > 0):
                    pre_link_text = lt_split[0][0:index_last_bt]
                    if (i == 0):
                        # Strip leading whitespace from the line
                        md_file.write(pre_link_text.lstrip())
                    else:
                        md_file.write(pre_link_text)
                
                md_file.write("[")
                md_file.write(lt_split[0][index_last_bt + 1:].lstrip().rstrip()) # Remove trailing whitespace from the link text.
                md_file.write("]")
                
                md_file.write("(")
                md_file.write(lt_split[1])
                md_file.write(")")

            else:
                md_file.write(gt_split_elem)

    else:
        # No link. Just process the line.

        md_file.write(line.lstrip())

    return line

if __name__ == "__main__":

    if (len(sys.argv) < 2):
        print("Usage:\n\tpython convert_rst_file.py article \n\nDescription:\n\tCreates a Markdown-style version of the RST file.")
        sys.exit()

    article = sys.argv[1]

    rst_file = open(article)
    lines = rst_file.readlines()
    rst_file.close()
    
    md_file_name = article + ".md"

    md_file = open(md_file_name, "w")
    
    mode_admonition = False
    mode_raw = False
    mode_toctree = False

    prev_line_empty = False
    label = ""
    
    md_file.write("# ")

    for line in lines:
        if bool(mode_toctree):
            if (line.strip() == ""):
                if (prev_line_empty):
                    md_file.write("```\n\n")

                    mode_toctree = False
                    prev_line_empty = False
                else:
                    prev_line_empty = True

            elif line.startswith(" ") or line.startswith("\t"): # toctree element
                if (prev_line_empty):
                    md_file.write("\n")

                prev_line_empty = False

                md_file.write(line.lstrip())
            
            elif line.lstrip().startswith(".."):
                md_file.write("```\n\n")
                mode_toctree = False

                if line.lstrip().startswith(".. raw:: html"):
                    mode_raw = True
                    
                    md_file.write("```{raw} html\n")
                elif line.lstrip().startswith("..important"):
                    label = "important"
                    mode_admonition = True

                elif line.lstrip().startswith("..`note"):
                    label = "note"
                    mode_admonition = True

                elif line.lstrip().startswith("..tip"):
                    label = "tip"
                    mode_admonition = True

                elif line.lstrip().startswith("..warning"):
                    label = "warning"
                    mode_admonition = True
                elif line.strip() == "":
                    if (mode_admonition == True):
                        mode_admonition = False
                    elif (mode_raw == True):
                        mode_raw = False
                    elif (mode_toctree == True):
                        mode_toctree = False
                    
                    md_file.write("```\n")
                    md_file.write(line)
                else:
                    md_file.write(line)

                if (mode_admonition == True):
                    mode_toctree = False

                    md_file.write("```{" + label + "}\n")
            else: # non-toctree line
                mode_toctree = False

                md_file.write("```\n")
                if (prev_line_empty):
                    md_file.write("\n")
                
                md_file.write(line)
        elif bool(mode_admonition):
            if (line.strip() == ""):
                md_file.write("```\n\n")
                mode_admonition = False
            else:
                handle_admonition_line(line)
        elif bool(mode_raw):
            md_file.write(line.strip())
            md_file.write("\n```\n")

            mode_raw = False
        elif line.lstrip().startswith("====") or line.lstrip().startswith("-----") or line.lstrip().startswith("~~~~") or line.lstrip().startswith("____"):
              continue
        elif line.lstrip().startswith(".."):

            if line.lstrip().startswith(".. toctree"):
                mode_toctree = True
                
                md_file.write("```{toctree}\n")
            elif line.lstrip().startswith(".. raw:: html"):
                mode_raw = True
                
                md_file.write("```{raw} html\n")
            elif line.lstrip().startswith("..important"):
                label = "important"
                mode_admonition = True

            elif line.lstrip().startswith("..`note"):
                label = "note"
                mode_admonition = True

            elif line.lstrip().startswith("..tip"):
                label = "tip"
                mode_admonition = True

            elif line.lstrip().startswith("..warning"):
                label = "warning"
                mode_admonition = True
            elif line.strip() == "":
                if (mode_admonition == True):
                    mode_admonition = False
                elif (mode_raw == True):
                    mode_raw = False
                elif (mode_toctree == True):
                    mode_toctree = False
                
                md_file.write("```\n")
                md_file.write(line)
            else:
                md_file.write(line)

            if (mode_admonition == True):
                md_file.write("```{" + label + "}\n")
        else:
            md_file.write(line)
            
    md_file.close()
    
    print(md_file_name)