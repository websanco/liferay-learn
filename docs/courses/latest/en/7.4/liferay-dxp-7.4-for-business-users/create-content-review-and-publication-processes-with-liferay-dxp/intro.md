# Create Content Review and Publication Processes with Liferay DXP

[$LIFERAY_LEARN_YOUTUBE_URL$]=https://www.youtube.com/embed/8HM8GxReUcI

## Learning Objectives
* Understand how to use workflows for Content in Liferay DXP
* Learn how to create custom workflows for unique business review processes
* Learn how to create Publications to make simultaneous changes across a Site and schedule those changes for publication

## Tasks to Accomplish
* Create a Workflow Definition
* Walk through the Workflow process 
* Create a Publication with several Site changes
* Invite Users to work on a Publication
* Schedule the changes for a Publication and observe them on the Site

#### Exercise Prerequisites

* Unzipped module exercise files in the following folder structure:
    * Windows: `C:\liferay`
    * Unix Systems: `[user-home]/liferay`
* A Liferay DXP 7.4 instance up and running
        - If you have not started your instance yet, first, make sure you have downloaded Docker, then use the following commands to get and start the Liferay Docker Image: 
        * `docker pull liferay/[product]:[version]`
        * `docker run -it -m 8g -p 8080:8080 liferay/[product]:[version]`
    - Once started, set up the instance with the following basic configuration:
        * Portal Name: Marvin Robotics
        * First Name: Test
        * Last Name: Test
        * Email: test@marvinrobotics.com
    - Check out the Liferay Foundations: Introduction to Liferay DXP course if you have questions on starting a new instance of Liferay DXP