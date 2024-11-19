package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptions.ImpossiblePracticeDetailsException;
import model.Day;
import model.Session;
import model.Song;
import model.SongLibrary;
import persistence.JsonReader;
import persistence.JsonWriter;

// A music practice journal app that allows the user to add songs to a practice library 
// and start practice sessions on the songs. The app also allows the user to 
// view their practice progress on each song in the song library.
public class PracticeAppGui implements ActionListener {
    private final ImageIcon logo = new ImageIcon("src/main/ui/assets/on-2.png");
    private final ImageIcon practicing = new ImageIcon("src/main/ui/assets/practicing2.png");

    private JFrame frame;

    private JLabel headerLabel;

    private JPanel textFieldsPane;
    private JPanel panelForHeader;
    private JPanel buttonsPanel;
    private JPanel mainPanel;
    private JPanel wholePagePanel;
    private JPanel additionalButtonPanel;

    private JButton startAppButton;
    private JButton addSongButton;
    private JButton removeSongButton;
    private JButton detailsButton;
    private JButton filterInstrumentButton;
    private JButton filterComposerButton;
    private JButton loadButton;
    private JButton saveButton;
    private JButton returnToMainButton;
    private JButton practiceButton;
    private JButton practiceHistoryButton;
    private JButton monthlyProgressButton;
    private JButton endPracticeButton;
    private JButton fillDetailsButton;
    private JButton returnToDetailsButton;
    private JButton quitButton;

    private JOptionPane optionPane;

    private SongLibrary songlibrary;
    private SongLibrary filteredLibrary;

    private Song currentSong;
    private long currentInitialTime;
    private LocalDate currentDate;
    private LocalTime currentStartTime;
    private LocalTime currentEndTime;
    private long currentElapsedTime;
    private float currentFinalElapsedMinutes;

    private static final String JSON_STORE = "./data/SongLibrary.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: creates one instance of Conmoto Music Practice Journal GUI app
    public PracticeAppGui() {
        init();

        headerLabel = makeLabelForOpening();

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0, 0, 0, 0));
        mainPanel.add(headerLabel);

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(0, 0, 0, 0));
        buttonsPanel.add(startAppButton);

        wholePagePanel = new JPanel();
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.add(mainPanel);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);

        constructFrame();
    }

    // MODIFIES: this
    // EFFECTS: initializes the song library, filtered library, json writer, json
    // reader, and the buttons
    private void init() {
        songlibrary = new SongLibrary();
        filteredLibrary = new SongLibrary();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initButtons();
    }

    // MODIFIES: this
    // EFFECTS: initializes and creates all buttons in the entire app
    private void initButtons() {
        startAppButton = buttonFactory("Start App");
        addSongButton = buttonFactory("Add a song");
        removeSongButton = buttonFactory("Remove a song");
        detailsButton = buttonFactory("See song details");
        filterInstrumentButton = buttonFactory("Filter song library by instrument");
        filterComposerButton = buttonFactory("Filter song library by composer name");
        loadButton = buttonFactory("Load file");
        saveButton = buttonFactory("Save file");
        quitButton = buttonFactory("Quit app");
        returnToMainButton = buttonFactory("Return to full song library");
        quitButton = buttonFactory("Quit app");
        monthlyProgressButton = buttonFactory("View monthly progress");
        practiceHistoryButton = buttonFactory("View practice history");
        practiceButton = buttonFactory("Practice song");
        endPracticeButton = buttonFactory("End practice");
        fillDetailsButton = buttonFactory("Fill in practice details");
        returnToDetailsButton = buttonFactory("Return to song details page");
    }

    // MODIFIES: this
    // EFFECTS: creates the label in the opening page, which contains an ImageIcon
    // and a text.
    private JLabel makeLabelForOpening() {
        headerLabel = new JLabel();
        headerLabel.setIcon(logo);
        headerLabel.setText("Welcome To Conmoto Music Practice App!");
        headerLabel.setHorizontalTextPosition(JLabel.CENTER);
        headerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Futura", Font.BOLD, 30));
        headerLabel.setIconTextGap(-50);
        return headerLabel;
    }

    // MODIFIES: this
    // EFFECTS: displays the song library page containing the list of songs in the
    // song library
    public void displaySongLibrary() {
        frame.setVisible(false);

        headerLabel = makeGenericHeader("Your Song Library");
        panelForHeader = new JPanel();
        panelForHeader.setBackground(new Color(0, 0, 0, 0));
        panelForHeader.add(headerLabel);

        buttonsPanel = makeButtonsPanelSongLib();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(songlibrary.getSongList().size(), 1));
        mainPanel.setBackground(Color.WHITE);
        createSongLibraryContent();

        assembleWholePagePanelMonthlyProgress();

        constructFrame();
    }

    // MODIFIES: this
    // EFFECTS: adds content (titles of the songs in the song library) to the main
    // panel of the song library page
    private void createSongLibraryContent() {
        if (songlibrary.getSongList().isEmpty()) {
            JLabel labelForEmptyLibrary = new JLabel();
            labelForEmptyLibrary.setText("Your song library is empty. Add a song!");
            labelForEmptyLibrary.setForeground(Color.BLUE);
            labelForEmptyLibrary.setFont(new Font("Futura", Font.BOLD, 20));
            mainPanel.add(labelForEmptyLibrary);
        } else {
            for (int i = 0; i < songlibrary.getSongList().size(); i++) {
                JLabel songTitle = new JLabel();
                songTitle.setText((i + 1) + ". " + songlibrary.getSongList().get(i).getTitle());
                songTitle.setForeground(Color.BLUE);
                songTitle.setFont(new Font("Futura", Font.BOLD, 20));
                mainPanel.add(songTitle);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the button panel for the song library page
    private JPanel makeButtonsPanelSongLib() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 2));
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(addSongButton);
        buttonsPanel.add(removeSongButton);
        buttonsPanel.add(detailsButton);
        buttonsPanel.add(filterComposerButton);
        buttonsPanel.add(filterInstrumentButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(quitButton);

        return buttonsPanel;
    }

    // EFFECTS: tries to add a song to the song library. If a NumberFormatException
    // exception is thrown, then the app will fail to add the song.
    private void tryAddSong() {
        try {
            addSong();
        } catch (NumberFormatException nfe) {
            JOptionPane.showOptionDialog(null, "Number of bars and tempo must be an integer!", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays an option pane that allows the users to fill in the details
    // of the song they want to add to the song library, then adds the song to the
    // song library
    public void addSong() {
        JTextField title = new JTextField();
        JTextField composer = new JTextField();
        JTextField instrument = new JTextField();
        JTextField barCount = new JTextField();
        JTextField tempo = new JTextField();
        makeTextFieldsPanelForAddSong(title, composer, instrument, barCount, tempo);

        optionPane = new JOptionPane();
        int result = JOptionPane.showConfirmDialog(optionPane, textFieldsPane,
                "Please enter song details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String titleForSong = title.getText();
            String composerForSong = composer.getText();
            String instrumentForSong = instrument.getText();
            int barForSong = Integer.parseInt(barCount.getText());
            int tempoForSong = Integer.parseInt(tempo.getText());
            Song newSong = new Song(titleForSong, composerForSong, instrumentForSong, barForSong, tempoForSong);
            songlibrary.addSong(newSong);
            JOptionPane.showOptionDialog(null, "Song successfully added!", "Message", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);
            displaySongLibrary();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a panel containing the text fields for the users to fill in
    // the details of the song they want to add to the song library
    private void makeTextFieldsPanelForAddSong(JTextField title, JTextField composer, JTextField instrument,
            JTextField barCount, JTextField tempo) {
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(5, 1));
        textFieldsPane.add(new JLabel("Title:"));
        textFieldsPane.add(title);
        textFieldsPane.add(new JLabel("Composer:"));
        textFieldsPane.add(composer);
        textFieldsPane.add(new JLabel("Instrument:"));
        textFieldsPane.add(instrument);
        textFieldsPane.add(new JLabel("Number of bars:"));
        textFieldsPane.add(barCount);
        textFieldsPane.add(new JLabel("Tempo:"));
        textFieldsPane.add(tempo);
    }

    // MODIFIES: this
    // EFFECTS: Displays an option pane that allows the users to enter the title
    // of the song they want to remove from the song library, then removes the song
    // from the song library
    public void removeSong() {
        JTextField titleToRemove = new JTextField();
        makeTextFieldsPanelForRemoveSong(titleToRemove);

        optionPane = new JOptionPane();
        int result = JOptionPane.showConfirmDialog(optionPane, textFieldsPane,
                "Remove song", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            boolean checker = false;
            for (Song s : songlibrary.getSongList()) {
                if (titleToRemove.getText().equals(s.getTitle())) {
                    checker = songlibrary.removeSong(s);
                    break;
                }
            }
            if (checker) {
                JOptionPane.showOptionDialog(null, "Song successfully removed!", "Message", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, null, null);
            } else {
                JOptionPane.showOptionDialog(null, "No song with that title was found!", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            }
            displaySongLibrary();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a panel containing a text field for the users to enter
    // the title of the song they want to remove from the song library
    private void makeTextFieldsPanelForRemoveSong(JTextField titleToRemove) {
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(1, 1));
        textFieldsPane.add(new JLabel("Enter the title of the song you'd like to remove:"));
        textFieldsPane.add(titleToRemove);
    }

    // MODIFIES: this
    // EFFECTS: Displays an option pane that allows the users to enter the name
    // of the composer who they want to filter by, then displays the filtered song
    // library
    public void filterComposer() {
        optionPane = new JOptionPane();
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(1, 1));
        JTextField composerToFilter = new JTextField();
        textFieldsPane.add(new JLabel("Enter the name of the composer you want to filter by:"));
        textFieldsPane.add(composerToFilter);
        int result = JOptionPane.showConfirmDialog(optionPane, textFieldsPane,
                "Filter by composer", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            List<Song> filteredList = songlibrary.filterByComposer(composerToFilter.getText());
            if (filteredList.isEmpty()) {
                JOptionPane.showOptionDialog(null, "There are no songs with that composer", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            } else {
                filteredLibrary.setSongList(filteredList);
                displayFilteredLibraryMenu("composer", " \"" + composerToFilter.getText() + "\"");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays an option pane that allows the users to enter the name
    // of the instrument who they want to filter by, then displays the filtered song
    // library
    public void filterInstrument() {
        optionPane = new JOptionPane();
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(1, 1));
        JTextField instrumentToFilter = new JTextField();
        textFieldsPane.add(new JLabel("Enter the name of the instrument you want to filter by:"));
        textFieldsPane.add(instrumentToFilter);
        int result = JOptionPane.showConfirmDialog(optionPane, textFieldsPane,
                "Filter by instrument", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            List<Song> filteredList = songlibrary.filterByInstrument(instrumentToFilter.getText());
            if (filteredList.isEmpty()) {
                JOptionPane.showOptionDialog(null, "There are no songs with that instrument", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            } else {
                filteredLibrary.setSongList(filteredList);
                displayFilteredLibraryMenu("instrument", " \"" + instrumentToFilter.getText() + "\"");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the filtered song library page containing the list of songs
    // in the filtered song library
    public void displayFilteredLibraryMenu(String instrumentOrComposer, String name) {
        frame.setVisible(false);

        headerLabel = makeGenericHeader("Your Song Library Filtered By The " + instrumentOrComposer + name);
        panelForHeader = new JPanel();
        panelForHeader.setBackground(new Color(0, 0, 0, 0));
        panelForHeader.add(headerLabel);

        buttonsPanel = makeButtonsPanelFilteredLib();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(filteredLibrary.getSongList().size(), 1));
        mainPanel.setBackground(Color.WHITE);
        createFilteredLibraryContent();

        assembleWholePagePanelMonthlyProgress();

        constructFrame();
    }

    // MODIFIES: this
    // EFFECTS: adds content (titles of the songs in the filtered song library) to
    // the main panel of the filtered song library page
    private void createFilteredLibraryContent() {
        for (int i = 0; i < filteredLibrary.getSongList().size(); i++) {
            JLabel songTitle = new JLabel();
            songTitle.setText((i + 1) + ". " + filteredLibrary.getSongList().get(i).getTitle());
            songTitle.setForeground(Color.BLUE);
            songTitle.setFont(new Font("Futura", Font.BOLD, 20));
            mainPanel.add(songTitle);
        }
    }

    // MODIFIES: this
    // EFFECTS: creates the button panel for the filtered song library page
    private JPanel makeButtonsPanelFilteredLib() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(returnToMainButton);
        buttonsPanel.add(quitButton);

        return buttonsPanel;
    }

    // MODIFIES: this
    // EFFECTS: Displays an option pane that allows the users to enter the title
    // of the song whose details they want to view, sets that song as the current
    // song, then displays the song details page of that song
    public void songDetailsOptionPane() {
        optionPane = new JOptionPane();
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(1, 1));
        JTextField titleToView = new JTextField();
        textFieldsPane.add(new JLabel("Enter the title of the song whose details you want to view: "));
        textFieldsPane.add(titleToView);
        int result = JOptionPane.showConfirmDialog(optionPane, textFieldsPane,
                "View song details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            boolean checker = false;
            for (Song song1 : songlibrary.getSongList()) {
                if (titleToView.getText().equals(song1.getTitle())) {
                    checker = true;
                    this.currentSong = song1;
                    displaySongDetails();
                    break;
                }
            }

            if (!checker) {
                JOptionPane.showOptionDialog(null, "No song with that title was found.", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the song details page of the current song containing the
    // details (title, composer, instrument, bar count, tempo) of that current song
    public void displaySongDetails() {
        frame.setVisible(false);

        panelForHeader = makeHeaderPanelSongDetails();

        buttonsPanel = makeButtonsPanelSongDetails();

        additionalButtonPanel = makeAdditionalButtonPanel();

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));
        mainPanel.setBackground(new Color(139, 232, 203));
        addTitleToMainPanel();
        addComposerToMainPanel();
        addInstrumentToMainPanel();
        addBarCountToMainPanel();
        addTempoToMainPanel();

        assembleWholePagePanelSongDetails();

        constructFrame();
    }

    // MODIFIES: this
    // EFFECTS: creates a whole page panel which contains the entirety of the
    // contents of the song details page
    private void assembleWholePagePanelSongDetails() {
        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForHeader);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);
        wholePagePanel.add(additionalButtonPanel);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds tempo details of the current song into the main content panel
    // of the song details page
    private void addTempoToMainPanel() {
        JLabel tempoDisplay = new JLabel();
        tempoDisplay.setText("Tempo: " + currentSong.getTargetTempo());
        tempoDisplay.setForeground(Color.BLUE);
        tempoDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel tempoPanel = new JPanel();
        tempoPanel.setBackground(new Color(139, 232, 203));
        tempoPanel.add(tempoDisplay);
        mainPanel.add(tempoPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds bar count details of the current song into the main content
    // panel of the song details page
    private void addBarCountToMainPanel() {
        JLabel barDisplay = new JLabel();
        barDisplay.setText("Number of bars: " + currentSong.getBarNumber());
        barDisplay.setForeground(Color.BLUE);
        barDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel barPanel = new JPanel();
        barPanel.setBackground(new Color(139, 232, 203));
        barPanel.add(barDisplay);
        mainPanel.add(barPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds instrument details of the current song into the main content
    // panel of the song details page
    private void addInstrumentToMainPanel() {
        JLabel instrumentDisplay = new JLabel();
        instrumentDisplay.setText("Instrument: " + currentSong.getInstrument());
        instrumentDisplay.setForeground(Color.BLUE);
        instrumentDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel instrumentPanel = new JPanel();
        instrumentPanel.setBackground(new Color(139, 232, 203));
        instrumentPanel.add(instrumentDisplay);
        mainPanel.add(instrumentPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds composer details of the current song into the main content
    // panel of the song details page
    private void addComposerToMainPanel() {
        JLabel composerDisplay = new JLabel();
        composerDisplay.setText("Composer: " + currentSong.getComposer());
        composerDisplay.setForeground(Color.BLUE);
        composerDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel composerPanel = new JPanel();
        composerPanel.setBackground(new Color(139, 232, 203));
        composerPanel.add(composerDisplay);
        mainPanel.add(composerPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds title details of the current song into the main content
    // panel of the song details page
    private void addTitleToMainPanel() {
        JLabel titleDisplay = new JLabel();
        titleDisplay.setText("Title: " + currentSong.getTitle());
        titleDisplay.setForeground(Color.BLUE);
        titleDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel titPanel = new JPanel();
        titPanel.setBackground(new Color(139, 232, 203));
        titPanel.add(titleDisplay);
        mainPanel.add(titPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates the button panel for the song details page
    private JPanel makeButtonsPanelSongDetails() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(2, 2));
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(returnToMainButton);
        buttonsPanel.add(quitButton);
        buttonsPanel.add(monthlyProgressButton);
        buttonsPanel.add(practiceHistoryButton);

        return buttonsPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates a header panel for the song details page containing the
    // song title
    private JPanel makeHeaderPanelSongDetails() {
        headerLabel = makeGenericHeader(currentSong.getTitle());
        panelForHeader = new JPanel();
        panelForHeader.setBackground(new Color(0, 0, 0, 0));
        panelForHeader.add(headerLabel);
        return panelForHeader;
    }

    // MODIFIES: this
    // EFFECTS: creates and additional button panel containing the practice button
    // for the song details page
    private JPanel makeAdditionalButtonPanel() {
        additionalButtonPanel = new JPanel();
        additionalButtonPanel.add(practiceButton);
        additionalButtonPanel.setLayout(new GridLayout(1, 1));
        additionalButtonPanel.setBackground(new Color(2, 5, 98));
        return additionalButtonPanel;
    }

    // MODIFIES: this
    // EFFECTS: Displays an option pane that allows the users to start a practice
    // session with the current song, then displays the practicing page for that
    // current song
    public void practiceOptionPane() {
        optionPane = new JOptionPane();
        int result = JOptionPane.showOptionDialog(null, "Do you want to start a practice session with this song?",
                "Start practice session?",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            displayPractice();
        }
    }

    // MODIFIES: this
    // EFFECTS: starts a practice session and displays the practicing page of the
    // current song and monitors practice duration
    public void displayPractice() {
        frame.setVisible(false);
        initPracticeStartDateTime();

        headerLabel = makeGenericHeader(currentSong.getTitle());
        panelForHeader = new JPanel();
        panelForHeader.setBackground(new Color(0, 0, 0, 0));
        panelForHeader.add(headerLabel);

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(endPracticeButton);

        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForHeader);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(new JLabel(practicing));
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);

        constructFrame();
    }

    // MODIFIES: this
    // EFFECTS: Initializes the date and time in which a practice session was
    // started
    private void initPracticeStartDateTime() {
        currentInitialTime = System.currentTimeMillis();
        currentDate = LocalDate.now();
        currentStartTime = LocalTime.now();
    }

    // MODIFIES: this
    // EFFECTS: Displays an option pane that allows the users to end the current
    // practice sessioin, then displays the end practice page of the current session
    public void endPracticeOptionPane() {
        optionPane = new JOptionPane();
        int result = JOptionPane.showOptionDialog(null, "Do you want to end this practice session?",
                "End practice session?",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            displayEndPractice();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the end practice page of the current practice session
    // containing the practice duration of that practice session
    public void displayEndPractice() {
        frame.setVisible(false);
        initPracticeEndTime();

        headerLabel = makeGenericHeader(currentSong.getTitle());
        panelForHeader = new JPanel();
        panelForHeader.setBackground(new Color(0, 0, 0, 0));
        panelForHeader.add(headerLabel);

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(fillDetailsButton);

        JLabel practiceDuration = new JLabel("You practiced for " + currentFinalElapsedMinutes + " minutes!");
        practiceDuration.setForeground(Color.WHITE);
        practiceDuration.setFont(new Font("Futura", Font.BOLD, 30));

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(0, 0, 0, 0));
        mainPanel.add(practiceDuration);

        assembleWholePagePanelEndPractice();

        constructFrame();
    }

    // MODIFIES: this
    // EFFECTS: creates a whole page panel which contains the entirety of the
    // contents of the end practice page
    private void assembleWholePagePanelEndPractice() {
        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForHeader);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(mainPanel);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);
    }

    // MODIFIES: this
    // EFFECTS: initializes the time at which a practice session was ended, and
    // calculates the practice duration
    private void initPracticeEndTime() {
        currentEndTime = LocalTime.now();
        currentElapsedTime = System.currentTimeMillis() - currentInitialTime;
        currentFinalElapsedMinutes = convertDurationToMinutes(currentElapsedTime);
    }

    // EFFECTS: tries to ask the user to fill in the practice details of the current
    // practice session, then logs the current practice session into the session
    // list and day list of the current song. If a NumberFormatException or
    // ImpossiblePracticeDetailsException exception is thrown, then the app will
    // fail to log the current practice session and the user will have to re-fill
    // the practice details
    public void tryFillInPracticeDetails() {
        try {
            fillInPracticeDetails();
        } catch (ImpossiblePracticeDetailsException | NumberFormatException e1) {
            String part1 = "Tempo and bar numbers must be integers, ";
            String part2 = "overall song mastery must be a float between 0 and 10 inclusive,";
            String part3 = "and ending bar number must be greater than or equal to starting bar number!";
            JOptionPane.showOptionDialog(null, part1 + part2 + part3,
                    "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays an option pane that allows the users to fill in the details
    // of the current practice session then adds the practice session to the session
    // list and day list of the song.
    // Throws ImpossiblePracticeDetailsException if
    // 1. tempo, starting bar, or ending bar is 0 or below
    // 2. ending bar is smaller than starting bar
    // 3. mastery level is below 0 or greater than 10
    public void fillInPracticeDetails() throws ImpossiblePracticeDetailsException {
        JTextField practiceTempo = new JTextField();
        JTextField startingBar = new JTextField();
        JTextField endingBar = new JTextField();
        JTextField practiceMastery = new JTextField();
        makeTextFieldsPanelForSongDetails(practiceTempo, startingBar, endingBar, practiceMastery);

        optionPane = new JOptionPane();
        int result = JOptionPane.showConfirmDialog(optionPane, textFieldsPane,
                "Fill in your practice session details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            handlePracticeDetailsLogging(practiceTempo, startingBar, endingBar, practiceMastery);
        }
    }

    // MODIFIES: this
    // EFFECTS: collects the practice details of the current practice session and
    // logs the practice session to the session list and day list of the current
    // song
    // Throws ImpossiblePracticeDetailsException if
    // 1. tempo, starting bar, or ending bar is 0 or below
    // 2. ending bar is smaller than starting bar
    // 3. mastery level is below 0 or greater than 10
    private void handlePracticeDetailsLogging(JTextField practiceTempo, JTextField startingBar, JTextField endingBar,
            JTextField practiceMastery) throws ImpossiblePracticeDetailsException {
        int tempoToBeLogged = Integer.parseInt(practiceTempo.getText());
        int startBarToBeLogged = Integer.parseInt(startingBar.getText());
        int endBarToBeLogged = Integer.parseInt(endingBar.getText());
        float masteryToBeLogged = Float.parseFloat(practiceMastery.getText());

        boolean tempoOrBarNumbersLessThanOrEqualToZero = tempoToBeLogged <= 0 || startBarToBeLogged <= 0
                || endBarToBeLogged <= 0;
        boolean endBarSmallerThanStartBar = endBarToBeLogged < startBarToBeLogged;
        boolean masteryNotBetweenZeroAndTenInclusive = masteryToBeLogged > 10 || masteryToBeLogged < 0;

        if (tempoOrBarNumbersLessThanOrEqualToZero || endBarSmallerThanStartBar
                || masteryNotBetweenZeroAndTenInclusive) {
            throw new ImpossiblePracticeDetailsException();
        }

        Session session = new Session(tempoToBeLogged, startBarToBeLogged, endBarToBeLogged, masteryToBeLogged,
                currentElapsedTime, currentDate, currentStartTime, currentEndTime);
        currentSong.logSessionToSessionList(session);
        currentSong.logSessionToDay(session);
        JOptionPane.showOptionDialog(null, "Current practice session has been logged!", "Message",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, null, null);
        displaySongDetails();
    }

    // MODIFIES: this
    // EFFECTS: creates a panel containing the text fields for the users to fill in
    // the details of the current practice session
    private void makeTextFieldsPanelForSongDetails(JTextField tempo, JTextField startBar, JTextField endBar,
            JTextField mastery) {
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(4, 1));
        textFieldsPane.add(new JLabel("Practice tempo:"));
        textFieldsPane.add(tempo);
        textFieldsPane.add(new JLabel("Starting bar:"));
        textFieldsPane.add(startBar);
        textFieldsPane.add(new JLabel("Ending bar:"));
        textFieldsPane.add(endBar);
        textFieldsPane.add(new JLabel("Overall song mastery (0-10):"));
        textFieldsPane.add(mastery);
    }

    // EFFECTS: displays an option pane stating that the user has not started any
    // practice sessions if that is indeed the case, otherwise display practice
    // history page
    public void handlePracticeHistoryButton() {
        if (currentSong.getSessions().isEmpty()) {
            JOptionPane.showOptionDialog(null, "You have not started any practice sessions yet", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

        } else {
            displayPracticeHistory();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the practice history page of the current song containing
    // all the practice session of that song along with their details (date, time,
    // duration, tempo, bars practiced, mastery level)
    public void displayPracticeHistory() {
        frame.setVisible(false);

        headerLabel = makeGenericHeader("Practice history for " + currentSong.getTitle());
        panelForHeader = new JPanel();
        panelForHeader.setBackground(new Color(0, 0, 0, 0));
        panelForHeader.add(headerLabel);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(returnToDetailsButton);
        buttonsPanel.add(quitButton);

        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForHeader);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);
        wholePagePanel.add(Box.createVerticalStrut(40));

        constructMainPanelsPracticeHistory();

        constructFrame();
    }

    // MODIFIES: this
    // EFFECTS: constructs the main panel of the practice history page, which
    // consists of all the practice sessions of the current song along with their
    // details (date, time, duration, tempo, bars practiced, mastery level)
    private void constructMainPanelsPracticeHistory() {
        for (int i = 0; i < currentSong.getSessions().size(); i++) {
            Session session = currentSong.getSessions().get(i);
            String formattedDate = formatDate(session.getPracticeDate());
            String formattedStartTime = formatTime(session.getStartTime());
            String formattedEndTime = formatTime(session.getEndTime());
            float finalDuration = convertDurationToMinutes(session.getDuration());

            mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(5, 1));
            if (i % 2 == 0) {
                mainPanel.setBackground(Color.WHITE);
            } else {
                mainPanel.setBackground(new Color(139, 232, 203));
            }

            addLabelsForPracticeHistory(formattedDate + " " + formattedStartTime + "-" + formattedEndTime);
            addLabelsForPracticeHistory("Tempo: " + session.getTempo());
            addLabelsForPracticeHistory("Bars practiced: " + session.getStartBar() + "-" + session.getEndBar());
            addLabelsForPracticeHistory("Overall mastery: " + session.getOverallMastery());
            addLabelsForPracticeHistory("Duration: " + finalDuration + " minutes");

            wholePagePanel.add(mainPanel);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a label with a certain string to the main panel of the practice
    // history page. Used to add labels containing the practice session details into
    // the main panel
    private void addLabelsForPracticeHistory(String s) {
        JLabel label = new JLabel();
        label.setText(s);
        label.setForeground(Color.BLUE);
        label.setFont(new Font("Futura", Font.BOLD, 20));
        mainPanel.add(label);
    }

    // EFFECTS: displays an option pane stating that the user has not started any
    // practice sessions if that is indeed the case, otherwise display monthly
    // practice progress page
    public void handleMonthlyProgressButton() {
        if (currentSong.getSessions().isEmpty()) {
            JOptionPane.showOptionDialog(null, "You have not started any practice sessions yet", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
        } else {
            monthlyProgressOptionPanel();
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays an option pane that allows the users to enter the month and
    // year whose practice progress report they want to view
    public void monthlyProgressOptionPanel() {
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        JComboBox month = new JComboBox<>(months);
        JTextField year = new JTextField();
        makeTextFieldsPaneForMonthlyProgress(month, year);

        optionPane = new JOptionPane();
        int result = JOptionPane.showConfirmDialog(optionPane, textFieldsPane,
                "Enter month/year for progress report", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String monthString = (String) month.getSelectedItem();
            Month monthToView = convertStringToMonth(monthString);
            int yearToView = Integer.parseInt(year.getText());
            List<Day> daysInMonth = currentSong.returnDaysInMonth(monthToView, yearToView);
            if (daysInMonth.isEmpty()) {
                JOptionPane.showOptionDialog(null, "No practice session in that month of the year was found.",
                        "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            } else {
                displayMonthlyProgress(monthString, yearToView, daysInMonth);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a panel containing the text field and combo box for the
    // users to enter the month and year whose practice progress report they want
    // to view
    private void makeTextFieldsPaneForMonthlyProgress(JComboBox month, JTextField year) {
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(2, 1));
        textFieldsPane.add(new JLabel("Month to view:"));
        textFieldsPane.add(month);
        textFieldsPane.add(new JLabel("Year to view:"));
        textFieldsPane.add(year);
    }

    // MODIFIES: this
    // EFFECTS: displays the monthly practice progress page of the current song
    // containing the user's practice progress in terms of daily total practice
    // duration, daily average tempo, daily average mastery level, and daily bars
    // practiced for all days in the given month of the year in which at least one
    // practice session was started
    public void displayMonthlyProgress(String month, int year, List<Day> daysInMonth) {
        frame.setVisible(false);

        headerLabel = makeGenericHeader("Monthly progress report for " + month + " " + year);
        panelForHeader = new JPanel();
        panelForHeader.setBackground(new Color(0, 0, 0, 0));
        panelForHeader.add(headerLabel);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(returnToDetailsButton);
        buttonsPanel.add(quitButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 4));
        mainPanel.setBackground(new Color(0, 0, 0, 0));
        displayDurationProgress(daysInMonth);
        displayTempoProgress(daysInMonth);
        displayMasteryProgress(daysInMonth);
        displayBarsPracticedProgress(daysInMonth);

        assembleWholePagePanelMonthlyProgress();

        constructFrame();
    }

    // MODIFIES: this
    // EFFECTS: creates a whole page panel which contains the entirety of the
    // contents of the monthly practice history progress page
    private void assembleWholePagePanelMonthlyProgress() {
        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForHeader);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(mainPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel that displays the total practice duration in a given
    // day for all days in the dayList, then adds the panel to the main content
    // panel of the monthly practice progress page
    public void displayDurationProgress(List<Day> dayList) {
        JPanel durationPanel = new JPanel();
        durationPanel.setLayout(new GridLayout(1 + dayList.size(), 1));
        durationPanel.setBackground(Color.WHITE);
        JLabel subHeader = new JLabel("Daily total duration progress: ");
        subHeader.setForeground(Color.BLUE);
        subHeader.setFont(new Font("Futura", Font.PLAIN, 20));
        durationPanel.add(subHeader);
        for (Day day : dayList) {
            String formattedDate = formatDate(day.getLocalDate());
            float finalDuration = convertDurationToMinutes(day.getTotalDuration());
            JLabel content = new JLabel(formattedDate + ": " + finalDuration + " minutes");
            content.setFont(new Font("Arial", Font.PLAIN, 15));
            durationPanel.add(content);
        }
        mainPanel.add(durationPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel that displays the average practice tempo in a given
    // day for all days in the dayList, then adds the panel to the main content
    // panel of the monthly practice progress page
    public void displayTempoProgress(List<Day> dayList) {
        JPanel tempoPanel = new JPanel();
        tempoPanel.setLayout(new GridLayout(1 + dayList.size(), 1));
        tempoPanel.setBackground(new Color(139, 232, 203));
        JLabel subHeader = new JLabel("Daily average tempo progress: ");
        subHeader.setForeground(Color.BLUE);
        subHeader.setFont(new Font("Futura", Font.PLAIN, 20));
        tempoPanel.add(subHeader);
        for (Day day : dayList) {
            String formattedDate = formatDate(day.getLocalDate());
            JLabel content = new JLabel(formattedDate + ": " + day.getAverageTempo() + "\n");
            content.setFont(new Font("Arial", Font.PLAIN, 15));
            tempoPanel.add(content);
        }
        mainPanel.add(tempoPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel that displays the average practice mastery level in
    // a given day for all days in the dayList, then adds the panel to the main
    // content panel of the monthly practice progress page
    public void displayMasteryProgress(List<Day> dayList) {
        JPanel masteryPanel = new JPanel();
        masteryPanel.setLayout(new GridLayout(1 + dayList.size(), 1));
        masteryPanel.setBackground(new Color(204, 139, 134));
        JLabel subHeader = new JLabel("Daily average mastery progress: ");
        subHeader.setForeground(Color.BLUE);
        subHeader.setFont(new Font("Futura", Font.PLAIN, 20));
        masteryPanel.add(subHeader);
        for (Day day : dayList) {
            String formattedDate = formatDate(day.getLocalDate());
            JLabel content = new JLabel(formattedDate + ": " + day.getAverageMastery());
            content.setFont(new Font("Arial", Font.PLAIN, 15));
            masteryPanel.add(content);
        }
        mainPanel.add(masteryPanel);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel that displays all bars practiced in a given
    // day for all days in the dayList, then adds the panel to the main content
    // panel of the monthly practice progress page
    public void displayBarsPracticedProgress(List<Day> dayList) {
        JPanel barPanel = new JPanel();
        barPanel.setLayout(new GridLayout(1 + dayList.size(), 1));
        barPanel.setBackground(new Color(255, 230, 109));
        JLabel subHeader = new JLabel("Daily bars practiced progress: ");
        subHeader.setForeground(Color.BLUE);
        subHeader.setFont(new Font("Futura", Font.PLAIN, 20));
        barPanel.add(subHeader);
        addBarsPracticedProgressToBarPanel(dayList, barPanel);
        mainPanel.add(barPanel);
    }

    // MODIFIES: this, barPanel
    // EFFECTS: creates a label displaying all bars practiced in a given
    // day for all days in the dayList, then adds the labels to barPanel
    private void addBarsPracticedProgressToBarPanel(List<Day> dayList, JPanel barPanel) {
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
            JLabel content = new JLabel(formattedDate + ": " + allBarsPracticed);
            content.setFont(new Font("Arial", Font.PLAIN, 15));
            barPanel.add(content);
        }
    }

    // MODIFIES: this
    // EFFECTS: displays an option pane that confirms that the user wishes to quit
    // the app, then displays a option pane with a thank you message, then exits the
    // program
    public void quitApp() {
        optionPane = new JOptionPane();
        int result = JOptionPane.showOptionDialog(null, "Do you want to quit Conmoto Music Practice App?",
                "Quit app?",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            JOptionPane.showOptionDialog(null,
                    "Thank you for using Conmoto Music Practice App! See you next time!", "Goodbye!",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);
            System.exit(0);
        }
    }

    // MODIFIES: this
    // EFFECTS: displays an option pane that confirms that the user wishes to save
    // SongLibrary to file, then saves SongLibrary to file
    private void saveSongLibrary() {
        optionPane = new JOptionPane();
        int result = JOptionPane.showConfirmDialog(optionPane, "Are you sure you want to save file?",
                "Save file?", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                jsonWriter.open();
                jsonWriter.write(songlibrary);
                jsonWriter.close();
                JOptionPane.showOptionDialog(null,
                        "Saved SongLibrary" + " to " + JSON_STORE,
                        "Message",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                displaySongLibrary();

            } catch (FileNotFoundException e) {
                JOptionPane.showOptionDialog(null,
                        "Unable to write to file: " + JSON_STORE,
                        "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                displaySongLibrary();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays an option pane that confirms that the user wishes to load
    // SongLibrary from file, then loads SongLibrary from file
    private void loadSongLibrary() {
        optionPane = new JOptionPane();
        int result = JOptionPane.showConfirmDialog(optionPane, "Are you sure you want to load file?",
                "Load file?", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                songlibrary = jsonReader.read();
                JOptionPane.showOptionDialog(null,
                        "Loaded SongLibrary" + " from " + JSON_STORE,
                        "Message",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                displaySongLibrary();

            } catch (IOException e) {
                JOptionPane.showOptionDialog(null,
                        "Unable to read from file: " + JSON_STORE,
                        "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
                displaySongLibrary();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: A reusable helper method that creates a generic header for a page
    // with the text s
    private JLabel makeGenericHeader(String s) {
        headerLabel = new JLabel();
        headerLabel.setText(s);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Futura", Font.BOLD, 30));
        return headerLabel;
    }

    // MODIFIES: this
    // EFFECTS: A reusable helper method that constructs and initializes a new
    // frame. This method is used to construct the frame for all pages in the app
    private void constructFrame() {
        frame = new JFrame();
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(wholePagePanel);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        frame.pack();
    }

    // MODIFIES: this
    // EFFECTS: A reusable helper method that creates a button with the text s
    private JButton buttonFactory(String s) {
        JButton newButton = new JButton();
        newButton.setText(s);
        newButton.addActionListener(this);
        newButton.setFocusable(false);
        newButton.setFont(new Font("Futura", Font.PLAIN, 20));
        newButton.setForeground(new Color(2, 5, 98));
        return newButton;
    }

    // EFFECTS: handles button presses from the user
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startAppButton) {
            displaySongLibrary();
        } else if (e.getSource() == addSongButton) {
            tryAddSong();
        } else if (e.getSource() == removeSongButton) {
            removeSong();
        } else if (e.getSource() == filterComposerButton) {
            filterComposer();
        } else if (e.getSource() == filterInstrumentButton) {
            filterInstrument();
        } else if (e.getSource() == returnToMainButton) {
            displaySongLibrary();
        } else if (e.getSource() == detailsButton) {
            songDetailsOptionPane();
        } else if (e.getSource() == returnToDetailsButton) {
            displaySongDetails();
        } else if (e.getSource() == monthlyProgressButton) {
            handleMonthlyProgressButton();
        } else if (e.getSource() == practiceHistoryButton) {
            handlePracticeHistoryButton();
        } else if (e.getSource() == practiceButton) {
            practiceOptionPane();
        } else if (e.getSource() == endPracticeButton) {
            endPracticeOptionPane();
        } else if (e.getSource() == fillDetailsButton) {
            tryFillInPracticeDetails();
        } else if (e.getSource() == quitButton) {
            quitApp();
        } else if (e.getSource() == saveButton) {
            saveSongLibrary();
        } else if (e.getSource() == loadButton) {
            loadSongLibrary();
        }
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

}
