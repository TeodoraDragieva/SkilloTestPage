<img align="center" src="skilloLogo.png" alt="Skillo Academy Logo" />


<div align="center">

# Test automation framework
</div>

# Automating iSkillo website

## Table of Contents
- [Overview](#overview)
- [Application / software under test]()
- [Installation](#installation)
- [Usage](#usage)
- [Bug report](#bug-report)
- [Contact](#contact)

## Overview
Iskillo social networks is an online platform where users can create profiles,
connect with friends and family.

All Iskillo users can share content, and engage with
a broader community.

ISkillo have revolutionized communication,
enabling instant sharing of information and fostering
global connections not only in Bulgaria.

Many QA engineers are connected via the fast variety of  test automation activities performed on ISkillo.

## Test activities performed with Selenium 4.25 and TestNG 10 Java unit framework:
List of the test cases.
Registration feature:
- Verify user cаn registered in the system with valid data.
- Verify user can NOT register in the system with already taken username.
- - Verify user can NOT register in the system with alerady taken password.

- Login feature
    - Verify already registered user can successfully login in the system
    - Verify already registered user can NOT successfully login in the system  with WRONG PASSWORD
    - Verify already registered user can NOT successfully login in the system  with WRONG USERNAME
    - Verify already registered user can NOT successfully login in the system  with NO CREDENTIALS

- Post feature
    - Verify  user can create a new post.
    - Verify user can delete a post.
    - Verify user can like post.
    - Verify user can dislike post.

  
## Installation

- Clone the repository (please use this link): https://github.com/TeodoraDragieva/SkilloTestPage
  
- Make sure you have JAVA version 23 and up and running

- Make sure you have Maven version 3.0.9 and up


## Usage

INSTALLATION:

Please visit the Test Automation Framework with linK:

Make sure that you can clone the repository. Follow the 3 different ways to do so:

Tip number 1:
- Go to the GitHub website with the link above and click on download button.
- Next action is to unarchived the repository in your favorite place.

Tip number 2:
- Go to the GitHub website copy the gitRepo HTTPS link and use git Bash
    - Git clone "https://github.com/TeodoraDragieva/SkilloTestPage"
    - cd code repo src folder
    - Other git commands needed

Tip number 3:
- Use iIntelliJ Idea Community Edition v 21.+ and from the git menu clone the project
    - New project from VCS - > provide git repo link
    - click on 

CHECK FOLDER PATHS:

These are steps that need to be done for Windows OS users:
Go to SRC TEST RESOURCES folder and verify if the following folders are presented:
- There is a folder with name "reports"
- There is a folder with name "screenshots"
- There is a folder with name "upload"

IF NOT
When you build the project in src/test/java/gui you can find the folders created by the automation script.

RUNNING AUTOMATION

STEP 1:
Go with the terminal/shell/msPrompt to the folder of the project that POM.XML lives (exists).

STEP 2:
Run command:

mvn clean test

STEP 3:

Wait a bit the automation to start and after the test execution a report will be generated.

## Bug report
1. On the Registration Page the button at the end of the Registration Form should be named "Sing up", not "Sing in".
2. On the Registation Form, when we fill the username filed we receive message "Minimum 2 characters!", but we can create Username with not less than 4 characters.
3. On the Registation Form, when we start to type new email we do not receive message what are the requirments.
4. The User can register with unvalid date - example with "01/01/999999""

## Contact

- [Teodora Dragieva](mailto:teodoradr.2018@gmail.com)
- Project Link: [TAF Selenium 4 TestNG 7 V1 T](https://github.com/TeodoraDragieva/SkilloTestPage)