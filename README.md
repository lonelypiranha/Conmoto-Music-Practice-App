# My Personal Project

## Project Description

I plan on making a **music practice journal app**. The app allows the user to **log** their **music practice sessions** into the app and track their **practice duration, tempo, bars practiced, and overall mastery** after their practice sessions. The app also allows the user to **add songs** that they are practicing into a **song library**, and they can see their **monthly progress** (in terms of practice duration, bars practiced, tempo, and overall mastery) on each song. 

This app will be used by **musicians** and **people who play a musical instrument/sing** who want to keep track of their progress on songs they are practicing. This project is interesting to me because I play the piano myself and I often wish there was an app that could help keep track of my progress on pieces that I'm practicing. I want to keep track of how long I practiced, which bars I practiced, at what tempo, and the overall mastery of the song I am practicing since these information are very useful for me to **structure my future practice sessions.**

## User Stories
- As a user, I want to be able to **add a song** to my **song library** and specify the composer, instrument, target tempo and number of bars.
- As a user, I want to be able to **view** the **list of song titles** in my song library.
- As a user, I want to be able to **start** a **practice session** using a song from my song library and **start a stopwatch** during my practice session to track my practice duration.
- As a user, I want to be able to **log** my **practice duration, tempo, bars practiced, and overall mastery** after **every practice session.**
- As a user, I want to be able to **view** my **monthly practice progress** (in terms of practice duration, bars practiced, tempo, and overall mastery) on **each song** in my song library.
- As a user, I want to be able to save my song library, along with the song details and practice history of each song in it, to file (if I want to).
- As a user, I want to be able to load my song library, along with the song details and practice history of each song in it, from file (if I want to).

# Instructions for End User
- You can add a song to the song library by clicking the **Add a song** button and filling in the details of the song you want to add.
- You can remove a song from the song library by clicking the **Remove a song** button and entering the title of the song you want to remove.
- You can filter the song library by composer or instrument name by clicking the **Filter song library by composer** or **Filter song library by instrument** buttons and entering the name of the composer or instrument that you want to filter by.
- You can locate my visual component by running the program, and the app logo will be the first thing that is displayed. You can also add a song, see the song details, then start a practice session with the song. In the practicing page, you will see a text **currently practicing** with the background image of a piano.
- You can save the state of my application by clicking the **Save file** button and confirming that you want to save file.
- You can reload the state of my application by clicking the **Load file** button and confirming that you want to load file.

## Phase 4: Task 2
Sun Nov 24 16:32:28 PST 2024
A song with the title sonata has been added.

Sun Nov 24 16:32:48 PST 2024
A song with the title fantasiestucke has been added.

Sun Nov 24 16:32:54 PST 2024
Song library is filtered by the composer schumann

Sun Nov 24 16:33:00 PST 2024
Song library is filtered by the composer scriabin

Sun Nov 24 16:33:05 PST 2024
Song library is filtered by the instrument clarinet

Sun Nov 24 16:33:10 PST 2024
Song library is filtered by the instrument piano

Sun Nov 24 16:33:20 PST 2024
A song with the title fantasiestucke was removed.

## Phase 4: Task 3
From my UML class diagram, I can see that there is excessive coupling between the Song, Day, and Session classes because Song has a list of Session and a list of Day, and each Day object also has a list of Session. I would reduce this unnecessary coupling by first introducing a new HashMap field to the Song class in which the keys are LocalDate objects representing the dates in which the song was practiced, and the values will be Set<Session> objects representing the set of practice sessions started om the particular date. Then I would move the methods from the Day class to the Song class and modify it so that it can operate on the HashMap. Lastly, I would delete the Day class completely because it's redundant now that a HashMap has replaced its function. This refactoring is useful as it removes an entire class, making the code easier to read, change, and debug as there are fewer places we need to change when we want to change the behavior of the code. It also reduces coupling between the classes, which makes it easier for us to change a class without affecting other classes that depend on it.

One other thing I noticed is that my PracticeAppGUI class is very long, and that it contains numerous JFrames that at its core have the same design: They all have the same background color, font, layout, constituent panels, etc. So I think this could be refactored by creating an abstract class containing all the shared features of those JFrames, and then separating all the JFrames into separate classes and make them extend said abstract class. This will make the PracticeAppGUI class much shorter and increase readability. It would also reduce repetition in the code since all of the similarities between the JFrames I created are abstracted into a single class. This would in turn make it easier to make changes in the code because there are fewer places we need to change when we want to change the behavior of the program.



