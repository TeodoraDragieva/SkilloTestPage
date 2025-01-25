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

iSkillo Social Networks is an innovative online platform that allows users to create personal profiles, connect with friends and family, and engage with a global community.

Users can share content, interact through posts, and be part of a vibrant network that fosters communication and collaboration beyond borders, extending its impact far beyond Bulgaria.

What makes iSkillo particularly valuable for QA engineers is its integration with diverse automation testing activities. 

The platform serves as a robust environment where testers can perform a wide variety of test automation scenarios, helping to ensure seamless user experiences and uncovering potential system vulnerabilities.

## Test activities performed with Selenium 4.25 and TestNG 10 Java unit framework:

**List of the test cases.**

**Registration feature:**
- Verify user cаn registered in the system with valid data.
- Verify user can NOT register in the system with already taken username.
- Verify user can NOT register in the system with alerady taken password.

**Login feature:**
    - Verify already registered user can successfully login in the system
    - Verify already registered user can NOT successfully login in the system  with WRONG PASSWORD
    - Verify already registered user can NOT successfully login in the system  with WRONG USERNAME
    - Verify already registered user can NOT successfully login in the system  with NO CREDENTIALS

**Post feature:**
    - Verify  user can create a new post.
    - Verify user can delete a post.
    - Verify user can like post.
    - Verify user can dislike post.
  
**End-To-End Test:**
    - Verify User Can Register, Login and create a post.

## Installation

- Clone the repository (please use this link): https://github.com/TeodoraDragieva/SkilloTestPage
  
- Make sure you have JAVA version 23 and up and running

- Make sure you have Maven version 3.0.9 and up

## Usage

INSTALLATION:

Please visit the Test Automation Framework with linK: https://github.com/TeodoraDragieva/SkilloTestPage

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
    - New project from VCS - > https://github.com/TeodoraDragieva/SkilloTestPage
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

STEP 1.:
Go with the terminal/shell/msPrompt to the folder of the project that POM.XML lives (exists).

STEP 2.:
Run command:

mvn clean test

STEP 3.:
Wait a bit the automation to start and after the test execution a report will be generated.

## Bug report

This section outlines known issues identified during testing on the iSkillo platform.

1. ID:01
Name: Incorrect Button Label

Issue: On the Registration Page, the button at the end of the Registration Form is labeled as "Sign in" instead of "Sign up."

Impact: This may confuse users who are completing the registration process.

Severity: Minor
Steps to Reproduce:
Step 1.: Navigate to the Registration Page: http://training.skillo-bg.com:4300/users/register
Step 2.: Complete all required fields in the Registration Form.
Step 3.: Observe the button label at the end of the form.

Expected Result:
The button

Actual Result:
The button is labeled "Sign in," which may confuse users.

Environment:
OS: Windows 11
Browser: Chrome 131.0

2. ID:02
Name: Inconsistent Username Validation Message
Issue: When filling out the username field on the Registration Form, a message appears stating "Minimum 2 characters!" However, the system requires at least 4 characters for a valid username.
Impact: Misleading validation message causes confusion for users during registration.
Severity: Moderate
Steps to Reproduce:
Step 1.: Navigate to the Registration Page: http://training.skillo-bg.com:4300/users/register
Step 2.: Enter fewer than 4 characters in the username field.
Step 3.: Observe the validation message displayed.

Expected Result:
The validation message should state "Minimum 4 characters!" to match the actual requirement.

Actual Result:
The validation message incorrectly states "Minimum 2 characters!"

Environment:
OS: Windows 11
Browser: Chrome 131.0

3. ID:03
Name: Missing Email Field Validation Message

Issue: When users begin typing in the email field of the Registration Form, no validation message is displayed to indicate the email format requirements.

Impact: Users are unaware of the expected format, increasing the likelihood of errors.

Severity: Minor

Steps to Reproduce:
Step 1.: Navigate to the Registration Page: http://training.skillo-bg.com:4300/users/register
Step 2.: Start typing an email address in the email field.
Step 3.: Observe the absence of a validation message.

Expected Result:
A message should be displayed, indicating the email requirements (e.g., "Must include '@' and a domain name.").

Actual Result:
No message is shown to guide the user.

Environment:
OS: Windows 11
Browser: Chrome 131.0


4. ID:04 
Name: Invalid Date of Birth Acceptance

Issue: The system allows users to register with an invalid date of birth, such as "01/01/999999."

Impact: Leads to unrealistic or malformed user data in the system.

Severity: Major

Steps to Reproduce:
Step 1.: Navigate to the Registration Page: http://training.skillo-bg.com:4300/users/register
Step 2.:Enter an invalid date of birth (e.g., "01/01/999999") in the date of birth field.
Step 3.:Complete the rest of the Registration Form and submit.
Step 4.:Observe that registration is successful despite the invalid date of birth.

Expected Result:
The system should reject the invalid date of birth and display an error message.

Actual Result:
The system accepts the invalid date of birth and allows the user to register.

Environment:
OS: Windows 11
Browser: Chrome 131.0


## Contact

- [Teodora Dragieva](mailto:teodoradr.2018@gmail.com)
- Project Link: [TAF Selenium 4 TestNG 7 V1 T](https://github.com/TeodoraDragieva/SkilloTestPage)