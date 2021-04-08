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
- Refactor GUI class into more focused classes to improve higher cohesion and lower coupling
- This can be done by refactoring the GUI class into the button class, table class, sound class, and panel class
 where each class would be focused on different aspects of the graphical user interface
- GUI can be refactored for duplicate code into their own classes for the creation of buttons, tables, and panes
 and/or refactored by adding hierarchy classes
- Refactor the console class by removing the DanceScheduleApp class because the code is unused and only the GUI 
(graphical user interface) class is used
- Refactor repetitive code in the classes to a more abstract method (such as ToJson in both the Day and WeeklySchedule
 class, or the addTableConstraint methods in GUI)
