# Dance Studio Scheduling Application

## Aim
This application's aim to manage dance classes and students in a weekly schedule.
The application:
 - will schedule dance classes (add and remove) to a calendar 
 - provide information about the classes
 - allow the dance studio to register/remove students to those classes
  - provide a visualization of the dance schedule. 

This application will be used by the dance studio and its administration team.
## Motivation
As a dancer in Vancouver many small studios use posters to advertise their classes on social media. Dance class
schedules are difficult to find for smaller studios, and I wanted to create an application that can help these studios 
manage their classes. Instead of multiple spreadsheets, this application will be able to keep track of which students
take their classes. This is especially important during this global pandemic when contract tracing is essential.

## User Stories
- as a user, I want to be able to add classes to the schedule
- as a user, I want to be able to remove classes from a schedule
- as a user, I want to be able to register students for classes
- as a user, I want to be able to see the schedule for the week
- as a user, I want to be able to remove students from classes
- as a user, I want to be able to change a dance class' details

- as a user, I want to be able to save my dance schedule to file
- as a user, I want to be able to load my dance schedule from file
- as a user, I want to have the option to save my dance schedule to file when I attempt to quit the application

## Phase 4: Task 2
- Tested and designed the DanceClass class to be robust
- The methods in the DanceClass that achieve this is the constructor for the DanceClass and registerStudent()

## Phase 4: Task 3
- Refactor GUI class into more focused classes to improve higher cohesion (such as buttons, tables etc.)
- Refactor DanceScheduleApp into more focused classes to improve higher cohesion (such as the main menu, and each
action as its own class - save/load, display schedule, add dance class, remove dance class etc.)
- Add class hierarchy to remove duplicate code for DanceScheduleApp
- GUI can be refactored for duplicate code into their own classes for the creation of buttons, tables, and panes
 and/or refactored by adding hierarchy classes
- DanceScheduleApp and GUI have associations to the same objects, refactor so DanceScheduleApp can have an association 
to GUI class (remove unnecessary association)
