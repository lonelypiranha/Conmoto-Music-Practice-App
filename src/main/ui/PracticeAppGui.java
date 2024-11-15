package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Session;
import model.Song;
import model.SongLibrary;

public class PracticeAppGui implements ActionListener {
    JFrame frame;
    JOptionPane optionPane;
    JLabel labelOpening;
    ImageIcon logo;
    ImageIcon practicing;
    JButton buttonOpening;
    JPanel panelOpening;
    JPanel panelButton;
    JPanel panelLabel;
    JPanel textFieldsPane;
    JPanel wholePagePanel;
    JPanel mainPanel;

    JLabel headerLabel;
    JPanel panelForTitle;
    JPanel buttonsPanel;
    JButton addSongButton;
    JButton removeSongButton;
    JButton detailsButton;
    JButton filterInstrumentButton;
    JButton filterComposerButton;
    JButton loadButton;
    JButton saveButton;
    JButton returnToMainButton;
    JButton practiceButton;
    JButton practiceHistoryButton;
    JButton monthlyProgressButton;
    JButton endPracticeButton;
    JButton fillDetailsButton;
    JButton returnToDetailsButton;
    JButton quitButton;

    SongLibrary songlibrary;
    SongLibrary filteredLibrary;

    Song currentSong;
    long currentInitialTime;
    LocalDate currentDate;
    LocalTime currentStartTime;
    LocalTime currentEndTime;
    long currentElapsedTime;
    float currentFinalElapsedMinutes;

    public PracticeAppGui() {
        frame = new JFrame();
        panelOpening = new JPanel();
        panelButton = new JPanel();
        panelLabel = new JPanel();
        logo = new ImageIcon("src/main/ui/on-2.png");
        practicing = new ImageIcon("src/main/ui/practicing2.png");
        songlibrary = new SongLibrary();
        filteredLibrary = new SongLibrary();

        labelOpening = new JLabel();
        labelOpening.setIcon(logo);
        labelOpening.setText("Welcome To Conmoto Music Practice App!");
        labelOpening.setHorizontalTextPosition(JLabel.CENTER);
        labelOpening.setVerticalTextPosition(JLabel.BOTTOM);
        labelOpening.setForeground(Color.WHITE);
        labelOpening.setFont(new Font("Futura", Font.BOLD, 30));
        labelOpening.setIconTextGap(-50);
        labelOpening.setHorizontalAlignment(JLabel.CENTER);
        labelOpening.setVerticalAlignment(JLabel.TOP);

        buttonOpening = new JButton();
        buttonOpening.setText("Start App");
        buttonOpening.setSize(100, 100);
        buttonOpening.setHorizontalAlignment(JButton.CENTER);
        buttonOpening.setVerticalAlignment(JButton.CENTER);
        buttonOpening.addActionListener(this);
        buttonOpening.setFocusable(false);
        buttonOpening.setHorizontalTextPosition(JButton.CENTER);
        buttonOpening.setVerticalTextPosition(JButton.CENTER);
        buttonOpening.setFont(new Font("Futura", Font.PLAIN, 20));
        buttonOpening.setForeground(new Color(2, 5, 98));

        panelOpening.setBackground(new Color(0, 0, 0, 0));
        panelButton.setBackground(new Color(0, 0, 0, 0));
        panelLabel.setBackground(new Color(0, 0, 0, 0));
        panelButton.setLayout(new GridBagLayout());
        panelButton.add(buttonOpening);
        panelLabel.add(labelOpening);

        panelOpening.setLayout(new BoxLayout(panelOpening, BoxLayout.PAGE_AXIS));
        panelOpening.add(panelLabel);
        panelOpening.add(Box.createVerticalStrut(40));
        panelOpening.add(panelButton);

        frame.setSize(1000, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(panelOpening);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(2, 5, 98));
    }

    public void displaySongList() {
        frame.setVisible(false);
        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        frame.setVisible(true);

        headerLabel = new JLabel();
        headerLabel.setText("Your Song Library");
        headerLabel.setHorizontalTextPosition(JLabel.CENTER);
        headerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Futura", Font.BOLD, 30));
        headerLabel.setIconTextGap(-50);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setVerticalAlignment(JLabel.TOP);

        panelForTitle = new JPanel();
        panelForTitle.setBackground(new Color(0, 0, 0, 0));
        panelForTitle.add(headerLabel);

        addSongButton = new JButton();
        addSongButton.setText("Add a song");
        addSongButton.setSize(100, 100);
        addSongButton.setHorizontalAlignment(JButton.CENTER);
        addSongButton.setVerticalAlignment(JButton.CENTER);
        addSongButton.addActionListener(this);
        addSongButton.setFocusable(false);
        addSongButton.setHorizontalTextPosition(JButton.CENTER);
        addSongButton.setVerticalTextPosition(JButton.CENTER);
        addSongButton.setFont(new Font("Futura", Font.PLAIN, 20));
        addSongButton.setForeground(new Color(2, 5, 98));

        removeSongButton = new JButton();
        removeSongButton.setText("Remove a song");
        removeSongButton.setSize(100, 100);
        removeSongButton.setHorizontalAlignment(JButton.CENTER);
        removeSongButton.setVerticalAlignment(JButton.CENTER);
        removeSongButton.addActionListener(this);
        removeSongButton.setFocusable(false);
        removeSongButton.setFont(new Font("Futura", Font.PLAIN, 20));
        removeSongButton.setForeground(new Color(2, 5, 98));

        detailsButton = new JButton();
        detailsButton.setText("See song details");
        detailsButton.setSize(100, 100);
        detailsButton.setHorizontalAlignment(JButton.CENTER);
        detailsButton.setVerticalAlignment(JButton.CENTER);
        detailsButton.addActionListener(this);
        detailsButton.setFocusable(false);
        detailsButton.setFont(new Font("Futura", Font.PLAIN, 20));
        detailsButton.setForeground(new Color(2, 5, 98));

        filterInstrumentButton = new JButton();
        filterInstrumentButton.setText("Filter song list by instrument");
        filterInstrumentButton.setSize(100, 100);
        filterInstrumentButton.setHorizontalAlignment(JButton.CENTER);
        filterInstrumentButton.setVerticalAlignment(JButton.CENTER);
        filterInstrumentButton.addActionListener(this);
        filterInstrumentButton.setFocusable(false);
        filterInstrumentButton.setFont(new Font("Futura", Font.PLAIN, 20));
        filterInstrumentButton.setForeground(new Color(2, 5, 98));

        filterComposerButton = new JButton();
        filterComposerButton.setText("Filter song list by composer name");
        filterComposerButton.setSize(100, 100);
        filterComposerButton.setHorizontalAlignment(JButton.CENTER);
        filterComposerButton.setVerticalAlignment(JButton.CENTER);
        filterComposerButton.addActionListener(this);
        filterComposerButton.setFocusable(false);
        filterComposerButton.setFont(new Font("Futura", Font.PLAIN, 20));
        filterComposerButton.setForeground(new Color(2, 5, 98));

        loadButton = new JButton();
        loadButton.setText("Load file");
        loadButton.setSize(100, 100);
        loadButton.setHorizontalAlignment(JButton.CENTER);
        loadButton.setVerticalAlignment(JButton.CENTER);
        loadButton.addActionListener(this);
        loadButton.setFocusable(false);
        loadButton.setFont(new Font("Futura", Font.PLAIN, 20));
        loadButton.setForeground(new Color(2, 5, 98));

        saveButton = new JButton();
        saveButton.setText("Save file");
        saveButton.setSize(100, 100);
        saveButton.setHorizontalAlignment(JButton.CENTER);
        saveButton.setVerticalAlignment(JButton.CENTER);
        saveButton.addActionListener(this);
        saveButton.setFocusable(false);
        saveButton.setFont(new Font("Futura", Font.PLAIN, 20));
        saveButton.setForeground(new Color(2, 5, 98));

        quitButton = new JButton();
        quitButton.setText("Quit app");
        quitButton.setSize(100, 100);
        quitButton.setHorizontalAlignment(JButton.CENTER);
        quitButton.setVerticalAlignment(JButton.CENTER);
        quitButton.addActionListener(this);
        quitButton.setFocusable(false);
        quitButton.setFont(new Font("Futura", Font.PLAIN, 20));
        quitButton.setForeground(new Color(2, 5, 98));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 3));
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(addSongButton);
        buttonsPanel.add(removeSongButton);
        buttonsPanel.add(detailsButton);
        buttonsPanel.add(filterComposerButton);
        buttonsPanel.add(filterInstrumentButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(quitButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(songlibrary.getSongList().size(), 1));
        mainPanel.setBackground(Color.WHITE);

        if (songlibrary.getSongList().isEmpty()) {
            JLabel emptiness = new JLabel();
            emptiness.setText("Your song library is empty. Add a song!");
            emptiness.setForeground(Color.BLUE);
            emptiness.setFont(new Font("Futura", Font.BOLD, 20));
            mainPanel.add(emptiness);
        } else {
            for (int i = 0; i < songlibrary.getSongList().size(); i++) {
                JLabel songTitle = new JLabel();
                songTitle.setText((i + 1) + ". " + songlibrary.getSongList().get(i).getTitle());
                songTitle.setForeground(Color.BLUE);
                songTitle.setFont(new Font("Futura", Font.BOLD, 20));
                mainPanel.add(songTitle);
            }
        }

        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForTitle);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(mainPanel);

        frame.add(wholePagePanel);
    }

    public void addSong() {
        optionPane = new JOptionPane();
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(5, 1));
        JTextField title = new JTextField();
        JTextField composer = new JTextField();
        JTextField instrument = new JTextField();
        JTextField barCount = new JTextField();
        JTextField tempo = new JTextField();
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
            frame.setVisible(false);
            displaySongList();
        }
    }

    public void removeSong() {
        optionPane = new JOptionPane();
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(1, 1));
        JTextField titleToRemove = new JTextField();
        textFieldsPane.add(new JLabel("Enter the title of the song you'd like to remove:"));
        textFieldsPane.add(titleToRemove);
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
            frame.setVisible(false);
            displaySongList();
        }
    }

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
                frame.setVisible(false);
                displayFilteredLibraryMenu("composer", " \"" + composerToFilter.getText() + "\"");
            }
        }
    }

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
                frame.setVisible(false);
                displayFilteredLibraryMenu("instrument", " \"" + instrumentToFilter.getText() + "\"");
            }
        }
    }

    public void displayFilteredLibraryMenu(String instrumentOrComposer, String name) {
        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        frame.setVisible(true);

        headerLabel = new JLabel();
        headerLabel.setText("Your Song Library Filtered By The " + instrumentOrComposer + name);
        headerLabel.setHorizontalTextPosition(JLabel.CENTER);
        headerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Futura", Font.BOLD, 30));
        headerLabel.setIconTextGap(-50);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setVerticalAlignment(JLabel.TOP);

        panelForTitle = new JPanel();
        panelForTitle.setBackground(new Color(0, 0, 0, 0));
        panelForTitle.add(headerLabel);

        // removeSongButton = new JButton();
        // removeSongButton.setText("Remove a song");
        // removeSongButton.setSize(100, 100);
        // removeSongButton.setHorizontalAlignment(JButton.CENTER);
        // removeSongButton.setVerticalAlignment(JButton.CENTER);
        // removeSongButton.addActionListener(this);
        // removeSongButton.setFocusable(false);
        // removeSongButton.setFont(new Font("Futura", Font.PLAIN, 20));
        // removeSongButton.setForeground(new Color(2, 5, 98));

        // detailsButton = new JButton();
        // detailsButton.setText("See song details");
        // detailsButton.setSize(100, 100);
        // detailsButton.setHorizontalAlignment(JButton.CENTER);
        // detailsButton.setVerticalAlignment(JButton.CENTER);
        // detailsButton.addActionListener(this);
        // detailsButton.setFocusable(false);
        // detailsButton.setFont(new Font("Futura", Font.PLAIN, 20));
        // detailsButton.setForeground(new Color(2, 5, 98));

        returnToMainButton = new JButton();
        returnToMainButton.setText("Return to full song library");
        returnToMainButton.setSize(100, 100);
        returnToMainButton.setHorizontalAlignment(JButton.CENTER);
        returnToMainButton.setVerticalAlignment(JButton.CENTER);
        returnToMainButton.addActionListener(this);
        returnToMainButton.setFocusable(false);
        returnToMainButton.setFont(new Font("Futura", Font.PLAIN, 20));
        returnToMainButton.setForeground(new Color(2, 5, 98));

        quitButton = new JButton();
        quitButton.setText("Quit app");
        quitButton.setSize(100, 100);
        quitButton.setHorizontalAlignment(JButton.CENTER);
        quitButton.setVerticalAlignment(JButton.CENTER);
        quitButton.addActionListener(this);
        quitButton.setFocusable(false);
        quitButton.setFont(new Font("Futura", Font.PLAIN, 20));
        quitButton.setForeground(new Color(2, 5, 98));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.setBackground(new Color(2, 5, 98));
        // barSongList.add(removeSongButton);
        // barSongList.add(detailsButton);
        buttonsPanel.add(returnToMainButton);
        buttonsPanel.add(quitButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(filteredLibrary.getSongList().size(), 1));
        mainPanel.setBackground(Color.WHITE);

        for (int i = 0; i < filteredLibrary.getSongList().size(); i++) {
            JLabel songTitle = new JLabel();
            songTitle.setText((i + 1) + ". " + filteredLibrary.getSongList().get(i).getTitle());
            songTitle.setForeground(Color.BLUE);
            songTitle.setFont(new Font("Futura", Font.BOLD, 20));
            mainPanel.add(songTitle);
        }

        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForTitle);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(mainPanel);

        frame.add(wholePagePanel);
    }

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

    public void displaySongDetails() {
        frame.setVisible(false);
        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        frame.setVisible(true);

        headerLabel = new JLabel();
        headerLabel.setText(currentSong.getTitle());
        headerLabel.setHorizontalTextPosition(JLabel.CENTER);
        headerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Futura", Font.BOLD, 30));
        headerLabel.setIconTextGap(-50);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setVerticalAlignment(JLabel.TOP);

        panelForTitle = new JPanel();
        panelForTitle.setBackground(new Color(0, 0, 0, 0));
        panelForTitle.add(headerLabel);

        returnToMainButton = new JButton();
        returnToMainButton.setText("Return to full song library");
        returnToMainButton.setSize(100, 100);
        returnToMainButton.setHorizontalAlignment(JButton.CENTER);
        returnToMainButton.setVerticalAlignment(JButton.CENTER);
        returnToMainButton.addActionListener(this);
        returnToMainButton.setFocusable(false);
        returnToMainButton.setFont(new Font("Futura", Font.PLAIN, 20));
        returnToMainButton.setForeground(new Color(2, 5, 98));

        quitButton = new JButton();
        quitButton.setText("Quit app");
        quitButton.setSize(100, 100);
        quitButton.setHorizontalAlignment(JButton.CENTER);
        quitButton.setVerticalAlignment(JButton.CENTER);
        quitButton.addActionListener(this);
        quitButton.setFocusable(false);
        quitButton.setFont(new Font("Futura", Font.PLAIN, 20));
        quitButton.setForeground(new Color(2, 5, 98));

        monthlyProgressButton = new JButton();
        monthlyProgressButton.setText("View monthly progress");
        monthlyProgressButton.setSize(100, 100);
        monthlyProgressButton.setHorizontalAlignment(JButton.CENTER);
        monthlyProgressButton.setVerticalAlignment(JButton.CENTER);
        monthlyProgressButton.addActionListener(this);
        monthlyProgressButton.setFocusable(false);
        monthlyProgressButton.setFont(new Font("Futura", Font.PLAIN, 20));
        monthlyProgressButton.setForeground(new Color(2, 5, 98));

        practiceHistoryButton = new JButton();
        practiceHistoryButton.setText("View practice history");
        practiceHistoryButton.setSize(100, 100);
        practiceHistoryButton.setHorizontalAlignment(JButton.CENTER);
        practiceHistoryButton.setVerticalAlignment(JButton.CENTER);
        practiceHistoryButton.addActionListener(this);
        practiceHistoryButton.setFocusable(false);
        practiceHistoryButton.setFont(new Font("Futura", Font.PLAIN, 20));
        practiceHistoryButton.setForeground(new Color(2, 5, 98));

        practiceButton = new JButton();
        practiceButton.setText("Practice song");
        practiceButton.setSize(100, 100);
        practiceButton.setHorizontalAlignment(JButton.CENTER);
        practiceButton.setVerticalAlignment(JButton.CENTER);
        practiceButton.addActionListener(this);
        practiceButton.setFocusable(false);
        practiceButton.setFont(new Font("Futura", Font.PLAIN, 20));
        practiceButton.setForeground(new Color(2, 5, 98));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(returnToMainButton);
        buttonsPanel.add(quitButton);
        buttonsPanel.add(monthlyProgressButton);
        buttonsPanel.add(practiceHistoryButton);
        buttonsPanel.add(practiceButton);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(5, 1));
        mainPanel.setBackground(Color.WHITE);

        JLabel titleDisplay = new JLabel();
        titleDisplay.setText("Title: " + currentSong.getTitle());
        titleDisplay.setForeground(Color.BLUE);
        titleDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel titPanel = new JPanel();
        titPanel.add(titleDisplay);
        mainPanel.add(titPanel);

        JLabel composerDisplay = new JLabel();
        composerDisplay.setText("Composer: " + currentSong.getComposer());
        composerDisplay.setForeground(Color.BLUE);
        composerDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel composerPanel = new JPanel();
        composerPanel.add(composerDisplay);
        mainPanel.add(composerPanel);

        JLabel instrumentDisplay = new JLabel();
        instrumentDisplay.setText("Instrument: " + currentSong.getInstrument());
        instrumentDisplay.setForeground(Color.BLUE);
        instrumentDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel instrumentPanel = new JPanel();
        instrumentPanel.add(instrumentDisplay);
        mainPanel.add(instrumentPanel);

        JLabel barDisplay = new JLabel();
        barDisplay.setText("Number of bars: " + currentSong.getBarNumber());
        barDisplay.setForeground(Color.BLUE);
        barDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel barPanel = new JPanel();
        barPanel.add(barDisplay);
        mainPanel.add(barPanel);

        JLabel tempoDisplay = new JLabel();
        tempoDisplay.setText("Tempo: " + currentSong.getTargetTempo());
        tempoDisplay.setForeground(Color.BLUE);
        tempoDisplay.setFont(new Font("Futura", Font.BOLD, 20));
        JPanel tempoPanel = new JPanel();
        tempoPanel.add(tempoDisplay);
        mainPanel.add(tempoPanel);

        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForTitle);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(mainPanel);

        frame.add(wholePagePanel);
    }

    public void practiceOptionPane() {
        optionPane = new JOptionPane();
        int result = JOptionPane.showOptionDialog(null, "Do you want to start a practice session with this song?",
                "Start practice session?",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            frame.setVisible(false);
            displayPractice();
        }
    }

    public void displayPractice() {
        currentInitialTime = System.currentTimeMillis();
        currentDate = LocalDate.now();
        currentStartTime = LocalTime.now();

        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        frame.setVisible(true);

        headerLabel = new JLabel();
        headerLabel.setText(currentSong.getTitle());
        headerLabel.setHorizontalTextPosition(JLabel.CENTER);
        headerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Futura", Font.BOLD, 30));
        headerLabel.setIconTextGap(-50);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setVerticalAlignment(JLabel.TOP);

        panelForTitle = new JPanel();
        panelForTitle.setBackground(new Color(0, 0, 0, 0));
        panelForTitle.add(headerLabel);

        endPracticeButton = new JButton();
        endPracticeButton.setText("End practice");
        endPracticeButton.setSize(100, 100);
        endPracticeButton.setHorizontalAlignment(JButton.CENTER);
        endPracticeButton.setVerticalAlignment(JButton.CENTER);
        endPracticeButton.addActionListener(this);
        endPracticeButton.setFocusable(false);
        endPracticeButton.setFont(new Font("Futura", Font.PLAIN, 20));
        endPracticeButton.setForeground(new Color(2, 5, 98));

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(endPracticeButton);

        JLabel practicingLabel = new JLabel(practicing);

        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForTitle);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(practicingLabel);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);

        frame.add(wholePagePanel);
    }

    public void endPracticeOptionPane() {
        optionPane = new JOptionPane();
        int result = JOptionPane.showOptionDialog(null, "Do you want to end this practice session?",
                "End practice session?",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (result == JOptionPane.OK_OPTION) {
            displayEndPractice();
        }
    }

    public void displayEndPractice() {
        currentEndTime = LocalTime.now();
        currentElapsedTime = System.currentTimeMillis() - currentInitialTime;
        currentFinalElapsedMinutes = convertDurationToMinutes(currentElapsedTime);

        frame.setVisible(false);
        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        frame.setVisible(true);

        headerLabel = new JLabel();
        headerLabel.setText(currentSong.getTitle());
        headerLabel.setHorizontalTextPosition(JLabel.CENTER);
        headerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Futura", Font.BOLD, 30));
        headerLabel.setIconTextGap(-50);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setVerticalAlignment(JLabel.TOP);

        panelForTitle = new JPanel();
        panelForTitle.setBackground(new Color(0, 0, 0, 0));
        panelForTitle.add(headerLabel);

        fillDetailsButton = new JButton();
        fillDetailsButton.setText("Fill in practice details");
        fillDetailsButton.setSize(100, 100);
        fillDetailsButton.setHorizontalAlignment(JButton.CENTER);
        fillDetailsButton.setVerticalAlignment(JButton.CENTER);
        fillDetailsButton.addActionListener(this);
        fillDetailsButton.setFocusable(false);
        fillDetailsButton.setFont(new Font("Futura", Font.PLAIN, 20));
        fillDetailsButton.setForeground(new Color(2, 5, 98));

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(fillDetailsButton);

        JLabel practiceDuration = new JLabel("You practiced for " + currentFinalElapsedMinutes + " minutes!");
        practiceDuration.setForeground(Color.WHITE);
        practiceDuration.setFont(new Font("Futura", Font.BOLD, 30));
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setVerticalAlignment(JLabel.CENTER);

        panelLabel = new JPanel();
        panelLabel.setBackground(new Color(0, 0, 0, 0));
        panelLabel.add(practiceDuration);

        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForTitle);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(panelLabel);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);

        frame.add(wholePagePanel);
    }

    public void fillInPracticeDetails() throws IncorrectFormatException {
        optionPane = new JOptionPane();
        textFieldsPane = new JPanel();
        textFieldsPane.setLayout(new GridLayout(4, 1));
        JTextField practiceTempo = new JTextField();
        JTextField startingBar = new JTextField();
        JTextField endingBar = new JTextField();
        JTextField practiceMastery = new JTextField();
        textFieldsPane.add(new JLabel("Practice tempo:"));
        textFieldsPane.add(practiceTempo);
        textFieldsPane.add(new JLabel("Starting bar:"));
        textFieldsPane.add(startingBar);
        textFieldsPane.add(new JLabel("Ending bar:"));
        textFieldsPane.add(endingBar);
        textFieldsPane.add(new JLabel("Overall song mastery:"));
        textFieldsPane.add(practiceMastery);
        int result = JOptionPane.showConfirmDialog(optionPane, textFieldsPane,
                "Fill in your practice session details", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            int tempoToBeLogged = Integer.parseInt(practiceTempo.getText());
            int startBarToBeLogged = Integer.parseInt(startingBar.getText());
            int endBarToBeLogged = Integer.parseInt(endingBar.getText());
            float masteryToBeLogged = Float.parseFloat(practiceMastery.getText());

            if (tempoToBeLogged <= 0 || startBarToBeLogged <= 0 || endBarToBeLogged <= 0) {
                throw new IncorrectFormatException();
            }
            if (endBarToBeLogged < startBarToBeLogged) {
                throw new IncorrectFormatException();
            }
            if (masteryToBeLogged > 10 || masteryToBeLogged < 0) {
                throw new IncorrectFormatException();
            }
            Session session = new Session(tempoToBeLogged, startBarToBeLogged, endBarToBeLogged, masteryToBeLogged,
                    currentElapsedTime, currentDate, currentStartTime, currentEndTime);
            currentSong.logSessionToSessionList(session);
            currentSong.logSessionToDay(session);
            int result2 = JOptionPane.showOptionDialog(null, "Current practice session has been logged!", "Message",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (result2 == JOptionPane.OK_OPTION) {
                frame.setVisible(false);
                displaySongDetails();
            }

        }
    }

    public void displayPracticeHistory() {
        frame.setVisible(false);
        frame = new JFrame();
        frame.setSize(840, 840);
        frame.setTitle("Conmoto Music Practice App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.getContentPane().setBackground(new Color(2, 5, 98));
        frame.setVisible(true);

        headerLabel = new JLabel();
        headerLabel.setText("Practice history for " + currentSong.getTitle());
        headerLabel.setHorizontalTextPosition(JLabel.CENTER);
        headerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setFont(new Font("Futura", Font.BOLD, 30));
        headerLabel.setIconTextGap(-50);
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setVerticalAlignment(JLabel.TOP);

        panelForTitle = new JPanel();
        panelForTitle.setBackground(new Color(0, 0, 0, 0));
        panelForTitle.add(headerLabel);

        returnToDetailsButton = new JButton();
        returnToDetailsButton.setText("Return to song details page");
        returnToDetailsButton.setSize(100, 100);
        returnToDetailsButton.setHorizontalAlignment(JButton.CENTER);
        returnToDetailsButton.setVerticalAlignment(JButton.CENTER);
        returnToDetailsButton.addActionListener(this);
        returnToDetailsButton.setFocusable(false);
        returnToDetailsButton.setFont(new Font("Futura", Font.PLAIN, 20));
        returnToDetailsButton.setForeground(new Color(2, 5, 98));

        quitButton = new JButton();
        quitButton.setText("Quit app");
        quitButton.setSize(100, 100);
        quitButton.setHorizontalAlignment(JButton.CENTER);
        quitButton.setVerticalAlignment(JButton.CENTER);
        quitButton.addActionListener(this);
        quitButton.setFocusable(false);
        quitButton.setFont(new Font("Futura", Font.PLAIN, 20));
        quitButton.setForeground(new Color(2, 5, 98));

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1, 2));
        buttonsPanel.setBackground(new Color(2, 5, 98));
        buttonsPanel.add(returnToDetailsButton);
        buttonsPanel.add(quitButton);

        // songList = new JPanel();
        // songList.setLayout(new GridLayout(songlibrary.getSongList().size(), 1));
        // songList.setBackground(Color.WHITE);

        wholePagePanel = new JPanel();
        wholePagePanel.setLayout(new BoxLayout(wholePagePanel, BoxLayout.PAGE_AXIS));
        wholePagePanel.setBackground(new Color(0, 0, 0, 0));
        wholePagePanel.add(panelForTitle);
        wholePagePanel.add(Box.createVerticalStrut(40));
        wholePagePanel.add(buttonsPanel);
        wholePagePanel.add(Box.createVerticalStrut(40));

        if (currentSong.getSessions().isEmpty()) {
            mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(songlibrary.getSongList().size(), 1));
            mainPanel.setBackground(Color.WHITE);
            JLabel emptiness = new JLabel();
            emptiness.setText("No practice sessions found.");
            emptiness.setForeground(Color.BLUE);
            emptiness.setFont(new Font("Futura", Font.BOLD, 20));
            mainPanel.add(emptiness);
            wholePagePanel.add(mainPanel);
        } else {
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
                }
                else {
                    mainPanel.setBackground(new Color(139, 232, 203));
                }

                JLabel date = new JLabel();
                date.setText(formattedDate + " " + formattedStartTime + "-" + formattedEndTime);
                date.setForeground(Color.BLUE);
                date.setFont(new Font("Futura", Font.BOLD, 20));
                mainPanel.add(date);

                JLabel tempo = new JLabel();
                tempo.setText("Tempo: " + session.getTempo());
                tempo.setForeground(Color.BLUE);
                tempo.setFont(new Font("Futura", Font.BOLD, 20));
                mainPanel.add(tempo);

                JLabel bars = new JLabel();
                bars.setText("Bars practiced: " + session.getStartBar() + "-" + session.getEndBar());
                bars.setForeground(Color.BLUE);
                bars.setFont(new Font("Futura", Font.BOLD, 20));
                mainPanel.add(bars);

                JLabel mastery = new JLabel();
                mastery.setText("Overall mastery: " + session.getOverallMastery());
                mastery.setForeground(Color.BLUE);
                mastery.setFont(new Font("Futura", Font.BOLD, 20));
                mainPanel.add(mastery);

                JLabel duration = new JLabel();
                duration.setText("Duration: " + finalDuration + " minutes");
                duration.setForeground(Color.BLUE);
                duration.setFont(new Font("Futura", Font.BOLD, 20));
                mainPanel.add(duration);
                wholePagePanel.add(mainPanel);
            }
        }

        frame.add(wholePagePanel);
    }

    public void monthlyProgressOptionPanel() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonOpening) {
            frame.setVisible(false);
            displaySongList();
        } else if (e.getSource() == addSongButton) {
            try {
                addSong();
            } catch (NumberFormatException nfe) {
                JOptionPane.showOptionDialog(null, "Number of bars and tempo must be an integer!", "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            }
        } else if (e.getSource() == removeSongButton) {
            removeSong();
        } else if (e.getSource() == filterComposerButton) {
            filterComposer();
        } else if (e.getSource() == filterInstrumentButton) {
            filterInstrument();
        } else if (e.getSource() == returnToMainButton) {
            displaySongList();
        } else if (e.getSource() == detailsButton) {
            songDetailsOptionPane();
        } else if (e.getSource() == returnToDetailsButton) {
            displaySongDetails();
        }else if (e.getSource() == monthlyProgressButton) {
            monthlyProgressOptionPanel();
        } else if (e.getSource() == practiceHistoryButton) {
            displayPracticeHistory();
        } else if (e.getSource() == practiceButton) {
            practiceOptionPane();
        } else if (e.getSource() == endPracticeButton) {
            endPracticeOptionPane();
        } else if (e.getSource() == fillDetailsButton) {
            try {
                fillInPracticeDetails();
            } catch (IncorrectFormatException | NumberFormatException e1) {
                JOptionPane.showOptionDialog(null,
                        "Tempo and bar numbers must be integers, overall song mastery must be a float between 0 and 10 inclusive, and ending bar number must be greater than or equal to starting bar number!",
                        "Warning",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
            }
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

}
