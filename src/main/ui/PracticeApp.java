package ui;

import java.time.Month;
import java.time.format.DateTimeFormatter;

import model.Day;
import model.Session;
import model.Song;
import model.SongLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.Scanner;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

// A music practice journal app that allows the user to add songs to a practice library 
// and start practice sessions on the songs. The app also allows the user to 
// view their practice progress on each song in the song library.
public class PracticeApp {
    private SongLibrary songlibrary;
    private SongLibrary filteredLibrary;

    private Scanner scanner;
    private static final String JSON_STORE = "./data/SongLibrary.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates one instance of Conmoto Music Practice Journal console app
    public PracticeApp() {
        this.songlibrary = new SongLibrary();
        this.filteredLibrary = new SongLibrary();
        this.scanner = new Scanner(System.in);
        scanner.useDelimiter(System.lineSeparator());
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        System.out.println("Welcome to Conmoto Music Practice Journal App!\n");

        handleMainMenu();
    }

    // EFFECTS: displays and processes inputs for the main menu
    // SOURCE: All code in this project that involves displaying and processing user
    // commands is inspired by the Flashcard Reviewer app from lab 4
    public void handleMainMenu() {
        displayMenu();
        String input = this.scanner.next();
        processMenuCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the main menu
    public void displayMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("v -> view song library");
        System.out.println("l -> load SongLibrary from file");
        System.out.println("q -> quit application");
        printNewLine();

    }

    // EFFECTS: processes the user's input in the main menu
    public void processMenuCommands(String input) {
        printNewLine();
        if (input.equals("v")) {
            viewSongLibrary();
        } else if (input.equals("l")) {
            loadSongLibrary("main");
        } else if (input.equals("q")) {
            quitApp();
        } else {
            System.out.println("Invalid input. Please enter a valid input\n");
            handleMainMenu();
        }

    }

    // EFFECTS: displays the title of all songs in the song library
    public void viewSongLibrary() {
        System.out.println("Your song library:\n");
        if (songlibrary.getSongList().isEmpty()) {
            System.out.println("Your song library is empty. Please add a song.\n");
            handleEmptyMenu();
        } else {
            int i = 1;
            for (Song s : songlibrary.getSongList()) {
                System.out.println(i + ". " + s.getTitle());
                i++;
            }
            printNewLine();
            handleViewMenu();
        }
    }

    // EFFECTS: displays and processes inputs for the song library menu when there
    // are no songs in the song library
    public void handleEmptyMenu() {
        displayEmptyLibraryMenu();
        String input = this.scanner.next();
        processEmptyLibraryCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the song library
    // menu when there are no songs in the song library
    public void displayEmptyLibraryMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("a -> add a song to the song library");
        System.out.println("l -> load SongLibrary from file");
        System.out.println("s -> save SongLibrary to file");
        System.out.println("q -> quit application");
        printNewLine();
    }

    // EFFECTS: processes the user's input in the song library menu when there are
    // no songs in the song library
    public void processEmptyLibraryCommands(String input) {
        printNewLine();
        if (input.equals("a")) {
            addSong();
        } else if (input.equals("l")) {
            loadSongLibrary("empty");
        } else if (input.equals("s")) {
            saveSongLibrary("empty");
        } else if (input.equals("q")) {
            quitApp();
        } else {
            System.out.println("Invalid input. Please enter a valid input\n");
            handleEmptyMenu();
        }
    }

    // EFFECTS: displays and processes inputs for the song library menu
    public void handleViewMenu() {
        displayViewMenu();
        String input = this.scanner.next();
        processViewCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the song library
    // menu
    public void displayViewMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("a -> add a song to the song library");
        System.out.println("r -> remove a song to the song library");
        System.out.println("d -> view the details of a song");
        System.out.println("v -> view all songs in the song library");
        System.out.println("i -> filter song library by instrument");
        System.out.println("c -> filter song library by composer");
        System.out.println("l -> load SongLibrary from file");
        System.out.println("s -> save SongLibrary to file");
        System.out.println("q -> quit application\n");
    }

    // EFFECTS: processes the user's input in the song library menu
    public void processViewCommands(String input) {
        printNewLine();
        if (input.equals("a")) {
            addSong();
        } else if (input.equals("r")) {
            removeSong();
        } else if (input.equals("d")) {
            viewSongDetails();
        } else if (input.equals("v")) {
            viewSongLibrary();
        } else if (input.equals("i")) {
            filterByInstrument();
        } else if (input.equals("c")) {
            filterByComposer();
        } else if (input.equals("l")) {
            loadSongLibrary("view");
        } else if (input.equals("s")) {
            saveSongLibrary("view");
        } else if (input.equals("q")) {
            quitApp();
        } else {
            System.out.println("Invalid input. Please enter a valid input\n");
            handleViewMenu();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a song to the song library
    public void addSong() {
        System.out.println("Please enter the title of your song: ");
        String title = this.scanner.next();
        printNewLine();
        System.out.println("Please enter the composer of your song: ");
        String composer = this.scanner.next();
        printNewLine();
        System.out.println("Please enter the instrument your song is composed for: ");
        String instrument = this.scanner.next();
        printNewLine();
        System.out.println("Please enter the number of bars in your song: ");
        int barCount = this.scanner.nextInt();
        printNewLine();
        System.out.println("Please enter the target tempo of your song: ");
        int tempo = this.scanner.nextInt();
        printNewLine();
        Song s = new Song(title, composer, instrument, barCount, tempo);

        songlibrary.addSong(s);

        System.out.println("A new song is successfully added to your song library!\n");

        viewSongLibrary();
    }

    // MODIFIES: this
    // EFFECTS: removes a song from the song library
    public void removeSong() {
        System.out.println("Enter the exact title of the song you want to remove: ");
        String song = this.scanner.next();
        printNewLine();
        boolean checker = false;
        for (Song s : songlibrary.getSongList()) {
            if (song.equals(s.getTitle())) {
                checker = songlibrary.removeSong(s);
                break;
            }
        }
        if (checker) {
            System.out.println("Song successfully removed.\n");
        } else {
            System.out.println("No song with that title was found.\n");
        }
        viewSongLibrary();
    }

    // MODIFIES: this
    // EFFECTS: filters through the song library by composer name,
    // then sets the filtered song list to filteredLibrary
    public void filterByComposer() {
        System.out.println("Enter the exact name of the composer you want to filter by: ");
        String composer = this.scanner.next();
        printNewLine();
        List<Song> filteredList = songlibrary.filterByComposer(composer);
        filteredLibrary.setSongList(filteredList);
        viewFilteredLibrary("composer", " \"" + composer + "\"");
    }

    // MODIFIES: this
    // EFFECTS: filters through the song library by instrument name,
    // then sets the filtered song list to filteredLibrary
    public void filterByInstrument() {
        System.out.println("Enter the exact name of the instrument you want to filter by: ");
        String instrument = this.scanner.next();
        printNewLine();
        List<Song> filteredList = songlibrary.filterByInstrument(instrument);
        filteredLibrary.setSongList(filteredList);
        viewFilteredLibrary("instrument", " \"" + instrument + "\"");
    }

    // EFFECTS: displays the title of all songs in filteredLibrary
    public void viewFilteredLibrary(String instrumentOrComposer, String name) {
        System.out.println("Your song library filtered by the " + instrumentOrComposer + name + ":\n");
        if (filteredLibrary.getSongList().isEmpty()) {
            System.out.println("There are no songs with that " + instrumentOrComposer + "\n");
            handleFilteredLibrary();
        } else {
            int i = 1;
            for (Song s1 : filteredLibrary.getSongList()) {
                System.out.println(i + ". " + s1.getTitle());
                i++;
            }
            printNewLine();
            handleFilteredLibrary();
        }
    }

    // EFFECTS: displays and processes inputs for the filtered song library menu
    public void handleFilteredLibrary() {
        displayFilteredLibraryMenu();
        String input = this.scanner.next();
        processFilteredLibraryCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the filtered song
    // library menu
    public void displayFilteredLibraryMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("r -> return to full song library menu");
        System.out.println("q -> quit application\n");
    }

    // EFFECTS: processes the user's input in the filtered song library menu
    public void processFilteredLibraryCommands(String input) {
        printNewLine();
        if (input.equals("r")) {
            viewSongLibrary();
        } else if (input.equals("q")) {
            quitApp();
        } else {
            System.out.println("Invalid input. Please enter a valid input\n");
            handleFilteredLibrary();
        }
    }

    // EFFECTS:
    // 1. View details of a specific song, including its title, composer,
    // instrument, bar count, and tempo
    // 2. Gives options for the user to start a practice session for the song amd
    // view the song's practice history and monthly progress summary.
    public void viewSongDetails() {
        System.out.println("Enter the exact title of the song whose details you want to view: ");
        String song = this.scanner.next();
        printNewLine();
        boolean checker = false;
        for (Song song1 : songlibrary.getSongList()) {
            if (song.equals(song1.getTitle())) {
                System.out.println("Song title: " + song1.getTitle());
                System.out.println("Composer: " + song1.getComposer());
                System.out.println("Instrument: " + song1.getInstrument());
                System.out.println("Number of bars: " + song1.getBarNumber());
                System.out.println("Target tempo: " + song1.getTargetTempo() + "\n");
                handleSongMenu(song1);
                break;
            }
        }

        if (!checker) {
            System.out.println("No song with that title was found.\n");
            viewSongLibrary();
        }
    }

    // EFFECTS: displays and processes inputs for the song details menu
    public void handleSongMenu(Song song) {
        displaySongMenu();
        String input = this.scanner.next();
        processSongCommands(input, song);
    }

    // EFFECTS: displays a list of commands that can be used in the song details
    // menu
    public void displaySongMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("p -> start a practice session with this song");
        System.out.println("h -> view the practice history of this song");
        System.out.println("m -> view the monthly progress for this song");
        System.out.println("r -> return to song library menu");
        System.out.println("q -> quit application\n");
    }

    // EFFECTS: processes the user's input in the song details menu
    public void processSongCommands(String input, Song song) {
        printNewLine();
        if (input.equals("p")) {
            practice(song);
        } else if (input.equals("h")) {
            viewPracticeHistory(song);
        } else if (input.equals("m")) {
            viewMonthlyProgress(song);
        } else if (input.equals("r")) {
            viewSongLibrary();
        } else if (input.equals("q")) {
            quitApp();
        } else {
            System.out.println("Invalid input. Please enter a valid input\n");
            handleSongMenu(song);
        }
    }

    // EFFECTS: starts a practice session for a given song, and monitors practice
    // duration.
    public void practice(Song song) {
        System.out.println("Starting a practice session...");
        System.out.println("Now practicing and monitoring practice duration...\n");
        long initialTime = System.currentTimeMillis();
        LocalDate date = LocalDate.now();
        LocalTime startTime = LocalTime.now();
        handlePracticeMenu(song, initialTime, date, startTime);
    }

    // EFFECTS: displays and processes inputs for the practice menu
    public void handlePracticeMenu(Song song, long initialTime, LocalDate date, LocalTime startTime) {
        System.out.println("Please enter \"e\" to end this practice session");
        String input = this.scanner.next();
        if (input.equals("e")) {
            endPractice(song, initialTime, date, startTime);
        } else {
            System.out.println("Invalid input.");
            handlePracticeMenu(song, initialTime, date, startTime);
        }
    }

    // EFFECTS: ends practice session, displays practice duration, then proceeds to
    // prompt the user for the practice session details
    public void endPractice(Song song, long initialTime, LocalDate date, LocalTime startTime) {
        LocalTime endTime = LocalTime.now();
        long elapsedTime = System.currentTimeMillis() - initialTime;
        float finalElapsedMinutes = convertDurationToMinutes(elapsedTime);
        printNewLine();
        System.out.println("Practice session has ended. You practiced for " + finalElapsedMinutes + " minutes!\n");
        collectPracticeDetails(song, elapsedTime, date, startTime, endTime);
    }

    // MODIFIES: song
    // EFFECTS: Prompts user for practice sesion details, then logs the practice
    // session into the song's session list and day list
    public void collectPracticeDetails(Song song, long elapsedTime, LocalDate date, LocalTime startTime,
            LocalTime endTime) {
        System.out.println("Please fill in your practice session details below: \n");
        System.out.println("Enter the tempo you practiced at: ");
        int tempo = this.scanner.nextInt();
        printNewLine();
        System.out.println("Enter the starting bar number you practiced at: ");
        int startBar = this.scanner.nextInt();
        printNewLine();
        System.out.println("Enter the ending bar number you practiced at: ");
        int endBar = this.scanner.nextInt();
        printNewLine();
        System.out.println("Enter your overall mastery of the song from 0 - 10: ");
        float overallMastery = this.scanner.nextFloat();
        printNewLine();
        Session session = new Session(tempo, startBar, endBar, overallMastery, elapsedTime, date, startTime, endTime);
        song.logSessionToSessionList(session);
        song.logSessionToDay(session);
        System.out.println("Current practice session has been logged!");
        handlePostPracticeMenu();
    }

    // EFFECTS: displays and processes inputs for the post-practice, practice
    // history, and monthly progress menu
    public void handlePostPracticeMenu() {
        displayPostPracticeMenu();
        String input = this.scanner.next();
        processPostPracticeCommands(input);
    }

    // EFFECTS: displays a list of commands that can be used in the post-practice,
    // practice history, and monthly progress menu
    public void displayPostPracticeMenu() {
        System.out.println("Please select an option:\n");
        System.out.println("r -> return to song library menu");
        System.out.println("q -> quit application\n");
    }

    // EFFECTS: processes the user's input in the post-practice, practice history,
    // and monthly progress menu
    public void processPostPracticeCommands(String input) {
        printNewLine();
        if (input.equals("r")) {
            viewSongLibrary();
        } else if (input.equals("q")) {
            quitApp();
        } else {
            System.out.println("Invalid input. Please enter a valid input\n");
            handlePostPracticeMenu();
        }
    }

    // EFFECTS:
    // 1. Displays the practice progress of a song by displaying the practice
    // duration,
    // tempo, bars practiced, and mastery of all practice sessions in a given month
    // of the year
    // 2. If there are multiple practice sessions for a song in a single day, total
    // the practice durations and average out the tempos and masteries of all
    // practice sessions in that day
    public void viewMonthlyProgress(Song song) {
        System.out.println("Enter the exact year whose progress report you want to view: ");
        int year = this.scanner.nextInt();
        printNewLine();
        System.out.println("Enter the exact month name whose progress report you want to view.");
        System.out.println("Capitalize the first letter of the month name.");
        String monthString = this.scanner.next();
        Month month = convertStringToMonth(monthString);
        List<Day> daysInMonth = song.returnDaysInMonth(month, year);
        printNewLine();
        System.out.println("Monthly progress report for " + monthString + " " + year + "\n");
        if (daysInMonth.isEmpty()) {
            System.out.println("No practice session in that month of the year was found.\n");
        } else {
            displayDurationProgress(daysInMonth);
            displayTempoProgress(daysInMonth);
            displayMasteryProgress(daysInMonth);
            displayBarsPracticedProgress(daysInMonth);
        }
        handlePostPracticeMenu();
    }

    // EFFECTS: displays the total practice duration in a given day for all days in
    // the dayList
    public void displayDurationProgress(List<Day> dayList) {
        System.out.println("Daily total duration progress: ");
        for (Day day : dayList) {
            String formattedDate = formatDate(day.getLocalDate());
            float finalDuration = convertDurationToMinutes(day.getTotalDuration());
            System.out.println(formattedDate + ": " + finalDuration + " minutes\n");
        }
    }

    // EFFECTS: displays the average tempo in a given day for all days in the
    // dayList
    public void displayTempoProgress(List<Day> dayList) {
        System.out.println("Daily average tempo progress: ");
        for (Day day : dayList) {
            String formattedDate = formatDate(day.getLocalDate());
            System.out.println(formattedDate + ": " + day.getAverageTempo() + "\n");
        }
    }

    // EFFECTS: displays the average mastery level in a given day for all days in
    // the dayList
    public void displayMasteryProgress(List<Day> dayList) {
        System.out.println("Daily average mastery progress: ");
        for (Day day : dayList) {
            String formattedDate = formatDate(day.getLocalDate());
            System.out.println(formattedDate + ": " + day.getAverageMastery() + "\n");
        }
    }

    // EFFECTS: displays the bars practiced in a given day for all days in the
    // dayList
    public void displayBarsPracticedProgress(List<Day> dayList) {
        System.out.println("Daily bars practiced progress: ");
        for (Day day : dayList) {
            String allBarsPracticed = "";
            List<Session> sesList = day.getSessionList();
            for (int i = 0; i < sesList.size(); i++) {
                if (i == 0) {
                    String barsPracticed = String.valueOf(sesList.get(i).getStartBar()) + "-"
                            + String.valueOf(sesList.get(i).getEndBar());
                    allBarsPracticed = allBarsPracticed + barsPracticed;
                } else {
                    String barsPracticed = ", " + String.valueOf(sesList.get(i).getStartBar()) + "-"
                            + String.valueOf(sesList.get(i).getEndBar());
                    allBarsPracticed = allBarsPracticed + barsPracticed;
                }
            }
            String formattedDate = formatDate(day.getLocalDate());
            System.out.println(formattedDate + ": " + allBarsPracticed + "\n");
        }
    }

    // EFFECTS: displays all practice sessions of a given song along with their
    // details (practice duration, tempo, etc.)
    public void viewPracticeHistory(Song song) {
        System.out.println("Practice history for " + song.getTitle() + ":" + "\n");
        if (song.getSessions().isEmpty()) {
            System.out.println("No practice sessions found.\n");
        } else {
            for (Session session : song.getSessions()) {
                String formattedDate = formatDate(session.getPracticeDate());
                String formattedStartTime = formatTime(session.getStartTime());
                String formattedEndTime = formatTime(session.getEndTime());
                float finalDuration = convertDurationToMinutes(session.getDuration());

                System.out.println(formattedDate + " " + formattedStartTime + "-" + formattedEndTime);
                System.out.println("Tempo: " + session.getTempo());
                System.out.println("Bars practiced: " + session.getStartBar() + "-" + session.getEndBar());
                System.out.println("Overall mastery: " + session.getOverallMastery());
                System.out.println("Duration: " + finalDuration + " minutes\n");
            }
        }
        handlePostPracticeMenu();
    }

    // EFFECTS: formats the date into the given pattern, then returns the formatted
    // date as a String
    public String formatDate(LocalDate date) {
        DateTimeFormatter formatPatternDate = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
        String formattedDate = date.format(formatPatternDate);
        return formattedDate;
    }

    // EFFECTS: formats the time into the given pattern, then returns the formatted
    // time as a String
    public String formatTime(LocalTime time) {
        DateTimeFormatter formatPatternTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(formatPatternTime);
        return formattedTime;
    }

    // EFFECTS: converts the duration (in miliseconds) into minutes, returning the
    // converted duration as a float
    public float convertDurationToMinutes(long duration) {
        double durationMinutes = (double) duration / 60000.0;
        String durationMinutesString = String.valueOf(durationMinutes);
        String durationMinutesStringTruncated = durationMinutesString.substring(0, 4);
        float finalDuration = Float.parseFloat(durationMinutesStringTruncated);
        return finalDuration;
    }

    // EFFECTS: saves the SongLibrary to file
    private void saveSongLibrary(String s) {
        try {
            jsonWriter.open();
            jsonWriter.write(songlibrary);
            jsonWriter.close();
            System.out.println("Saved SongLibrary" + " to " + JSON_STORE);
            if (s.equals("empty")) {
                viewSongLibrary();
            } else if (s.equals("view")) {
                viewSongLibrary();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads SongLibrary from file
    private void loadSongLibrary(String s) {
        try {
            songlibrary = jsonReader.read();
            System.out.println("Loaded SongLibrary" + " from " + JSON_STORE);
            if (s.equals("main")) {
                handleMainMenu();
            } else if (s.equals("empty")) {
                viewSongLibrary();
            } else if (s.equals("view")) {
                viewSongLibrary();
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: prints a closing message and exits program
    public void quitApp() {
        System.out.println("Thanks for using Conmoto Music Practice Journal App! See you next time!\n");
        System.exit(0);
    }

    // EFFECTS: returns a Month object that corresponds to the month name string
    @SuppressWarnings("methodlength")
    public Month convertStringToMonth(String month) {
        switch (month) {
            case "January":
                return Month.JANUARY;
            case "February":
                return Month.FEBRUARY;
            case "March":
                return Month.MARCH;
            case "April":
                return Month.APRIL;
            case "May":
                return Month.MAY;
            case "June":
                return Month.JUNE;
            case "July":
                return Month.JULY;
            case "August":
                return Month.AUGUST;
            case "September":
                return Month.SEPTEMBER;
            case "October":
                return Month.OCTOBER;
            case "November":
                return Month.NOVEMBER;
            case "December":
                return Month.DECEMBER;
            default:
                return null;
        }
    }

    // EFFECTS: prints out a new line
    public void printNewLine() {
        System.out.println('\n');
    }

}
