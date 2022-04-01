## Installing a Bundle in your Workspace

<div class="ahead">

#### Exercise Goals

- Install a bundle during Liferay Workspace wizard
- Install a bundle using Developer Studio + Gradle Task
- Install a bundle using the command line

</div>

#### Install a Bundle using the Command Line

1. **Open** a new terminal / shell

2. **Navigate** to the location where you created the *gradebook-workspace*

3. We'll start be removing the existing bundles directory

    `$>rm -rf bundles`
    
4. Next we'll leverage the BLADE command line tools to execute the task

    `$>blade gw initBundle`  
    
    > Note: <br/><br/>
    This gw argument is used to tell blade that you which to use the gradle wrapper in this workspace to 
    execute the gradle task refrence that follows it

5. **List** the contents of the directory and notice that the *bundles* directory is back and contains the
same contents that we saw previously

